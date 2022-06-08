package com.sinonc.system.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 农业经营主体证件信息 对象 business_cert
 *
 * @author ruoyi
 * @date 2022-02-16
 */
public class BusinessCert extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long certId;

    /**
     * 主体信息ID
     */
    @Excel(name = "主体信息ID ")
    private Long entityId;

    /**
     * 主体名称
     */
    @Excel(name = "主体名称")
    private String entityName;

    /**
     * 证件名称
     */
    @Excel(name = "证件名称")
    private String certName;

    /**
     * 证件图片地址
     */
    @Excel(name = "证件图片地址 ")
    private String certPicurl;

    /**
     * 上传时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上传时间 ", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uploadTime;

    /**
     * 上传人会员ID
     */
    @Excel(name = "上传人会员ID ")
    private Long uploadMemberid;

    public void setCertId(Long certId) {
        this.certId = certId;
    }

    public Long getCertId() {
        return certId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setCertName(String certName) {
        this.certName = certName;
    }

    public String getCertName() {
        return certName;
    }

    public void setCertPicurl(String certPicurl) {
        this.certPicurl = certPicurl;
    }

    public String getCertPicurl() {
        return certPicurl;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadMemberid(Long uploadMemberid) {
        this.uploadMemberid = uploadMemberid;
    }

    public Long getUploadMemberid() {
        return uploadMemberid;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("certId", getCertId())
                .append("entityId", getEntityId())
                .append("certName", getCertName())
                .append("certPicurl", getCertPicurl())
                .append("uploadTime", getUploadTime())
                .append("uploadMemberid", getUploadMemberid())
                .toString();
    }
}
