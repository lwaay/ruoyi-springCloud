package com.sinonc.orders.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinonc.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 优惠券表 od_coupon
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Alias("Coupon")
public class Coupon extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**  */
    @ApiModelProperty(value = "主键")
    private Long couponId;
    /** 优惠券名称 */
    @ApiModelProperty(value = "优惠券名称")
    private String couponName;
    /** 创建者id */
    @ApiModelProperty(value = "创建者")
    private String createBy;
    /** 优惠券创建时间 */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /** 优惠券生效时间 */
    @ApiModelProperty(value = "生效时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    /** 优惠券失效时间 */
    @ApiModelProperty(value = "失效时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date invalidTime;
    /**  */
    private String updateBy;
    /**  */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /** 优惠券金额 */
    @ApiModelProperty(value = "优惠金额")
    private BigDecimal couponAmount;
    /** 最低使用价格 */
    @ApiModelProperty(value = "最低使用价格")
    private BigDecimal minimumPrice;
    /** 优惠券类型。0，店铺通用券；1，店铺指定商品券；2，平台通用券；3，平台指定商品券 */
    @ApiModelProperty(value = "优惠券类型：0，店铺通用券；1，店铺指定商品券；2，平台通用券；3，平台指定商品券")
    private Integer couponType;
    /** 优惠券说明 */
    @ApiModelProperty(value = "优惠券说明")
    private String explain;
    /** 能够使用券的指定商品列表（json数组格式存储），若为全品类，则为空 */
    @ApiModelProperty(value = "商品列表")
    private String goodsList;
    /** 优惠券总数量 */
    @ApiModelProperty(value = "优惠券总数量")
    private Integer number;
    /** 优惠券所属店铺，若为平台券，则值为0 */
    @ApiModelProperty(value = "所属店铺")
    private Long shopIdP;
    /** 标记删除，0，存在，1，删除 */
    private Integer delFlag;
    /** 剩余数量*/
    @ApiModelProperty(value = "优惠券剩余数量")
    private Integer surplus;

    //商品卷关联商品
    private List<Goods> linkGoods;

    public void setCouponId(Long couponId) {
            this.couponId = couponId;
    }

    public Long getCouponId() {
            return couponId;
    }
    public void setCouponName(String couponName) {
            this.couponName = couponName;
    }

    public String getCouponName() {
            return couponName;
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
    public void setStartTime(Date startTime) {
            this.startTime = startTime;
    }

    public Date getStartTime() {
            return startTime;
    }

    public Date getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(Date invalidTime) {
        this.invalidTime = invalidTime;
    }

    public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
    }

    public String getUpdateBy() {
            return updateBy;
    }
    public void setUpdateTime(Date updateTime) {
            this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
            return updateTime;
    }
    public void setCouponAmount(BigDecimal couponAmount) {
            this.couponAmount = couponAmount;
    }

    public BigDecimal getCouponAmount() {
            return couponAmount;
    }
    public void setMinimumPrice(BigDecimal minimumPrice) {
            this.minimumPrice = minimumPrice;
    }

    public BigDecimal getMinimumPrice() {
            return minimumPrice;
    }
    public void setCouponType(Integer couponType) {
            this.couponType = couponType;
    }

    public Integer getCouponType() {
            return couponType;
    }
    public void setExplain(String explain) {
            this.explain = explain;
    }

    public String getExplain() {
            return explain;
    }
    public void setGoodsList(String goodsList) {
            this.goodsList = goodsList;
    }

    public String getGoodsList() {
            return goodsList;
    }
    public void setNumber(Integer number) {
            this.number = number;
    }

    public Integer getNumber() {
            return number;
    }
    public void setShopIdP(Long shopIdP) {
            this.shopIdP = shopIdP;
    }

    public Long getShopIdP() {
            return shopIdP;
    }
    public void setDelFlag(Integer delFlag) {
            this.delFlag = delFlag;
    }

    public Integer getDelFlag() {
            return delFlag;
    }

    public Integer getSurplus() {
        return surplus;
    }

    public void setSurplus(Integer surplus) {
        this.surplus = surplus;
    }

    public List<Goods> getLinkGoods() {
        return linkGoods;
    }

    public void setLinkGoods(List<Goods> linkGoods) {
        this.linkGoods = linkGoods;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("couponId",getCouponId())
                .append("couponName",getCouponName())
                .append("createBy",getCreateBy())
                .append("createTime",getCreateTime())
                .append("startTime",getStartTime())
                .append("endTime",getEndTime())
                .append("updateBy",getUpdateBy())
                .append("updateTime",getUpdateTime())
                .append("couponAmount",getCouponAmount())
                .append("minimumPrice",getMinimumPrice())
                .append("couponType",getCouponType())
                .append("explain",getExplain())
                .append("goodsList",getGoodsList())
                .append("number",getNumber())
                .append("shopIdP",getShopIdP())
                .append("delFlag",getDelFlag())
                .toString();
    }
}
