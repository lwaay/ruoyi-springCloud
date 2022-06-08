package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 商品 数据层
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Mapper
public interface GoodsMapper {
	/**
     * 查询商品信息
     *
     * @param goodsId 商品ID
     * @return 商品信息
     */
	public Goods selectGoodsById(Long goodsId);

	/**
	 * 查询商品信息
	 *
	 * @param ids 商品ID
	 * @return 商品信息
	 */
	List<Goods> selectGoodsByIds(String[] ids);

	/**
     * 查询商品列表
     *
     * @param goods 商品信息
     * @return 商品集合
     */
	public List<Goods> selectGoodsList(Goods goods);

	/**
     * 新增商品
     *
     * @param goods 商品信息
     * @return 结果
     */
	public int insertGoods(Goods goods);

	/**
     * 修改商品
     *
     * @param goods 商品信息
     * @return 结果
     */
	public int updateGoods(Goods goods);

	/**
     * 删除商品
     *
     * @param goodsId 商品ID
     * @return 结果
     */
	public int deleteGoodsById(Long goodsId);

	/**
     * 批量删除商品
     *
     * @param goodsIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteGoodsByIds(String[] goodsIds);

	/**
	 * 查询预订商品的销售情况
	 * @param goodsId 商品id
	 * @return 结果
	 */
	List<Map<String, String>> selectBookGoodsSellInfo(Long goodsId);

	/**
	 * 统计商品销售量
	 * @param goodsId
	 * @param orderType 订单类型：0，认养订单，1，交易订单,2,预订订单  3,竞拍订单
	 * @return
	 */
	Integer countGoods(Long goodsId,int orderType);

	/**
	 * 商品数
	 * @return
	 */
	Integer goodsCount();
}
