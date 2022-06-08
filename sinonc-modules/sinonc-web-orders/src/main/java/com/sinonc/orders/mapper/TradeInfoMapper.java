package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.TradeInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 支付 数据层
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Mapper
public interface TradeInfoMapper {
	/**
     * 查询支付信息
     *
     * @param tradeId 支付ID
     * @return 支付信息
     */
	public TradeInfo selectTradeInfoById(Long tradeId);

	/**
     * 查询支付列表
     *
     * @param tradeInfo 支付信息
     * @return 支付集合
     */
	public List<TradeInfo> selectTradeInfoList(TradeInfo tradeInfo);

	/**
     * 新增支付
     *
     * @param tradeInfo 支付信息
     * @return 结果
     */
	public int insertTradeInfo(TradeInfo tradeInfo);

	/**
     * 修改支付
     *
     * @param tradeInfo 支付信息
     * @return 结果
     */
	public int updateTradeInfo(TradeInfo tradeInfo);

	/**
     * 删除支付
     *
     * @param tradeId 支付ID
     * @return 结果
     */
	public int deleteTradeInfoById(Long tradeId);

	/**
     * 批量删除支付
     *
     * @param tradeIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteTradeInfoByIds(String[] tradeIds);


	/**
	 * 查询总交易额
	 * @param tradeInfo
	 * @return
	 */
	public String selectTotalAmount(TradeInfo tradeInfo);

	/**
	 * 查询 付款交易信息
	 * @param orderNo
	 * @return
	 */
    public TradeInfo selectPayTradeByOrderNo(String orderNo);
}
