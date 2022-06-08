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
 * 采收信息（种植）
 * 对象 origins_plant_reap
 *
 * @author ruoyi
 * @date 2021-04-25
 */
public class OriginsPlantReap extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 主键
     */
    private String reapId;

    /**
     * 批次ID
     */
    @Excel(name = "批次ID")
    private String batchId;

    /**
     * 采收日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "采收日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reapDate;

    /**
     * 采收量
     */
    @Excel(name = "采收量")
    private BigDecimal reapAmount;

    /**
     * 采收量单位
     */
    @Excel(name = "采收量单位")
    private String amountUnit;

    /**
     * 收获方式
     */
    @Excel(name = "收获方式")
    private String reapShfs;

    /**
     * 产品类型
     */
    @Excel(name = "产品类型")
    private String reapCplx;

    /**
     * 录入时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "录入时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inputTime;

    @JSONField(name = "REAP_ID")
    public void setReapId(String reapId) {
        this.reapId = reapId;
    }

    public String getReapId() {
        return reapId;
    }

    @JSONField(name = "BATCH_ID")
    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getBatchId() {
        return batchId;
    }

    @JSONField(name = "REAP_DATE")
    public void setReapDate(Date reapDate) {
        this.reapDate = reapDate;
    }

    public Date getReapDate() {
        return reapDate;
    }

    @JSONField(name = "REAP_AMOUNT")
    public void setReapAmount(BigDecimal reapAmount) {
        this.reapAmount = reapAmount;
    }

    public BigDecimal getReapAmount() {
        return reapAmount;
    }

    @JSONField(name = "AMOUNT_UNIT")
    public void setAmountUnit(String amountUnit) {
        this.amountUnit = amountUnit;
    }

    public String getAmountUnit() {
        return amountUnit;
    }

    @JSONField(name = "REAP_SHFS")
    public void setReapShfs(String reapShfs) {
        this.reapShfs = reapShfs;
    }

    public String getReapShfs() {
        return reapShfs;
    }

    @JSONField(name = "REAP_CPLX")
    public void setReapCplx(String reapCplx) {
        this.reapCplx = reapCplx;
    }

    public String getReapCplx() {
        return reapCplx;
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
                .append("reapId", getReapId())
                .append("batchId", getBatchId())
                .append("reapDate", getReapDate())
                .append("reapAmount", getReapAmount())
                .append("amountUnit", getAmountUnit())
                .append("reapShfs", getReapShfs())
                .append("reapCplx", getReapCplx())
                .append("inputTime", getInputTime())
                .append("remark", getRemark())
                .toString();
    }
}
