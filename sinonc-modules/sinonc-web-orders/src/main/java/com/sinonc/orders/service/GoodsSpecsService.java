package com.sinonc.orders.service;

import com.sinonc.orders.domain.GoodsSpecs;

import java.util.List;

/**
 * 商品规格 服务层
 *
 * @author sinonc
 * @date 2019-07-25
 */
public interface GoodsSpecsService {

	/**
     * 查询商品规格信息
     *
     * @param goodsSpecId 商品规格ID
     * @return 商品规格信息
     */
	public GoodsSpecs getGoodsSpecsById(Long goodsSpecId);

	/**
     * 查询商品规格列表
     *
     * @param goodsSpecs 商品规格信息
     * @return 商品规格集合
     */
	public List<GoodsSpecs> listGoodsSpecs(GoodsSpecs goodsSpecs);

	/**
     * 新增商品规格
     *
     * @param goodsSpecs 商品规格信息
     * @return 结果
     */
	public int addGoodsSpecs(GoodsSpecs goodsSpecs);

	/**
	 * 批量新增商品规格
	 * @param goodsSpecs 商品规格列表
	 * @return 结果
	 */
	public int batchAddGoodsSpecs(List<GoodsSpecs> goodsSpecs);

	/**
     * 修改商品规格
     *
     * @param goodsSpecs 商品规格信息
     * @return 结果
     */
	public int updateGoodsSpecs(GoodsSpecs goodsSpecs);

	/**
     * 删除商品规格信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteGoodsSpecsByIds(String ids);

	/**
	 * 根据商品ID删除对应商品的属性关联关系
	 * @param goodsId 商品ID
	 * @return 结果
	 */
    public int deleteGoodsSpecsByGoodsId(Long goodsId);
}
