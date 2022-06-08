package com.sinonc.orders.service;

import com.sinonc.orders.domain.Goods;
import com.sinonc.orders.domain.Specs;
import com.sinonc.orders.dto.GoodsDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 商品 服务层
 *
 * @author sinonc
 * @date 2019-07-25
 */
public interface GoodsService {

    /**
     * 查询商品信息
     *
     * @param goodsId 商品ID
     * @return 商品信息
     */
    public Goods getGoodsById(Long goodsId);

    /**
     * 查询商品列表
     *
     * @param goods 商品信息
     * @return 商品集合
     */
    public List<Goods> listGoods(Goods goods);

    /**
     * 新增商品
     *
     * @param goodsDto 商品信息
     * @return 结果
     */
    public int addGoods(GoodsDto goodsDto);

    /**
     * 修改商品
     *
     * @param goodsDto 商品信息
     * @return 结果
     */
    public int updateGoods(GoodsDto goodsDto);

    /**
     * 删除商品信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGoodsByIds(String ids);

    /**
     * 获取预订商品的销售情况
     * @param goodsId 商品ID
     * @return 结果
     */
    public List<Map<String, String>> getBookGoodsSellInfo(Long goodsId);

    /**
     * 判断是否有足够的可销售量，true有，false没有
     * @param shopId
     * @return
     */
    public boolean judgeSaleAllWeightAndStock(Long shopId, List<Specs> changeSpecsList , String inSpecsIds);

    /**
     * 获取可销售总重量
     * @param shopId
     * @return
     */
    public BigDecimal nabSaleAllWeight(Long shopId);

    /**
     * 获取已销售量
     * @param shopId
     * @return
     */
    public BigDecimal nabOrderItemWeight(Long shopId);

    /**
     * 获取已上架量
     * @param shopId
     * @param changeSpecsList
     * @param inSpecsIds
     * @return
     */
    public BigDecimal nabSpecsWeight(Long shopId,List<Specs> changeSpecsList ,String inSpecsIds);

    /**
     * 统计商品销售量
     * @param goodsId
     * @param orderType 订单类型：0，认养订单，1，交易订单,2,预订订单  3,竞拍订单
     * @return
     */
    public Integer countGoods(Long goodsId,int orderType);

    /**
     * 商品数
     * @return
     */
    public Integer goodsCount();
}
