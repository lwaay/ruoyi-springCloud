package com.sinonc.orders.service;

import com.sinonc.orders.domain.ShopDiscountLog;

import java.util.List;

/**
 * 商家改价记录 服务层
 *
 * @author sinonc
 * @date 2019-11-20
 */
public interface ShopDiscountLogService {

	/**
     * 查询商家改价记录信息
     *
     * @param logId 商家改价记录ID
     * @return 商家改价记录信息
     */
	public ShopDiscountLog getShopDiscountLogById(Long logId);

	/**
     * 查询商家改价记录列表
     *
     * @param shopDiscountLog 商家改价记录信息
     * @return 商家改价记录集合
     */
	public List<ShopDiscountLog> listShopDiscountLog(ShopDiscountLog shopDiscountLog);

	/**
     * 新增商家改价记录
     *
     * @param shopDiscountLog 商家改价记录信息
     * @return 结果
     */
	public int addShopDiscountLog(ShopDiscountLog shopDiscountLog);

	/**
     * 修改商家改价记录
     *
     * @param shopDiscountLog 商家改价记录信息
     * @return 结果
     */
	public int updateShopDiscountLog(ShopDiscountLog shopDiscountLog);

	/**
     * 删除商家改价记录信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteShopDiscountLogByIds(String ids);

}
