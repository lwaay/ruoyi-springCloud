package com.sinonc.orders.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 竞拍记录表 od_auctionmember
 *
 * @author sinonc
 * @date 2019-11-12
 */

public class Auctionmember {

    private static final long serialVersionUID = 1L;

    /** id自增 */
    private Long auctionmemberId;
    /** 用户id */
    private Long memberId;
    /** 竞拍商品id */
    private Long goodsId;
    /** 用户出价值 */
    private BigDecimal auctionPrice;
    /** 用户出价时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /** 累计价格 */
    private BigDecimal nowPrice;
    /** 是否结束 */
    private Integer isEnd;
    /**  */
    private String searchDate;
    private String memberName;
    private String goodName;
    public BigDecimal getNowPrice() {
        return nowPrice;
    }

    public void setNowPrice(BigDecimal nowPrice) {
        this.nowPrice = nowPrice;
    }

    public Integer getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(Integer isEnd) {
        this.isEnd = isEnd;
    }

    public void setAuctionmemberId(Long auctionmemberId) {
            this.auctionmemberId = auctionmemberId;
    }

    public Long getAuctionmemberId() {
            return auctionmemberId;
    }
    public void setMemberId(Long memberId) {
            this.memberId = memberId;
    }

    public Long getMemberId() {
            return memberId;
    }
    public void setGoodsId(Long goodsId) {
            this.goodsId = goodsId;
    }

    public Long getGoodsId() {
            return goodsId;
    }
    public void setAuctionPrice(BigDecimal auctionPrice) {
            this.auctionPrice = auctionPrice;
    }

    public BigDecimal getAuctionPrice() {
            return auctionPrice;
    }
    public void setCreateTime(Date createTime) {
            this.createTime = createTime;
    }

    public Date getCreateTime() {
            return createTime;
    }

    @Override
    public String toString() {
        return "Auctionmember{" +
                "auctionmemberId=" + auctionmemberId +
                ", memberId=" + memberId +
                ", goodsId=" + goodsId +
                ", auctionPrice=" + auctionPrice +
                ", createTime=" + createTime +
                ", nowPrice=" + nowPrice +
                ", isEnd=" + isEnd +
                '}';
    }

    public String getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(String searchDate) {
        this.searchDate = searchDate;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }
}
