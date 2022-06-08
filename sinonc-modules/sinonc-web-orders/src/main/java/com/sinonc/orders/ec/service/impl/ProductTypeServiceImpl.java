package com.sinonc.orders.ec.service.impl;

import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.orders.ec.domain.ProductType;
import com.sinonc.orders.ec.mapper.ProductTypeMapper;
import com.sinonc.orders.ec.service.IProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 大屏自定义品类Service业务层处理
 *
 * @author ruoyi
 * @date 2021-03-31
 */
@Service
public class ProductTypeServiceImpl implements IProductTypeService {
    @Autowired
    private ProductTypeMapper productTypeMapper;

    /**
     * 查询大屏自定义品类
     *
     * @param typeId 大屏自定义品类ID
     * @return 大屏自定义品类
     */
    @Override
    public ProductType selectProductTypeById(Long typeId) {
        return productTypeMapper.selectProductTypeById(typeId);
    }

    /**
     * 查询大屏自定义品类列表
     *
     * @param productType 大屏自定义品类
     * @return 大屏自定义品类
     */
    @Override
    public List<ProductType> selectProductTypeList(ProductType productType) {
        return productTypeMapper.selectProductTypeList(productType);
    }

    /**
     * 新增大屏自定义品类
     *
     * @param productType 大屏自定义品类
     * @return 结果
     */
    @Override
    public int insertProductType(ProductType productType) {
                                                                                                                                        productType.setCreateTime(DateUtils.getNowDate());
                                                return productTypeMapper.insertProductType(productType);
    }

    /**
     * 修改大屏自定义品类
     *
     * @param productType 大屏自定义品类
     * @return 结果
     */
    @Override
    public int updateProductType(ProductType productType) {
                                                                                                                                                            return productTypeMapper.updateProductType(productType);
    }

    /**
     * 批量删除大屏自定义品类
     *
     * @param typeIds 需要删除的大屏自定义品类ID
     * @return 结果
     */
    @Override
    public int deleteProductTypeByIds(Long[] typeIds) {
        return productTypeMapper.deleteProductTypeByIds(typeIds);
    }

    /**
     * 删除大屏自定义品类信息
     *
     * @param typeId 大屏自定义品类ID
     * @return 结果
     */
    @Override
    public int deleteProductTypeById(Long typeId) {
        return productTypeMapper.deleteProductTypeById(typeId);
    }

    /**
     * 查询默认品类
     * @return
     */
    @Override
    public List<ProductType> selectListDefault(){
        return productTypeMapper.selectListDefault();
    }
}
