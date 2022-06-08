package com.sinonc.system.controller.api;


import com.sinonc.common.core.constant.UserConstants;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.redis.service.RedisService;
import com.sinonc.common.sms.SmsService;
import com.sinonc.system.api.domain.WxUser;
import com.sinonc.system.api.model.LoginUser;
import com.sinonc.system.api.vo.WxRegisterVo;
import com.sinonc.system.api.vo.WxUserInfoVo;
import com.sinonc.system.api.vo.WxUserVo;
import com.sinonc.system.service.IWxUserService;
import com.sinonc.system.vo.ReWxUserVo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.concurrent.TimeUnit;


/**
 *
 */
@RestController
@RequestMapping("api/wxUser")
@Slf4j
public class ApiWxUserController extends BaseController {


    @Autowired
    private IWxUserService wxUserService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private SmsService smsService;
    /**
     * 手机，验证码登录
     *
     * @param phone 电话
     * @return
     */
    @GetMapping("/getUserByPhone/{phone}")
    public R<WxUser> getUserByPhone(@PathVariable("phone") String phone) {
        return R.ok(wxUserService.selectWxUserByPhone(phone));
    }

    @GetMapping("getWxUserById/{id}")
    public AjaxResult getWxUserById(@PathVariable("id") Long id) {
        WxUser wxUser = wxUserService.selectWxUserById(id);
        return AjaxResult.success("success", wxUser);
    }


    @PostMapping("modWxUser")
    public AjaxResult modWxUser(@RequestBody WxUser wxUser) {
        int result = wxUserService.updateWxUser(wxUser);
        return AjaxResult.success("success", result);
    }

    @ApiOperation("修改密码")
    @PostMapping("updatePassword")
    public AjaxResult updatePassword(@RequestBody ReWxUserVo reWxUserVo) {
        if (StringUtils.isEmpty(reWxUserVo.getPhone()) || StringUtils.isEmpty(reWxUserVo.getOldPassword()) || StringUtils.isEmpty(reWxUserVo.getNewPassword()) ) {
            return AjaxResult.error("参数错误！");
        }
        WxUser wxUser = wxUserService.selectWxUserByPhone(reWxUserVo.getPhone());
        if(!Optional.ofNullable(wxUser).isPresent()){
            return AjaxResult.error("该号码未注册！");
        }
        //判断旧密码是否正确
        if(!reWxUserVo.getOldPassword().equals(wxUser.getPassword())){
            return AjaxResult.error("旧密码错误，请重新输入");
        }
        WxUser newUser = new WxUser();
        newUser.setId(wxUser.getId());
        newUser.setPassword(reWxUserVo.getNewPassword());

        return toAjax(wxUserService.updateWxUser(newUser));
    }

    /**
     * app微信登录注册
     *
     * @param wxUserInfoVo 请求参数
     * @return
     */
    @PostMapping("/wxAppRegister")
    public R<LoginUser> wxAppRegister(@RequestBody WxUserInfoVo wxUserInfoVo){
        WxUser user = wxUserService.wxAppRegister(wxUserInfoVo);
        log.info("wxUserId*************************:{}", user.getId());
        LoginUser loginUser = new LoginUser();
        loginUser.setWxUser(user);
        return R.ok(loginUser);
    }

    /**
     * 微信登录注册
     *
     * @param wxRegisterVo 请求参数
     * @return
     */
    @PostMapping("/wxLogin")
    public R<LoginUser> wechatLogin(@RequestBody WxRegisterVo wxRegisterVo){
        WxUser user = wxUserService.wxRegister(wxRegisterVo);
        log.info("wxUserId*************************:{}", user.getId());
        LoginUser loginUser = new LoginUser();
        loginUser.setWxUser(user);
        return R.ok(loginUser);
    }

    /**
     * 手机，验证码注册用户
     *
     * @param wxUserVo 电话
     * @return
     */
    @PostMapping("/registerUser")
    public R<LoginUser> registerUser(@RequestBody @Validated WxUserVo wxUserVo){
        WxUser user = wxUserService.registerUser(wxUserVo);
        log.info("wxUserId*************************:{}", user.getId());
        LoginUser loginUser = new LoginUser();
        loginUser.setWxUser(user);
        return R.ok(loginUser);
    }

    /**
     * 手机，验证码登录
     *
     * @param phone 电话
     * @return
     */
    @GetMapping("/phoneLogin/{phone}")
    public R<LoginUser> phoneLogin(@PathVariable("phone") String phone) {
        WxUser user = wxUserService.phoneLogin(phone);
        log.info("wxUserId*************************:{}", user.getId());
        LoginUser loginUser = new LoginUser();
        loginUser.setWxUser(user);
        return R.ok(loginUser);
    }

    /**
     * 账号密码登录
     *
     * @param wxUserVo
     * @return
     */
    @PostMapping("/wxUserLogin")
    public R<LoginUser> loginWxUser(@RequestBody WxUserVo wxUserVo) {
        WxUser user = wxUserService.login(wxUserVo.getUsername(), wxUserVo.getPassword());
        LoginUser loginUser = new LoginUser();
        loginUser.setWxUser(user);
        return R.ok(loginUser);
    }

    /**
     * 发送验证码
     * @param phone
     * @return
     */
    @GetMapping("/sendCode/{phone}")
    public R<String> sendCode(@PathVariable("phone")String phone){
        if(redisService.hasKey(UserConstants.WX_USER + phone)){
            return R.fail("您已经发送过了，请五分钟之后再试");
        }
        StringBuilder random= new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int max=9,min=0;
            int ran2 = (int) (Math.random()*(max-min)+min);
            System.out.println(ran2);
            random.append(ran2);
        }
        String code = random.toString();
        log.info("code:***************************"+code);
        redisService.setCacheObject(UserConstants.WX_USER + phone,code,5L, TimeUnit.MINUTES);
        try {
            smsService.sendSmsCode(phone,"您的验证码是" + code + "，5分钟内有效，请勿泄漏！");
            return R.ok("已向手机尾号为" + phone.substring(7,11) + "的用户发送验证码！");
        }catch (Exception e){
            return R.fail(e.getMessage());
        }
    }




}
