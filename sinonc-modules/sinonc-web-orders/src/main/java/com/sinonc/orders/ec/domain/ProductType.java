package com.sinonc.orders.ec.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 大屏自定义品类对象 product_type
 *
 * @author ruoyi
 * @date 2021-03-31
 */
public class ProductType extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long typeId;

    /**
     * 品类名称
     */
    @Excel(name = "品类名称")
    private String typeName;

    /**
     * 是否过滤产地为梁平
     */
    @Excel(name = "是否过滤产地为梁平")
    private String isFilter;

    /**
     * 查询关键字
     */
    @Excel(name = "查询关键字")
    private String productType;

    /**
     * 状态
     */
    @Excel(name = "状态")
    private String status;

    /** 是否默认（0否，1是） */
    private String defaultType;

    public String getIsFilter() {
        return isFilter;
    }

    public void setIsFilter(String isFilter) {
        this.isFilter = isFilter;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductType() {
        return productType;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setDefaultType(String defaultType) {
        this.defaultType =defaultType;
    }

    public String getDefaultType() {
        return defaultType;
    }

    public ProductType(){}

    public ProductType(String status){
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("typeId", getTypeId())
                .append("typeName", getTypeName())
                .append("isFilter", getIsFilter())
                .append("productType", getProductType())
                .append("status", getStatus())
                .append("default", getDefaultType())
                .append("createTime", getCreateTime())
                .append("createBy", getCreateBy())
                .toString();
    }
}
