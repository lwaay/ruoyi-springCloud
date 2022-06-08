package com.sinonc.agriculture.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 信丰脐橙APP版本表 od_app_version
 *
 * @author sinonc
 * @date 2019-11-22
 */

public class AppVersion {

    private static final long serialVersionUID = 1L;

    /** id */
    private Long versionId;
    /** 类别*/
    private Integer type;
    /** 版本号 */
    private String version;
    /** 说明 */
    private String remark;
    /** 安装包下载链接 */
    private String downloadUrl;
    /**下载页地址*/
    private String downloadPage;
    /**  */
    private Date createTime;
    /**  */
    private String createBy;
    /**  */
    private Date updateTime;
    /**  */
    private String updateBy;
    /** */
    private String searchDate;

    public void setVersionId(Long versionId) {
            this.versionId = versionId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getVersionId() {
            return versionId;
    }
    public void setVersion(String version) {
            this.version = version;
    }

    public String getVersion() {
            return version;
    }
    public void setRemark(String remark) {
            this.remark = remark;
    }

    public String getRemark() {
            return remark;
    }
    public void setDownloadUrl(String downloadUrl) {
            this.downloadUrl = downloadUrl;
    }

    public String getDownloadUrl() {
            return downloadUrl;
    }

    public String getDownloadPage() {
        return downloadPage;
    }

    public void setDownloadPage(String downloadPage) {
        this.downloadPage = downloadPage;
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
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("versionId",getVersionId())
                .append("version",getVersion())
                .append("remark",getRemark())
                .append("downloadUrl",getDownloadUrl())
                .append("createTime",getCreateTime())
                .append("createBy",getCreateBy())
                .append("updateTime",getUpdateTime())
                .append("updateBy",getUpdateBy())
            .toString();
        }

    public String getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(String searchDate) {
        this.searchDate = searchDate;
    }
}
