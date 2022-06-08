package com.sinonc.orders.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 退款申请表 od_refund
 *
 * @author sinonc
 * @date 2019-11-11
 */

public class Refund {

    private static final long serialVersionUID = 1L;

    /**
     * 等待同意
     */
    public static final int WAIT_CONFIRM = 0;
    /**
     * 卖家同意
     */
    public static final int SELLER_CONFIRM = 1;
    /**
     * 卖家拒绝
     */
    public static final int SELLER_REFUSE = 2;
    /**
     * 买家取消
     */
    public static final int BUYER_CANCEL = 3;

    /**
     * 退款ID
     */
    private Long rfId;
    /**
     * 订单ID
     */
    @NotNull(message = "orderId 不能为空")
    private Long orderId;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 退款单号
     */
    private String refundNo;
    /**
     * 会员ID
     */
    private Long memberId;
    /**
     *
     */
    private BigDecimal orderAmount;
    /**
     * 退款原因
     */
    @NotEmpty(message = "退款原因不能为空")
    private String rfReason;
    /**
     * 图片
     */
    private String rfImg;
    /**
     * 退款金额
     */
    @NotNull(message = "退款金额不能为空")
    private BigDecimal rfAmount;
    /**
     * 退款描述
     */
    @NotEmpty(message = "退款说明不能为空")
    private String rfDescribe;
    /**
     * 店铺ID
     */
    private Long shopId;
    /**
     * 退款创建时间
     */
    private Date createTime;
    /**
     * 退款确认时间
     */
    private Date confirmTime;
    //确认者
    private String confirmBy;
    /**
     *
     */
    private Date updateTime;
    /**
     * 退款交易信息ID
     */
    private Long tradeId;
    /**
     * 状态：,0，等待卖家同意；1，卖家同意；2，卖家拒绝；3,买家取消
     */
    private Integer rfStatus;

    public void setRfId(Long rfId) {
        this.rfId = rfId;
    }

    public Long getRfId() {
        return rfId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setRfReason(String rfReason) {
        this.rfReason = rfReason;
    }

    public String getRfReason() {
        return rfReason;
    }

    public void setRfImg(String rfImg) {
        this.rfImg = rfImg;
    }

    public String getRfImg() {
        return rfImg;
    }

    public void setRfAmount(BigDecimal rfAmount) {
        this.rfAmount = rfAmount;
    }

    public BigDecimal getRfAmount() {
        return rfAmount;
    }

    public void setRfDescribe(String rfDescribe) {
        this.rfDescribe = rfDescribe;
    }

    public String getRfDescribe() {
        return rfDescribe;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    public Long getTradeId() {
        return tradeId;
    }

    public void setRfStatus(Integer rfStatus) {
        this.rfStatus = rfStatus;
    }

    public Integer getRfStatus() {
        return rfStatus;
    }

    public String getRefundNo() {
        return refundNo;
    }

    public void setRefundNo(String refundNo) {
        this.refundNo = refundNo;
    }

    public String getConfirmBy() {
        return confirmBy;
    }

    public void setConfirmBy(String confirmBy) {
        this.confirmBy = confirmBy;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("rfId", getRfId())
                .append("orderId", getOrderId())
                .append("orderNo", getOrderNo())
                .append("memberId", getMemberId())
                .append("orderAmount", getOrderAmount())
                .append("rfReason", getRfReason())
                .append("rfImg", getRfImg())
                .append("rfAmount", getRfAmount())
                .append("rfDescribe", getRfDescribe())
                .append("shopId", getShopId())
                .append("createTime", getCreateTime())
                .append("confirmTime", getConfirmTime())
                .append("updateTime", getUpdateTime())
                .append("tradeId", getTradeId())
                .append("rfStatus", getRfStatus())
                .toString();
    }
}
