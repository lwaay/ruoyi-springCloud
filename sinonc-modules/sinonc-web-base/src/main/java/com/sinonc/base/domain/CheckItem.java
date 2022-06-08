package com.sinonc.base.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 检验项目表 base_check_item
 *
 * @author sinonc
 * @date 2019-11-14
 */

public class CheckItem {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long checkItemId;
    /** 检测项目 */
    private String checkItem;
    /** 检测值类型 */
    private String valueType;
    /** 检测结果值 */
    private Double checkValue;
    /** 单位 */
    private String checkUnit;
    /** 检验单ID */
    private Long checkIdP;
    /** 质量评述 */
    private String comment;
    /** 创建时间 */
    private Date createTime;
    /** 创建用户ID */
    private String createBy;
    /** 更新时间 */
    private Date updateTime;
    /** 更新人 用户ID */
    private String updateBy;


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCheckItem() {
        return checkItem;
    }

    public void setCheckItem(String checkItem) {
        this.checkItem = checkItem;
    }

    public void setCheckItemId(Long checkItemId) {
            this.checkItemId = checkItemId;
    }

    public Long getCheckItemId() {
            return checkItemId;
    }
    public void setValueType(String valueType) {
            this.valueType = valueType;
    }

    public String getValueType() {
            return valueType;
    }
    public void setCheckValue(Double checkValue) {
            this.checkValue = checkValue;
    }

    public Double getCheckValue() {
            return checkValue;
    }
    public void setCheckUnit(String checkUnit) {
            this.checkUnit = checkUnit;
    }

    public String getCheckUnit() {
            return checkUnit;
    }
    public void setCheckIdP(Long checkIdP) {
            this.checkIdP = checkIdP;
    }

    public Long getCheckIdP() {
            return checkIdP;
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
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("checkItemId",getCheckItemId())
                .append("checkItem",getCheckItem())
                .append("valueType",getValueType())
                .append("checkValue",getCheckValue())
                .append("checkUnit",getCheckUnit())
                .append("checkIdP",getCheckIdP())
                .append("createTime",getCreateTime())
                .append("createBy",getCreateBy())
                .append("updateTime",getUpdateTime())
                .append("updateBy",getUpdateBy())
            .toString();
        }
}
