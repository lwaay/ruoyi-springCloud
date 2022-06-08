package com.sinonc.system.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSONObject;
import com.sinonc.common.core.constant.UserConstants;
import com.sinonc.common.core.exception.BaseException;
import com.sinonc.common.core.exception.BusinessException;
import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.redis.service.RedisService;
import com.sinonc.system.api.vo.WxRegisterVo;
import com.sinonc.system.api.vo.WxUserInfoVo;
import com.sinonc.common.core.utils.WechatUtil;
import com.sinonc.system.api.vo.WxUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.system.mapper.WxUserMapper;
import com.sinonc.system.api.domain.WxUser;
import com.sinonc.system.service.IWxUserService;

/**
 * 认养用户基础Service业务层处理
 *
 * @author ruoyi
 * @date 2022-02-12
 */
@Service
@Slf4j
public class WxUserServiceImpl implements IWxUserService {

    private static final String headImg = "http://sinonc-develop-test.oss-cn-hangzhou.aliyuncs.com/69ede2ae-7475-40e9-81b7-0f887c7f5d43.png";
    @Autowired
    private WxUserMapper wxUserMapper;

    @Autowired
    private RedisService redisService;

    /**
     * 查询认养用户基础
     *
     * @param phone 认养用户电话
     * @return 认养用户基础
     */
    @Override
    public WxUser selectWxUserByPhone(String phone){
        return wxUserMapper.selectWxUserByPhone(phone);
    }

    /**
     * 查询认养用户基础
     *
     * @param id 认养用户基础ID
     * @return 认养用户基础
     */
    @Override
    public WxUser selectWxUserById(Long id) {
        WxUser wxUser = wxUserMapper.selectWxUserById(id);
        if(StringUtils.isEmpty(wxUser.getHeadimg())){
            wxUser.setHeadimg(headImg);
        }
        return wxUser;
    }

    /**
     * 查询认养用户基础列表
     *
     * @param wxUser 认养用户基础
     * @return 认养用户基础
     */
    @Override
    public List<WxUser> selectWxUserList(WxUser wxUser) {
        return wxUserMapper.selectWxUserList(wxUser);
    }

    /**
     * 新增认养用户基础
     *
     * @param wxUser 认养用户基础
     * @return 结果
     */
    @Override
    public int insertWxUser(WxUser wxUser) {
        wxUser.setCreateTime(DateUtils.getNowDate());
        int rows = wxUserMapper.insertWxUser(wxUser);
        if (rows > 0){
            redisService.setCacheObject(UserConstants.WX_USER+wxUser.getId(),wxUser.getPassword(),UserConstants.OUT_TIME, TimeUnit.DAYS);
        }
        return rows;
    }

    /**
     * 修改认养用户基础
     *
     * @param wxUser 认养用户基础
     * @return 结果
     */
    @Override
    public int updateWxUser(WxUser wxUser) {
        wxUser.setUpdateTime(DateUtils.getNowDate());
        return wxUserMapper.updateWxUser(wxUser);
    }

    /**
     * 批量删除认养用户基础
     *
     * @param ids 需要删除的认养用户基础ID
     * @return 结果
     */
    @Override
    public int deleteWxUserByIds(Long[] ids) {
        return wxUserMapper.deleteWxUserByIds(ids);
    }

    /**
     * 删除认养用户基础信息
     *
     * @param id 认养用户基础ID
     * @return 结果
     */
    @Override
    public int deleteWxUserById(Long id) {
        return wxUserMapper.deleteWxUserById(id);
    }

    /**
     * qpp微信登录
     * @param wxUserInfoVo
     * @return 用户
     */
    @Override
    public WxUser wxAppRegister(WxUserInfoVo wxUserInfoVo){
        WxUser wxUser = wxUserMapper.selectWxUserByUnionId(wxUserInfoVo.getUnionId());
        if(!Optional.ofNullable(wxUser).isPresent()){
            wxUser = new WxUser();
            wxUser.setName(wxUserInfoVo.getNickName());
            wxUser.setWxname(wxUserInfoVo.getNickName());
            wxUser.setHeadimg(wxUserInfoVo.getAvatarUrl());
            wxUser.setSex(Long.valueOf(wxUserInfoVo.getGender()));
            wxUser.setUnionid(wxUserInfoVo.getUnionId());
            wxUser.setOpenid(wxUserInfoVo.getOpenId());
            wxUser.setType("APP");
            wxUserMapper.insertWxUser(wxUser);
        }
        return wxUser;
    }

    @Override
    public WxUser wxRegister(WxRegisterVo wxRegisterVo) {
        JSONObject jsonObject = WechatUtil.getSessionKeyOrOpenid(wxRegisterVo.getCode());
        Integer resultCode = jsonObject.getInteger("errcode");
        if (resultCode != null && resultCode != 0) {
            throw new BusinessException("获取微信openId异常，wxRegisterDto: {}" + jsonObject.toJSONString());
        }
        String openid = jsonObject.getString("openid");

        //获取唯一标识(只有绑定微信开发平台才有)
        String unionId = jsonObject.getString("unionid");

        WxUser user;
        if (StringUtils.isEmpty(unionId)){
            user = wxUserMapper.selectWxUserByUnionId(unionId);
        }else {
            user = wxUserMapper.selectWxUserByOpenId(openid);
        }

        if (user == null) {
            // 注册
            String sessionKey = jsonObject.getString("session_key");
            String result = WechatUtil.wxDecrypt(wxRegisterVo.getEncryptedData(), sessionKey, wxRegisterVo.getIv());
            JSONObject json = JSONObject.parseObject(result);
            String phone = json.getString("purePhoneNumber");
            WxUser userByPhone = wxUserMapper.selectWxUserByPhone(phone);
            try {
                if (userByPhone == null) {
                    user = new WxUser("用户" + phone, phone, "用户" + phone, "", "888888", openid, null, unionId);
                    user.setType("H5");
                    this.insertWxUser(user);
                } else {
                    userByPhone.setOpenid(openid);
                    userByPhone.setUnionid(unionId);
                    wxUserMapper.updateWxUser(userByPhone);
                    user = userByPhone;
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            } finally {
            }

        }
        return user;
    }

    /**
     * 手机，验证码注册
     *
     * @param wxUserVo 校验参数
     * @return 用户
     */
    @Override
    public WxUser registerUser(WxUserVo wxUserVo){
        log.info("用户注册信息：{}", wxUserVo.getPhone());
        WxUser wxUser = wxUserMapper.selectWxUserByPhone(wxUserVo.getPhone());
        //如果用户不为空，返回用户
        if(Optional.ofNullable(wxUser).isPresent()){
            throw new BaseException("该手机号已注册");
        }

        //添加用户
        wxUser = new WxUser();
        wxUser.setPhone(wxUserVo.getPhone());
        wxUser.setPassword(wxUserVo.getPassword());
        wxUser.setName("用户" + wxUserVo.getPhone());
        wxUser.setWxname("用户" + wxUserVo.getPhone());
        this.insertWxUser(wxUser);

        return wxUser;
    }

    /**
     * 手机验证码登录
     * @param phone
     * @return
     */
    @Override
    public WxUser phoneLogin(String phone){
        WxUser wxUser = wxUserMapper.selectWxUserByPhone(phone);
        if(!Optional.ofNullable(wxUser).isPresent()){
            //添加用户
            wxUser = new WxUser();
            wxUser.setPhone(phone);
            wxUser.setName("用户" + phone);
            wxUser.setCreateTime(DateUtils.getNowDate());
            this.insertWxUser(wxUser);
        }

        return wxUser;
    }

    /**
     * 账号密码登录
     * @param username 用户
     * @param password 密码
     * @return
     */
    @Override
    public WxUser login(String username, String password){
        WxUser wxUser = wxUserMapper.selectWxUserByNameAndWord(username, password);
        if(!Optional.ofNullable(wxUser).isPresent()){
            wxUser = wxUserMapper.selectWxUserByPhoneAndWord(username,password);
        }
        if(!Optional.ofNullable(wxUser).isPresent()){
            throw new BaseException("账号不存在或密码错误");
        }
        return wxUser;
    }

    @Override
    public List<WxUser> selectWxUserByIds(Long[] ids) {
        return wxUserMapper.selectWxUserByIds(ids);
    }
}
