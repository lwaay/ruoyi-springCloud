package com.sinonc.orders.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 评级详细项目表 od_category_item
 *
 * @author sinonc
 * @date 2019-11-23
 */

public class CategoryItem {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long itemId;
    /**评级类别ID**/
    private Long categoryIdP;
    /** 项目名称 */
    private String itemName;
    /** 评定说明 */
    private String caption;
    /** 分值 */
    private BigDecimal itemValue;
    /** 类别百分占比 */
    private BigDecimal itemPercent;
    /** 综述 */
    private String sumUp;
    /** 备注 */
    private String remark;
    /** 创建人 */
    private String createBy;
    /** 创建时间 */
    private Date createTime;
    /** 更新人 */
    private String updateBy;
    /** 更新时间 */
    private Date updateTime;
    /** //评级类别，土壤评级/果树评级/认证荣誉评级/服务评级/生产过程评级/果品评级/环境评级 */
    private String categoryName;


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getCategoryIdP() {
        return categoryIdP;
    }

    public void setCategoryIdP(Long categoryIdP) {
        this.categoryIdP = categoryIdP;
    }

    public void setItemId(Long itemId) {
            this.itemId = itemId;
    }

    public Long getItemId() {
            return itemId;
    }
    public void setItemName(String itemName) {
            this.itemName = itemName;
    }

    public String getItemName() {
            return itemName;
    }
    public void setCaption(String caption) {
            this.caption = caption;
    }

    public String getCaption() {
            return caption;
    }
    public void setItemValue(BigDecimal itemValue) {
            this.itemValue = itemValue;
    }

    public BigDecimal getItemValue() {
            return itemValue;
    }
    public void setItemPercent(BigDecimal itemPercent) {
            this.itemPercent = itemPercent;
    }

    public BigDecimal getItemPercent() {
            return itemPercent;
    }
    public void setSumUp(String sumUp) {
            this.sumUp = sumUp;
    }

    public String getSumUp() {
            return sumUp;
    }
    public void setRemark(String remark) {
            this.remark = remark;
    }

    public String getRemark() {
            return remark;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("itemId",getItemId())
                .append("itemName",getItemName())
                .append("caption",getCaption())
                .append("itemValue",getItemValue())
                .append("itemPercent",getItemPercent())
                .append("sumUp",getSumUp())
                .append("remark",getRemark())
                .append("createBy",getCreateBy())
                .append("createTime",getCreateTime())
                .append("updateBy",getUpdateBy())
                .append("updateTime",getUpdateTime())
                .toString();
    }
}
