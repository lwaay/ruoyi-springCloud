package com.sinonc.orders.domain;

import com.sinonc.consume.api.domain.WxUserConsume;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 订单评论表 od_evaluation
 *
 * @author sinonc
 * @date 2019-12-24
 */

public class Evaluation {

    private static final long serialVersionUID = 1L;

    /** 主键id自增 */
    private Long evaluationId;
    /** 商品id */
    private Long goodsId;
    /** 会员id */
    private Long memberId;
    /** 店铺id */
    private Long shopId;
    /** 回复内容 */
    private String replyContent;
    /** 评论内容 */
    private String evaluationContent;
    /** 评论时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
    /** 订单ID */
    private Long orderId;
    /** 商品评分 */
    private Integer goodsStar;
    /** 物流评分 */
    private Integer expressStar;
    /** 订单评论表 */
    private Integer serviceStar;
    /** 评价图片 */
    private String images;

    /**
     * 评论用户信息
     */
    private WxUserConsume wxUserConsume;

    public WxUserConsume getWxUserConsume() {
        return wxUserConsume;
    }

    public void setWxUserConsume(WxUserConsume wxUserConsume) {
        this.wxUserConsume = wxUserConsume;
    }

    public void setEvaluationId(Long evaluationId) {
            this.evaluationId = evaluationId;
    }

    public Long getEvaluationId() {
            return evaluationId;
    }
    public void setGoodsId(Long goodsId) {
            this.goodsId = goodsId;
    }

    public Long getGoodsId() {
            return goodsId;
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
    public void setReplyContent(String replyContent) {
            this.replyContent = replyContent;
    }

    public String getReplyContent() {
            return replyContent;
    }
    public void setEvaluationContent(String evaluationContent) {
            this.evaluationContent = evaluationContent;
    }

    public String getEvaluationContent() {
            return evaluationContent;
    }
    public void setCreateTime(Date createTime) {
            this.createTime = createTime;
    }

    public Date getCreateTime() {
            return createTime;
    }
    public void setUpdateTime(Date updateTime) {
            this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
            return updateTime;
    }
    public void setOrderId(Long orderId) {
            this.orderId = orderId;
    }

    public Long getOrderId() {
            return orderId;
    }
    public void setGoodsStar(Integer goodsStar) {
            this.goodsStar = goodsStar;
    }

    public Integer getGoodsStar() {
            return goodsStar;
    }
    public void setExpressStar(Integer expressStar) {
            this.expressStar = expressStar;
    }

    public Integer getExpressStar() {
            return expressStar;
    }
    public void setServiceStar(Integer serviceStar) {
            this.serviceStar = serviceStar;
    }

    public Integer getServiceStar() {
            return serviceStar;
    }
    public void setImages(String images) {
            this.images = images;
    }

    public String getImages() {
            return images;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("evaluationId",getEvaluationId())
                .append("goodsId",getGoodsId())
                .append("memberId",getMemberId())
                .append("shopId",getShopId())
                .append("replyContent",getReplyContent())
                .append("evaluationContent",getEvaluationContent())
                .append("createTime",getCreateTime())
                .append("updateTime",getUpdateTime())
                .append("orderId",getOrderId())
                .append("goodsStar",getGoodsStar())
                .append("expressStar",getExpressStar())
                .append("serviceStar",getServiceStar())
                .append("images",getImages())
                .toString();
    }
}
