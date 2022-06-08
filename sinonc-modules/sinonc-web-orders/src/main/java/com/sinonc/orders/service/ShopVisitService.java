package com.sinonc.orders.service;

import com.sinonc.orders.domain.ShopVisit;

import java.util.List;

/**
 * 店铺访问量 服务层
 *
 * @author sinonc
 * @date 2019-11-19
 */
public interface ShopVisitService {

	/**
     * 查询店铺访问量信息
     *
     * @param visitId 店铺访问量ID
     * @return 店铺访问量信息
     */
	public ShopVisit getShopVisitById(Long visitId);

	/**
     * 查询店铺访问量列表
     *
     * @param shopVisit 店铺访问量信息
     * @return 店铺访问量集合
     */
	public List<ShopVisit> listShopVisit(ShopVisit shopVisit);

	/**
     * 新增店铺访问量
     *
     * @param shopVisit 店铺访问量信息
     * @return 结果
     */
	public int addShopVisit(ShopVisit shopVisit);

	/**
     * 修改店铺访问量
     *
     * @param shopVisit 店铺访问量信息
     * @return 结果
     */
	public int updateShopVisit(ShopVisit shopVisit);

	/**
     * 删除店铺访问量信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteShopVisitByIds(String ids);

	/**
	 * 查询单日访问量
	 * @param shopVisit
	 * @return
	 */
	public Long getShopVisit(ShopVisit shopVisit);
}
