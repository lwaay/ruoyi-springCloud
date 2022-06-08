package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.ShopDiscountLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 商家改价记录 数据层
 *
 * @author sinonc
 * @date 2019-11-20
 */
@Mapper
public interface ShopDiscountLogMapper {
	/**
     * 查询商家改价记录信息
     *
     * @param logId 商家改价记录ID
     * @return 商家改价记录信息
     */
	public ShopDiscountLog selectShopDiscountLogById(Long logId);

	/**
     * 查询商家改价记录列表
     *
     * @param shopDiscountLog 商家改价记录信息
     * @return 商家改价记录集合
     */
	public List<ShopDiscountLog> selectShopDiscountLogList(ShopDiscountLog shopDiscountLog);

	/**
     * 新增商家改价记录
     *
     * @param shopDiscountLog 商家改价记录信息
     * @return 结果
     */
	public int insertShopDiscountLog(ShopDiscountLog shopDiscountLog);

	/**
     * 修改商家改价记录
     *
     * @param shopDiscountLog 商家改价记录信息
     * @return 结果
     */
	public int updateShopDiscountLog(ShopDiscountLog shopDiscountLog);

	/**
     * 删除商家改价记录
     *
     * @param logId 商家改价记录ID
     * @return 结果
     */
	public int deleteShopDiscountLogById(Long logId);

	/**
     * 批量删除商家改价记录
     *
     * @param logIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteShopDiscountLogByIds(String[] logIds);

}
