package com.sinonc.auth.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.common.utils.MD5Utils;
import com.sinonc.base.api.RemoteWhitelistService;
import com.sinonc.common.core.constant.Constants;
import com.sinonc.common.core.constant.UserConstants;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.enums.UserStatus;
import com.sinonc.common.core.exception.BaseException;
import com.sinonc.common.core.exception.BusinessException;
import com.sinonc.common.core.exception.CustomException;
import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.core.utils.ServletUtils;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.utils.WechatUtil;
import com.sinonc.common.core.utils.ip.IpUtils;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.redis.service.RedisService;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.consume.api.RemoteWxUserConsumeService;
import com.sinonc.consume.api.domain.WxUserConsume;
import com.sinonc.system.api.RemoteLogService;
import com.sinonc.system.api.RemoteUserService;
import com.sinonc.system.api.RemoteWxUserService;
import com.sinonc.system.api.domain.SysUser;
import com.sinonc.system.api.domain.WxUser;
import com.sinonc.system.api.model.LoginUser;
import com.sinonc.system.api.vo.WxRegisterVo;
import com.sinonc.system.api.vo.WxUserInfoVo;
import com.sinonc.system.api.vo.WxUserVo;
import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.text.translate.NumericEntityUnescaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 登录校验方法
 *
 * @author ruoyi
 */
@Slf4j
@Component
public class SysLoginService {
    @Autowired
    private RemoteLogService remoteLogService;

    @Autowired
    private RemoteUserService remoteUserService;

    @Autowired
    private RemoteWhitelistService whitelistService;

    @Autowired
    private RemoteWxUserService wxUserService;

    @Autowired
    private RemoteWxUserConsumeService wxUserConsumeService;

    private static final Integer SUCCESS = 200;

    @Autowired
    private RedisService redisService;

    /**
     * 判断是否白名单ip
     *
     * @return
     */
    public Boolean isIpWhite() {
        String ips = IpUtils.getIpAddr(ServletUtils.getRequest());
        String ip = "";
        if (ips.contains(",")) {
            ip = ips.substring(0, ips.indexOf(","));
        } else {
            ip = ips;
        }
        R<Boolean> result = whitelistService.isWhite(ip);
        if (result.getCode() != Constants.SUCCESS) {
            throw new CustomException("白名单获取失败，请联系管理员", Constants.WHITE_CODE);
        }
        if (!result.getData()) {
            throw new CustomException("您的IP不在白名单内", Constants.WHITE_CODE);
        }
        return result.getData();
    }

    /**
     * 微信登录注册
     *
     * @param wxRegisterVo
     * @return
     */
    public LoginUser consumerWxLogin(WxRegisterVo wxRegisterVo) {
//        String accessToken = WechatUtil.getAccessToken();
//        JSONObject jsonObject = WechatUtil.getPhoneNumber(accessToken, wxRegisterVo.getCode());
//        Integer resultCode = jsonObject.getInteger("errcode");
//        if (resultCode != null && resultCode != 0) {
//            throw new BaseException("获取微信openId异常，wxRegisterDto: {}" + jsonObject.toJSONString());
//        }
//
//        // 注册
//        String phoneInfo = jsonObject.getString("phone_info");
//
//        JSONObject json = JSONObject.parseObject(phoneInfo);
//        String phone = json.getString("purePhoneNumber");
//        WxUserConsume userByPhone = wxUserConsumeService.getUserByPhone(phone).getData();
//        try {
//            if (!Optional.ofNullable(userByPhone).isPresent()) {
//                JSONObject consumeSessionKeyOrOpenid = WechatUtil.getConsumeSessionKeyOrOpenid(wxRegisterVo.getLoginCode());
//                userByPhone = new WxUserConsume("用户" + phone, phone, "用户" + phone, "", "888888", consumeSessionKeyOrOpenid.getString("openid"), null, null);
//                userByPhone.setType("H5");
//                wxUserConsumeService.addConsume(userByPhone);
//            }
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        }
//
//        //id获取不到，重新查
//        WxUserConsume consume = wxUserConsumeService.getUserByPhone(phone).getData();
        WxUserConsume data = wxUserService.consumerWxLogin(wxRegisterVo).getData();
        LoginUser loginUser = new LoginUser();
        log.info("************************");
        log.info(JSON.toJSONString(data));
        loginUser.setWxUserConsume(data);
        return loginUser;
    }


    /**
     * 消费版app微信登录注册
     *
     * @param wxUserInfoVo
     * @return
     */
    public LoginUser wxConsumerRegister(WxUserInfoVo wxUserInfoVo) {
        WxUserConsume wxUserConsume = wxUserConsumeService.getUserByUnionId(wxUserInfoVo.getUnionId()).getData();
        if (!Optional.ofNullable(wxUserConsume).isPresent()) {
            wxUserConsume = new WxUserConsume();
            wxUserConsume.setName(wxUserInfoVo.getNickName());
            wxUserConsume.setWxname(wxUserInfoVo.getNickName());
            wxUserConsume.setHeadimg(wxUserInfoVo.getAvatarUrl());
            wxUserConsume.setSex(Long.valueOf(wxUserInfoVo.getGender()));
            wxUserConsume.setUnionid(wxUserInfoVo.getUnionId());
            wxUserConsume.setOpenid(wxUserInfoVo.getOpenId());
            wxUserConsume.setType("APP");
            //添加用户
            wxUserConsumeService.addConsume(wxUserConsume);
        }
        LoginUser loginUser = new LoginUser();
        loginUser.setWxUserConsume(wxUserConsume);
        return loginUser;
    }

    /**
     * 消费版手机号登录
     *
     * @param phone
     * @param code
     * @return
     */
    public LoginUser consumePhoneLogin(String phone, String code) {
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(code)) {
            throw new BaseException("参数不能为空");
        }
        String value = redisService.getCacheObject(UserConstants.WX_USER_CONSUME + phone);
        if (StringUtils.isEmpty(value)) {
            throw new BaseException("验证码获取错误，请重试");
        }
        if (!value.equals(code)) {
            throw new BaseException("验证码错误");
        }
        //验证完删除
        redisService.deleteObject(UserConstants.WX_USER_CONSUME + phone);
        WxUserConsume wxUserConsume = wxUserConsumeService.getUserByPhone(phone).getData();
        LoginUser loginUser = new LoginUser();
        loginUser.setWxUserConsume(wxUserConsume);
        return loginUser;
    }

    /**
     * 消费版密码登录
     *
     * @param phone
     * @param password
     * @return
     */
    public LoginUser consumeUserLogin(String phone, String password) {
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(password)) {
            throw new BusinessException("参数不能为空");
        }
        WxUserConsume wxUserConsume = wxUserConsumeService.getUserByPhone(phone).getData();
        if (!Optional.ofNullable(wxUserConsume).isPresent()) {
            throw new BusinessException("账户不存在");
        }
        if (!password.equals(wxUserConsume.getPassword())) {
            throw new BusinessException("用户不存在或密码错误");
        }
        LoginUser loginUser = new LoginUser();
        loginUser.setWxUserConsume(wxUserConsume);
        return loginUser;
    }

    /**
     * 消费版忘记密码
     *
     * @return
     */
    public Integer consumerForget(WxUserVo wxUserVo) {
        String value = redisService.getCacheObject(UserConstants.WX_USER_CONSUME + wxUserVo.getPhone());
        if (StringUtils.isEmpty(value)) {
            throw new BaseException("验证码获取错误，请重试");
        }
        if (!value.equals(wxUserVo.getCode())) {
            throw new BaseException("验证码错误");
        }
        //验证完删除
        redisService.deleteObject(UserConstants.WX_USER_CONSUME + wxUserVo.getPhone());
        WxUserConsume wxUserConsume = wxUserConsumeService.getUserByPhone(wxUserVo.getPhone()).getData();

        if (!Optional.ofNullable(wxUserConsume).isPresent()) {
            throw new BaseException("该手机号未注册，请先注册");
        }
        //添加用户
        wxUserConsume.setPassword(wxUserVo.getPassword());

        return wxUserConsumeService.updateConsume(wxUserConsume).getData();
    }

    /**
     * 消费版手机号注册
     *
     * @return
     */
    public LoginUser consumerPhoneRegister(WxUserVo wxUserVo) {
        String value = redisService.getCacheObject(UserConstants.WX_USER_CONSUME + wxUserVo.getPhone());
        if (StringUtils.isEmpty(value)) {
            throw new BaseException("验证码获取错误，请重试");
        }
        if (!value.equals(wxUserVo.getCode())) {
            throw new BaseException("验证码错误");
        }
        //验证完删除
        redisService.deleteObject(UserConstants.WX_USER_CONSUME + wxUserVo.getPhone());

        WxUserConsume wxUserConsume = wxUserConsumeService.getUserByPhone(wxUserVo.getPhone()).getData();

        if (Optional.ofNullable(wxUserConsume).isPresent()) {
            throw new BaseException("该手机号已注册用户");
        }
        //添加用户
        wxUserConsume = new WxUserConsume();
        wxUserConsume.setPhone(wxUserVo.getPhone());
        wxUserConsume.setPassword(wxUserVo.getPassword());
        wxUserConsume.setName("用户" + wxUserVo.getPhone());
        wxUserConsume.setWxname("用户" + wxUserVo.getPhone());
        wxUserConsume.setCreateTime(DateUtils.getNowDate());
        wxUserConsume.setType("APP");
        wxUserConsumeService.addConsume(wxUserConsume);

        LoginUser loginUser = new LoginUser();
        loginUser.setWxUserConsume(wxUserConsume);
        return loginUser;
    }

    /**
     * app微信登录注册
     *
     * @param wxUserInfoVo
     * @return
     */
    public LoginUser wxRegister(WxUserInfoVo wxUserInfoVo) {
        return wxUserService.wxRegister(wxUserInfoVo).getData();
    }

    /**
     * 微信登录注册
     *
     * @param wxRegisterVo
     * @return
     */
    public LoginUser wxLogin(WxRegisterVo wxRegisterVo) {
        return wxUserService.wxLogin(wxRegisterVo).getData();
    }

    /**
     * 服务版版忘记密码
     *
     * @return
     */
    public Integer wxUserForget(WxUserVo wxUserVo) {
        String value = redisService.getCacheObject(UserConstants.WX_USER + wxUserVo.getPhone());
        if (StringUtils.isEmpty(value)) {
            throw new BaseException("验证码获取错误，请重试");
        }
        if (!value.equals(wxUserVo.getCode())) {
            throw new BaseException("验证码错误");
        }
        //验证完删除
        redisService.deleteObject(UserConstants.WX_USER + wxUserVo.getPhone());
        WxUser wxUser = wxUserService.getUserByPhone(wxUserVo.getPhone()).getData();

        if (!Optional.ofNullable(wxUser).isPresent()) {
            throw new BaseException("该手机号未注册，请先注册");
        }
        //添加用户
        wxUser.setPassword(wxUserVo.getPassword());

        AjaxResult result = wxUserService.updateUserBy(wxUser);
        if (!SUCCESS.equals(result.get("code"))) {
            throw new BusinessException("修改微信用户失败");
        }
        return (Integer) result.get("data");
    }

    /**
     * 手机号登录
     *
     * @param phone
     * @param code
     * @return
     */
    public LoginUser phoneLogin(String phone, String code) {
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(code)) {
            throw new BaseException("参数不能为空");
        }
        String value = redisService.getCacheObject(UserConstants.WX_USER + phone);
        if (StringUtils.isEmpty(value)) {
            throw new BaseException("验证码获取错误，请重试");
        }
        if (!value.equals(code)) {
            throw new BaseException("验证码错误");
        }
        //验证完删除
        redisService.deleteObject(UserConstants.WX_USER + phone);
        return wxUserService.phoneLogin(phone).getData();
    }

    /**
     * 手机号注册
     *
     * @param wxUserVo
     * @return
     */
    public LoginUser registerUser(WxUserVo wxUserVo) {
        WxUser wxUser = wxUserService.getUserByPhone(wxUserVo.getPhone()).getData();
        if (Optional.ofNullable(wxUser).isPresent()) {
            throw new BaseException("该手机号已注册");
        }
        String value = redisService.getCacheObject(UserConstants.WX_USER + wxUserVo.getPhone());
        if (StringUtils.isEmpty(value)) {
            throw new BaseException("验证码获取错误，请重试");
        }
        if (!value.equals(wxUserVo.getCode())) {
            throw new BaseException("验证码错误");
        }
        //验证完删除
        redisService.deleteObject(UserConstants.WX_USER + wxUserVo.getPhone());
        R<LoginUser> result = wxUserService.registerUser(wxUserVo);
        return result.getData();
    }

    /**
     * 账号密码登录
     *
     * @param wxUserVo
     * @return
     */
    public LoginUser loginWxUser(WxUserVo wxUserVo) {
        if (StringUtils.isEmpty(wxUserVo.getUsername()) || StringUtils.isEmpty(wxUserVo.getPassword())) {
            throw new BaseException("参数不能为空");
        }
        WxUser wxUser = wxUserService.getUserByPhone(wxUserVo.getUsername()).getData();
        if (!Optional.ofNullable(wxUser).isPresent()) {
            throw new BaseException("用户不存在");
        }
        if (!wxUserVo.getPassword().equals(wxUser.getPassword())) {
            throw new BaseException("密码错误");
        }
        LoginUser loginUser = new LoginUser();
        loginUser.setWxUser(wxUser);
        return loginUser;
    }

    /**
     * 登录
     */
    public LoginUser login(String username, String password) {
        String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        Boolean hasLock = redisService.getCacheObject("LONGIN_FAIL_LOCK:" + username);
        if (hasLock != null && hasLock) {
            throw new BaseException("用户尝试登录次数过多,账户已被锁定。");
        }
        // 用户名或密码为空 错误
        if (StringUtils.isAnyBlank(username, password)) {
            remoteLogService.saveLogininfor(username, Constants.LOGIN_FAIL, "用户/密码必须填写", ip);
            throw new BaseException("用户/密码必须填写");
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            remoteLogService.saveLogininfor(username, Constants.LOGIN_FAIL, "用户密码不在指定范围", ip);
            throw new BaseException("用户密码不在指定范围");
        }
        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            remoteLogService.saveLogininfor(username, Constants.LOGIN_FAIL, "用户名不在指定范围", ip);
            throw new BaseException("用户名不在指定范围");
        }
        // 查询用户信息
        R<LoginUser> userResult = remoteUserService.getUserInfo(username);

        if (R.FAIL == userResult.getCode()) {
            throw new BaseException(userResult.getMsg());
        }

        if (StringUtils.isNull(userResult.getData())) {
            remoteLogService.saveLogininfor(username, Constants.LOGIN_FAIL, "登录用户不存在", ip);
            throw new BaseException("登录用户：" + username + " 不存在");
        }
        LoginUser userInfo = userResult.getData();
        SysUser user = userResult.getData().getSysUser();
        if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            remoteLogService.saveLogininfor(username, Constants.LOGIN_FAIL, "对不起，您的账号已被删除", ip);

            throw new BaseException("对不起，您的账号：" + username + " 已被删除");
        }
        if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            remoteLogService.saveLogininfor(username, Constants.LOGIN_FAIL, "用户已停用，请联系管理员", ip);
            throw new BaseException("对不起，您的账号：" + username + " 已停用");
        }
        if (!SecurityUtils.matchesPassword(password, user.getPassword())) {
            remoteLogService.saveLogininfor(username, Constants.LOGIN_FAIL, "用户密码错误", ip);
            throw new BaseException("用户不存在/密码错误");
        }
        remoteLogService.saveLogininfor(username, Constants.LOGIN_SUCCESS, "登录成功", ip);
        return userInfo;
    }

    public void logout(String loginName) {
        String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        remoteLogService.saveLogininfor(loginName, Constants.LOGOUT, "退出成功", ip);
    }

    public void failLogin(String userName) {
        R<Integer> res = remoteLogService.failLoginCount(userName);
        if (res.getCode() != 200) {
            return;
        }
        Integer count = res.getData();
        //判断大于5次锁定用户
        if (count > 5) {
            redisService.setCacheObject("LONGIN_FAIL_LOCK:" + userName, true, 1L, TimeUnit.HOURS);
        }
    }


    public SysUser getEncryptPassword(SysUser user) {

        R<LoginUser> userResult = remoteUserService.getUserInfo(user.getUserName());

        if (userResult == null) {
            return new SysUser();
        }
        LoginUser userInfo = userResult.getData();
        if (userInfo == null) {
            return new SysUser();
        }

        SysUser sysUser = userInfo.getSysUser();
        if (sysUser == null) {
            return new SysUser();
        }

        if (!SecurityUtils.matchesPassword(user.getPassword(), sysUser.getPassword())) {
            return new SysUser();
        }
        String md5Key = MD5Utils.md5Hex(user.getUserName() + user.getPassword() + System.currentTimeMillis(), "UTF-8");
        redisService.setCacheObject(md5Key, user.getPassword());
        user.setPassword(md5Key);
        return user;
    }

}
