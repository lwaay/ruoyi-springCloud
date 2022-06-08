package com.sinonc.agriculture.controller.api;

import com.github.pagehelper.PageInfo;
import com.sinonc.agriculture.domain.AgriQuestion;
import com.sinonc.agriculture.domain.ExpertInfo;
import com.sinonc.agriculture.domain.QuestionAnswer;
import com.sinonc.agriculture.dto.QuestionAnswerDto;
import com.sinonc.agriculture.service.AgriQuestionService;
import com.sinonc.agriculture.service.ExpertInfoService;
import com.sinonc.agriculture.service.QuestionAnswerDtoService;
import com.sinonc.agriculture.service.QuestionAnswerService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.security.service.TokenService;
import com.sinonc.system.api.model.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "农业问题回答接口")
@RestController
@RequestMapping("api/agriculture/answer")
public class ApiQuestionAnswerController extends BaseController {

    @Autowired
    private QuestionAnswerDtoService answerDtoService;

    @Autowired
    private QuestionAnswerService answerService;

    @Autowired
    private AgriQuestionService questionService;

    @Autowired
    private ExpertInfoService expertInfoService;

    @Autowired
    private TokenService tokenService;

    @GetMapping("/isExpert")
    public AjaxResult ifExpert(){
        LoginUser loginUser = tokenService.getLoginUser();
        if(ObjectUtils.isEmpty(loginUser)){
            return AjaxResult.error("请先登录");
        }
        //判断登录用户是否专家
        ExpertInfo expertInfo = expertInfoService.selectExpertInfoByUserId(loginUser.getUserid());
        if (ObjectUtils.isEmpty(expertInfo)){
            return AjaxResult.success(false);
        }
        return AjaxResult.success(true);
    }

    @PostMapping("add")
    @ApiOperation("添加回答")
    @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "head")
    public AjaxResult addAnswer(@RequestBody  QuestionAnswer answer, BindingResult result) {

        if (result.hasErrors()) {
            return AjaxResult.error(result.getFieldError().getDefaultMessage());
        }
        LoginUser loginUser = tokenService.getLoginUser();
        if(ObjectUtils.isEmpty(loginUser)){
            return AjaxResult.error("请先登录");
        }
        //判断登录用户是否专家
        ExpertInfo expertInfo = expertInfoService.selectExpertInfoByUserId(loginUser.getUserid());
        if (ObjectUtils.isEmpty(expertInfo)){
            return AjaxResult.error("您还不是专家，无法解答问题");
        }
        answer.setNikeName(loginUser.getUsername());
        answer.setMemberId(loginUser.getUserid());



        return toAjax(answerService.addQuestionAnswer(answer));
    }

    @GetMapping("list/{questionId}")
    @ApiOperation("回答列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "pageNum", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", paramType = "query")
    })
    public AjaxResult list(@PathVariable Long questionId) {

        QuestionAnswer questionAnswer = new QuestionAnswer();
        questionAnswer.setQuestionId(questionId);

        startPage();
        List<QuestionAnswerDto> questionAnswerList = answerDtoService.getQuestionAnswerDtoList(questionAnswer, !ObjectUtils.isEmpty(tokenService.getLoginUser()));
        PageInfo<QuestionAnswerDto> pageInfo = new PageInfo<>(questionAnswerList);

        AjaxResult success = AjaxResult.success(pageInfo.getList());
        success.put("isHasNextPage", pageInfo.isHasNextPage());

        return success;
    }

    @PostMapping("setBest/{questionId}/{answerId}")
    @ApiOperation("设置最佳答案")
    @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "head")
    public AjaxResult setBest(@PathVariable("questionId") Long questionId, @PathVariable("answerId") Long answerId) {

        AgriQuestion question = questionService.getAgriQuestionById(questionId);

        if (question == null) {
            return AjaxResult.error("questionId不存在");
        } else if (!question.getMemberId().equals(tokenService.getLoginUser().getUserid())) {
            return AjaxResult.error("非法的用户身份");
        }

        return toAjax(answerService.setBestAnswer(questionId, answerId));
    }


}
