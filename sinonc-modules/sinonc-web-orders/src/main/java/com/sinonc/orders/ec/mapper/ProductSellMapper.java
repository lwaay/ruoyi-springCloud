package com.sinonc.orders.ec.mapper;


import com.sinonc.orders.ec.vo.EshopMonthVo;
import com.sinonc.orders.ec.vo.ProductSellVo;
import com.sinonc.orders.ec.vo.ProductSortVo;

import java.util.List;

/**
 *农产品电商渠道占比分析
 *
 */
public interface ProductSellMapper {

    /**
     * 品类销量占比
     * @param productSortVo
     * @return
     */
    public ProductSellVo selectSellProportion(ProductSortVo productSortVo);

    /**
     * 品类销售额占比
     * @param productSortVo
     * @return
     */
    public ProductSellVo selectDuckList(ProductSortVo productSortVo);

    /**
     * 品类产品数占比
     * @param productSortVo
     * @return
     */
    public ProductSellVo selectGoodsList(ProductSortVo productSortVo);

    /**
     *  电商月销售统计
     *  @param productSortVo
     *  @return
     */
    public List<EshopMonthVo> selectListByMonth(ProductSortVo productSortVo);

    /**
     * 梁平区电商品牌销售对比分析
     *@param productSortVo
     * @return
     */
    public ProductSellVo selectBrandPriceList(ProductSortVo productSortVo);

    /**
     * 梁平区农产品特产店铺销售对比分析
     * @param productSortVo
     * @return
     */
    List<ProductSellVo> selectShopList(ProductSortVo productSortVo);


    /**
     * 查询电商平台销售数据的最后日期
     * @return
     */
    public String selectDateForParam();

}
