package com.sinonc.system.api;

import com.sinonc.common.core.constant.ServiceNameConstants;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.consume.api.domain.WxUserConsume;
import com.sinonc.system.api.domain.SysUser;
import com.sinonc.system.api.domain.WxUser;
import com.sinonc.system.api.factory.RemoteUserFallbackFactory;
import com.sinonc.system.api.factory.RemoteWxUserFallbackFactory;
import com.sinonc.system.api.model.LoginUser;
import com.sinonc.system.api.vo.WxRegisterVo;
import com.sinonc.system.api.vo.WxUserInfoVo;
import com.sinonc.system.api.vo.WxUserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户服务
 *
 * @author ruoyi
 */
@FeignClient(contextId = "remoteWxUserService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteWxUserFallbackFactory.class)
public interface RemoteWxUserService {

    /**
     * app微信登录注册
     * @param wxUserInfoVo
     */
    @PostMapping(value = "/api/wxUser/wxAppRegister")
    public R<LoginUser> wxRegister(@RequestBody WxUserInfoVo wxUserInfoVo);

    /**
     * H5、小程序登录注册
     *
     * @param wxRegisterVo 微信登录加密信息
     * @return 结果
     */
    @PostMapping(value = "/api/wxUser/wxLogin")
    public R<LoginUser> wxLogin(@RequestBody WxRegisterVo wxRegisterVo);

    /**
     * 根据账号或手机号获取用户信息
     *
     * @param phone 手机号
     * @return 结果
     */
    @GetMapping(value = "/api/wxUser/getUserByPhone/{phone}")
    public R<WxUser> getUserByPhone(@PathVariable("phone") String phone);


    /**
     * 手机号登录
     *
     * @param phone 手机号
     * @return 结果
     */
    @GetMapping(value = "/api/wxUser/phoneLogin/{phone}")
    public R<LoginUser> phoneLogin(@PathVariable("phone") String phone);

    /**
     * 微信注册用户
     *
     * @param wxUserVo 用户信息
     * @return 结果
     */
    @PostMapping(value = "/api/wxUser/registerUser")
    public R<LoginUser> registerUser(@RequestBody WxUserVo wxUserVo);

    /**
     * 账号密码登录
     *
     * @param wxUserVo
     * @return
     */
    @PostMapping(value = "/api/wxUser/wxUserLogin")
    public R<LoginUser> loginWxUser(@RequestBody WxUserVo wxUserVo);


    /**
     * 查询会员信息
     *
     * @param memberId 会员ID
     * @return 结果
     */
    @GetMapping(value = "/wxUser/getWxUserByMemberId/{phone}")
    public R<WxUser> getWxUserByMemberId(@PathVariable("phone") Long memberId);

    /**
     * 查询会员信息
     *
     * @param id 会员ID
     * @return 结果
     */
    @GetMapping(value = "/wxUser/getUserById/{id}")
    public R<WxUser> getUserById(@PathVariable("id") Long id);

    /**
     * 修改会员信息
     *
     * @param wxUser 会员信息
     * @return 结果
     */
    @PostMapping(value = "/wxUser/updateUserBy")
    public AjaxResult updateUserBy(@RequestBody WxUser wxUser);

    @PostMapping(value = "/api/consume/consumerWxLogin")
    public R<WxUserConsume> consumerWxLogin(@RequestBody WxRegisterVo wxRegisterVo);
}
