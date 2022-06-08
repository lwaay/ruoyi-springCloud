package com.sinonc.orders.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 竞拍活动押金表 od_auction_bond
 *
 * @author sinonc
 * @date 2019-11-19
 */

public class AuctionBond {

    private static final long serialVersionUID = 1L;

    /** ID自增 */
    private Long auctionbondId;
    /** 竞拍活动id */
    private Long auctionId;
    /** 会员id */
    private Long memberId;
    /** 店铺id */
    private Long shopId;
    /** 商品id */
    private Long goodsId;
    /** 订单号 */
    private Long orderId;
    /** 支付押金金额 */
    private BigDecimal price;
    /** 支付时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;
    /** 支付状态 */
    private Integer paymentStatus;
    /** 竞拍活动订单号 */
    private String auctionOrderno;
    /** 第三方订单号 */
    private String otherOrderno;
    /** 退款单号 */
    private String refundNo;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /** 支付类型，微信/支付宝 */
    private Integer payType;
    /** 竞拍获得标识 */
    private Integer win;
    /** 退款金额 */
    private BigDecimal refundPrice;
    /** 退款时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date refundTime;

    public void setAuctionbondId(Long auctionbondId) {
            this.auctionbondId = auctionbondId;
    }

    public Long getAuctionbondId() {
            return auctionbondId;
    }
    public void setAuctionId(Long auctionId) {
            this.auctionId = auctionId;
    }

    public Long getAuctionId() {
            return auctionId;
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
    public void setGoodsId(Long goodsId) {
            this.goodsId = goodsId;
    }

    public Long getGoodsId() {
            return goodsId;
    }
    public void setOrderId(Long orderId) {
            this.orderId = orderId;
    }

    public Long getOrderId() {
            return orderId;
    }
    public void setPrice(BigDecimal price) {
            this.price = price;
    }

    public BigDecimal getPrice() {
            return price;
    }
    public void setPayTime(Date payTime) {
            this.payTime = payTime;
    }

    public Date getPayTime() {
            return payTime;
    }
    public void setPaymentStatus(Integer paymentStatus) {
            this.paymentStatus = paymentStatus;
    }

    public Integer getPaymentStatus() {
            return paymentStatus;
    }
    public void setAuctionOrderno(String auctionOrderno) {
            this.auctionOrderno = auctionOrderno;
    }

    public String getAuctionOrderno() {
            return auctionOrderno;
    }
    public void setOtherOrderno(String otherOrderno) {
            this.otherOrderno = otherOrderno;
    }

    public String getOtherOrderno() {
            return otherOrderno;
    }
    public void setRefundNo(String refundNo) {
            this.refundNo = refundNo;
    }

    public String getRefundNo() {
            return refundNo;
    }
    public void setCreateTime(Date createTime) {
            this.createTime = createTime;
    }

    public Date getCreateTime() {
            return createTime;
    }
    public void setPayType(Integer payType) {
            this.payType = payType;
    }

    public Integer getPayType() {
            return payType;
    }
    public void setWin(Integer win) {
            this.win = win;
    }

    public Integer getWin() {
            return win;
    }
    public void setRefundPrice(BigDecimal refundPrice) {
            this.refundPrice = refundPrice;
    }

    public BigDecimal getRefundPrice() {
            return refundPrice;
    }
    public void setRefundTime(Date refundTime) {
            this.refundTime = refundTime;
    }

    public Date getRefundTime() {
            return refundTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("auctionbondId",getAuctionbondId())
                .append("auctionId",getAuctionId())
                .append("memberId",getMemberId())
                .append("shopId",getShopId())
                .append("goodsId",getGoodsId())
                .append("orderId",getOrderId())
                .append("price",getPrice())
                .append("payTime",getPayTime())
                .append("paymentStatus",getPaymentStatus())
                .append("auctionOrderno",getAuctionOrderno())
                .append("otherOrderno",getOtherOrderno())
                .append("refundNo",getRefundNo())
                .append("createTime",getCreateTime())
                .append("payType",getPayType())
                .append("win",getWin())
                .append("refundPrice",getRefundPrice())
                .append("refundTime",getRefundTime())
                .toString();
    }
}
