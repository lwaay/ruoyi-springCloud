package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.BookGoods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 商品和预订活动 数据层
 *
 * @author sinonc
 * @date 2019-09-25
 */
@Mapper
public interface BookGoodsMapper {
	/**
     * 查询商品和预订活动信息
     *
     * @param bgId 商品和预订活动ID
     * @return 商品和预订活动信息
     */
	public BookGoods selectBookGoodsById(Long bgId);

	/**
	 * 根据商品ID查询预订信息
	 * @param goodsId 商品ID
	 * @return 结果
	 */
	public BookGoods selectByGoodsId(Long goodsId);

	/**
     * 查询商品和预订活动列表
     *
     * @param bookGoods 商品和预订活动信息
     * @return 商品和预订活动集合
     */
	public List<BookGoods> selectBookGoodsList(BookGoods bookGoods);

	/**
     * 新增商品和预订活动
     *
     * @param bookGoods 商品和预订活动信息
     * @return 结果
     */
	public int insertBookGoods(BookGoods bookGoods);

	/**
     * 修改商品和预订活动
     *
     * @param bookGoods 商品和预订活动信息
     * @return 结果
     */
	public int updateBookGoods(BookGoods bookGoods);

	/**
     * 删除商品和预订活动
     *
     * @param bgId 商品和预订活动ID
     * @return 结果
     */
	public int deleteBookGoodsById(Long bgId);

	/**
     * 批量删除商品和预订活动
     *
     * @param bgIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteBookGoodsByIds(String[] bgIds);

    int deleteBookGoodsByGoodsIds(String[] goodsIds);
}
