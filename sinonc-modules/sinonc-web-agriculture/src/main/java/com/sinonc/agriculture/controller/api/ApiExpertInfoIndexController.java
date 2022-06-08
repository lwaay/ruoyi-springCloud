package com.sinonc.agriculture.controller.api;

import com.sinonc.agriculture.constants.ConcernInfoConstants;
import com.sinonc.agriculture.constants.MemberDictConstants;
import com.sinonc.agriculture.domain.ConcernInfo;
import com.sinonc.agriculture.domain.ExpertInfo;
import com.sinonc.agriculture.service.ConcernInfoService;
import com.sinonc.agriculture.service.ExpertInfoService;
import com.sinonc.agriculture.service.MemberDictService;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.security.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhangxinlong
 * @apiNote TODO
 * @date 2022-05-21  17:28
 */
@Api(tags = "农业问题接口")
@RestController
@RequestMapping("/api/expertinfoindex")
@Slf4j
public class ApiExpertInfoIndexController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ConcernInfoService concernInfoService;

    @Autowired
    private MemberDictService memberDictService;

    @Autowired
    private ExpertInfoService expertInfoService;

    @ApiOperation("根据会员ID查询首页专家列表")
    @GetMapping("listExpertByMemberId")
    public AjaxResult listExpertByMemberId() {
        Long memberId = tokenService.getLoginUser().getUserid();
        List<ExpertInfo> list = new ArrayList<>();

        //关注的专家
        ConcernInfo paraConcernInfo = new ConcernInfo();
        paraConcernInfo.setMemberId(memberId);
        paraConcernInfo.setTargetType(ConcernInfoConstants.CONCERN_INFO_EXPERT);
        List<ConcernInfo> listConcernInfo = concernInfoService.selectConcernInfoList(paraConcernInfo);
        log.info("listConcernInfo:" + listConcernInfo.size());
        StringBuffer expertInfoIdString = new StringBuffer();
        for (int i = 0; i < listConcernInfo.size(); i++) {
            ConcernInfo concernInfo = listConcernInfo.get(i);
            Long expertInfoId = concernInfo.getTargetId();
            ExpertInfo tempExpertInfo = expertInfoService.selectExpertInfoById(expertInfoId);
            if (tempExpertInfo == null) {
                log.info("tempExpertInfo:is null");
                continue;
            }
            expertInfoIdString.append(expertInfoId);
            list.add(tempExpertInfo);
            tempExpertInfo.setChecked(true);
            Map rasMap = memberDictService.selectMemberDictGroupByMemberIdSingle(tempExpertInfo.getMemberId());
            if (rasMap == null) {
                log.info("rasMap:is null");
                continue;
            }
            String ras = (String) rasMap.get("revrate");
            tempExpertInfo.setRevrate(Double.parseDouble(ras));
        }

        //按排名
        List<Map> expertInfoMemberIdList = memberDictService.selectMemberDictGroupByMemberId();
        log.info("expertInfoMemberIdList:" + expertInfoMemberIdList.size());
        for (int i = 0; i < expertInfoMemberIdList.size(); i++) {
            Map map = expertInfoMemberIdList.get(i);
            Long tempMemberId = (Long) map.get("memberId");
            ExpertInfo expertInfo = expertInfoService.selectExpertInfoByUserId(tempMemberId);
            if (expertInfo == null) {
                log.info("expertInfo: is null");
                continue;
            }
            if (StringUtils.isNotEmpty(expertInfoIdString)) {
                if (expertInfoIdString.indexOf(String.valueOf(expertInfo.getExpertId())) != -1) {
                    log.info("expertInfo: is null");
                    continue;
                }
            }

            list.add(expertInfo);
            Map rasMap = memberDictService.selectMemberDictGroupByMemberIdSingle(expertInfo.getMemberId());
            if (rasMap == null) {
                log.info("rasMap: is null");
                continue;
            }
            String ras = (String) rasMap.get("revrate");
            expertInfo.setRevrate(Double.parseDouble(ras));
        }

        return AjaxResult.success(list);
    }

}
