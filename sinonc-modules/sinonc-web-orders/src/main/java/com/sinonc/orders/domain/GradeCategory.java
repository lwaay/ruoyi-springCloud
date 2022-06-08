package com.sinonc.orders.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 评级类别表 od_grade_category
 *
 * @author sinonc
 * @date 2019-11-23
 */

public class GradeCategory {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long categoryId;
    /** 评级ID */
    private Long gradeIdP;
    /** //评级类别，土壤评级/果树评级/认证荣誉评级/服务评级/生产过程评级/果品评级/环境评级 */
    private String categoryName;
    /** 分值 */
    private BigDecimal categoryValue;
    /** 评定结果 */
    private String categoryResult;
    /** 基地百分占比 */
    private BigDecimal categoryPercent;
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
    /**评级详细项目列表**/
    private List categoryItemList;


    public List getCategoryItemList() {
        return categoryItemList;
    }

    public void setCategoryItemList(List categoryItemList) {
        this.categoryItemList = categoryItemList;
    }

    public void setCategoryId(Long categoryId) {
            this.categoryId = categoryId;
    }

    public Long getCategoryId() {
            return categoryId;
    }
    public void setGradeIdP(Long gradeIdP) {
            this.gradeIdP = gradeIdP;
    }

    public Long getGradeIdP() {
            return gradeIdP;
    }
    public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
    }

    public String getCategoryName() {
            return categoryName;
    }
    public void setCategoryValue(BigDecimal categoryValue) {
            this.categoryValue = categoryValue;
    }

    public BigDecimal getCategoryValue() {
            return categoryValue;
    }
    public void setCategoryResult(String categoryResult) {
            this.categoryResult = categoryResult;
    }

    public String getCategoryResult() {
            return categoryResult;
    }
    public void setCategoryPercent(BigDecimal categoryPercent) {
            this.categoryPercent = categoryPercent;
    }

    public BigDecimal getCategoryPercent() {
            return categoryPercent;
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
                .append("categoryId",getCategoryId())
                .append("gradeIdP",getGradeIdP())
                .append("categoryName",getCategoryName())
                .append("categoryValue",getCategoryValue())
                .append("categoryResult",getCategoryResult())
                .append("categoryPercent",getCategoryPercent())
                .append("remark",getRemark())
                .append("createBy",getCreateBy())
                .append("createTime",getCreateTime())
                .append("updateBy",getUpdateBy())
                .append("updateTime",getUpdateTime())
                .toString();
    }
}
