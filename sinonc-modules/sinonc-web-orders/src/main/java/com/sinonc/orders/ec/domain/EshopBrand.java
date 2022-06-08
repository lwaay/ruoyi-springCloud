package com.sinonc.orders.ec.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 大屏自定义品牌对象 eshop_brand
 *
 * @author ruoyi
 * @date 2021-03-31
 */
public class EshopBrand extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long brandId;

    /**
     * 品牌名称
     */
    @Excel(name = "品牌名称")
    private String brandName;

    /**
     * 是否过滤产地为梁平
     */
    @Excel(name = "是否过滤产地为梁平")
    private String isFilter;

    /**
     * 查询关键字
     */
    @Excel(name = "查询关键字")
    private String brands;

    /**
     * 状态(0显示，1隐藏)
     */
    @Excel(name = "状态(0显示，1隐藏)")
    private String status;

    /**
     * 是否默认（0否，1是）
     */
    @Excel(name = "是否默认", readConverterExp = "0=否，1是")
    private String defaultType;

    public String getIsFilter() {
        return isFilter;
    }

    public void setIsFilter(String isFilter) {
        this.isFilter = isFilter;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrands(String brands) {
        this.brands = brands;
    }

    public String getBrands() {
        return brands;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setDefaultType(String defaultType) {
        this.defaultType = defaultType;
    }

    public String getDefaultType() {
        return defaultType;
    }

    public EshopBrand(){}

    public EshopBrand(String status){
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("brandId", getBrandId())
                .append("brandName", getBrandName())
                .append("isFilter", getIsFilter())
                .append("brands", getBrands())
                .append("status", getStatus())
                .append("defaultType", getDefaultType())
                .append("createTime", getCreateTime())
                .append("createBy", getCreateBy())
                .toString();
    }
}
