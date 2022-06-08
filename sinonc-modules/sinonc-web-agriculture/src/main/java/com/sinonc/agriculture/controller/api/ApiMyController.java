package com.sinonc.agriculture.controller.api;

import com.sinonc.agriculture.constants.ConcernInfoConstants;
import com.sinonc.agriculture.constants.MemberDictConstants;
import com.sinonc.agriculture.domain.ConcernInfo;
import com.sinonc.agriculture.domain.MemberDict;
import com.sinonc.agriculture.mapper.MemberDictMapper;
import com.sinonc.agriculture.service.ConcernInfoService;
import com.sinonc.agriculture.service.ExpertInfoService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.security.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api
@RestController
@RequestMapping("/api/agriculture/my")
public class ApiMyController extends BaseController {

    @Autowired
    private ConcernInfoService concernInfoService;

    @Autowired
    private ExpertInfoService expertInfoService;

    @Autowired
    private TokenService tokenService;

    @ApiOperation("查询我的相关数据")
    @ApiImplicitParam(name = "token", value = "token", paramType = "head")
    @GetMapping("infoCount")
    public AjaxResult myInfoCount() {
        Long userid = tokenService.getLoginUser().getWxUser().getId();
        Map<String, Object> dataMap = new HashMap<>(3);
        //关注问题数
        List<ConcernInfo> questionList = concernInfoService.getByMemberIdAndType(userid, ConcernInfoConstants.CONCERN_INFO_QUESTION);
        // 关注数
        List<ConcernInfo> concernInfoList = concernInfoService.getConcernInfoByMemberIdByExpertInfo(userid);
        // 粉丝数
        List<Map<String, Object>> maps = concernInfoService.selectConcernInfoByIdforMerber(userid);
        dataMap.put("fansCount", maps.size());
        dataMap.put("careCount", concernInfoList.size());
        dataMap.put("questionCount", questionList.size());
        return AjaxResult.success(dataMap);
    }


}
