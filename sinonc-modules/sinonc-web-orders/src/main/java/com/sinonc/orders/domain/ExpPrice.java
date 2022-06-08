package com.sinonc.orders.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 物流价格模版表 od_exp_price
 *
 * @author sinonc
 * @date 2019-11-26
 */

public class ExpPrice {

    private static final long serialVersionUID = 1L;

    /** id */
    private Long expPriceId;
    /** 省份名称 */
    private String province;
    /** 邮费 */
    private BigDecimal expPrice;
    /**  */
    private Integer minCount;
    /**  */
    private Integer maxCount;
    /** 店铺ID */
    private Integer shopIdP;
    /** 商品id */
    private Long goodsIdP;
    /**  */
    private Date createTime;
    /**  */
    private String createBy;
    /**  */
    private Date updateTime;
    /**  */
    private String updateBy;

    public void setExpPriceId(Long expPriceId) {
            this.expPriceId = expPriceId;
    }

    public Long getExpPriceId() {
            return expPriceId;
    }
    public void setProvince(String province) {
            this.province = province;
    }

    public String getProvince() {
            return province;
    }
    public void setExpPrice(BigDecimal expPrice) {
            this.expPrice = expPrice;
    }

    public BigDecimal getExpPrice() {
            return expPrice;
    }
    public void setMinCount(Integer minCount) {
            this.minCount = minCount;
    }

    public Integer getMinCount() {
            return minCount;
    }
    public void setMaxCount(Integer maxCount) {
            this.maxCount = maxCount;
    }

    public Integer getMaxCount() {
            return maxCount;
    }
    public void setShopIdP(Integer shopIdP) {
            this.shopIdP = shopIdP;
    }

    public Integer getShopIdP() {
            return shopIdP;
    }
    public void setGoodsIdP(Long goodsIdP) {
            this.goodsIdP = goodsIdP;
    }

    public Long getGoodsIdP() {
            return goodsIdP;
    }
    public void setCreateTime(Date createTime) {
            this.createTime = createTime;
    }

    public Date getCreateTime() {
            return createTime;
    }
    public void setCreateBy(String createBy) {
            this.createBy = createBy;
    }

    public String getCreateBy() {
            return createBy;
    }
    public void setUpdateTime(Date updateTime) {
            this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
            return updateTime;
    }
    public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
    }

    public String getUpdateBy() {
            return updateBy;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("expPriceId",getExpPriceId())
                .append("province",getProvince())
                .append("expPrice",getExpPrice())
                .append("minCount",getMinCount())
                .append("maxCount",getMaxCount())
                .append("shopIdP",getShopIdP())
                .append("goodsIdP",getGoodsIdP())
                .append("createTime",getCreateTime())
                .append("createBy",getCreateBy())
                .append("updateTime",getUpdateTime())
                .append("updateBy",getUpdateBy())
                .toString();
    }
}
