package com.sinonc.orders.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商家改价记录表 od_shop_discount_log
 *
 * @author sinonc
 * @date 2019-11-20
 */

public class ShopDiscountLog {

    private static final long serialVersionUID = 1L;

    /**  */
    private Long logId;
    /**  */
    private Long memberId;
    /**  */
    private Long shopId;
    /** 订单ID */
    private Long orderId;
    /** 订单号 */
    private String orderNo;
    /** 订单原价 */
    private BigDecimal originalPrice;
    /** 订单现价 */
    private BigDecimal presentPrice;
    /** 优惠金额 */
    private BigDecimal discountPrice;
    /**  */
    private String createBy;
    /**  */
    private Date createTime;

    public void setLogId(Long logId) {
            this.logId = logId;
    }

    public Long getLogId() {
            return logId;
    }
    public void setMemberId(Long memberId) {
            this.memberId = memberId;
    }

    public Long getMemberId() {
            return memberId;
    }
    public void setShopId(Long shopId) {
            this.shopId = shopId;
    }

    public Long getShopId() {
            return shopId;
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
    public void setOriginalPrice(BigDecimal originalPrice) {
            this.originalPrice = originalPrice;
    }

    public BigDecimal getOriginalPrice() {
            return originalPrice;
    }
    public void setPresentPrice(BigDecimal presentPrice) {
            this.presentPrice = presentPrice;
    }

    public BigDecimal getPresentPrice() {
            return presentPrice;
    }
    public void setDiscountPrice(BigDecimal discountPrice) {
            this.discountPrice = discountPrice;
    }

    public BigDecimal getDiscountPrice() {
            return discountPrice;
    }
    public void setCreateBy(String createBy) {
            this.createBy = createBy;
    }

    public String getCreateBy() {
            return createBy;
    }
    public void setCreateTime(Date createTime) {
            this.createTime = createTime;
    }

    public Date getCreateTime() {
            return createTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("logId",getLogId())
                .append("memberId",getMemberId())
                .append("shopId",getShopId())
                .append("orderId",getOrderId())
                .append("orderNo",getOrderNo())
                .append("originalPrice",getOriginalPrice())
                .append("presentPrice",getPresentPrice())
                .append("discountPrice",getDiscountPrice())
                .append("createBy",getCreateBy())
                .append("createTime",getCreateTime())
                .toString();
    }
}
