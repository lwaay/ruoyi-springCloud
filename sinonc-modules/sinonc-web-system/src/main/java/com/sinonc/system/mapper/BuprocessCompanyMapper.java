package com.sinonc.system.mapper;

import java.util.List;

import com.sinonc.system.domain.BuprocessCompany;

/**
 * 加工农企Mapper接口
 *
 * @author ruoyi
 * @date 2022-02-24
 */
public interface BuprocessCompanyMapper {
    /**
     * 查询加工农企
     *
     * @param proceId 加工农企ID
     * @return 加工农企
     */
    public BuprocessCompany selectBuprocessCompanyById(Long proceId);

    /**
     * 查询加工农企列表
     *
     * @param buprocessCompany 加工农企
     * @return 加工农企集合
     */
    public List<BuprocessCompany> selectBuprocessCompanyList(BuprocessCompany buprocessCompany);

    /**
     * 新增加工农企
     *
     * @param buprocessCompany 加工农企
     * @return 结果
     */
    public int insertBuprocessCompany(BuprocessCompany buprocessCompany);

    /**
     * 修改加工农企
     *
     * @param buprocessCompany 加工农企
     * @return 结果
     */
    public int updateBuprocessCompany(BuprocessCompany buprocessCompany);

    /**
     * 删除加工农企
     *
     * @param proceId 加工农企ID
     * @return 结果
     */
    public int deleteBuprocessCompanyById(Long proceId);

    /**
     * 批量删除加工农企
     *
     * @param proceIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBuprocessCompanyByIds(Long[] proceIds);
}
