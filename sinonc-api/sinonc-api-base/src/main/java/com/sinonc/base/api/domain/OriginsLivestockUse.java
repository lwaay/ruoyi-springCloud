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
 * 兽药使用记录信息（畜禽）
 * 对象 origins_livestock_use
 *
 * @author ruoyi
 * @date 2021-04-25
 */
public class OriginsLivestockUse extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 主键
     */
    private String useId;

    /**
     * 用药日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "用药日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date useDate;

    /**
     * 方式 1按圈喂养 2按耳标喂养
     */
    @Excel(name = "方式 1按圈喂养 2按耳标喂养")
    private Integer type;

    /**
     * 圈舍号
     */
    @Excel(name = "圈舍号")
    private String houseId;

    /**
     * 耳标号（畜类才有）
     */
    @Excel(name = "耳标号", readConverterExp = "畜=类才有")
    private String eartagId;

    /**
     * 用药名称
     */
    @Excel(name = "用药名称")
    private String medicineName;

    /**
     * 企业ID
     */
    @Excel(name = "企业ID")
    private String corpId;

    /**
     * 录入时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "录入时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date adddatetime;

    /**
     * 品种类型（1 畜类 ； 2  禽类）
     */
    @Excel(name = "品种类型", readConverterExp = "1=,畜=类,；=,2=,禽=类")
    private Integer breedType;

    /**
     * 畜禽业批次
     */
    @Excel(name = "畜禽业批次")
    private String batchId;

    /**
     * 兽药批次码
     */
    @Excel(name = "兽药批次码")
    private String syzsm;

    /**
     * 投入品类型
     */
    @Excel(name = "投入品类型")
    private String trplx;

    /**
     * 用药量
     */
    @Excel(name = "用药量")
    private BigDecimal yyl;

    /**
     * 药量单位
     */
    @Excel(name = "药量单位")
    private String yydw;

    /**
     * 安全间隔期
     */
    @Excel(name = "安全间隔期")
    private Integer aqjgq;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String bz;

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
     * 使用方法
     */
    @Excel(name = "使用方法")
    private String syff;

    /**
     * 记录人
     */
    @Excel(name = "记录人")
    private String jlr;

    public void setUseId(String useId) {
        this.useId = useId;
    }

    public String getUseId() {
        return useId;
    }

    @JSONField(name = "USE_DATE")
    public void setUseDate(Date useDate) {
        this.useDate = useDate;
    }

    public Date getUseDate() {
        return useDate;
    }

    @JSONField(name = "TYPE")
    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    @JSONField(name = "HOUSE_ID")
    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getHouseId() {
        return houseId;
    }

    @JSONField(name = "EARTAG_ID")
    public void setEartagId(String eartagId) {
        this.eartagId = eartagId;
    }

    public String getEartagId() {
        return eartagId;
    }

    @JSONField(name = "MEDICINE_NAME")
    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineName() {
        return medicineName;
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

    @JSONField(name = "BREED_TYPE")
    public void setBreedType(Integer breedType) {
        this.breedType = breedType;
    }

    public Integer getBreedType() {
        return breedType;
    }

    @JSONField(name = "BATCH_ID")
    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getBatchId() {
        return batchId;
    }

    @JSONField(name = "SYZSM")
    public void setSyzsm(String syzsm) {
        this.syzsm = syzsm;
    }

    public String getSyzsm() {
        return syzsm;
    }

    @JSONField(name = "TRPLX")
    public void setTrplx(String trplx) {
        this.trplx = trplx;
    }

    public String getTrplx() {
        return trplx;
    }

    @JSONField(name = "YYL")
    public void setYyl(BigDecimal yyl) {
        this.yyl = yyl;
    }

    public BigDecimal getYyl() {
        return yyl;
    }

    @JSONField(name = "YYDW")
    public void setYydw(String yydw) {
        this.yydw = yydw;
    }

    public String getYydw() {
        return yydw;
    }

    @JSONField(name = "AQJGQ")
    public void setAqjgq(Integer aqjgq) {
        this.aqjgq = aqjgq;
    }

    public Integer getAqjgq() {
        return aqjgq;
    }

    @JSONField(name = "BZ")
    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getBz() {
        return bz;
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

    @JSONField(name = "SYFF")
    public void setSyff(String syff) {
        this.syff = syff;
    }

    public String getSyff() {
        return syff;
    }

    @JSONField(name = "JLR")
    public void setJlr(String jlr) {
        this.jlr = jlr;
    }

    public String getJlr() {
        return jlr;
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
                .append("useId", getUseId())
                .append("useDate", getUseDate())
                .append("type", getType())
                .append("houseId", getHouseId())
                .append("eartagId", getEartagId())
                .append("medicineName", getMedicineName())
                .append("corpId", getCorpId())
                .append("adddatetime", getAdddatetime())
                .append("breedType", getBreedType())
                .append("batchId", getBatchId())
                .append("syzsm", getSyzsm())
                .append("trplx", getTrplx())
                .append("yyl", getYyl())
                .append("yydw", getYydw())
                .append("aqjgq", getAqjgq())
                .append("bz", getBz())
                .append("num", getNum())
                .append("numUnit", getNumUnit())
                .append("syff", getSyff())
                .append("jlr", getJlr())
                .toString();
    }
}
