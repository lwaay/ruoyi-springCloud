package com.sinonc.orders.ec.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinonc.orders.ec.domain.EshopProductSale;
import feign.Param;

import java.util.Date;
import java.util.List;

/**
 * @author huanghao
 * @apiNote 电商数据
 * @date 2021/3/16 14:20
 */
public interface EshopProductSaleMapper extends BaseMapper<EshopProductSale> {

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
     * 删除实时产品销量
     *
     * @param id 实时产品销量ID
     * @return 结果
     */
    public int deleteEshopProductSaleById(Long id);

    /**
     * 批量删除实时产品销量
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEshopProductSaleByIds(Long[] ids);

    List<EshopProductSale> getStoreRealTimeStatisticDataToday(@Param("goodsStore") String goodsStore);

    EshopProductSale getTypeRealTimeStatisticDataToday(@Param("goodsType") String goodsType);

    EshopProductSale getTypeRealTimeStatisticDataLast(@org.apache.ibatis.annotations.Param("goodsType") String goodsType, @org.apache.ibatis.annotations.Param("nowDate") Date nowDate);

    List<EshopProductSale> listTypeRealTimeStatisticDataToday(@Param("goodsType") String goodsType);

    List<EshopProductSale> listTypeRealTimeStatisticDataLast(@org.apache.ibatis.annotations.Param("goodsType") String goodsType, @org.apache.ibatis.annotations.Param("nowDate") Date nowDate);

    List<EshopProductSale> getStoreRealTimeStatisticDataYesterday(@Param("goodsStore") String goodsStore);

    EshopProductSale getStoreRealTimeStatisticDataAfter(@org.apache.ibatis.annotations.Param("goodsStore") String goodsStore, @org.apache.ibatis.annotations.Param("goodsId") Long goodsId);

    EshopProductSale getStoreRealTimeStatisticDataLast(@org.apache.ibatis.annotations.Param("goodsStore") String goodsStore, @org.apache.ibatis.annotations.Param("goodsId") Long goodsId, @org.apache.ibatis.annotations.Param("nowDate") Date nowDate);

    EshopProductSale getRealTimeStatisticDataLast(@org.apache.ibatis.annotations.Param("goodsId") Long goodsId, @org.apache.ibatis.annotations.Param("nowDate") Date nowDate);
}
