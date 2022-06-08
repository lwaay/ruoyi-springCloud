package com.sinonc.agriculture.controller.api;

import com.sinonc.agriculture.domain.AnswerOption;
import com.sinonc.agriculture.service.AnswerOptionService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.security.service.TokenService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/agriculture/option")
public class ApiAnswerOptionController extends BaseController {

    @Autowired
    private AnswerOptionService optionService;

    @Autowired
    private TokenService tokenService;

    @ApiOperation("添加选项")
    @PostMapping("add")
    public AjaxResult answerOperate(@RequestBody AnswerOption option, BindingResult result) {

        if (result.hasErrors()) {
            return AjaxResult.error(result.getFieldError().getDefaultMessage());
        }

        Long memberId = tokenService.getLoginUser().getUserid();
        option.setMemberId(memberId);

        try {
            return toAjax(optionService.addOption(option));
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }

    }

}
