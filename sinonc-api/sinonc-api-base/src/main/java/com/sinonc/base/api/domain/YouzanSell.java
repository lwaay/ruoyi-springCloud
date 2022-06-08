package com.sinonc.base.api.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 生产加工销售对象 youzan_sell
 *
 * @author ruoyi
 * @date 2021-09-02
 */
public class YouzanSell extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long ysellId;

    /**
     * 企业名称
     */
    @Excel(name = "企业名称")
    private String companyName;

    /**
     * 批次码
     */
    @Excel(name = "批次码")
    private String batchCode;

    /**
     * 产品批次
     */
    @Excel(name = "产品批次")
    private String batchNo;

    /**
     * 录入时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "录入时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recordTime;

    /**
     * 图片
     */
    @Excel(name = "图片")
    private String picUrl;

    /**
     * 产品名称
     */
    @Excel(name = "产品名称")
    private String goodName;

    /**
     * 销售时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "销售时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date successTime;

    /**
     * 销售数量
     */
    @Excel(name = "销售数量")
    private Long goodNum;

    /**
     * 销售单位
     */
    @Excel(name = "销售单位")
    private String goodUnit;

    /**
     * 单件规格
     */
    @Excel(name = "单件规格")
    private String specifications;

    /**
     * 销售状态
     */
    @Excel(name = "销售状态")
    private String orderStatus;

    /**
     * 主体名称
     */
    @Excel(name = "主体名称")
    private String buyer;

    /**
     * 主体地址
     */
    @Excel(name = "主体地址")
    private String deliveryAddress;

    /**
     * 联系人
     */
    @Excel(name = "联系人")
    private String receiverName;

    /**
     * 联系人电话
     */
    @Excel(name = "联系人电话")
    private String receiverTel;

    /**
     * 订单编号
     */
    @Excel(name = "订单编号")
    private String orderTid;

    /**
     * 订单明细ID
     */
    @Excel(name = "订单明细ID")
    private String orderOid;

    /**
     * 商品ID
     */
    @Excel(name = "商品ID")
    private String itemId;

    /**
     * 规格id
     */
    @Excel(name = "规格id")
    private String skuId;

    /**
     * 门店ID
     */
    @Excel(name = "门店ID")
    private String nodeKdtId;

    /**
     * 总店ID
     */
    @Excel(name = "总店ID")
    private String rootKdtId;

    /**
     * 销售日期(created(订单创建时间))
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "销售日期(created(订单创建时间))", width = 30, dateFormat = "yyyy-MM-dd")
    private Date sellTime;

    /**
     * 省
     */
    @Excel(name = "省")
    private String deliveryProvince;

    /**
     * 市
     */
    @Excel(name = "市")
    private String deliveryCity;

    /**
     * 区
     */
    @Excel(name = "区")
    private String deliveryDistrict;

    public void setYsellId(Long ysellId) {
        this.ysellId = ysellId;
    }

    public Long getYsellId() {
        return ysellId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setSuccessTime(Date successTime) {
        this.successTime = successTime;
    }

    public Date getSuccessTime() {
        return successTime;
    }

    public void setGoodNum(Long goodNum) {
        this.goodNum = goodNum;
    }

    public Long getGoodNum() {
        return goodNum;
    }

    public void setGoodUnit(String goodUnit) {
        this.goodUnit = goodUnit;
    }

    public String getGoodUnit() {
        return goodUnit;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverTel(String receiverTel) {
        this.receiverTel = receiverTel;
    }

    public String getReceiverTel() {
        return receiverTel;
    }

    public void setOrderTid(String orderTid) {
        this.orderTid = orderTid;
    }

    public String getOrderTid() {
        return orderTid;
    }

    public void setOrderOid(String orderOid) {
        this.orderOid = orderOid;
    }

    public String getOrderOid() {
        return orderOid;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setNodeKdtId(String nodeKdtId) {
        this.nodeKdtId = nodeKdtId;
    }

    public String getNodeKdtId() {
        return nodeKdtId;
    }

    public void setRootKdtId(String rootKdtId) {
        this.rootKdtId = rootKdtId;
    }

    public String getRootKdtId() {
        return rootKdtId;
    }

    public void setSellTime(Date sellTime) {
        this.sellTime = sellTime;
    }

    public Date getSellTime() {
        return sellTime;
    }

    public void setDeliveryProvince(String deliveryProvince) {
        this.deliveryProvince = deliveryProvince;
    }

    public String getDeliveryProvince() {
        return deliveryProvince;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryDistrict(String deliveryDistrict) {
        this.deliveryDistrict = deliveryDistrict;
    }

    public String getDeliveryDistrict() {
        return deliveryDistrict;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("ysellId", getYsellId())
                .append("companyName", getCompanyName())
                .append("batchCode", getBatchCode())
                .append("batchNo", getBatchNo())
                .append("recordTime", getRecordTime())
                .append("picUrl", getPicUrl())
                .append("goodName", getGoodName())
                .append("successTime", getSuccessTime())
                .append("goodNum", getGoodNum())
                .append("goodUnit", getGoodUnit())
                .append("specifications", getSpecifications())
                .append("orderStatus", getOrderStatus())
                .append("buyer", getBuyer())
                .append("deliveryAddress", getDeliveryAddress())
                .append("receiverName", getReceiverName())
                .append("receiverTel", getReceiverTel())
                .append("orderTid", getOrderTid())
                .append("orderOid", getOrderOid())
                .append("itemId", getItemId())
                .append("skuId", getSkuId())
                .append("nodeKdtId", getNodeKdtId())
                .append("rootKdtId", getRootKdtId())
                .append("sellTime", getSellTime())
                .append("deliveryProvince", getDeliveryProvince())
                .append("deliveryCity", getDeliveryCity())
                .append("deliveryDistrict", getDeliveryDistrict())
                .append("remark", getRemark())
                .toString();
    }
}
