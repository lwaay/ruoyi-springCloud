package com.sinonc.agriculture.controller.api;

import com.github.pagehelper.PageInfo;
import com.sinonc.agriculture.domain.MsgContent;
import com.sinonc.agriculture.service.MsgContentService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.security.service.TokenService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/agriculture/msgContent")
public class ApiMsgContentController extends BaseController {

    @Autowired
    private MsgContentService msgContentService;

    @Autowired
    private TokenService tokenService;

    @ApiOperation("获取消息列表")
    @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "head")
    @GetMapping("list/{sessionId}")
    public AjaxResult getList(@PathVariable String sessionId) {

        startPage();
        List<MsgContent> msgContentList = msgContentService.getBySessionId(sessionId, tokenService.getLoginUser().getUserid());
        PageInfo<MsgContent> pageInfo = new PageInfo<>(msgContentList);

        AjaxResult success = AjaxResult.success(pageInfo.getList());
        success.put("hasNextPage", pageInfo.isHasNextPage());

        return success;
    }


    @ApiOperation("发送信息")
    @PostMapping("sendMsg")
    @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "head")
    public AjaxResult sendMsg(@RequestBody @Valid MsgContent msgContent, BindingResult result) {

        if (result.hasErrors()) {
            return AjaxResult.error(result.getFieldError().getDefaultMessage());
        }

        if (tokenService.getLoginUser().getUserid().equals(msgContent.getToMemberId())) {
            return AjaxResult.error("不允许与自己发送私信");
        }

        msgContent.setFromMemberId(tokenService.getLoginUser().getUserid());
        msgContent.setStatus(0);

        try {
            return toAjax(msgContentService.sendMsg(msgContent));
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

}
