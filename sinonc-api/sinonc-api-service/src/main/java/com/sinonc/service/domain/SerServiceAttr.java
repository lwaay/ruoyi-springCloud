package com.sinonc.service.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 社会化服务规格对象 ser_service_attr
 *
 * @author ruoyi
 * @date 2022-02-16
 */
public class SerServiceAttr extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long attrId;

    /**
     * 服务id
     */
    @Excel(name = "服务id")
    private Long serviceIdP;

    /**
     * 规格
     */
    @Excel(name = "规格")
    private String spec;

    /**
     * 起批量
     */
    @Excel(name = "起批量")
    private String startNum;

    /**
     * 价格
     */
    @Excel(name = "价格")
    private BigDecimal price;

    /**
     * 单位
     */
    @Excel(name = "单位")
    private String unit;

    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }

    public Long getAttrId() {
        return attrId;
    }

    public void setServiceIdP(Long serviceIdP) {
        this.serviceIdP = serviceIdP;
    }

    public Long getServiceIdP() {
        return serviceIdP;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getSpec() {
        return spec;
    }

    public void setStartNum(String startNum) {
        this.startNum = startNum;
    }

    public String getStartNum() {
        return startNum;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("attrId", getAttrId())
                .append("serviceIdP", getServiceIdP())
                .append("spec", getSpec())
                .append("startNum", getStartNum())
                .append("price", getPrice())
                .append("unit", getUnit())
                .toString();
    }
}
