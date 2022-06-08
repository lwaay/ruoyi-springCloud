package com.sinonc.orders.service;

/**
 * 订单结算服务
 */
public interface SettlementService {

    /**
     * 直购自动结算
     */
    public int tradeOrderSettlement();

    /**
     * 预订自动结算
     * @return 结果
     */
    public int bookOrderSettlement();


    /**
     * 定金结算
     * @return 结果
     */
    public int bookEarnestSettlement();


    /**
     * 认养订单结算
     * @param settlement 结算状态
     * @return
     */
    public int adoptOrderSettlement(Integer settlement);

}
