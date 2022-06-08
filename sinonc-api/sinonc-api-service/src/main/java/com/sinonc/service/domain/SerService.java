package com.sinonc.service.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import com.sinonc.origins.api.domain.PmBusiness;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 社会化服务对象 ser_service
 *
 * @author ruoyi
 * @date 2022-02-16
 */
public class SerService extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long serviceId;

    /**
     * 类型
     */
    @Excel(name = "类型")
    private Integer type;

    /**
     * 标题
     */
    @Excel(name = "标题")
    private String title;

    /**
     * 服务商id
     */
    @Excel(name = "服务商id")
    private Long busiId;

    /**
     * 服务商名称
     */
    @Excel(name = "服务商名称")
    private String busiName;

    /**
     * 详情
     */
    @Excel(name = "详情")
    private String detail;

    /**
     * 描述
     */
    @Excel(name = "描述")
    private String describe;

    /**
     * 最低价
     */
    @Excel(name = "最低价")
    private BigDecimal lowerPrice;

    /**
     * 最高价
     */
    @Excel(name = "最高价")
    private BigDecimal highPrice;

    /**
     * 图片地址
     */
    @Excel(name = "图片地址")
    private String images;

    /**
     * 联系电话
     */
    @Excel(name = "联系电话")
    private String phone;

    /**
     * 用工人数
     */
    @Excel(name = "用工人数")
    private String workerNum;

    /**
     * 发布人
     */
    @Excel(name = "发布人")
    private String publisher;

    /**
     * 服务开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "服务开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    /**
     * 服务结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "服务结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /**
     * 地址编码
     */
    @Excel(name = "地址编码")
    private String areaCode;

    /**
     * 地址全称
     */
    @Excel(name = "地址全称")
    private String areaName;

    /**
     * 创建人名称
     */
    @Excel(name = "创建人名称")
    private String createName;

    /**
     * 页大小
     */
    private Integer pageSize;

    /**
     * 页大小
     */
    private Integer pageNum;

    /**
     * 经营主体信息
     * @param serviceId
     */
    private PmBusiness business;

    /**
     * 规格信息
     * @param serviceId
     */
    private List<SerServiceAttr> attrs;

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setBusiId(Long busiId) {
        this.busiId = busiId;
    }

    public Long getBusiId() {
        return busiId;
    }

    public void setBusiName(String busiName) {
        this.busiName = busiName;
    }

    public String getBusiName() {
        return busiName;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getDescribe() {
        return describe;
    }

    public void setLowerPrice(BigDecimal lowerPrice) {
        this.lowerPrice = lowerPrice;
    }

    public BigDecimal getLowerPrice() {
        return lowerPrice;
    }

    public void setHighPrice(BigDecimal highPrice) {
        this.highPrice = highPrice;
    }

    public BigDecimal getHighPrice() {
        return highPrice;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getImages() {
        return images;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getCreateName() {
        return createName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWorkerNum() {
        return workerNum;
    }

    public void setWorkerNum(String workerNum) {
        this.workerNum = workerNum;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("serviceId", getServiceId())
                .append("type", getType())
                .append("title", getTitle())
                .append("busiId", getBusiId())
                .append("busiName", getBusiName())
                .append("detail", getDetail())
                .append("describe", getDescribe())
                .append("lowerPrice", getLowerPrice())
                .append("highPrice", getHighPrice())
                .append("images", getImages())
                .append("startDate", getStartDate())
                .append("endDate", getEndDate())
                .append("areaCode", getAreaCode())
                .append("areaName", getAreaName())
                .append("createBy", getCreateBy())
                .append("createName", getCreateName())
                .append("createTime", getCreateTime())
                .append("phone", getPhone())
                .append("workerNum", getWorkerNum())
                .append("publisher", getPublisher())
                .toString();
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public List<SerServiceAttr> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<SerServiceAttr> attrs) {
        this.attrs = attrs;
    }

    public PmBusiness getBusiness() {
        return business;
    }

    public void setBusiness(PmBusiness business) {
        this.business = business;
    }
}
