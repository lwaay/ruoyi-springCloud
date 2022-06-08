package com.sinonc.system.controller.api;

import com.sinonc.common.core.constant.UserConstants;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.redis.service.RedisService;
import com.sinonc.common.sms.SmsService;
import com.sinonc.consume.api.domain.WxUserConsume;
import com.sinonc.system.api.vo.WxRegisterVo;
import com.sinonc.system.service.IWxUserConsumeService;
import com.sinonc.system.vo.ReWxUserVo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author: lw
 * @date: 2022/3/28 9:52
 * @description:
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/consume")
@Slf4j
public class ApiWxUserConsumeController extends BaseController {

    @Autowired
    private RedisService redisService;
    @Autowired
    private IWxUserConsumeService consumeService;
    @Autowired
    private SmsService smsService;

    /**
     * 发送验证码
     * @param phone
     * @return
     */
    @GetMapping("/sendCode/{phone}")
    public R<String> sendCode(@PathVariable("phone")String phone){
        if(redisService.hasKey(UserConstants.WX_USER_CONSUME + phone)){
            return R.fail("您已经发送过了，请五分钟之后再试");
        }
        StringBuilder random= new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int max=9,min=0;
            int ran2 = (int) (Math.random()*(max-min)+min);
            random.append(ran2);
        }
        String code = random.toString();
        logger.info("验证码:************" + code);
        redisService.setCacheObject(UserConstants.WX_USER_CONSUME + phone,code,5L, TimeUnit.MINUTES);
        try {
            smsService.sendSmsCode(phone,"您的验证码是" + code + "，5分钟内有效，请勿泄漏！");
            return R.ok("已向手机尾号为" + phone.substring(7,11) + "的用户发送验证码！");
        }catch (Exception e){
            return R.fail(e.getMessage());
        }
    }

    /**
     * 获取消费版用户详细信息
     */
    @GetMapping(value = "getInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(consumeService.selectWxUserConsumeById(id));
    }

    @ApiOperation("修改密码")
    @PostMapping("/updatePassword")
    public AjaxResult updatePassword(@RequestBody ReWxUserVo reWxUserVo) {
        if (StringUtils.isEmpty(reWxUserVo.getPhone()) || StringUtils.isEmpty(reWxUserVo.getOldPassword()) || StringUtils.isEmpty(reWxUserVo.getNewPassword()) ) {
            return AjaxResult.error("参数错误！");
        }
        WxUserConsume wxUserConsume = consumeService.selectWxUserConsumeByPhone(reWxUserVo.getPhone());
        if(!Optional.ofNullable(wxUserConsume).isPresent()){
            return AjaxResult.error("该号码未注册！");
        }
        //判断旧密码是否正确
        if(!reWxUserVo.getOldPassword().equals(wxUserConsume.getPassword())){
            return AjaxResult.error("旧密码错误，请重新输入");
        }
        //判断旧密码是否正确
        if(reWxUserVo.getNewPassword().equals(wxUserConsume.getPassword())){
            return AjaxResult.error("新密码不能与旧密码重复");
        }
        WxUserConsume newUser = new WxUserConsume();
        newUser.setId(wxUserConsume.getId());
        newUser.setPassword(reWxUserVo.getNewPassword());

        return toAjax(consumeService.updateWxUserConsume(newUser));
    }

    @ApiOperation("修改用户密码")
    @PostMapping("/changePassword")
    public AjaxResult changePassword(@RequestBody ReWxUserVo reWxUserVo) {
        if (StringUtils.isEmpty(reWxUserVo.getPhone()) || StringUtils.isEmpty(reWxUserVo.getCode()) || StringUtils.isEmpty(reWxUserVo.getNewPassword()) ) {
            return AjaxResult.error("参数错误！");
        }
        String value = redisService.getCacheObject(UserConstants.WX_USER_CONSUME + reWxUserVo.getPhone());
        if(StringUtils.isEmpty(value)){
            return AjaxResult.error("验证码获取错误，请重试");
        }
        if(!value.equals(reWxUserVo.getCode())){
            return AjaxResult.error("验证码错误");
        }
        //验证完删除
        redisService.deleteObject(UserConstants.WX_USER_CONSUME + reWxUserVo.getPhone());
        WxUserConsume wxUserConsume = consumeService.selectWxUserConsumeByPhone(reWxUserVo.getPhone());
        if(reWxUserVo.getNewPassword().equals(wxUserConsume.getPassword())){
            return AjaxResult.error("新密码不能与旧密码相同");
        }
        wxUserConsume.setPassword(reWxUserVo.getNewPassword());

        return toAjax(consumeService.updateWxUserConsume(wxUserConsume));
    }

    @ApiOperation("修改用户")
    @PostMapping("/updateConsume")
    public AjaxResult updateConsume(@RequestBody WxUserConsume wxUserConsume) {
        return toAjax(consumeService.updateWxUserConsume(wxUserConsume));
    }

    @PostMapping("/consumerWxLogin")
    public R<WxUserConsume> consumerWxLogin(@RequestBody WxRegisterVo wxRegisterVo){
        return R.ok(consumeService.wxRegister(wxRegisterVo));
    }

}
