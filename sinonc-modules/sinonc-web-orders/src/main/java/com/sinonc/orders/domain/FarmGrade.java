package com.sinonc.orders.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * 基地评级表 od_farm_grade
 *
 * @author sinonc
 * @date 2019-11-23
 */

public class FarmGrade {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long gradeId;
    /** 基地ID */
    private Long farmIdP;
    /** 评级种类 */
    private String gradeType;
    /** 评定日期 */
    @NotNull(message="评定日期不能为空！")
    private Date gradeDate;
    /** 评级分值 */
    private BigDecimal gradeValue;
    /** 评级结果 */
    private String gradeResult;
    /** 评定人 */
    @NotEmpty(message="评定人不能为空！")
    private String gradeMan;
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
    /**评级项目明细**/
    private String gradeItemJson;
    /**评级类别集合**/
    private Map gradeCategoryMap;
    /**基地名称**/
    private String farmName;


    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public Map<String,GradeCategory> getGradeCategoryMap() {
        return gradeCategoryMap;
    }

    public void setGradeCategoryMap(Map gradeCategoryMap) {
        this.gradeCategoryMap = gradeCategoryMap;
    }

    public String getGradeItemJson() {
        return gradeItemJson;
    }

    public void setGradeItemJson(String gradeItemJson) {
        this.gradeItemJson = gradeItemJson;
    }

    public void setGradeId(Long gradeId) {
            this.gradeId = gradeId;
    }

    public Long getGradeId() {
            return gradeId;
    }
    public void setFarmIdP(Long farmIdP) {
            this.farmIdP = farmIdP;
    }

    public Long getFarmIdP() {
            return farmIdP;
    }
    public void setGradeType(String gradeType) {
            this.gradeType = gradeType;
    }

    public String getGradeType() {
            return gradeType;
    }
    public void setGradeDate(Date gradeDate) {
            this.gradeDate = gradeDate;
    }

    public Date getGradeDate() {
            return gradeDate;
    }
    public void setGradeValue(BigDecimal gradeValue) {
            this.gradeValue = gradeValue;
    }

    public BigDecimal getGradeValue() {
            return gradeValue;
    }
    public void setGradeResult(String gradeResult) {
            this.gradeResult = gradeResult;
    }

    public String getGradeResult() {
            return gradeResult;
    }
    public void setGradeMan(String gradeMan) {
            this.gradeMan = gradeMan;
    }

    public String getGradeMan() {
            return gradeMan;
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
                .append("gradeId",getGradeId())
                .append("farmIdP",getFarmIdP())
                .append("gradeType",getGradeType())
                .append("gradeDate",getGradeDate())
                .append("gradeValue",getGradeValue())
                .append("gradeResult",getGradeResult())
                .append("gradeMan",getGradeMan())
                .append("remark",getRemark())
                .append("createBy",getCreateBy())
                .append("createTime",getCreateTime())
                .append("updateBy",getUpdateBy())
                .append("updateTime",getUpdateTime())
                .toString();
    }
}
