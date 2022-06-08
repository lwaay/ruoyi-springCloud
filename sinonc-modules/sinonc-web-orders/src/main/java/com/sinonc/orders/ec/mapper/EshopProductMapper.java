package com.sinonc.orders.ec.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinonc.orders.ec.domain.EshopProduct;
import com.sinonc.orders.ec.dto.GroupByTypeTimeDto;
import com.sinonc.orders.ec.vo.ProductSortVo;
import com.sinonc.orders.ec.vo.RealTimeStatisticVo;
import feign.Param;

import java.util.List;

/**
 * 电商数据Mapper接口
 *
 * @author ruoyi
 * @date 2020-09-27
 */
public interface EshopProductMapper extends BaseMapper<EshopProduct> {
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
     * 删除电商数据
     *
     * @param eshopId 电商数据ID
     * @return 结果
     */
    public int deleteEshopProductById(Long eshopId);

    /**
     * 批量删除电商数据
     *
     * @param eshopIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteEshopProductByIds(Long[] eshopIds);

    /**
     * 分组查询
     * @param productSortVo
     * @return
     */
    List<GroupByTypeTimeDto> groupByTypeAndTime(ProductSortVo productSortVo);

    /**
     * 根据品类id查询电商
     * @param productSortVo
     * @return
     */
    List<EshopProduct> selectByTypeIds(ProductSortVo productSortVo);

    /**
     * 根据品类id查询电商，以商品维度
     * @param productSortVo
     * @return
     */
    List<EshopProduct> selectByTypeNames(ProductSortVo productSortVo);
    /**
     * 根据品类id查询电商，以店铺维度
     * @param productSortVo
     * @return
     */
    List<EshopProduct> selectByShop(ProductSortVo productSortVo);

    /**
     * 根据类别与时间分组汇总查询销售量
     * @param productSortVo
     * @return
     */
    List<GroupByTypeTimeDto> groupSaleAmountByTypeAndTime(ProductSortVo productSortVo);

    RealTimeStatisticVo getShopRealTimeStatisticData(@Param("productType") String productType);

    /**
     * 根据品牌名称模糊查询
     * @param name
     * @return
     */
    List<String> selectBrandListByName(String name);

    /**
     * 根据商品id获取产品信息
     */
    EshopProduct selectProductByGoodsId(Long goodsId);
}
