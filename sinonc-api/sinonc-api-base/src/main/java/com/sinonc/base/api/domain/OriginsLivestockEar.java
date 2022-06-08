package com.sinonc.base.api.domain;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 圈舍与耳标信息（畜类）
 * 对象 origins_livestock_ear
 *
 * @author ruoyi
 * @date 2021-04-25
 */
public class OriginsLivestockEar extends BaseEntity {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private Long earId;
    /**
     * 主键
     */
    private String id;

    /**
     * 耳标号
     */
    @Excel(name = "耳标号")
    private String earTag;

    /**
     * 圈舍号
     */
    @Excel(name = "圈舍号")
    private String houseId;

    /**
     * 企业ID
     */
    @Excel(name = "企业ID")
    private String corpId;

    /**
     * 记录日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date adddatetime;

    /**
     * 产品质量状况
     */
    @Excel(name = "产品质量状况")
    private String prodquality;

    /**
     * 批次码
     */
    @Excel(name = "批次码")
    private String batchId;

    @JSONField(name = "ID")
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @JSONField(name = "EAR_TAG")
    public void setEarTag(String earTag) {
        this.earTag = earTag;
    }

    public String getEarTag() {
        return earTag;
    }

    @JSONField(name = "HOUSE_ID")
    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getHouseId() {
        return houseId;
    }

    @JSONField(name = "CORP_ID")
    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getCorpId() {
        return corpId;
    }

    @JSONField(name = "ADDDATETIME")
    public void setAdddatetime(Date adddatetime) {
        this.adddatetime = adddatetime;
    }

    public Date getAdddatetime() {
        return adddatetime;
    }

    @JSONField(name = "PRODQUALITY")
    public void setProdquality(String prodquality) {
        this.prodquality = prodquality;
    }

    public String getProdquality() {
        return prodquality;
    }

    @JSONField(name = "BATCH_ID")
    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getBatchId() {
        return batchId;
    }


    public Long getEarId() {
        return earId;
    }

    public void setEarId(Long earId) {
        this.earId = earId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("earTag", getEarTag())
                .append("houseId", getHouseId())
                .append("corpId", getCorpId())
                .append("adddatetime", getAdddatetime())
                .append("prodquality", getProdquality())
                .append("batchId", getBatchId())
                .toString();
    }
}
