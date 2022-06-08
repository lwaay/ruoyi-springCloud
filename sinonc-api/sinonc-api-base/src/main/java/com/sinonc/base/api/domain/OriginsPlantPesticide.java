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
 * 施药信息（种植）
 * 对象 origins_plant_pesticide
 *
 * @author ruoyi
 * @date 2021-04-25
 */
public class OriginsPlantPesticide extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 主键
     */
    private String applyPesticideId;

    /**
     * 批次ID
     */
    @Excel(name = "批次ID")
    private String batchId;

    /**
     * 农药名称
     */
    @Excel(name = "农药名称")
    private String pesticideName;

    /**
     * 农药登记证号
     */
    @Excel(name = "农药登记证号")
    private String nydjzh;

    /**
     * 农药生产单位
     */
    @Excel(name = "农药生产单位")
    private String nyscdw;

    /**
     * 农药批次码
     */
    @Excel(name = "农药批次码")
    private String pesticideBatchNo;

    /**
     * 施药量
     */
    @Excel(name = "施药量")
    private BigDecimal pesticideAmount;

    /**
     * 施药量单位
     */
    @Excel(name = "施药量单位")
    private String yldw;

    /**
     * 施药日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "施药日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    /**
     * 安全间隔期(天)
     */
    @Excel(name = "安全间隔期(天)")
    private Integer aqjgq;

    /**
     * 投入品类型
     */
    @Excel(name = "投入品类型")
    private String trplx;

    /**
     * 使用面积
     */
    @Excel(name = "使用面积")
    private BigDecimal symj;

    /**
     * 使用面积单位
     */
    @Excel(name = "使用面积单位")
    private String mjdw;

    /**
     * 使用方法
     */
    @Excel(name = "使用方法")
    private String syff;

    /**
     * 录入时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "录入时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inputTime;

    @JSONField(name = "APPLY_PESTICIDE_ID")
    public void setApplyPesticideId(String applyPesticideId) {
        this.applyPesticideId = applyPesticideId;
    }

    public String getApplyPesticideId() {
        return applyPesticideId;
    }

    @JSONField(name = "BATCH_ID")
    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getBatchId() {
        return batchId;
    }

    @JSONField(name = "PESTICIDE_NAME")
    public void setPesticideName(String pesticideName) {
        this.pesticideName = pesticideName;
    }

    public String getPesticideName() {
        return pesticideName;
    }

    @JSONField(name = "NYDJZH")
    public void setNydjzh(String nydjzh) {
        this.nydjzh = nydjzh;
    }

    public String getNydjzh() {
        return nydjzh;
    }

    @JSONField(name = "NYSCDW")
    public void setNyscdw(String nyscdw) {
        this.nyscdw = nyscdw;
    }

    public String getNyscdw() {
        return nyscdw;
    }

    @JSONField(name = "PESTICIDE_BATCH_NO")
    public void setPesticideBatchNo(String pesticideBatchNo) {
        this.pesticideBatchNo = pesticideBatchNo;
    }

    public String getPesticideBatchNo() {
        return pesticideBatchNo;
    }

    @JSONField(name = "PESTICIDE_AMOUNT")
    public void setPesticideAmount(BigDecimal pesticideAmount) {
        this.pesticideAmount = pesticideAmount;
    }

    public BigDecimal getPesticideAmount() {
        return pesticideAmount;
    }

    @JSONField(name = "YLDW")
    public void setYldw(String yldw) {
        this.yldw = yldw;
    }

    public String getYldw() {
        return yldw;
    }

    @JSONField(name = "START_DATE")
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    @JSONField(name = "AQJGQ")
    public void setAqjgq(Integer aqjgq) {
        this.aqjgq = aqjgq;
    }

    public Integer getAqjgq() {
        return aqjgq;
    }

    @JSONField(name = "TRPLX")
    public void setTrplx(String trplx) {
        this.trplx = trplx;
    }

    public String getTrplx() {
        return trplx;
    }

    @JSONField(name = "SYMJ")
    public void setSymj(BigDecimal symj) {
        this.symj = symj;
    }

    public BigDecimal getSymj() {
        return symj;
    }

    @JSONField(name = "MJDW")
    public void setMjdw(String mjdw) {
        this.mjdw = mjdw;
    }

    public String getMjdw() {
        return mjdw;
    }

    @JSONField(name = "SYFF")
    public void setSyff(String syff) {
        this.syff = syff;
    }

    public String getSyff() {
        return syff;
    }

    @JSONField(name = "INPUT_TIME")
    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
    }

    public Date getInputTime() {
        return inputTime;
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
                .append("applyPesticideId", getApplyPesticideId())
                .append("batchId", getBatchId())
                .append("pesticideName", getPesticideName())
                .append("nydjzh", getNydjzh())
                .append("nyscdw", getNyscdw())
                .append("pesticideBatchNo", getPesticideBatchNo())
                .append("pesticideAmount", getPesticideAmount())
                .append("yldw", getYldw())
                .append("startDate", getStartDate())
                .append("aqjgq", getAqjgq())
                .append("trplx", getTrplx())
                .append("symj", getSymj())
                .append("mjdw", getMjdw())
                .append("syff", getSyff())
                .append("inputTime", getInputTime())
                .append("remark", getRemark())
                .toString();
    }
}
