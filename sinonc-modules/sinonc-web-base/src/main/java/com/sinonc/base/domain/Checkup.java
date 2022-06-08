package com.sinonc.base.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinonc.common.core.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 土壤、果蔬检测表 base_checkup
 *
 * @author sinonc
 * @date 2019-11-14
 */

public class Checkup {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long checkId;
    /** 检验类别 */
    private String checkType;
    /** 检验单号 */
    private String checkCode;
    /** 基地编码 */
    private String areaCode;
    /** 检验单位 */
    private String company;
    /** 样品数 */
    private Long sampNum;
    /** 检验时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "检验时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkDate;
    /** 到期日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "到期日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dueDate;
    /** 检验综述 */
    private String sumUp;
    /** 创建时间 */
    private Date createTime;
    /** 创建用户ID */
    private String createBy;
    /** 更新时间 */
    private Date updateTime;
    /** 更新人 用户ID */
    private String updateBy;
    /**检测项目json格式数据 */
    private String checkItemJson;
    /**检测项目ID集合 */
    private String checkItemIds;
    /**最近年份 */
    private String latelyYear;
    /**基地名称**/
    private String farmName;

    private Long farmId;


    public Long getFarmId() {
        return farmId;
    }

    public void setFarmId(Long farmId) {
        this.farmId = farmId;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getLatelyYear() {
        return latelyYear;
    }

    public void setLatelyYear(String latelyYear) {
        this.latelyYear = latelyYear;
    }

    public String getCheckItemIds() {
        return checkItemIds;
    }

    public void setCheckItemIds(String checkItemIds) {
        this.checkItemIds = checkItemIds;
    }

    public String getCheckItemJson() {
        return checkItemJson;
    }

    public void setCheckItemJson(String checkItemJson) {
        this.checkItemJson = checkItemJson;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public void setCheckId(Long checkId) {
            this.checkId = checkId;
    }

    public Long getCheckId() {
            return checkId;
    }
    public void setCheckType(String checkType) {
            this.checkType = checkType;
    }

    public String getCheckType() {
            return checkType;
    }
    public void setAreaCode(String areaCode) {
            this.areaCode = areaCode;
    }

    public String getAreaCode() {
            return areaCode;
    }
    public void setCompany(String company) {
            this.company = company;
    }

    public String getCompany() {
            return company;
    }
    public void setSampNum(Long sampNum) {
            this.sampNum = sampNum;
    }

    public Long getSampNum() {
            return sampNum;
    }
    public void setCheckDate(Date checkDate) {
            this.checkDate = checkDate;
    }

    public Date getCheckDate() {
            return checkDate;
    }
    public void setDueDate(Date dueDate) {
            this.dueDate = dueDate;
    }

    public Date getDueDate() {
            return dueDate;
    }
    public void setSumUp(String sumUp) {
            this.sumUp = sumUp;
    }

    public String getSumUp() {
            return sumUp;
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

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("checkId",getCheckId())
                .append("checkType",getCheckType())
                .append("areaCode",getAreaCode())
                .append("company",getCompany())
                .append("sampNum",getSampNum())
                .append("checkDate",getCheckDate())
                .append("dueDate",getDueDate())
                .append("sumUp",getSumUp())
                .append("createTime",getCreateTime())
                .append("createBy",getCreateBy())
                .append("updateTime",getUpdateTime())
                .append("updateBy",getUpdateBy())
            .toString();
        }
}
