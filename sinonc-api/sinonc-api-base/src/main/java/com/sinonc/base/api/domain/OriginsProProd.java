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
 * 产品信息（生产加工）
 * 对象 origins_pro_prod
 *
 * @author ruoyi
 * @date 2021-04-25
 */
public class OriginsProProd extends BaseEntity {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private Long id;

    /**
     * 产品信息标识
     */
    private String prodId;

    /**
     * 批次码
     */
    @Excel(name = "批次码")
    private String batchNo;

    /**
     * 产品批次名称
     */
    @Excel(name = "产品批次名称")
    private String batchName;

    /**
     * 产品名称
     */
    @Excel(name = "产品名称")
    private String productName;

    /**
     * 产品类别
     */
    @Excel(name = "产品类别")
    private String cplb;

    /**
     * 原料（投料）
     */
    @Excel(name = "原料", readConverterExp = "投=料")
    private String feedinId;

    /**
     * 食品添加剂
     */
    @Excel(name = "食品添加剂")
    private String sptjj;

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
     * 质量安全状况
     */
    @Excel(name = "质量安全状况")
    private String prodQuality;

    /**
     * 生产日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生产日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date prodDate;

    /**
     * 加工方式
     */
    @Excel(name = "加工方式")
    private String jgWay;

    /**
     * 加工内容
     */
    @Excel(name = "加工内容")
    private String jgContent;

    /**
     * 记录人
     */
    @Excel(name = "记录人")
    private String jgUser;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @JSONField(name = "PROD_ID")
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdId() {
        return prodId;
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

    @JSONField(name = "PRODUCT_NAME")
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    @JSONField(name = "CPLB")
    public void setCplb(String cplb) {
        this.cplb = cplb;
    }

    public String getCplb() {
        return cplb;
    }

    @JSONField(name = "FEEDIN_ID")
    public void setFeedinId(String feedinId) {
        this.feedinId = feedinId;
    }

    public String getFeedinId() {
        return feedinId;
    }

    @JSONField(name = "SPTJJ")
    public void setSptjj(String sptjj) {
        this.sptjj = sptjj;
    }

    public String getSptjj() {
        return sptjj;
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

    @JSONField(name = "PROD_QUALITY")
    public void setProdQuality(String prodQuality) {
        this.prodQuality = prodQuality;
    }

    public String getProdQuality() {
        return prodQuality;
    }

    @JSONField(name = "PROD_DATE")
    public void setProdDate(Date prodDate) {
        this.prodDate = prodDate;
    }

    public Date getProdDate() {
        return prodDate;
    }

    @JSONField(name = "JG_WAY")
    public void setJgWay(String jgWay) {
        this.jgWay = jgWay;
    }

    public String getJgWay() {
        return jgWay;
    }

    @JSONField(name = "JG_CONTENT")
    public void setJgContent(String jgContent) {
        this.jgContent = jgContent;
    }

    public String getJgContent() {
        return jgContent;
    }

    @JSONField(name = "JG_USER")
    public void setJgUser(String jgUser) {
        this.jgUser = jgUser;
    }

    public String getJgUser() {
        return jgUser;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("prodId", getProdId())
                .append("batchNo", getBatchNo())
                .append("batchName", getBatchName())
                .append("productName", getProductName())
                .append("cplb", getCplb())
                .append("feedinId", getFeedinId())
                .append("sptjj", getSptjj())
                .append("num", getNum())
                .append("numUnit", getNumUnit())
                .append("saveStatus", getSaveStatus())
                .append("prodQuality", getProdQuality())
                .append("remark", getRemark())
                .append("prodDate", getProdDate())
                .append("jgWay", getJgWay())
                .append("jgContent", getJgContent())
                .append("jgUser", getJgUser())
                .append("inputTime", getInputTime())
                .append("corpId", getCorpId())
                .toString();
    }
}
