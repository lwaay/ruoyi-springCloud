package com.sinonc.orders.ec.service.impl;

import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.orders.ec.domain.EshopProductSale;
import com.sinonc.orders.ec.mapper.EshopProductSaleMapper;
import com.sinonc.orders.ec.service.IEshopProductSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 实时产品销量Service业务层处理
 *
 * @author ruoyi
 * @date 2021-04-13
 */
@Service
public class EshopProductSaleServiceImpl implements IEshopProductSaleService {
    @Autowired
    private EshopProductSaleMapper eshopProductSaleMapper;

    /**
     * 查询实时产品销量
     *
     * @param id 实时产品销量ID
     * @return 实时产品销量
     */
    @Override
    public EshopProductSale selectEshopProductSaleById(Long id) {
        return eshopProductSaleMapper.selectEshopProductSaleById(id);
    }

    /**
     * 查询实时产品销量列表
     *
     * @param eshopProductSale 实时产品销量
     * @return 实时产品销量
     */
    @Override
    public List<EshopProductSale> selectEshopProductSaleList(EshopProductSale eshopProductSale) {
        return eshopProductSaleMapper.selectEshopProductSaleList(eshopProductSale);
    }

    /**
     * 新增实时产品销量
     *
     * @param eshopProductSale 实时产品销量
     * @return 结果
     */
    @Override
    public int insertEshopProductSale(EshopProductSale eshopProductSale) {
        eshopProductSale.setCreateTime(DateUtils.getNowDate());
        return eshopProductSaleMapper.insertEshopProductSale(eshopProductSale);
    }

    /**
     * 修改实时产品销量
     *
     * @param eshopProductSale 实时产品销量
     * @return 结果
     */
    @Override
    public int updateEshopProductSale(EshopProductSale eshopProductSale) {
        return eshopProductSaleMapper.updateEshopProductSale(eshopProductSale);
    }

    /**
     * 批量删除实时产品销量
     *
     * @param ids 需要删除的实时产品销量ID
     * @return 结果
     */
    @Override
    public int deleteEshopProductSaleByIds(Long[] ids) {
        return eshopProductSaleMapper.deleteEshopProductSaleByIds(ids);
    }

    /**
     * 删除实时产品销量信息
     *
     * @param id 实时产品销量ID
     * @return 结果
     */
    @Override
    public int deleteEshopProductSaleById(Long id) {
        return eshopProductSaleMapper.deleteEshopProductSaleById(id);
    }
}
