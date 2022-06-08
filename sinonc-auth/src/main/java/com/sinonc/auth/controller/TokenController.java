package com.sinonc.auth.controller;

import com.sinonc.auth.form.LoginBody;
import com.sinonc.auth.service.SysLoginService;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.exception.BaseException;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.security.service.TokenService;
import com.sinonc.system.api.domain.SysUser;
import com.sinonc.system.api.model.LoginUser;
import com.sinonc.system.api.vo.WxRegisterVo;
import com.sinonc.system.api.vo.WxUserInfoVo;
import com.sinonc.system.api.vo.WxUserVo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

/**
 * token 控制
 *
 * @author ruoyi
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TokenController {
    @Autowired
    private TokenService tokenService;

    private static final Logger log = LoggerFactory.getLogger(TokenController.class);

    @Autowired
    private SysLoginService sysLoginService;

    @GetMapping("ipWhite")
    public R<?> login() {
        // 用户登录
        Boolean b = sysLoginService.isIpWhite();
        return R.ok(b);
    }

    @PostMapping("/consumerWxLogin")
    @ApiOperation(value = "消费版小程序微信登录注册", notes = "消费版小程序微信登录注册")
    public R<?> consumerWxLogin(@RequestBody WxRegisterVo wxRegisterVo) {
        // 用户登录
        LoginUser userInfo = sysLoginService.consumerWxLogin(wxRegisterVo);
        if(!Optional.ofNullable(userInfo).isPresent()){
            throw new BaseException("微信注册用户失败");
        }
        // 获取登录token
        return R.ok(tokenService.createWxUserConsumeToken(userInfo));

    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @PostMapping("/consumerWxRegister")
    @ApiOperation(value = "消费版app微信登录注册", notes = "消费版app微信登录注册")
    public R<?> wxConsumerRegister(@Validated @RequestBody WxUserInfoVo wxUserInfoVo) {
        // app用户注册
        LoginUser userInfo = sysLoginService.wxConsumerRegister(wxUserInfoVo);
        if(!Optional.ofNullable(userInfo).isPresent()){
            throw new BaseException("获取微信用户失败");
        }
        // 获取登录token
        return R.ok(tokenService.createWxUserConsumeToken(userInfo));
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping("/consumerPhoneLogin")
    @ApiOperation(value = "消费版app电话验证码登录", notes = "消费版app电话验证码登录")
    public R<?> wxConsumerRegister(String phone, String code) {
        // 用户登录
        LoginUser userInfo = sysLoginService.consumePhoneLogin(phone, code);
        if(!Optional.ofNullable(userInfo).isPresent()){
            throw new BaseException("获取微信用户失败");
        }
        // 获取登录token
        return R.ok(tokenService.createWxUserConsumeToken(userInfo));
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping("/consumerUserLogin")
    @ApiOperation(value = "消费版app密码登录", notes = "消费版app密码登录")
    public R<?> consumerUserLogin(String phone, String password) {
        // 用户登录
        LoginUser userInfo = sysLoginService.consumeUserLogin(phone, password);
        if(!Optional.ofNullable(userInfo).isPresent()){
            throw new BaseException("获取微信用户失败");
        }
        // 获取登录token
        return R.ok(tokenService.createWxUserConsumeToken(userInfo));
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @PostMapping("/consumerForget")
    @ApiOperation(value = "消费版app忘记密码", notes = "消费版app忘记密码")
    public R<?> consumerForget(@Validated @RequestBody WxUserVo wxUserVo) {
        // 获取登录token
        return R.ok(sysLoginService.consumerForget(wxUserVo));
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @PostMapping("/consumerPhoneRegister")
    @ApiOperation(value = "消费版app手机验证码注册", notes = "消费版app手机验证码注册")
    public R<?> consumerPhoneRegister(@Validated @RequestBody WxUserVo wxUserVo) {
        // 用户登录
        LoginUser userInfo = sysLoginService.consumerPhoneRegister(wxUserVo);
        if(!Optional.ofNullable(userInfo).isPresent()){
            throw new BaseException("获取微信用户失败");
        }
        // 获取登录token
        return R.ok(tokenService.createWxUserConsumeToken(userInfo));
    }

    @PostMapping("/wxAppRegister")
    @ApiOperation(value = "app微信登录注册", notes = "app微信登录注册")
    public R<?> wxRegister(@Validated @RequestBody WxUserInfoVo wxUserInfoVo) {
        // app用户注册
        LoginUser userInfo = sysLoginService.wxRegister(wxUserInfoVo);
        if(!Optional.ofNullable(userInfo).isPresent()){
            throw new BaseException("获取微信用户失败");
        }
        // 获取登录token
        return R.ok(tokenService.createWxUserToken(userInfo));
    }

    @PostMapping("/wxLogin")
    public R<?> wxLogin(@RequestBody WxRegisterVo wxRegisterVo) {
        // 用户登录
        LoginUser userInfo = sysLoginService.wxLogin(wxRegisterVo);
        if(!Optional.ofNullable(userInfo).isPresent()){
            throw new BaseException("微信注册用户失败");
        }
        // 获取登录token
        return R.ok(tokenService.createWxUserToken(userInfo));

    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @PostMapping("/wxUserForget")
    @ApiOperation(value = "服务版app忘记密码", notes = "服务版app忘记密码")
    public R<?> wxUserForget(@Validated @RequestBody WxUserVo wxUserVo) {
        // 获取登录token
        return R.ok(sysLoginService.wxUserForget(wxUserVo));
    }

    @GetMapping("/phoneLogin")
    public R<?> phoneLogin(String phone, String code) {
        // 用户登录
        LoginUser userInfo = sysLoginService.phoneLogin(phone, code);
        if(!Optional.ofNullable(userInfo).isPresent()){
            throw new BaseException("获取微信用户失败");
        }
        // 获取登录token
        return R.ok(tokenService.createWxUserToken(userInfo));

    }

    @PostMapping("/registerUser")
    public R<?> registerUser(@RequestBody @Validated WxUserVo wxUserVo) {
        // 手机用户注册
        LoginUser userInfo = sysLoginService.registerUser(wxUserVo);
        if(!Optional.ofNullable(userInfo).isPresent()){
            throw new BaseException("用户注册失败");
        }
        // 获取登录token
        return R.ok(tokenService.createWxUserToken(userInfo));

    }

    @PostMapping("/wxUserLogin")
    public R<?> loginWxUser(@RequestBody WxUserVo wxUserVo) {
        // 用户登录
        LoginUser userInfo = sysLoginService.loginWxUser(wxUserVo);
        if(!Optional.ofNullable(userInfo).isPresent()){
            throw new BaseException("获取微信用户失败");
        }
        // 获取登录token
        return R.ok(tokenService.createWxUserToken(userInfo));

    }

    @PostMapping("login")
    public R<?> login(@RequestBody LoginBody form) {
        try {
            log.error("login***********************************************************");
            // 用户登录
            LoginUser userInfo = sysLoginService.login(form.getUsername(), form.getPassword());
            // 获取登录token
            return R.ok(tokenService.createToken(userInfo));
        } catch (BaseException baseException) {
            sysLoginService.failLogin(form.getUsername());
            throw baseException;
        }
    }

    @PostMapping("/api/login")
    public R<?> apiLogin(@RequestBody LoginBody form) {
        try {
            // 用户登录
            LoginUser userInfo = sysLoginService.login(form.getUsername(), form.getPassword());
            // 获取登录token
            Map<String,Object> res = tokenService.createToken(userInfo);
            SysUser user = userInfo.getSysUser();
            if (Optional.ofNullable(user).isPresent()){
                user.setPassword(null);
                user.setSalt(null);
            }
            res.put("user",user);
            return R.ok(res);
        } catch (BaseException baseException) {
            sysLoginService.failLogin(form.getUsername());
            throw baseException;

        }
    }

    @DeleteMapping("logout")
    public R<?> logout(HttpServletRequest request) {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser)) {
            String username = loginUser.getUsername();
            // 删除用户缓存记录
            tokenService.delLoginUser(loginUser.getToken());
            // 记录用户退出日志
            sysLoginService.logout(username);
        }
        return R.ok();
    }

    @PostMapping("refresh")
    public R<?> refresh(HttpServletRequest request) {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser)) {
            // 刷新令牌有效期
            return R.ok(tokenService.refreshToken(loginUser));
        }
        return R.ok();
    }


    /**
     * 根据用户名，密码获取加密密码
     */
    @PostMapping("getEncryptPassword")
    public R<?> getEncryptPassword(@RequestBody SysUser user) {
        if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())) {
            return R.fail();
        }
        SysUser sysUser = sysLoginService.getEncryptPassword(user);
        return R.ok(sysUser);
    }
}
