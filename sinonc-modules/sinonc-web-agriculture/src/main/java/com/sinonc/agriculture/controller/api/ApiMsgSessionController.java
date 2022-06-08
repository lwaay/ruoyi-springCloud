package com.sinonc.agriculture.controller.api;

import com.sinonc.agriculture.domain.MsgSession;
import com.sinonc.agriculture.service.MsgSessionDtoService;
import com.sinonc.agriculture.service.MsgSessionService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.security.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequestMapping("api/agriculture/msgSession")
public class ApiMsgSessionController extends BaseController {

    @Autowired
    private MsgSessionService sessionService;

    @Autowired
    private MsgSessionDtoService sessionDtoService;

    @Autowired
    private TokenService tokenService;


    @ApiOperation("创建私信会话")
    @GetMapping("create/{toMemberId}")
    @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "head")
    public AjaxResult createSession(@PathVariable Long toMemberId) {
        MsgSession msgSession = sessionService.createMsgSession(tokenService.getLoginUser().getUserid(), toMemberId);
        return AjaxResult.success(msgSession);
    }

    @ApiOperation("会话列表")
    @GetMapping("list")
    @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "head")
    public AjaxResult list() {
        return AjaxResult.success(sessionDtoService.getMsgSessionDtoList(tokenService.getLoginUser().getUserid()));
    }

    @ApiOperation("删除会话")
    @GetMapping("delete/{msgSid}")
    @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "head")
    public AjaxResult deleteSession(@PathVariable Long msgSid) {
        return toAjax(sessionService.deleteMsgSession(msgSid));
    }

}
