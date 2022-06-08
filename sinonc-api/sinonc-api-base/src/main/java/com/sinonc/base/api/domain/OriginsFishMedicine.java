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
 * 用药信息（水产）
 * 对象 origins_fish_medicine
 *
 * @author ruoyi
 * @date 2021-04-25
 */
public class OriginsFishMedicine extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 主键
     */
    private String medicineId;

    /**
     * 池塘ID
     */
    @Excel(name = "池塘ID")
    private String pondId;

    /**
     * 药品登记证号
     */
    @Excel(name = "药品登记证号")
    private String ypdjzh;

    /**
     * 用药时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "用药时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date medicineTime;

    /**
     * 药品名称
     */
    @Excel(name = "药品名称")
    private String medicineName;

    /**
     * 使用方法
     */
    @Excel(name = "使用方法")
    private String medicineType;

    /**
     * 用药量
     */
    @Excel(name = "用药量")
    private BigDecimal num;

    /**
     * 用药量单位
     */
    @Excel(name = "用药量单位")
    private String numUnit;

    /**
     * 药品批次码
     */
    @Excel(name = "药品批次码")
    private String ypzsm;

    /**
     * 生产厂家
     */
    @Excel(name = "生产厂家")
    private String sccj;

    /**
     * 安全间隔期
     */
    @Excel(name = "安全间隔期")
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
     * 面积单位
     */
    @Excel(name = "面积单位")
    private String mjdw;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 录入时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "录入时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date adddatetime;


    @JSONField(name = "MEDICINE_ID")
    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineId() {
        return medicineId;
    }

    @JSONField(name = "POND_ID")
    public void setPondId(String pondId) {
        this.pondId = pondId;
    }

    public String getPondId() {
        return pondId;
    }

    @JSONField(name = "YPDJZH")
    public void setYpdjzh(String ypdjzh) {
        this.ypdjzh = ypdjzh;
    }

    public String getYpdjzh() {
        return ypdjzh;
    }

    @JSONField(name = "MEDICINE_TIME")
    public void setMedicineTime(Date medicineTime) {
        this.medicineTime = medicineTime;
    }

    public Date getMedicineTime() {
        return medicineTime;
    }

    @JSONField(name = "MEDICINE_NAME")
    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineName() {
        return medicineName;
    }

    @JSONField(name = "MEDICINE_TYPE")
    public void setMedicineType(String medicineType) {
        this.medicineType = medicineType;
    }

    public String getMedicineType() {
        return medicineType;
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

    @JSONField(name = "YPZSM")
    public void setYpzsm(String ypzsm) {
        this.ypzsm = ypzsm;
    }

    public String getYpzsm() {
        return ypzsm;
    }

    @JSONField(name = "SCCJ")
    public void setSccj(String sccj) {
        this.sccj = sccj;
    }

    public String getSccj() {
        return sccj;
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

    @JSONField(name = "ADDDATETIME")
    public void setAdddatetime(Date adddatetime) {
        this.adddatetime = adddatetime;
    }

    public Date getAdddatetime() {
        return adddatetime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("medicineId", getMedicineId())
                .append("pondId", getPondId())
                .append("ypdjzh", getYpdjzh())
                .append("medicineTime", getMedicineTime())
                .append("medicineName", getMedicineName())
                .append("medicineType", getMedicineType())
                .append("num", getNum())
                .append("numUnit", getNumUnit())
                .append("ypzsm", getYpzsm())
                .append("sccj", getSccj())
                .append("aqjgq", getAqjgq())
                .append("trplx", getTrplx())
                .append("symj", getSymj())
                .append("mjdw", getMjdw())
                .append("createBy", getCreateBy())
                .append("adddatetime", getAdddatetime())
                .toString();
    }
}
