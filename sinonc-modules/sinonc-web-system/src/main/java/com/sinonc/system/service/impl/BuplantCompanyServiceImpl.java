package com.sinonc.system.service.impl;

import java.util.List;

import com.sinonc.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.system.mapper.BuplantCompanyMapper;
import com.sinonc.system.domain.BuplantCompany;
import com.sinonc.system.service.IBuplantCompanyService;

/**
 * 种植户（生产商）Service业务层处理
 *
 * @author ruoyi
 * @date 2022-02-24
 */
@Service
public class BuplantCompanyServiceImpl implements IBuplantCompanyService {
    @Autowired
    private BuplantCompanyMapper buplantCompanyMapper;

    /**
     * 查询种植户（生产商）
     *
     * @param plantId 种植户（生产商）ID
     * @return 种植户（生产商）
     */
    @Override
    public BuplantCompany selectBuplantCompanyById(Long plantId) {
        return buplantCompanyMapper.selectBuplantCompanyById(plantId);
    }

    /**
     * 查询种植户（生产商）列表
     *
     * @param buplantCompany 种植户（生产商）
     * @return 种植户（生产商）
     */
    @Override
    public List<BuplantCompany> selectBuplantCompanyList(BuplantCompany buplantCompany) {
        return buplantCompanyMapper.selectBuplantCompanyList(buplantCompany);
    }

    /**
     * 新增种植户（生产商）
     *
     * @param buplantCompany 种植户（生产商）
     * @return 结果
     */
    @Override
    public int insertBuplantCompany(BuplantCompany buplantCompany) {
        buplantCompany.setCreateTime(DateUtils.getNowDate());
        return buplantCompanyMapper.insertBuplantCompany(buplantCompany);
    }

    /**
     * 修改种植户（生产商）
     *
     * @param buplantCompany 种植户（生产商）
     * @return 结果
     */
    @Override
    public int updateBuplantCompany(BuplantCompany buplantCompany) {
        buplantCompany.setUpdateTime(DateUtils.getNowDate());
        return buplantCompanyMapper.updateBuplantCompany(buplantCompany);
    }

    /**
     * 批量删除种植户（生产商）
     *
     * @param plantIds 需要删除的种植户（生产商）ID
     * @return 结果
     */
    @Override
    public int deleteBuplantCompanyByIds(Long[] plantIds) {
        return buplantCompanyMapper.deleteBuplantCompanyByIds(plantIds);
    }

    /**
     * 删除种植户（生产商）信息
     *
     * @param plantId 种植户（生产商）ID
     * @return 结果
     */
    @Override
    public int deleteBuplantCompanyById(Long plantId) {
        return buplantCompanyMapper.deleteBuplantCompanyById(plantId);
    }
}
