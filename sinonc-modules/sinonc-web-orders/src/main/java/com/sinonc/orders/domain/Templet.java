package com.sinonc.orders.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * 认养证书模版表 od_templet
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Alias("Templet")
public class Templet {

    private static final long serialVersionUID = 1L;

    /**  */
    private Long templetId;
    /** 模板名称 */
    private String templetName;
    /** 模板编号 */
    private String templetNo;
    /** 模板图片路径 */
    private String templetUrl;
    /** 创建时间 */
    private Date createTime;
    /** 创建人 */
    private String createBy;
    /**  */
    private String searchDate;
    public void setTempletId(Long templetId) {
            this.templetId = templetId;
    }

    public Long getTempletId() {
            return templetId;
    }
    public void setTempletName(String templetName) {
            this.templetName = templetName;
    }

    public String getTempletName() {
            return templetName;
    }
    public void setTempletNo(String templetNo) {
            this.templetNo = templetNo;
    }

    public String getTempletNo() {
            return templetNo;
    }
    public void setTempletUrl(String templetUrl) {
            this.templetUrl = templetUrl;
    }

    public String getTempletUrl() {
            return templetUrl;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("templetId",getTempletId())
                .append("templetName",getTempletName())
                .append("templetNo",getTempletNo())
                .append("templetUrl",getTempletUrl())
                .append("createTime",getCreateTime())
                .append("createBy",getCreateBy())
                .toString();
    }

    public String getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(String searchDate) {
        this.searchDate = searchDate;
    }
}
