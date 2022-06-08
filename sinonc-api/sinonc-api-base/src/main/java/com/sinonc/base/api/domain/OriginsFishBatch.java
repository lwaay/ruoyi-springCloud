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
 * 批次信息（水产）
 * 对象 origins_fish_batch
 *
 * @author ruoyi
 * @date 2021-04-25
 */
public class OriginsFishBatch extends BaseEntity {
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
     * 批次编号
     */
    @Excel(name = "批次编号")
    private String batchNo;

    /**
     * 产品批次
     */
    @Excel(name = "产品批次")
    private String batchName;

    /**
     * 产品类别
     */
    @Excel(name = "产品类别")
    private String cplb;

    /**
     * 产品名称
     */
    @Excel(name = "产品名称")
    private String name;

    /**
     * 鱼塘标识
     */
    @Excel(name = "鱼塘标识")
    private String landBlockId;

    /**
     * 放养品种
     */
    @Excel(name = "放养品种")
    private String fryId;

    /**
     * 收获时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "收获时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date shsj;

    /**
     * 收获方式
     */
    @Excel(name = "收获方式")
    private String shfs;

    /**
     * 录入时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "录入时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inputTime;

    /**
     * 产品质量状况
     */
    @Excel(name = "产品质量状况")
    private String prodQuality;

    /**
     * 批次审核状态
     */
    @Excel(name = "批次审核状态")
    private Integer batchCheckStatu;

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
     * 图片
     */
    @Excel(name = "图片")
    private String batchPic;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remarks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


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

    @JSONField(name = "BATCH_NAME")
    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getBatchName() {
        return batchName;
    }

    @JSONField(name = "CPLB")
    public void setCplb(String cplb) {
        this.cplb = cplb;
    }

    public String getCplb() {
        return cplb;
    }

    @JSONField(name = "NAME")
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @JSONField(name = "LAND_BLOCK_ID")
    public void setLandBlockId(String landBlockId) {
        this.landBlockId = landBlockId;
    }

    public String getLandBlockId() {
        return landBlockId;
    }

    @JSONField(name = "FRY_ID")
    public void setFryId(String fryId) {
        this.fryId = fryId;
    }

    public String getFryId() {
        return fryId;
    }

    @JSONField(name = "SHSJ")
    public void setShsj(Date shsj) {
        this.shsj = shsj;
    }

    public Date getShsj() {
        return shsj;
    }

    @JSONField(name = "SHFS")
    public void setShfs(String shfs) {
        this.shfs = shfs;
    }

    public String getShfs() {
        return shfs;
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

    @JSONField(name = "BATCH_PIC")
    public void setBatchPic(String batchPic) {
        this.batchPic = batchPic;
    }

    public String getBatchPic() {
        return batchPic;
    }

    @JSONField(name = "REMARKS")
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("batchId", getBatchId())
                .append("corpId", getCorpId())
                .append("batchNo", getBatchNo())
                .append("batchName", getBatchName())
                .append("cplb", getCplb())
                .append("name", getName())
                .append("landBlockId", getLandBlockId())
                .append("fryId", getFryId())
                .append("shsj", getShsj())
                .append("shfs", getShfs())
                .append("createBy", getCreateBy())
                .append("inputTime", getInputTime())
                .append("prodQuality", getProdQuality())
                .append("batchCheckStatu", getBatchCheckStatu())
                .append("num", getNum())
                .append("numUnit", getNumUnit())
                .append("saveStatus", getSaveStatus())
                .append("batchPic", getBatchPic())
                .append("remarks", getRemarks())
                .toString();
    }
}
