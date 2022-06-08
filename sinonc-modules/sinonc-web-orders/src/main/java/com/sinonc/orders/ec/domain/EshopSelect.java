package com.sinonc.orders.ec.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 品牌展示对象 eshop_select
 *
 * @author ruoyi
 * @date 2020-11-10
 */
public class EshopSelect extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 查询字段
     */
    @Excel(name = "查询字段")
    private String brand;

    /**
     * 是否显示（0不显示，1显示）
     */
    @Excel(name = "是否显示", readConverterExp = "0=不显示，1显示")
    private Integer show;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setShow(Integer show) {
        this.show = show;
    }

    public Integer getShow() {
        return show;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("brand", getBrand())
                .append("show", getShow())
                .toString();
    }

    public EshopSelect(){}

    public EshopSelect(Integer show){
        this.show = show;
    }
}
