package com.sinonc.orders.ec.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 农产品销售信息对象 farm_produce_sale
 *
 * @author ruoyi
 * @date 2021-08-11
 */
public class FarmProduceSale extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long saleId;

    /**
     * 销售品种（字典）
     */
    @Excel(name = "销售品种")
    private String produceType;

    /**
     * 单价（元）
     */
    @Excel(name = "单价")
    private BigDecimal unitPrice;

    /**
     * 销量
     */
    @Excel(name = "销量 ")
    private BigDecimal saleVol;

    /**
     * 单位（字典）
     */
    @Excel(name = "单位")
    private String unitName;

    /**
     * 收入 (元)
     */
    @Excel(name = "收入 (元)")
    private BigDecimal income;

    /**
     * 创建人ID
     */
    private Long createbyId;

    /**
     * 更新人ID
     */
    private Long updatebyId;

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setProduceType(String produceType) {
        this.produceType = produceType;
    }

    public String getProduceType() {
        return produceType;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setSaleVol(BigDecimal saleVol) {
        this.saleVol = saleVol;
    }

    public BigDecimal getSaleVol() {
        return saleVol;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setCreatebyId(Long createbyId) {
        this.createbyId = createbyId;
    }

    public Long getCreatebyId() {
        return createbyId;
    }

    public void setUpdatebyId(Long updatebyId) {
        this.updatebyId = updatebyId;
    }

    public Long getUpdatebyId() {
        return updatebyId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("saleId", getSaleId())
                .append("produceType", getProduceType())
                .append("unitPrice", getUnitPrice())
                .append("saleVol", getSaleVol())
                .append("unitName", getUnitName())
                .append("income", getIncome())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("createbyId", getCreatebyId())
                .append("updatebyId", getUpdatebyId())
                .append("remark", getRemark())
                .toString();
    }
}
