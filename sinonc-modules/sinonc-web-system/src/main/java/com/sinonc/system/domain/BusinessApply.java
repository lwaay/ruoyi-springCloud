package com.sinonc.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 主体类型申请对象 business_apply
 *
 * @author ruoyi
 * @date 2022-03-01
 */
public class BusinessApply extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long applyId;

    /**
     * 主体基础信息表主键
     */
    @Excel(name = "主体基础信息表主键")
    private Long entityId;

    /**
     * 各生产商的资料
     */
    @Excel(name = "各生产商的资料")
    private Long companyId;

    /**
     * 证件信息表主键（多个,隔开）
     */
    @Excel(name = "证件信息表主键", readConverterExp = "多=个,隔开")
    private String certIds;

    /**
     * 审核状态  0待审核  1审核通过
     */
    @Excel(name = "审核状态  0待审核  1审核通过")
    private String auditStatus;

    /**
     * 审核时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date auditTime;

    /**
     * 审核人系统用户ID
     */
    @Excel(name = "审核人系统用户ID")
    private Long auditSystemuserid;

    /**
     * 主体类型(1种植  2加工  3经销  4服务)
     */
    @Excel(name = "主体类型(1种植  2加工  3经销  4服务)")
    private String businessMaintype;

    @ApiModelProperty(value = "当前页")
    private Integer pageNum;

    @ApiModelProperty(value = "页大小")
    private Integer pageSize;


    @Override
    public Integer getPageNum() {
        return pageNum;
    }

    @Override
    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public Integer getPageSize() {
        return pageSize;
    }

    @Override
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public Long getApplyId() {
        return applyId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCertIds(String certIds) {
        this.certIds = certIds;
    }

    public String getCertIds() {
        return certIds;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditSystemuserid(Long auditSystemuserid) {
        this.auditSystemuserid = auditSystemuserid;
    }

    public Long getAuditSystemuserid() {
        return auditSystemuserid;
    }

    public void setBusinessMaintype(String businessMaintype) {
        this.businessMaintype = businessMaintype;
    }

    public String getBusinessMaintype() {
        return businessMaintype;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("applyId", getApplyId())
                .append("entityId", getEntityId())
                .append("companyId", getCompanyId())
                .append("certIds", getCertIds())
                .append("auditStatus", getAuditStatus())
                .append("auditTime", getAuditTime())
                .append("auditSystemuserid", getAuditSystemuserid())
                .append("businessMaintype", getBusinessMaintype())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
