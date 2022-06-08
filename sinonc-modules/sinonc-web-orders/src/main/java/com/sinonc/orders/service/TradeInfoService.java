package com.sinonc.orders.service;

import com.sinonc.orders.domain.TradeInfo;

import java.util.List;

/**
 * 支付 服务层
 *
 * @author sinonc
 * @date 2019-07-25
 */
public interface TradeInfoService {

	/**
     * 查询支付信息
     *
     * @param tradeId 支付ID
     * @return 支付信息
     */
	public TradeInfo getTradeInfoById(Long tradeId);

	/**
     * 查询支付列表
     *
     * @param tradeInfo 支付信息
     * @return 支付集合
     */
	public List<TradeInfo> listTradeInfo(TradeInfo tradeInfo);

	/**
     * 新增支付
     *
     * @param tradeInfo 支付信息
     * @return 结果
     */
	public int addTradeInfo(TradeInfo tradeInfo);

	/**
     * 修改支付
     *
     * @param tradeInfo 支付信息
     * @return 结果
     */
	public int updateTradeInfo(TradeInfo tradeInfo);

	/**
     * 删除支付信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTradeInfoByIds(String ids);

}
