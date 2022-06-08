package com.sinonc.origins.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 朔源访问对象 pro_visit
 *
 * @author ruoyi
 * @date 2020-10-21
 */
public class ProVisit extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long visitId;

    /**
     * 产品id
     */
    @Excel(name = "产品id")
    private Long productId;

    /**
     * 生产商
     */
    @Excel(name = "生产商")
    private String visitProduct;

    /**
     * 产品名
     */
    @Excel(name = "产品名")
    private String visitShopname;

    /**
     * 访问时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "访问时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date visitTime;

    /**
     * 来源地区
     */
    @Excel(name = "来源地区")
    private String visitCity;

    public void setVisitId(Long visitId) {
        this.visitId = visitId;
    }

    public Long getVisitId() {
        return visitId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setVisitProduct(String visitProduct) {
        this.visitProduct = visitProduct;
    }

    public String getVisitProduct() {
        return visitProduct;
    }

    public void setVisitShopname(String visitShopname) {
        this.visitShopname = visitShopname;
    }

    public String getVisitShopname() {
        return visitShopname;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitCity(String visitCity) {
        this.visitCity = visitCity;
    }

    public String getVisitCity() {
        return visitCity;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("visitId", getVisitId())
                .append("productId", getProductId())
                .append("visitProduct", getVisitProduct())
                .append("visitShopname", getVisitShopname())
                .append("visitTime", getVisitTime())
                .append("visitCity", getVisitCity())
                .toString();
    }
}
