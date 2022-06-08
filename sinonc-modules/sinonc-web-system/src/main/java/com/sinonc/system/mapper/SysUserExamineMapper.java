package com.sinonc.system.mapper;

import java.util.List;

import com.sinonc.system.domain.SysUserExamine;

/**
 * 用户注册审核Mapper接口
 *
 * @author ruoyi
 * @date 2021-10-30
 */
public interface SysUserExamineMapper {
    /**
     * 查询用户注册审核
     *
     * @param examineId 用户注册审核ID
     * @return 用户注册审核
     */
    public SysUserExamine selectSysUserExamineById(Long examineId);

    /**
     * 查询用户注册审核列表
     *
     * @param sysUserExamine 用户注册审核
     * @return 用户注册审核集合
     */
    public List<SysUserExamine> selectSysUserExamineList(SysUserExamine sysUserExamine);

    /**
     * 新增用户注册审核
     *
     * @param sysUserExamine 用户注册审核
     * @return 结果
     */
    public int insertSysUserExamine(SysUserExamine sysUserExamine);

    /**
     * 修改用户注册审核
     *
     * @param sysUserExamine 用户注册审核
     * @return 结果
     */
    public int updateSysUserExamine(SysUserExamine sysUserExamine);

    /**
     * 删除用户注册审核
     *
     * @param examineId 用户注册审核ID
     * @return 结果
     */
    public int deleteSysUserExamineById(Long examineId);

    /**
     * 批量删除用户注册审核
     *
     * @param examineIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysUserExamineByIds(Long[] examineIds);

    /**
     * 根据电话查询用户
     *
     * @param phone 用户电话
     * @return 用户注册审核
     */
    public SysUserExamine selectSysUserExamineByPhone(String phone);
}
