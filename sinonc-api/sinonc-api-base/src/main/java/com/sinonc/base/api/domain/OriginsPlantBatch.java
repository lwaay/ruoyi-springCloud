package com.sinonc.base.api.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 批次信息（种植）
 * 对象 origins_plant_batch
 *
 * @author ruoyi
 * @date 2021-05-06
 */
public class OriginsPlantBatch extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 主键
     */
    private String batchId;

    /**
     * 企业标志
     */
    @Excel(name = "企业标志")
    private String corpId;

    /**
     * 批次码
     */
    @Excel(name = "批次码")
    private String batchNo;

    /**
     * 地块ID
     */
    @Excel(name = "地块ID")
    private String landBlockId;

    /**
     * 录入时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "录入时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inputTime;

    /**
     * 质量安全状况
     */
    @Excel(name = "质量安全状况")
    private String prodQuality;

    /**
     * -1：未上报，0：已上报
     */
    @Excel(name = "-1：未上报，0：已上报")
    private Integer batchCheckStatu;

    /**
     * 产品批次
     */
    @Excel(name = "产品批次")
    private String batchName;

    /**
     * 产品名称
     */
    @Excel(name = "产品名称")
    private String name;

    /**
     * 数量
     */
    @Excel(name = "数量")
    private BigDecimal num;

    /**
     * 数量单位
     */
    @Excel(name = "数量单位")
    private String numUnit;

    /**
     * 质量安全认证种类
     */
    @Excel(name = "质量安全认证种类")
    private String saveStatus;

    /**
     * 是否重庆名牌农产品
     */
    @Excel(name = "是否重庆名牌农产品")
    private Integer isFamous;

    /**
     * 产品类别
     */
    @Excel(name = "产品类别")
    private String cplb;

    @JSONField(name = "BATCH_ID")
    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getBatchId() {
        return batchId;
    }

    @JSONField(name = "CORP_ID")
    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getCorpId() {
        return corpId;
    }

    @JSONField(name = "BATCH_NO")
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getBatchNo() {
        return batchNo;
    }

    @JSONField(name = "LAND_BLOCK_ID")
    public void setLandBlockId(String landBlockId) {
        this.landBlockId = landBlockId;
    }

    public String getLandBlockId() {
        return landBlockId;
    }

    @JSONField(name = "INPUT_TIME")
    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
    }

    public Date getInputTime() {
        return inputTime;
    }

    @JSONField(name = "PROD_QUALITY")
    public void setProdQuality(String prodQuality) {
        this.prodQuality = prodQuality;
    }

    public String getProdQuality() {
        return prodQuality;
    }

    @JSONField(name = "BATCH_CHECK_STATU")
    public void setBatchCheckStatu(Integer batchCheckStatu) {
        this.batchCheckStatu = batchCheckStatu;
    }

    public Integer getBatchCheckStatu() {
        return batchCheckStatu;
    }

    @JSONField(name = "BATCH_NAME")
    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getBatchName() {
        return batchName;
    }

    @JSONField(name = "NAME")
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @JSONField(name = "NUM")
    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public BigDecimal getNum() {
        return num;
    }

    @JSONField(name = "NUM_UNIT")
    public void setNumUnit(String numUnit) {
        this.numUnit = numUnit;
    }

    public String getNumUnit() {
        return numUnit;
    }

    @JSONField(name = "SAVE_STATUS")
    public void setSaveStatus(String saveStatus) {
        this.saveStatus = saveStatus;
    }

    public String getSaveStatus() {
        return saveStatus;
    }

    @JSONField(name = "IS_FAMOUS")
    public void setIsFamous(Integer isFamous) {
        this.isFamous = isFamous;
    }

    public Integer getIsFamous() {
        return isFamous;
    }

    @JSONField(name = "CPLB")
    public void setCplb(String cplb) {
        this.cplb = cplb;
    }

    public String getCplb() {
        return cplb;
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
                .append("batchId", getBatchId())
                .append("corpId", getCorpId())
                .append("batchNo", getBatchNo())
                .append("landBlockId", getLandBlockId())
                .append("inputTime", getInputTime())
                .append("prodQuality", getProdQuality())
                .append("batchCheckStatu", getBatchCheckStatu())
                .append("batchName", getBatchName())
                .append("name", getName())
                .append("num", getNum())
                .append("numUnit", getNumUnit())
                .append("saveStatus", getSaveStatus())
                .append("isFamous", getIsFamous())
                .append("cplb", getCplb())
                .append("remark", getRemark())
                .toString();
    }
}
