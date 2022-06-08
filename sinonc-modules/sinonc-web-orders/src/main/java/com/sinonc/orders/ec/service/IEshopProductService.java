package com.sinonc.orders.ec.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinonc.orders.ec.domain.EshopProduct;
import com.sinonc.orders.ec.bo.ProductSellBo;
import com.sinonc.orders.ec.vo.*;

import java.util.List;

/**
 * 电商数据Service接口
 *
 * @author ruoyi
 * @date 2020-09-27
 */
public interface IEshopProductService extends IService<EshopProduct> {

    /**
     * 导入电商数据
     *
     * @param eshopProductList        用户数据列表
     * @param operName        操作用户
     * @return 结果
     */
    public String importEshop(List<EshopProduct> eshopProductList, String operName);

    /**
     * 查询电商数据
     *
     * @param eshopId 电商数据ID
     * @return 电商数据
     */
    public EshopProduct selectEshopProductById(Long eshopId);

    /**
     * 查询电商数据列表
     *
     * @param eshopProduct 电商数据
     * @return 电商数据集合
     */
    public List<EshopProduct> selectEshopProductList(EshopProduct eshopProduct);


    /**
     * 查询电商数据列表
     *
     * @param eshopProduct 电商数据
     * @return 电商数据集合
     */
    public List<EshopProduct> selectProductList(EshopProduct eshopProduct);

    /**
     * 新增电商数据
     *
     * @param eshopProduct 电商数据
     * @return 结果
     */
    public int insertEshopProduct(EshopProduct eshopProduct);

    /**
     * 修改电商数据
     *
     * @param eshopProduct 电商数据
     * @return 结果
     */
    public int updateEshopProduct(EshopProduct eshopProduct);

    /**
     * 批量删除电商数据
     *
     * @param eshopIds 需要删除的电商数据ID
     * @return 结果
     */
    public int deleteEshopProductByIds(Long[] eshopIds);

    /**
     * 删除电商数据信息
     *
     * @param eshopId 电商数据ID
     * @return 结果
     */
    public int deleteEshopProductById(Long eshopId);


    /**
     * 获取商品信息
     *
     * @param productSortVo 筛选条件
     * @return EshopPorductDto
     */
    List<EshopPorductVo> getRankList(ProductSortVo productSortVo);

    /**
     * 获取品牌销量折线图
     *
     * @param productSortVo
     * @return 结果
     */
    ProductBrandTypeVo getBrandTypeList(ProductSortVo productSortVo);

    /**
     * 电商月销售统计
     *
     * @param productSortVo
     * @return
     */
    List<EshopMonthVo> getListByMonth(ProductSortVo productSortVo);

    /**
     * 查询各产品销售量占比
     *
     * @param productSortVo
     * @return
     */
    ProductSellBo getSellProportion(ProductSortVo productSortVo);

    /**
     * 梁平区电商品牌销售对比分析
     *
     * @param productSortVo
     * @return
     */
    List<ProductSellVo> getBrandPriceList(ProductSortVo productSortVo);

    /**
     * 梁平区农产品特产店铺销售对比分析
     *
     * @param productSortVo
     * @return
     */
    List<ProductSellVo> getShopList(ProductSortVo productSortVo);

    /**
     * 修改爬取状态
     *
     * @param goodsId
     * @return
     */
    int changeReptile(String goodsId);

    RealTimeStatisticVo getShopRealTimeStatisticData(String productType);

    List<RealTimeStoreStatisticVo> getStoreRealTimeStatisticData(String goodsStore);

    List<String> getReptileProductType();

    List<String> getReptileProductStore();

    /**
     *根据品牌名称模糊查询
     * @param name
     * @return
     */
    List<String> selectBrandListByName(String name);

    String selectDateForParam();
}
