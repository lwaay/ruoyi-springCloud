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
 * 饲料投喂记录信息（畜禽）
 * 对象 origins_livestock_feed
 *
 * @author ruoyi
 * @date 2021-04-25
 */
public class OriginsLivestockFeed extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 主键
     */
    private String feedId;

    /**
     * 投喂日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "投喂日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date feedDay;

    /**
     * 投喂总量
     */
    @Excel(name = "投喂总量")
    private BigDecimal dosage;

    /**
     * 投喂总量单位
     */
    @Excel(name = "投喂总量单位")
    private String dosageUnit;

    /**
     * 畜禽数量
     */
    @Excel(name = "畜禽数量")
    private BigDecimal num;

    /**
     * 数量单位
     */
    @Excel(name = "数量单位")
    private String numUnit;

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
     * 耳标号
     */
    @Excel(name = "耳标号")
    private String eartagId;

    /**
     * 外键企业ID
     */
    @Excel(name = "外键企业ID")
    private String corpId;

    /**
     * 入库时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入库时间", width = 30, dateFormat = "yyyy-MM-dd")
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
     * 饲料名称
     */
    @Excel(name = "饲料名称")
    private String slmc;

    /**
     * 原料来源
     */
    @Excel(name = "原料来源")
    private String ylly;

    /**
     * 生产厂家
     */
    @Excel(name = "生产厂家")
    private String sccj;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String bz;

    /**
     * 饲料追溯码
     */
    @Excel(name = "饲料追溯码")
    private String slzsm;

    /**
     * 动物名称(自动按批次号生产)
     */
    @Excel(name = "动物名称(自动按批次号生产)")
    private String dwmc;

    /**
     * 投入品类型(饲料)
     */
    @Excel(name = "投入品类型(饲料)")
    private String trplx;

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


    @JSONField(name = "FEED_ID")
    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public String getFeedId() {
        return feedId;
    }

    @JSONField(name = "FEED_DAY")
    public void setFeedDay(Date feedDay) {
        this.feedDay = feedDay;
    }

    public Date getFeedDay() {
        return feedDay;
    }

    @JSONField(name = "DOSAGE")
    public void setDosage(BigDecimal dosage) {
        this.dosage = dosage;
    }

    public BigDecimal getDosage() {
        return dosage;
    }

    @JSONField(name = "DOSAGE_UNIT")
    public void setDosageUnit(String dosageUnit) {
        this.dosageUnit = dosageUnit;
    }

    public String getDosageUnit() {
        return dosageUnit;
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

    @JSONField(name = "slmc")
    public void setSlmc(String slmc) {
        this.slmc = slmc;
    }

    public String getSlmc() {
        return slmc;
    }

    @JSONField(name = "ylly")
    public void setYlly(String ylly) {
        this.ylly = ylly;
    }

    public String getYlly() {
        return ylly;
    }

    @JSONField(name = "SCCJ")
    public void setSccj(String sccj) {
        this.sccj = sccj;
    }

    public String getSccj() {
        return sccj;
    }

    @JSONField(name = "BZ")
    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getBz() {
        return bz;
    }

    @JSONField(name = "SLZSM")
    public void setSlzsm(String slzsm) {
        this.slzsm = slzsm;
    }

    public String getSlzsm() {
        return slzsm;
    }

    @JSONField(name = "DWMC")
    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    public String getDwmc() {
        return dwmc;
    }

    @JSONField(name = "TRPLX")
    public void setTrplx(String trplx) {
        this.trplx = trplx;
    }

    public String getTrplx() {
        return trplx;
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
                .append("feedId", getFeedId())
                .append("feedDay", getFeedDay())
                .append("dosage", getDosage())
                .append("dosageUnit", getDosageUnit())
                .append("num", getNum())
                .append("numUnit", getNumUnit())
                .append("type", getType())
                .append("houseId", getHouseId())
                .append("eartagId", getEartagId())
                .append("corpId", getCorpId())
                .append("adddatetime", getAdddatetime())
                .append("breedType", getBreedType())
                .append("batchId", getBatchId())
                .append("slmc", getSlmc())
                .append("ylly", getYlly())
                .append("sccj", getSccj())
                .append("bz", getBz())
                .append("slzsm", getSlzsm())
                .append("dwmc", getDwmc())
                .append("trplx", getTrplx())
                .append("syff", getSyff())
                .append("jlr", getJlr())
                .toString();
    }
}
