package com.sinonc.orders.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 预订单表 od_book_order
 *
 * @author sinonc
 * @date 2019-11-01
 */

public class BookOrder {

    private static final long serialVersionUID = 1L;

    //交易状态
    /**
     * 等待付款
     */
    public static final int TRADE_STATUS_WAIT_PAY = 0;
    /**
     * 已付定金
     */
    public static final int TRADE_STATUS_PAY_EARNEST = 1;
    /**
     * 已付尾款
     */
    public static final int TRADE_STATUS_PAY_REST = 2;

    /**
     * 已退定金
     */
    public static final int TRADE_STATUS_REFUND_EARNEST = 3;

    /**
     * 已退全部尾款
     */
    public static final int TRADE_STATUS_REFUND_REST_ALL = 4;

    /**
     * 已退部分尾款
     */
    public static final int TRADE_STATUS_REFUND_REST_PART = 4;

    /**
     *
     */
    private Long boId;
    /**
     * 订单表ID
     */
    private Long orderId;

    /**
     * 店铺ID
     */
    private Long shopId;
    /**
     * 定金单号
     */
    private String earnestNo;
    /**
     * 定金
     */
    private BigDecimal earnestPrice;
    /**
     * 尾款单号
     */
    private String restNo;
    /**
     * 尾款
     */
    private BigDecimal restPrice;
    /**
     * 0，待支付；1，已付定金，2、已付尾款,3,已取消
     */
    private Integer tradeStatus;

    /**
     * 结算状态 0，未结算，1，已结算
     */
    private Integer settlement;

    /**
     * 结算金额
     */
    private BigDecimal settlementAmount;
    /**
     * 定金支付记录
     */
    private Long earnestTradeId;
    /**
     * 定金支付时间
     */
    private Date earnestTradeTime;
    /**
     * 尾款支付记录
     */
    private Long restTradeId;
    /**
     * 尾款支付时间
     */
    private Date restTradeTime;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 结算时间
     */
    private Date settlementTime;

    public void setBoId(Long boId) {
        this.boId = boId;
    }

    public Long getBoId() {
        return boId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public void setEarnestNo(String earnestNo) {
        this.earnestNo = earnestNo;
    }

    public String getEarnestNo() {
        return earnestNo;
    }

    public void setEarnestPrice(BigDecimal earnestPrice) {
        this.earnestPrice = earnestPrice;
    }

    public BigDecimal getEarnestPrice() {
        return earnestPrice;
    }

    public void setRestNo(String restNo) {
        this.restNo = restNo;
    }

    public String getRestNo() {
        return restNo;
    }

    public void setRestPrice(BigDecimal restPrice) {
        this.restPrice = restPrice;
    }

    public BigDecimal getRestPrice() {
        return restPrice;
    }

    public void setTradeStatus(Integer tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public Integer getTradeStatus() {
        return tradeStatus;
    }

    public void setEarnestTradeId(Long earnestTradeId) {
        this.earnestTradeId = earnestTradeId;
    }

    public Long getEarnestTradeId() {
        return earnestTradeId;
    }

    public void setEarnestTradeTime(Date earnestTradeTime) {
        this.earnestTradeTime = earnestTradeTime;
    }

    public Date getEarnestTradeTime() {
        return earnestTradeTime;
    }

    public void setRestTradeId(Long restTradeId) {
        this.restTradeId = restTradeId;
    }

    public Long getRestTradeId() {
        return restTradeId;
    }

    public void setRestTradeTime(Date restTradeTime) {
        this.restTradeTime = restTradeTime;
    }

    public Date getRestTradeTime() {
        return restTradeTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSettlement() {
        return settlement;
    }

    public void setSettlement(Integer settlement) {
        this.settlement = settlement;
    }

    public Date getSettlementTime() {
        return settlementTime;
    }

    public void setSettlementTime(Date settlementTime) {
        this.settlementTime = settlementTime;
    }

    public BigDecimal getSettlementAmount() {
        return settlementAmount;
    }

    public void setSettlementAmount(BigDecimal settlementAmount) {
        this.settlementAmount = settlementAmount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("boId", getBoId())
                .append("orderId", getOrderId())
                .append("earnestNo", getEarnestNo())
                .append("earnestPrice", getEarnestPrice())
                .append("restNo", getRestNo())
                .append("restPrice", getRestPrice())
                .append("tradeStatus", getTradeStatus())
                .append("earnestTradeId", getEarnestTradeId())
                .append("earnestTradeTime", getEarnestTradeTime())
                .append("restTradeId", getRestTradeId())
                .append("restTradeTime", getRestTradeTime())
                .toString();
    }
}
