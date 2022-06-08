package com.sinonc.system.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 农业经营主体基础信息对象 business_entity
 *
 * @author ruoyi
 * @date 2022-02-16
 */
    public class BusinessEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long entityId;

    /**
     * 主体名称
     */
    @Excel(name = "主体名称")
    private String entityName;

    /**
     * 主体类型（多选,隔开，生产商、加工商、贸易商、服务商，数据字典）
     */
    @Excel(name = "主体类型", readConverterExp = "多=选,隔开，生产商、加工商、贸易商、服务商，数据字典")
    private String businessMaintype;

    /**
     * 主体类别 （家庭农场、专业合作社、农业企业）
     */
    @Excel(name = "主体类别 ", readConverterExp = "家=庭农场、专业合作社、农业企业")
    private String businessType;

    /**
     * 联系地址
     */
    @Excel(name = "联系地址")
    private String contactAddress;

    /**
     * 负责人
     */
    @Excel(name = "负责人 ")
    private String principalMan;

    /**
     * 负责人电话
     */
    @Excel(name = "负责人电话")
    private String principalPhone;

    /**
     * 经营类别（多选,隔开，用工服务、农资服务、农机租赁，数据字典）
     */
    @Excel(name = "经营类别", readConverterExp = "多=选,隔开，用工服务、农资服务、农机租赁，数据字典")
    private String businessScope;

    /**
     * 证件信息ID（,隔开，多个）
     */
    @Excel(name = "证件信息ID", readConverterExp = ",=隔开，多个")
    private String busiCertids;

    /**
     * 注册时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "注册时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date registerTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modTime;

    /**
     * 是否显示（0-显示，1-不显示）
     */
    private String status;

    /**
     * 注册人会员ID
     */
    @Excel(name = "注册人会员ID")
    private Long modMemberid;

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setBusinessMaintype(String businessMaintype) {
        this.businessMaintype = businessMaintype;
    }

    public String getBusinessMaintype() {
        return businessMaintype;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setPrincipalMan(String principalMan) {
        this.principalMan = principalMan;
    }

    public String getPrincipalMan() {
        return principalMan;
    }

    public void setPrincipalPhone(String principalPhone) {
        this.principalPhone = principalPhone;
    }

    public String getPrincipalPhone() {
        return principalPhone;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusiCertids(String busiCertids) {
        this.busiCertids = busiCertids;
    }

    public String getBusiCertids() {
        return busiCertids;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setModTime(Date modTime) {
        this.modTime = modTime;
    }

    public Date getModTime() {
        return modTime;
    }

    public void setModMemberid(Long modMemberid) {
        this.modMemberid = modMemberid;
    }

    public Long getModMemberid() {
        return modMemberid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("entityId", getEntityId())
                .append("entityName", getEntityName())
                .append("businessMaintype", getBusinessMaintype())
                .append("businessType", getBusinessType())
                .append("contactAddress", getContactAddress())
                .append("principalMan", getPrincipalMan())
                .append("principalPhone", getPrincipalPhone())
                .append("businessScope", getBusinessScope())
                .append("busiCertids", getBusiCertids())
                .append("registerTime", getRegisterTime())
                .append("modTime", getModTime())
                .append("modMemberid", getModMemberid())
                .append("status", getStatus())
                .toString();
    }
}
