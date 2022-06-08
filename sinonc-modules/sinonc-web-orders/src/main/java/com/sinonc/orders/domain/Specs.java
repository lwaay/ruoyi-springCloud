package com.sinonc.orders.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 规格表 od_specs
 *
 * @author sinonc
 * @date 2019-07-26
 */
@Alias("Specs")
public class Specs {

    private static final long serialVersionUID = 1L;

    /** 主键id自增 */
    private Long specsId;
    /** 规格名称 */
    private String specsName;
    /** 用户id */
    private String createBy;
    /** 创建时间 */
    private Date createTime;
    /**  */
    private String updateBy;
    /** 修改时间 */
    private Date updateTime;
    /** 规格价钱 */
    private BigDecimal specsPrice;
    /** 库存 */
    private Integer stock;
    /**店铺ID*/
    private Long shopId;
    /**最小购买数量*/
    private Integer minCount;
    /**最大购买数量*/
    private Integer maxCount;
    /**规格单位*/
    private String unit;
    /**规格图片*/
    private String images;
    /**规格定金*/
    private BigDecimal earnest;
    /**删除标记**/
    private String delFlag;
    /**每份重量**/
    private BigDecimal perWeight;
    /**运费模板ID**/
    private Long fareIdP;

    public Long getFareIdP() {
        return fareIdP;
    }

    public void setFareIdP(Long fareIdP) {
        this.fareIdP = fareIdP;
    }

    public BigDecimal getPerWeight() {
        return perWeight;
    }

    public void setPerWeight(BigDecimal perWeight) {
        this.perWeight = perWeight;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    private Map<String, String> params;

    public Map<String, String> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public void setSpecsId(Long specsId) {
            this.specsId = specsId;
    }

    public Long getSpecsId() {
            return specsId;
    }
    public void setSpecsName(String specsName) {
            this.specsName = specsName;
    }

    public String getSpecsName() {
            return specsName;
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
    public void setSpecsPrice(BigDecimal specsPrice) {
            this.specsPrice = specsPrice;
    }

    public BigDecimal getSpecsPrice() {
            return specsPrice;
    }
    public void setStock(Integer stock) {
            this.stock = stock;
    }

    public Integer getStock() {
            return stock;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Integer getMinCount() {
        return minCount;
    }

    public void setMinCount(Integer minCount) {
        this.minCount = minCount;
    }

    public Integer getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(Integer maxCount) {
        this.maxCount = maxCount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public BigDecimal getEarnest() {
        return earnest;
    }

    public void setEarnest(BigDecimal earnest) {
        this.earnest = earnest;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("specsId",getSpecsId())
                .append("specsName",getSpecsName())
                .append("createBy",getCreateBy())
                .append("createTime",getCreateTime())
                .append("updateBy",getUpdateBy())
                .append("updateTime",getUpdateTime())
                .append("specsPrice",getSpecsPrice())
                .append("stock",getStock())
                .append("fareIdP",getFareIdP())
                .toString();
    }
}
