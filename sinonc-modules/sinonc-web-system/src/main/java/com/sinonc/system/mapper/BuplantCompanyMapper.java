package com.sinonc.system.mapper;

import java.util.List;

import com.sinonc.system.domain.BuplantCompany;

/**
 * 种植户（生产商）Mapper接口
 *
 * @author ruoyi
 * @date 2022-02-24
 */
public interface BuplantCompanyMapper {
    /**
     * 查询种植户（生产商）
     *
     * @param plantId 种植户（生产商）ID
     * @return 种植户（生产商）
     */
    public BuplantCompany selectBuplantCompanyById(Long plantId);

    /**
     * 查询种植户（生产商）列表
     *
     * @param buplantCompany 种植户（生产商）
     * @return 种植户（生产商）集合
     */
    public List<BuplantCompany> selectBuplantCompanyList(BuplantCompany buplantCompany);

    /**
     * 新增种植户（生产商）
     *
     * @param buplantCompany 种植户（生产商）
     * @return 结果
     */
    public int insertBuplantCompany(BuplantCompany buplantCompany);

    /**
     * 修改种植户（生产商）
     *
     * @param buplantCompany 种植户（生产商）
     * @return 结果
     */
    public int updateBuplantCompany(BuplantCompany buplantCompany);

    /**
     * 删除种植户（生产商）
     *
     * @param plantId 种植户（生产商）ID
     * @return 结果
     */
    public int deleteBuplantCompanyById(Long plantId);

    /**
     * 批量删除种植户（生产商）
     *
     * @param plantIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBuplantCompanyByIds(Long[] plantIds);
}
