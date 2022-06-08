package com.sinonc.agriculture.controller.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinonc.agriculture.constants.NewsTypeConstants;
import com.sinonc.agriculture.constants.SysConstants;
import com.sinonc.agriculture.domain.AccpetScore;
import com.sinonc.agriculture.domain.OdAdvertisement;
import com.sinonc.agriculture.domain.PolicyNews;
import com.sinonc.agriculture.domain.PolicynewsComment;
import com.sinonc.agriculture.dto.PolicyNewsDto;
import com.sinonc.agriculture.service.AccpetScoreService;
import com.sinonc.agriculture.service.OdAdvertisementService;
import com.sinonc.agriculture.service.PolicyNewsService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 专家评分接口
 */
@Api(tags = "专家评分接口")
@RestController
@RequestMapping("api/agriculture")
public class ApiAccpetScoreController extends BaseController {

    @Autowired
    private AccpetScoreService accpetScoreService;


    @ApiOperation("给专家评分")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "expertId", value = "专家ID", paramType = "query"),
            @ApiImplicitParam(name = "memberId", value = "评分会员ID", paramType = "query"),
            @ApiImplicitParam(name = "mannerScore", value = "服务态度评分", paramType = "query"),
            @ApiImplicitParam(name = "speedScore", value = "响应速度评分", paramType = "query"),
            @ApiImplicitParam(name = "abilityScore", value = "技术能力评分", paramType = "query")
    })
    @GetMapping(value = "/toScoreExpert")
    public AjaxResult toScoreExpert(Long expertId, Long memberId, String mannerScore, String speedScore, String abilityScore) {
        AccpetScore accpetScore=new AccpetScore();

        accpetScore.setExpertId(expertId);
        accpetScore.setMemberId(memberId);
        accpetScore.setMannerScore(mannerScore);
        accpetScore.setSpeedScore(speedScore);
        accpetScore.setAbilityScore(abilityScore);

        int rs=accpetScoreService.addAccpetScore(accpetScore);

        if (rs>0){
            AjaxResult success = AjaxResult.success();
            return  success;
        }else {
            return  AjaxResult.error("你今天已经给这位专家评分了，添加专家评分失败。");
        }
    }



}
