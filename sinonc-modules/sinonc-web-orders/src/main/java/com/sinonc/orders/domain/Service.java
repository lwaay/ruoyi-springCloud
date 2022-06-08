package com.sinonc.orders.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 认养服务明细表 od_service
 *
 * @author sinonc
 * @date 2019-08-09
 */

public class Service {

    private static final long serialVersionUID = 1L;

    /** 服务项目ID */
    private Integer serviceId;
    /** 会员id */
    private Long memberIdP;
    /** 订单ID */
    private Long orderIdP;
    /** 订单号 */
    private String orderNo;
    /** 商品（套餐）名称 */
    private String goodsName;
    /** 商品套餐服务项 */
    private String serviceItem;
    /** 服务状态，0：未使用，1，已使用 */
    private Integer serviceStatus;
    /** 服务类别：0，物品配送；1：其他服务 */
    private Integer serviceTyep;
    /**  */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
    /** 服务使用时间 */
    private Date serviceTime;
    /** 服务生效时间 */
    private Date startTime;
    /** 服务失效时间 */
    private Date endTime;
    /** 更新操作员用户名 */
    private String updateBy;
    /**  */
    private Integer delFlag;

    public void setServiceId(Integer serviceId) {
            this.serviceId = serviceId;
    }

    public Integer getServiceId() {
            return serviceId;
    }
    public void setMemberIdP(Long memberIdP) {
            this.memberIdP = memberIdP;
    }

    public Long getMemberIdP() {
            return memberIdP;
    }
    public void setOrderIdP(Long orderIdP) {
            this.orderIdP = orderIdP;
    }

    public Long getOrderIdP() {
            return orderIdP;
    }
    public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
    }

    public String getOrderNo() {
            return orderNo;
    }
    public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
    }

    public String getGoodsName() {
            return goodsName;
    }
    public void setServiceItem(String serviceItem) {
            this.serviceItem = serviceItem;
    }

    public String getServiceItem() {
            return serviceItem;
    }
    public void setServiceStatus(Integer serviceStatus) {
            this.serviceStatus = serviceStatus;
    }

    public Integer getServiceStatus() {
            return serviceStatus;
    }
    public void setServiceTyep(Integer serviceTyep) {
            this.serviceTyep = serviceTyep;
    }

    public Integer getServiceTyep() {
            return serviceTyep;
    }
    public void setCreateTime(Date createTime) {
            this.createTime = createTime;
    }

    public Date getCreateTime() {
            return createTime;
    }
    public void setUpdateTime(Date updateTime) {
            this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
            return updateTime;
    }
    public void setServiceTime(Date serviceTime) {
            this.serviceTime = serviceTime;
    }

    public Date getServiceTime() {
            return serviceTime;
    }
    public void setStartTime(Date startTime) {
            this.startTime = startTime;
    }

    public Date getStartTime() {
            return startTime;
    }
    public void setEndTime(Date endTime) {
            this.endTime = endTime;
    }

    public Date getEndTime() {
            return endTime;
    }
    public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
    }

    public String getUpdateBy() {
            return updateBy;
    }
    public void setDelFlag(Integer delFlag) {
            this.delFlag = delFlag;
    }

    public Integer getDelFlag() {
            return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("serviceId",getServiceId())
                .append("memberIdP",getMemberIdP())
                .append("orderIdP",getOrderIdP())
                .append("orderNo",getOrderNo())
                .append("goodsName",getGoodsName())
                .append("serviceItem",getServiceItem())
                .append("serviceStatus",getServiceStatus())
                .append("serviceTyep",getServiceTyep())
                .append("createTime",getCreateTime())
                .append("updateTime",getUpdateTime())
                .append("serviceTime",getServiceTime())
                .append("startTime",getStartTime())
                .append("endTime",getEndTime())
                .append("updateBy",getUpdateBy())
                .append("delFlag",getDelFlag())
                .toString();
    }
}
