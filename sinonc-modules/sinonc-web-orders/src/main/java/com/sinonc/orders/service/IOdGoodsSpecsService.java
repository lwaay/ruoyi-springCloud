package com.sinonc.orders.service;

import java.util.List;

import com.sinonc.orders.domain.OdGoodsSpecs;

/**
 * 商品规格Service接口
 *
 * @author ruoyi
 * @date 2022-03-23
 */
public interface IOdGoodsSpecsService {
    /**
     * 查询商品规格
     *
     * @param goodsSpecId 商品规格ID
     * @return 商品规格
     */
    public OdGoodsSpecs selectOdGoodsSpecsById(Long goodsSpecId);

    /**
     * 查询商品规格列表
     *
     * @param odGoodsSpecs 商品规格
     * @return 商品规格集合
     */
    public List<OdGoodsSpecs> selectOdGoodsSpecsList(OdGoodsSpecs odGoodsSpecs);

    /**
     * 新增商品规格
     *
     * @param odGoodsSpecs 商品规格
     * @return 结果
     */
    public int insertOdGoodsSpecs(OdGoodsSpecs odGoodsSpecs);

    /**
     * 修改商品规格
     *
     * @param odGoodsSpecs 商品规格
     * @return 结果
     */
    public int updateOdGoodsSpecs(OdGoodsSpecs odGoodsSpecs);

    /**
     * 批量删除商品规格
     *
     * @param goodsSpecIds 需要删除的商品规格ID
     * @return 结果
     */
    public int deleteOdGoodsSpecsByIds(Long[] goodsSpecIds);

    /**
     * 删除商品规格信息
     *
     * @param goodsSpecId 商品规格ID
     * @return 结果
     */
    public int deleteOdGoodsSpecsById(Long goodsSpecId);
}
