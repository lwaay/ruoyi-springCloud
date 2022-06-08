package com.sinonc.orders.ec.service;

import com.sinonc.orders.ec.domain.ProductType;

import java.util.List;

/**
 * 大屏自定义品类Service接口
 *
 * @author ruoyi
 * @date 2021-03-31
 */
public interface IProductTypeService {
    /**
     * 查询大屏自定义品类
     *
     * @param typeId 大屏自定义品类ID
     * @return 大屏自定义品类
     */
    public ProductType selectProductTypeById(Long typeId);

    /**
     * 查询大屏自定义品类列表
     *
     * @param productType 大屏自定义品类
     * @return 大屏自定义品类集合
     */
    public List<ProductType> selectProductTypeList(ProductType productType);

    /**
     * 新增大屏自定义品类
     *
     * @param productType 大屏自定义品类
     * @return 结果
     */
    public int insertProductType(ProductType productType);

    /**
     * 修改大屏自定义品类
     *
     * @param productType 大屏自定义品类
     * @return 结果
     */
    public int updateProductType(ProductType productType);

    /**
     * 批量删除大屏自定义品类
     *
     * @param typeIds 需要删除的大屏自定义品类ID
     * @return 结果
     */
    public int deleteProductTypeByIds(Long[] typeIds);

    /**
     * 删除大屏自定义品类信息
     *
     * @param typeId 大屏自定义品类ID
     * @return 结果
     */
    public int deleteProductTypeById(Long typeId);

    /**
     * 查询默认品类
     * @return
     */
    List<ProductType> selectListDefault();
}
