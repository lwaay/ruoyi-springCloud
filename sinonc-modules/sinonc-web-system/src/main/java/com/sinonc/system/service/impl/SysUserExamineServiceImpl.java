package com.sinonc.system.service.impl;

import java.util.List;

import com.sinonc.common.core.constant.Constants;
import com.sinonc.common.core.constant.UserConstants;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.system.api.domain.SysUser;
import com.sinonc.system.mapper.SysUserMapper;
import com.sinonc.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.system.mapper.SysUserExamineMapper;
import com.sinonc.system.domain.SysUserExamine;
import com.sinonc.system.service.ISysUserExamineService;

/**
 * 用户注册审核Service业务层处理
 *
 * @author ruoyi
 * @date 2021-10-30
 */
@Service
public class SysUserExamineServiceImpl implements ISysUserExamineService {
    @Autowired
    private SysUserExamineMapper sysUserExamineMapper;
    @Autowired
    private ISysUserService sysUserService;

    /**
     * 查询用户注册审核
     *
     * @param examineId 用户注册审核ID
     * @return 用户注册审核
     */
    @Override
    public SysUserExamine selectSysUserExamineById(Long examineId) {
        return sysUserExamineMapper.selectSysUserExamineById(examineId);
    }

    /**
     * 查询用户注册审核列表
     *
     * @param sysUserExamine 用户注册审核
     * @return 用户注册审核
     */
    @Override
    public List<SysUserExamine> selectSysUserExamineList(SysUserExamine sysUserExamine) {
        return sysUserExamineMapper.selectSysUserExamineList(sysUserExamine);
    }

    /**
     * 新增用户注册审核
     *
     * @param sysUserExamine 用户注册审核
     * @return 结果
     */
    @Override
    public int insertSysUserExamine(SysUserExamine sysUserExamine) {
        sysUserExamine.setCreateTime(DateUtils.getNowDate());
        sysUserExamine.setPassword(SecurityUtils.encryptPassword(sysUserExamine.getPassword()));
        return sysUserExamineMapper.insertSysUserExamine(sysUserExamine);
    }

    /**
     * 修改用户注册审核
     *
     * @param sysUserExamine 用户注册审核
     * @return 结果
     */
    @Override
    public int updateSysUserExamine(SysUserExamine sysUserExamine) {
        sysUserExamine.setUpdateTime(DateUtils.getNowDate());
        return sysUserExamineMapper.updateSysUserExamine(sysUserExamine);
    }

    /**
     * 批量删除用户注册审核
     *
     * @param examineIds 需要删除的用户注册审核ID
     * @return 结果
     */
    @Override
    public int deleteSysUserExamineByIds(Long[] examineIds) {
        return sysUserExamineMapper.deleteSysUserExamineByIds(examineIds);
    }

    /**
     * 删除用户注册审核信息
     *
     * @param examineId 用户注册审核ID
     * @return 结果
     */
    @Override
    public int deleteSysUserExamineById(Long examineId) {
        return sysUserExamineMapper.deleteSysUserExamineById(examineId);
    }

    /**
     * 根据电话查询用户
     *
     * @param phone 用户电话
     * @return 用户注册审核
     */
    @Override
    public SysUserExamine selectSysUserExamineByPhone(String phone){
        return sysUserExamineMapper.selectSysUserExamineByPhone(phone);
    }

    /**
     * 审核用户
     *
     * @param sysUserExamine 用户注册审核
     * @return 结果
     */
    @Override
    public int examine(SysUserExamine sysUserExamine){
        sysUserExamine.setExamineBy(SecurityUtils.getUsername());
        sysUserExamine.setExamineTime(DateUtils.getNowDate());
        int rows = sysUserExamineMapper.updateSysUserExamine(sysUserExamine);
        if(rows>0){
            sysUserExamine =sysUserExamineMapper.selectSysUserExamineById(sysUserExamine.getExamineId());
//            添加用户
            SysUser sysUser = new SysUser();
            sysUser.setUserName(sysUserExamine.getUserName());
            sysUser.setNickName(sysUserExamine.getUserName());
            sysUser.setPassword(sysUserExamine.getPassword());
            sysUser.setPhonenumber(sysUserExamine.getPhonenumber());
            sysUser.setDeptId(100L);
//            赋予岗位，权限
            Long[] postIds = {4L};
            sysUser.setPostIds(postIds);
            Long[] roleIds = {2L};
            sysUser.setRoleIds(roleIds);
            sysUserService.insertUser(sysUser);
        }
        return rows;
    }
}
