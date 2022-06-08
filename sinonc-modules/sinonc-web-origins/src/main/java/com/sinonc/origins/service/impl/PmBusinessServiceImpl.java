package com.sinonc.origins.service.impl;

import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.origins.api.domain.PmBusiness;
import com.sinonc.origins.mapper.PmBusinessMapper;
import com.sinonc.origins.service.IPmBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 经营主体Service业务层处理
 *
 * @author ruoyi
 * @date 2020-10-21
 */
@Service
public class PmBusinessServiceImpl implements IPmBusinessService {
    @Autowired
    private PmBusinessMapper pmBusinessMapper;

    @Override
    public long getAreaCount(String baseArea) {
        return pmBusinessMapper.selectAreaCount(baseArea);
    }

    /**
     * 查询经营主体
     *
     * @param busiId 经营主体ID
     * @return 经营主体
     */
    @Override
    public PmBusiness selectPmBusinessById(Long busiId) {
        return pmBusinessMapper.selectPmBusinessById(busiId);
    }

    /**
     * 获取所有经营主体
     */
    @Override
    public List<PmBusiness> getAllPmBusi(){
        return pmBusinessMapper.selectPmBusinessList(new PmBusiness());
    }

    /**
     * 根据部门id查询经营主体
     * @param deptId 经营主体ID
     * @return 经营主体
     */
    @Override
    public PmBusiness selectPmBusinessByDeptId(Long deptId){
        return pmBusinessMapper.selectPmBusinessByDeptId(deptId);
    }

    /**
     * 查询经营主体列表
     *
     * @param pmBusiness 经营主体
     * @return 经营主体
     */
    @Override
    public List<PmBusiness> selectPmBusinessList(PmBusiness pmBusiness) {
        return pmBusinessMapper.selectPmBusinessList(pmBusiness);
    }

    /**
     * 新增经营主体
     *
     * @param pmBusiness 经营主体
     * @return 结果
     */
    @Override
    public int insertPmBusiness(PmBusiness pmBusiness) {
        pmBusiness.setCreateTime(DateUtils.getNowDate());
        pmBusiness.setBusiAuthentication(2L);
        return pmBusinessMapper.insertPmBusiness(pmBusiness);
    }

    /**
     * 修改经营主体
     *
     * @param pmBusiness 经营主体
     * @return 结果
     */
    @Override
    public int updatePmBusiness(PmBusiness pmBusiness) {
        pmBusiness.setUpdateTime(DateUtils.getNowDate());
        pmBusiness.setBusiAuthentication(2L);
        return pmBusinessMapper.updatePmBusiness(pmBusiness);
    }

    /**
     * 批量删除经营主体
     *
     * @param busiIds 需要删除的经营主体ID
     * @return 结果
     */
    @Override
    public int deletePmBusinessByIds(Long[] busiIds) {
        return pmBusinessMapper.deletePmBusinessByIds(busiIds);
    }

    /**
     * 删除经营主体信息
     *
     * @param busiId 经营主体ID
     * @return 结果
     */
    @Override
    public int deletePmBusinessById(Long busiId) {
        return pmBusinessMapper.deletePmBusinessById(busiId);
    }

    /**
     * 修改经营主体审核信息
     */
    @Override
    public int auditPmBusiness(PmBusiness pmBusiness){
        if(pmBusiness == null || pmBusiness.getBusiId() == null || pmBusiness.getBusiId()<1L){
            return 0;
        }
        PmBusiness old = pmBusinessMapper.selectPmBusinessById(pmBusiness.getBusiId());
        if (old == null){
            return 0;
        }
        old.setBusiAuthentication(pmBusiness.getBusiAuthentication());
        return pmBusinessMapper.updatePmBusiness(old);
    }
}
