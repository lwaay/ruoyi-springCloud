package com.sinonc.system.service.impl;

import java.util.List;

import com.sinonc.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.system.mapper.BudistCompanyMapper;
import com.sinonc.system.domain.BudistCompany;
import com.sinonc.system.service.IBudistCompanyService;

/**
 * 经销商Service业务层处理
 *
 * @author ruoyi
 * @date 2022-02-24
 */
@Service
public class BudistCompanyServiceImpl implements IBudistCompanyService {
    @Autowired
    private BudistCompanyMapper budistCompanyMapper;

    /**
     * 查询经销商
     *
     * @param distId 经销商ID
     * @return 经销商
     */
    @Override
    public BudistCompany selectBudistCompanyById(Long distId) {
        return budistCompanyMapper.selectBudistCompanyById(distId);
    }

    /**
     * 查询经销商列表
     *
     * @param budistCompany 经销商
     * @return 经销商
     */
    @Override
    public List<BudistCompany> selectBudistCompanyList(BudistCompany budistCompany) {
        return budistCompanyMapper.selectBudistCompanyList(budistCompany);
    }

    /**
     * 新增经销商
     *
     * @param budistCompany 经销商
     * @return 结果
     */
    @Override
    public int insertBudistCompany(BudistCompany budistCompany) {
        budistCompany.setCreateTime(DateUtils.getNowDate());
        return budistCompanyMapper.insertBudistCompany(budistCompany);
    }

    /**
     * 修改经销商
     *
     * @param budistCompany 经销商
     * @return 结果
     */
    @Override
    public int updateBudistCompany(BudistCompany budistCompany) {
        budistCompany.setUpdateTime(DateUtils.getNowDate());
        return budistCompanyMapper.updateBudistCompany(budistCompany);
    }

    /**
     * 批量删除经销商
     *
     * @param distIds 需要删除的经销商ID
     * @return 结果
     */
    @Override
    public int deleteBudistCompanyByIds(Long[] distIds) {
        return budistCompanyMapper.deleteBudistCompanyByIds(distIds);
    }

    /**
     * 删除经销商信息
     *
     * @param distId 经销商ID
     * @return 结果
     */
    @Override
    public int deleteBudistCompanyById(Long distId) {
        return budistCompanyMapper.deleteBudistCompanyById(distId);
    }
}
