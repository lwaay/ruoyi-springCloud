package com.sinonc.base.api.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 采购信息对象 origins_purchase
 *
 * @author ruoyi
 * @date 2021-08-31
 */
public class OriginsPurchase extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long purchaseId;

    /**
     * 产品种类
     */
    @Excel(name = "产品种类")
    private String proType;

    /**
     * 产品名称
     */
    @Excel(name = "产品名称")
    private String proName;

    /**
     * 供应商
     */
    @Excel(name = "供应商")
    private String supplier;

    /**
     * 采购数量
     */
    @Excel(name = "采购数量")
    private Integer purchaseNum;

    /**
     * 发货时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发货时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dyTime;

    /**
     * 采购人
     */
    @Excel(name = "采购人")
    private String purchaseBy;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remark;

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }

    public String getProType() {
        return proType;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProName() {
        return proName;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setPurchaseNum(Integer purchaseNum) {
        this.purchaseNum = purchaseNum;
    }

    public Integer getPurchaseNum() {
        return purchaseNum;
    }

    public void setDyTime(Date dyTime) {
        this.dyTime = dyTime;
    }

    public Date getDyTime() {
        return dyTime;
    }

    public void setPurchaseBy(String purchaseBy) {
        this.purchaseBy = purchaseBy;
    }

    public String getPurchaseBy() {
        return purchaseBy;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("purchaseId", getPurchaseId())
                .append("proType", getProType())
                .append("proName", getProName())
                .append("supplier", getSupplier())
                .append("purchaseNum", getPurchaseNum())
                .append("dyTime", getDyTime())
                .append("purchaseBy", getPurchaseBy())
                .append("remark", getRemark())
                .toString();
    }
}
