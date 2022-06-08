package com.sinonc.openapi.service.impl;

import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.openapi.domain.DataUser;
import com.sinonc.openapi.mapper.DataUserMapper;
import com.sinonc.openapi.service.IDataUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 第三方接口用户Service业务层处理
 *
 * @author hhao
 * @date 2020-12-23
 */
@Service
public class DataUserServiceImpl implements IDataUserService {
    @Autowired
    private DataUserMapper dataUserMapper;

    /**
     * 查询第三方接口用户
     *
     * @param id 第三方接口用户ID
     * @return 第三方接口用户
     */
    @Override
    public DataUser selectDataUserById(Long id) {
        return dataUserMapper.selectDataUserById(id);
    }

    /**
     * 查询第三方接口用户列表
     *
     * @param dataUser 第三方接口用户
     * @return 第三方接口用户
     */
    @Override
    public List<DataUser> selectDataUserList(DataUser dataUser) {
        return dataUserMapper.selectDataUserList(dataUser);
    }

    /**
     * 新增第三方接口用户
     *
     * @param dataUser 第三方接口用户
     * @return 结果
     */
    @Override
    public int insertDataUser(DataUser dataUser) {
        dataUser.setCreateTime(DateUtils.getNowDate());
        return dataUserMapper.insertDataUser(dataUser);
    }

    /**
     * 修改第三方接口用户
     *
     * @param dataUser 第三方接口用户
     * @return 结果
     */
    @Override
    public int updateDataUser(DataUser dataUser) {
        return dataUserMapper.updateDataUser(dataUser);
    }

    /**
     * 批量删除第三方接口用户
     *
     * @param ids 需要删除的第三方接口用户ID
     * @return 结果
     */
    @Override
    public int deleteDataUserByIds(Long[] ids) {
        return dataUserMapper.deleteDataUserByIds(ids);
    }

    /**
     * 删除第三方接口用户信息
     *
     * @param id 第三方接口用户ID
     * @return 结果
     */
    @Override
    public int deleteDataUserById(Long id) {
        return dataUserMapper.deleteDataUserById(id);
    }
}
