package com.sinonc.openapi.service;

import com.sinonc.openapi.domain.DataUser;

import java.util.List;

/**
 * 第三方接口用户Service接口
 *
 * @author hhao
 * @date 2020-12-23
 */
public interface IDataUserService {
    /**
     * 查询第三方接口用户
     *
     * @param id 第三方接口用户ID
     * @return 第三方接口用户
     */
    public DataUser selectDataUserById(Long id);

    /**
     * 查询第三方接口用户列表
     *
     * @param dataUser 第三方接口用户
     * @return 第三方接口用户集合
     */
    public List<DataUser> selectDataUserList(DataUser dataUser);

    /**
     * 新增第三方接口用户
     *
     * @param dataUser 第三方接口用户
     * @return 结果
     */
    public int insertDataUser(DataUser dataUser);

    /**
     * 修改第三方接口用户
     *
     * @param dataUser 第三方接口用户
     * @return 结果
     */
    public int updateDataUser(DataUser dataUser);

    /**
     * 批量删除第三方接口用户
     *
     * @param ids 需要删除的第三方接口用户ID
     * @return 结果
     */
    public int deleteDataUserByIds(Long[] ids);

    /**
     * 删除第三方接口用户信息
     *
     * @param id 第三方接口用户ID
     * @return 结果
     */
    public int deleteDataUserById(Long id);
}
