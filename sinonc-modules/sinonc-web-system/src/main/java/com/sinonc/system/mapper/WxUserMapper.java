package com.sinonc.system.mapper;

import java.util.List;

import com.sinonc.system.api.domain.WxUser;

/**
 * 认养用户基础Mapper接口
 *
 * @author ruoyi
 * @date 2022-02-12
 */
public interface WxUserMapper {
    /**
     * 查询认养用户基础
     *
     * @param id 认养用户基础ID
     * @return 认养用户基础
     */
    public WxUser selectWxUserById(Long id);

    /**
     * 查询认养用户基础
     *
     * @param id 认养用户基础ID
     * @return 认养用户基础
     */
    public List<WxUser> selectWxUserByIds(Long[] id);

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
     * 删除认养用户基础
     *
     * @param id 认养用户基础ID
     * @return 结果
     */
    public int deleteWxUserById(Long id);

    /**
     * 批量删除认养用户基础
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWxUserByIds(Long[] ids);

    /**
     * 根据openid查询用户
     * @param openId
     * @return
     */
    WxUser selectWxUserByOpenId(String openId);

    /**
     * 根据unionId查询用户
     * @param unionId
     * @return
     */
    WxUser selectWxUserByUnionId(String unionId);

    /**
     * 电话
     * @param phone
     * @return
     */
    WxUser selectWxUserByPhone(String phone);

    /**
     * 账号密码登录
     * @param username 用户
     * @param password 密码
     * @return
     */
    WxUser selectWxUserByNameAndWord(String username, String password);

    /**
     * 账号密码登录
     * @param phone 用户
     * @param password 密码
     * @return
     */
    WxUser selectWxUserByPhoneAndWord(String phone, String password);


}
