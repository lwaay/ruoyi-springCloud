package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.ShopVisit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 店铺访问量 数据层
 *
 * @author sinonc
 * @date 2019-11-19
 */
@Mapper
public interface ShopVisitMapper {
	/**
     * 查询店铺访问量信息
     *
     * @param visitId 店铺访问量ID
     * @return 店铺访问量信息
     */
	public ShopVisit selectShopVisitById(Long visitId);

	/**
     * 查询店铺访问量列表
     *
     * @param shopVisit 店铺访问量信息
     * @return 店铺访问量集合
     */
	public List<ShopVisit> selectShopVisitList(ShopVisit shopVisit);


	/**
	 * 统计店铺访问量
	 * @param shopVisit
	 * @return
	 */
	public long sumShopVisitList(ShopVisit shopVisit);

	/**
     * 新增店铺访问量
     *
     * @param shopVisit 店铺访问量信息
     * @return 结果
     */
	public int insertShopVisit(ShopVisit shopVisit);

	/**
     * 修改店铺访问量
     *
     * @param shopVisit 店铺访问量信息
     * @return 结果
     */
	public int updateShopVisit(ShopVisit shopVisit);

	/**
     * 删除店铺访问量
     *
     * @param visitId 店铺访问量ID
     * @return 结果
     */
	public int deleteShopVisitById(Long visitId);

	/**
     * 批量删除店铺访问量
     *
     * @param visitIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteShopVisitByIds(String[] visitIds);

	/**
	 * 统计所有访问量
	 * @return
	 */
	public Long countVisit();

	/**
	 * 查询店铺今日访问量
	 * @param shopVisit
	 * @return
	 */
	public Long selectTodayVisit(ShopVisit shopVisit);
}
