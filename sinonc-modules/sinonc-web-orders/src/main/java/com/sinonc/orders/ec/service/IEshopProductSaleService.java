package com.sinonc.orders.ec.service;

import com.sinonc.orders.ec.domain.EshopProductSale;

import java.util.List;

/**
 * 实时产品销量Service接口
 *
 * @author ruoyi
 * @date 2021-04-13
 */
public interface IEshopProductSaleService {
    /**
     * 查询实时产品销量
     *
     * @param id 实时产品销量ID
     * @return 实时产品销量
     */
    public EshopProductSale selectEshopProductSaleById(Long id);

    /**
     * 查询实时产品销量列表
     *
     * @param eshopProductSale 实时产品销量
     * @return 实时产品销量集合
     */
    public List<EshopProductSale> selectEshopProductSaleList(EshopProductSale eshopProductSale);

    /**
     * 新增实时产品销量
     *
     * @param eshopProductSale 实时产品销量
     * @return 结果
     */
    public int insertEshopProductSale(EshopProductSale eshopProductSale);

    /**
     * 修改实时产品销量
     *
     * @param eshopProductSale 实时产品销量
     * @return 结果
     */
    public int updateEshopProductSale(EshopProductSale eshopProductSale);

    /**
     * 批量删除实时产品销量
     *
     * @param ids 需要删除的实时产品销量ID
     * @return 结果
     */
    public int deleteEshopProductSaleByIds(Long[] ids);

    /**
     * 删除实时产品销量信息
     *
     * @param id 实时产品销量ID
     * @return 结果
     */
    public int deleteEshopProductSaleById(Long id);
}
