package com.sinonc.system.service;

import java.util.List;

import com.sinonc.system.api.domain.WxUser;
import com.sinonc.system.api.vo.WxRegisterVo;
import com.sinonc.system.api.vo.WxUserInfoVo;
import com.sinonc.system.api.vo.WxUserVo;

/**
 * 认养用户基础Service接口
 *
 * @author ruoyi
 * @date 2022-02-12
 */
public interface IWxUserService {
    /**
     * 查询认养用户基础
     *
     * @param phone 认养用户电话
     * @return 认养用户基础
     */
    public WxUser selectWxUserByPhone(String phone);

    /**
     * 查询认养用户基础
     *
     * @param id 认养用户基础ID
     * @return 认养用户基础
     */
    public WxUser selectWxUserById(Long id);

    /**
     * 查询认养用户基础列表
     *
     * @param wxUser 认养用户基础
     * @return 认养用户基础集合
     */
    public List<WxUser> selectWxUserList(WxUser wxUser);

    /**
     * 新增认养用户基础
     *
     * @param wxUser 认养用户基础
     * @return 结果
     */
    public int insertWxUser(WxUser wxUser);

    /**
     * 修改认养用户基础
     *
     * @param wxUser 认养用户基础
     * @return 结果
     */
    public int updateWxUser(WxUser wxUser);

    /**
     * 批量删除认养用户基础
     *
     * @param ids 需要删除的认养用户基础ID
     * @return 结果
     */
    public int deleteWxUserByIds(Long[] ids);

    /**
     * 删除认养用户基础信息
     *
     * @param id 认养用户基础ID
     * @return 结果
     */
    public int deleteWxUserById(Long id);

    /**
     * qpp微信登录
     * @param wxUserInfoVo
     * @return 用户
     */
    WxUser wxAppRegister(WxUserInfoVo wxUserInfoVo);

    /**
     * 微信登录
     *
     * @param wxRegisterVo 校验参数
     * @return 用户
     */
    WxUser wxRegister(WxRegisterVo wxRegisterVo);

    /**
     * 手机，验证码登录
     *
     * @param wxUserVo 校验参数
     * @return 用户
     */
    WxUser registerUser(WxUserVo wxUserVo);

    /**
     * 手机验证码登录
     * @param phone
     * @return
     */
    WxUser phoneLogin(String phone);
    /**
     * 账号密码登录
     * @param username 用户
     * @param password 密码
     * @return
     */
    WxUser login(String username, String password);

    List<WxUser> selectWxUserByIds(Long[] ids);
}
