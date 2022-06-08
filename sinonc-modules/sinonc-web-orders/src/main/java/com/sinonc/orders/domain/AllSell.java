package com.sinonc.orders.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 信丰脐橙销售表 od_all_sell
 *
 * @author sinonc
 * @date 2019-11-14
 */

public class AllSell {

    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;
    /** 企业id(关联基地表)初始值为0 */
    private Long farmId;
    /** 企业名字(关联基地表)初始值为0 */
    private String firmName;
    /** 基地名称 */
    private String farmName;
    /** 销售类型：存放字典表（批发:0，电商零售:1,无:2） */
    private Integer type;
    /** 采购商 */
    private String buyer;
    /** 平台：存放字典表（淘宝:0，京东:1，拼多多:2，苏宁:3,无：4） */
    private Integer platform;
    /** 销售单价 */
    private Double price;
    /** 销售单位：存在字典表（箱：0，kg：1，份：2） */
    private Integer units;
    /** 数量 */
    private Long amount;
    /** 金额 */
    private Double money;
    /** 创建时间 */
    private Date createTime;
    /** 备注 */
    private String remark;

    public void setId(Long id) {
            this.id = id;
    }

    public Long getId() {
            return id;
    }
    public void setFarmId(Long farmId) {
            this.farmId = farmId;
    }

    public Long getFarmId() {
            return farmId;
    }
    public void setFarmName(String farmName) {
            this.farmName = farmName;
    }

    public String getFarmName() {
            return farmName;
    }
    public void setType(Integer type) {
            this.type = type;
    }

    public Integer getType() {
            return type;
    }
    public void setBuyer(String buyer) {
            this.buyer = buyer;
    }

    public String getBuyer() {
            return buyer;
    }
    public void setPlatform(Integer platform) {
            this.platform = platform;
    }

    public Integer getPlatform() {
            return platform;
    }
    public void setPrice(Double price) {
            this.price = price;
    }

    public Double getPrice() {
            return price;
    }
    public void setUnits(Integer units) {
            this.units = units;
    }

    public Integer getUnits() {
            return units;
    }
    public void setAmount(Long amount) {
            this.amount = amount;
    }

    public Long getAmount() {
            return amount;
    }
    public void setMoney(Double money) {
            this.money = money;
    }

    public Double getMoney() {
            return money;
    }
    public void setCreateTime(Date createTime) {
            this.createTime = createTime;
    }

    public Date getCreateTime() {
            return createTime;
    }
    public void setRemark(String remark) {
            this.remark = remark;
    }

    public String getRemark() {
            return remark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id",getId())
                .append("farmId",getFarmId())
                .append("farmName",getFarmName())
                .append("type",getType())
                .append("buyer",getBuyer())
                .append("platform",getPlatform())
                .append("price",getPrice())
                .append("units",getUnits())
                .append("amount",getAmount())
                .append("money",getMoney())
                .append("createTime",getCreateTime())
                .append("remark",getRemark())
                .toString();
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }
}
