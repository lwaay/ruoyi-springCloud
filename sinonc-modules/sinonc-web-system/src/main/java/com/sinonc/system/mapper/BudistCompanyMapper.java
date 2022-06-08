package com.sinonc.system.mapper;

import java.util.List;

import com.sinonc.system.domain.BudistCompany;

/**
 * 经销商Mapper接口
 *
 * @author ruoyi
 * @date 2022-02-24
 */
public interface BudistCompanyMapper {
    /**
     * 查询经销商
     *
     * @param distId 经销商ID
     * @return 经销商
     */
    public BudistCompany selectBudistCompanyById(Long distId);

    /**
     * 查询经销商列表
     *
     * @param budistCompany 经销商
     * @return 经销商集合
     */
    public List<BudistCompany> selectBudistCompanyList(BudistCompany budistCompany);

    /**
     * 新增经销商
     *
     * @param budistCompany 经销商
     * @return 结果
     */
    public int insertBudistCompany(BudistCompany budistCompany);

    /**
     * 修改经销商
     *
     * @param budistCompany 经销商
     * @return 结果
     */
    public int updateBudistCompany(BudistCompany budistCompany);

    /**
     * 删除经销商
     *
     * @param distId 经销商ID
     * @return 结果
     */
    public int deleteBudistCompanyById(Long distId);

    /**
     * 批量删除经销商
     *
     * @param distIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBudistCompanyByIds(Long[] distIds);
}
