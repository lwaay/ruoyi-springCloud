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
 * 投喂信息（水产）
 * 对象 origins_fish_feed
 *
 * @author ruoyi
 * @date 2021-04-25
 */
public class OriginsFishFeed extends BaseEntity {
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
     * 池塘ID
     */
    @Excel(name = "池塘ID")
    private String pondId;

    /**
     * 投喂时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "投喂时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date feedDate;

    /**
     * 饲料名称
     */
    @Excel(name = "饲料名称")
    private String feedName;

    /**
     * 生产厂家
     */
    @Excel(name = "生产厂家")
    private String feedCompany;

    /**
     * 饲料批次码
     */
    @Excel(name = "饲料批次码")
    private String slzsm;

    /**
     * 投喂数量
     */
    @Excel(name = "投喂数量")
    private BigDecimal feedNum;

    /**
     * 投喂数量单位
     */
    @Excel(name = "投喂数量单位")
    private String feedNumUnit;

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
    private Date adddatetime;

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

    @JSONField(name = "FEED_ID")
    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public String getFeedId() {
        return feedId;
    }

    @JSONField(name = "POND_ID")
    public void setPondId(String pondId) {
        this.pondId = pondId;
    }

    public String getPondId() {
        return pondId;
    }

    @JSONField(name = "FEED_DATE")
    public void setFeedDate(Date feedDate) {
        this.feedDate = feedDate;
    }

    public Date getFeedDate() {
        return feedDate;
    }

    @JSONField(name = "FEED_NAME")
    public void setFeedName(String feedName) {
        this.feedName = feedName;
    }

    public String getFeedName() {
        return feedName;
    }

    @JSONField(name = "FEED_COMPANY")
    public void setFeedCompany(String feedCompany) {
        this.feedCompany = feedCompany;
    }

    public String getFeedCompany() {
        return feedCompany;
    }

    @JSONField(name = "SLZSM")
    public void setSlzsm(String slzsm) {
        this.slzsm = slzsm;
    }

    public String getSlzsm() {
        return slzsm;
    }

    @JSONField(name = "FEED_NUM")
    public void setFeedNum(BigDecimal feedNum) {
        this.feedNum = feedNum;
    }

    public BigDecimal getFeedNum() {
        return feedNum;
    }

    @JSONField(name = "FEED_NUM_UNIT")
    public void setFeedNumUnit(String feedNumUnit) {
        this.feedNumUnit = feedNumUnit;
    }

    public String getFeedNumUnit() {
        return feedNumUnit;
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

    @JSONField(name = "ADDDATETIME")
    public void setAdddatetime(Date adddatetime) {
        this.adddatetime = adddatetime;
    }

    public Date getAdddatetime() {
        return adddatetime;
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
                .append("feedId", getFeedId())
                .append("pondId", getPondId())
                .append("feedDate", getFeedDate())
                .append("feedName", getFeedName())
                .append("feedCompany", getFeedCompany())
                .append("slzsm", getSlzsm())
                .append("feedNum", getFeedNum())
                .append("feedNumUnit", getFeedNumUnit())
                .append("trplx", getTrplx())
                .append("symj", getSymj())
                .append("mjdw", getMjdw())
                .append("syff", getSyff())
                .append("createBy", getCreateBy())
                .append("adddatetime", getAdddatetime())
                .append("remarks", getRemarks())
                .toString();
    }
}
