package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.GoodsSpecs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品规格 数据层
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Mapper
public interface GoodsSpecsMapper {
	/**
     * 查询商品规格信息
     *
     * @param goodsSpecId 商品规格ID
     * @return 商品规格信息
     */
	public GoodsSpecs selectGoodsSpecsById(Long goodsSpecId);

	/**
     * 查询商品规格列表
     *
     * @param goodsSpecs 商品规格信息
     * @return 商品规格集合
     */
	public List<GoodsSpecs> selectGoodsSpecsList(GoodsSpecs goodsSpecs);

	/**
     * 新增商品规格
     *
     * @param goodsSpecs 商品规格信息
     * @return 结果
     */
	public int insertGoodsSpecs(GoodsSpecs goodsSpecs);

	/**
     * 修改商品规格
     *
     * @param goodsSpecs 商品规格信息
     * @return 结果
     */
	public int updateGoodsSpecs(GoodsSpecs goodsSpecs);

	/**
     * 删除商品规格
     *
     * @param goodsSpecId 商品规格ID
     * @return 结果
     */
	public int deleteGoodsSpecsById(Long goodsSpecId);

	/**
     * 批量删除商品规格
     *
     * @param goodsSpecIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteGoodsSpecsByIds(String[] goodsSpecIds);

	/**
	 * 根据商品id和规格id查询商品规格
	 * @param goodsIdP
	 * @param specsIdP
	 * @return
	 */
    public GoodsSpecs selectGoodsIdAndSpecsId(@Param("goodsIdP")Long goodsIdP,@Param("specsIdP")Long specsIdP);


	public GoodsSpecs selectGoodsSpecsByGoodsId(@Param("goodsIdP")Long goodsIdP);

	/**
	 * 批量新增
	 * @param goodsSpecs 商品规格
	 * @return
	 */
	int batchInsert(List<GoodsSpecs> goodsSpecs);

	/**
	 * 根据商品ID删除规格关联关系
	 * @return
	 */
    int deleteGoodsSpecsByGoodsId(Long goodsId);

	/**
	 * 根据商品ID删除规格关联关系
	 * @return
	 */
    int deleteGoodsSpecsByGoodsIds(String[] goodsIds);
}
