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
 * 无害化处理记录信息（畜禽）
 * 对象 origins_livestock_innocent
 *
 * @author ruoyi
 * @date 2021-04-25
 */
public class OriginsLivestockInnocent extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 主键
     */
    private String innocentId;

    /**
     * 日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date innocentDate;

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
     * 处理数量
     */
    @Excel(name = "处理数量")
    private BigDecimal num;

    /**
     * 处理数量单位
     */
    @Excel(name = "处理数量单位")
    private String numUnit;

    /**
     * 处理方法
     */
    @Excel(name = "处理方法")
    private String dealType;

    /**
     * 外键企业ID
     */
    @Excel(name = "外键企业ID")
    private String corpId;

    /**
     * 操作时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date adddatetime;

    /**
     * 品种类型（1 畜类 ； 2  禽类）
     */
    @Excel(name = "品种类型", readConverterExp = "1=,畜=类,；=,2=,禽=类")
    private Integer breedType;

    /**
     * 外键 畜禽业批次id
     */
    @Excel(name = "外键 畜禽业批次id")
    private String batchId;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String bz;

    @JSONField(name = "INNOCENT_ID")
    public void setInnocentId(String innocentId) {
        this.innocentId = innocentId;
    }

    public String getInnocentId() {
        return innocentId;
    }

    @JSONField(name = "INNOCENT_DATE")
    public void setInnocentDate(Date innocentDate) {
        this.innocentDate = innocentDate;
    }

    public Date getInnocentDate() {
        return innocentDate;
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

    @JSONField(name = "DEAL_TYPE")
    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getDealType() {
        return dealType;
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

    @JSONField(name = "BZ")
    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getBz() {
        return bz;
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
                .append("innocentId", getInnocentId())
                .append("innocentDate", getInnocentDate())
                .append("type", getType())
                .append("houseId", getHouseId())
                .append("eartagId", getEartagId())
                .append("num", getNum())
                .append("numUnit", getNumUnit())
                .append("dealType", getDealType())
                .append("corpId", getCorpId())
                .append("adddatetime", getAdddatetime())
                .append("breedType", getBreedType())
                .append("batchId", getBatchId())
                .append("bz", getBz())
                .toString();
    }
}
