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
 * 施肥信息（种植）
 * 对象 origins_plant_fertilize
 *
 * @author ruoyi
 * @date 2021-04-25
 */
public class OriginsPlantFertilize extends BaseEntity {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private Long id;

    /**
     * 主键
     */
    private String fertilizeId;

    /**
     * 选择施肥日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "选择施肥日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date fertilizeDate;

    /**
     * 投入品类型
     */
    @Excel(name = "投入品类型")
    private String trplx;

    /**
     * 肥料名称
     */
    @Excel(name = "肥料名称")
    private String fertilizerName;

    /**
     * 肥料批次码
     */
    @Excel(name = "肥料批次码")
    private String flBatchNo;

    /**
     * 施肥方式
     */
    @Excel(name = "施肥方式")
    private String fertilizeMode;

    /**
     * 施肥量
     */
    @Excel(name = "施肥量")
    private BigDecimal topdressingAmount;

    /**
     * 施肥量单位
     */
    @Excel(name = "施肥量单位")
    private String dw;

    /**
     * 填写施肥面积
     */
    @Excel(name = "填写施肥面积")
    private BigDecimal area;

    /**
     * 选择面积单位
     */
    @Excel(name = "选择面积单位")
    private String mjdw;

    /**
     * 批次ID
     */
    @Excel(name = "批次ID")
    private String batchId;

    /**
     * 录入时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "录入时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inputTime;

    @JSONField(name = "FERTILIZE_ID")
    public void setFertilizeId(String fertilizeId) {
        this.fertilizeId = fertilizeId;
    }

    public String getFertilizeId() {
        return fertilizeId;
    }

    @JSONField(name = "FERTILIZE_DATE")
    public void setFertilizeDate(Date fertilizeDate) {
        this.fertilizeDate = fertilizeDate;
    }

    public Date getFertilizeDate() {
        return fertilizeDate;
    }

    @JSONField(name = "TRPLX")
    public void setTrplx(String trplx) {
        this.trplx = trplx;
    }

    public String getTrplx() {
        return trplx;
    }

    @JSONField(name = "FERTILIZER_NAME")
    public void setFertilizerName(String fertilizerName) {
        this.fertilizerName = fertilizerName;
    }

    public String getFertilizerName() {
        return fertilizerName;
    }

    @JSONField(name = "FL_BATCH_NO")
    public void setFlBatchNo(String flBatchNo) {
        this.flBatchNo = flBatchNo;
    }

    public String getFlBatchNo() {
        return flBatchNo;
    }

    @JSONField(name = "FERTILIZE_MODE")
    public void setFertilizeMode(String fertilizeMode) {
        this.fertilizeMode = fertilizeMode;
    }

    public String getFertilizeMode() {
        return fertilizeMode;
    }

    @JSONField(name = "TOPDRESSING_AMOUNT")
    public void setTopdressingAmount(BigDecimal topdressingAmount) {
        this.topdressingAmount = topdressingAmount;
    }

    public BigDecimal getTopdressingAmount() {
        return topdressingAmount;
    }

    @JSONField(name = "DW")
    public void setDw(String dw) {
        this.dw = dw;
    }

    public String getDw() {
        return dw;
    }

    @JSONField(name = "AREA")
    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getArea() {
        return area;
    }

    @JSONField(name = "MJDW")
    public void setMjdw(String mjdw) {
        this.mjdw = mjdw;
    }

    public String getMjdw() {
        return mjdw;
    }

    @JSONField(name = "BATCH_ID")
    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getBatchId() {
        return batchId;
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
                .append("fertilizeId", getFertilizeId())
                .append("fertilizeDate", getFertilizeDate())
                .append("trplx", getTrplx())
                .append("fertilizerName", getFertilizerName())
                .append("flBatchNo", getFlBatchNo())
                .append("fertilizeMode", getFertilizeMode())
                .append("topdressingAmount", getTopdressingAmount())
                .append("dw", getDw())
                .append("area", getArea())
                .append("mjdw", getMjdw())
                .append("batchId", getBatchId())
                .append("inputTime", getInputTime())
                .append("remark", getRemark())
                .toString();
    }
}
