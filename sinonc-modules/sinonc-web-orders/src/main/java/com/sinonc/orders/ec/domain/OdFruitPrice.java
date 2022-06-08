package com.sinonc.orders.ec.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 水果价格对象 od_fruit_price
 *
 * @author ruoyi
 * @date 2020-09-27
 */
public class OdFruitPrice extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 类别
     */
    @Excel(name = "类别")
    private String category;

    /**
     * 省份
     */
    @Excel(name = "省份")
    private String province;

    /**
     * 市场
     */
    @Excel(name = "市场")
    private String market;

    /**
     * 价格
     */
    @Excel(name = "价格")
    private String price;

    /**
     * 单位
     */
    @Excel(name = "单位")
    private String unit;

    /**
     * 昨日
     */
    @Excel(name = "昨日")
    private String yesterday;

    /**
     * 上周
     */
    @Excel(name = "上周")
    private String lastWeek;

    /**
     * 上月
     */
    @Excel(name = "上月")
    private String lastMonth;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvince() {
        return province;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getMarket() {
        return market;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public void setYesterday(String yesterday) {
        this.yesterday = yesterday;
    }

    public String getYesterday() {
        return yesterday;
    }

    public void setLastWeek(String lastWeek) {
        this.lastWeek = lastWeek;
    }

    public String getLastWeek() {
        return lastWeek;
    }

    public void setLastMonth(String lastMonth) {
        this.lastMonth = lastMonth;
    }

    public String getLastMonth() {
        return lastMonth;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("category", getCategory())
                .append("province", getProvince())
                .append("market", getMarket())
                .append("price", getPrice())
                .append("unit", getUnit())
                .append("createTime", getCreateTime())
                .append("yesterday", getYesterday())
                .append("lastWeek", getLastWeek())
                .append("lastMonth", getLastMonth())
                .toString();
    }
}
