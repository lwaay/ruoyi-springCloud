package com.sinonc.base.api.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;

/**
 * 溯源灌溉记录对象 origins_irrigation
 *
 * @author ruoyi
 * @date 2021-08-31
 */
public class OriginsIrrigation extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 批次码
     */
    @Excel(name = "批次码")
    private String batchCode;

    /**
     * 产品批次
     */
    @Excel(name = "产品批次")
    private String batch;

    /**
     * 产品名称
     */
    @Excel(name = "产品名称")
    private String proName;

    /**
     * 企业名称
     */
    @Excel(name = "企业名称")
    private String cropName;

    /**
     * 企业id
     */
    private String cropId;

    /**
     * 水源
     */
    @Excel(name = "水源")
    private String source;

    @Excel(name = "备注")
    private String remark;

    /**
     * 灌溉日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "灌溉日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date irrigationDate;

    /**
     * 图片路径
     */
    @Excel(name = "图片路径")
    private String imgUrl;

    /**
     * 灌溉方案
     * @param id
     */
    @Excel(name = "灌溉方案")
    private String plan;

    /**
     * 开始时间
     * @param id
     */
    @Excel(name= "灌溉开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 结束时间
     * @param id
     */
    @Excel(name="灌溉结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishTime;

    /**
     * 灌溉量
     * @param id
     */
    @Excel(name="灌溉量")
    private Double volum;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getBatch() {
        return batch;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProName() {
        return proName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropId(String cropId) {
        this.cropId = cropId;
    }

    public String getCropId() {
        return cropId;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }

    public void setIrrigationDate(Date irrigationDate) {
        this.irrigationDate = irrigationDate;
    }

    public Date getIrrigationDate() {
        return irrigationDate;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Double getVolum() {
        return volum;
    }

    public void setVolum(Double volum) {
        this.volum = volum;
    }
}
