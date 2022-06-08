package com.sinonc.orders.service;

import com.sinonc.orders.domain.BookGoods;

import java.util.List;

/**
 * 商品和预订活动 服务层
 *
 * @author sinonc
 * @date 2019-09-25
 */
public interface BookGoodsService {

	/**
     * 查询商品和预订活动信息
     *
     * @param bgId 商品和预订活动ID
     * @return 商品和预订活动信息
     */
	public BookGoods getBookGoodsById(Long bgId);

	/**
     * 查询商品和预订活动列表
     *
     * @param bookGoods 商品和预订活动信息
     * @return 商品和预订活动集合
     */
	public List<BookGoods> listBookGoods(BookGoods bookGoods);

	/**
     * 保存商品和预订活动
     *
     * @param bookGoods 商品和预订活动信息
     * @return 结果
     */
	public int saveBookGoods(BookGoods bookGoods);

	/**
     * 修改商品和预订活动
     *
     * @param bookGoods 商品和预订活动信息
     * @return 结果
     */
	public int updateBookGoods(BookGoods bookGoods);

	/**
	 * 用于定时任务更新预订活动状态
	 * @param bookId 预订活动ID
	 * @param status 活动状态
	 * @return 结果
	 */
	public int updateBookGoodsStatus(Long bookId, Integer status);

	/**
     * 删除商品和预订活动信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteBookGoodsByIds(String ids);

	/**
	 * 根据商品Id 删除预订活动
	 * @param goodsIds 商品ID
	 * @return 结果
	 */
	public int deleteBookGoodsByGoodsIds(String[] goodsIds);

	/**
	 * 根据商品ID查询预订信息
	 * @param goodsId 商品ID
	 */
    public BookGoods getByGoodsId(Long goodsId);
}
