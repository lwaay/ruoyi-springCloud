package com.sinonc.base.api.domain;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 投料信息（生产加工）
 * 对象 origins_pro_feedin
 *
 * @author ruoyi
 * @date 2021-04-25
 */
public class OriginsProFeedin extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 主键
     */
    private String feedinId;

    /**
     * 选择原料来源
     */
    @Excel(name = "选择原料来源")
    private String rawSource;

    /**
     * 选择原料名称
     */
    @Excel(name = "选择原料名称")
    private String rawName;

    /**
     * 生产厂家
     */
    @Excel(name = "生产厂家")
    private String manufacturer;

    /**
     * 录入时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "录入时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inputTime;

    /**
     * 企业ID
     */
    @Excel(name = "企业ID")
    private String corpId;

    @JSONField(name = "FEEDIN_ID")
    public void setFeedinId(String feedinId) {
        this.feedinId = feedinId;
    }

    public String getFeedinId() {
        return feedinId;
    }

    @JSONField(name = "RAW_SOURCE")
    public void setRawSource(String rawSource) {
        this.rawSource = rawSource;
    }

    public String getRawSource() {
        return rawSource;
    }

    @JSONField(name = "RAW_NAME")
    public void setRawName(String rawName) {
        this.rawName = rawName;
    }

    public String getRawName() {
        return rawName;
    }

    @JSONField(name = "MANUFACTURER")
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    @JSONField(name = "INPUT_TIME")
    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
    }

    public Date getInputTime() {
        return inputTime;
    }

    @JSONField(name = "CORP_ID")
    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getCorpId() {
        return corpId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("feedinId", getFeedinId())
                .append("rawSource", getRawSource())
                .append("rawName", getRawName())
                .append("manufacturer", getManufacturer())
                .append("inputTime", getInputTime())
                .append("corpId", getCorpId())
                .append("remark", getRemark())
                .toString();
    }
}
