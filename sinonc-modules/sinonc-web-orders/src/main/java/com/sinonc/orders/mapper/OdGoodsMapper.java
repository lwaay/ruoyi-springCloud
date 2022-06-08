package com.sinonc.orders.mapper;

import java.util.List;

import com.sinonc.orders.domain.OdGoods;

/**
 * 商品Mapper接口
 *
 * @author ruoyi
 * @date 2022-04-01
 */
public interface OdGoodsMapper {
    /**
     * 查询商品
     *
     * @param goodsId 商品ID
     * @return 商品
     */
    public OdGoods selectOdGoodsById(Long goodsId);

    /**
     * 查询商品列表
     *
     * @param odGoods 商品
     * @return 商品集合
     */
    public List<OdGoods> selectOdGoodsList(OdGoods odGoods);

    /**
     * 新增商品
     *
     * @param odGoods 商品
     * @return 结果
     */
    public int insertOdGoods(OdGoods odGoods);

    /**
     * 修改商品
     *
     * @param odGoods 商品
     * @return 结果
     */
    public int updateOdGoods(OdGoods odGoods);

    /**
     * 删除商品
     *
     * @param goodsId 商品ID
     * @return 结果
     */
    public int deleteOdGoodsById(Long goodsId);

    /**
     * 批量删除商品
     *
     * @param goodsIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteOdGoodsByIds(Long[] goodsIds);

    /**
     * 限定数量查询
     * @return
     */
    List<OdGoods> selectOdGoodsListLimit(OdGoods odGoods);

    /**
     * 查询热销商品
     * @param odGoods
     * @return
     */
    List<OdGoods> selectRxOdGoodsByShopId(OdGoods odGoods);
}
