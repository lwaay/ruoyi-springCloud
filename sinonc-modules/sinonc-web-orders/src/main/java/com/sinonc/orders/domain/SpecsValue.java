package com.sinonc.orders.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.ibatis.type.Alias;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 规格属性表 od_specs_value
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Alias("SpecsValue")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SpecsValue {

    private static final long serialVersionUID = 1L;

    /** 主键自增 */
    private Long specsValueId;
    /** 规格id */
    private Long specsId;
    /** 规格属性值 */
    private String specsValue;
    /** 用户id */
    private String createBy;
    /** 创建时间 */
    private Date createTime;
    /** 修改时间 */
    private Date updateTime;
    /**数量*/
    private Integer numbers;
    /**单位*/
    private String unit;
    /**服务类型*/
    private Integer type;
    /**店铺ID*/
    private Long shopId;
    /**规格所属数量*/
    private Integer guiNumbers;

    public Integer getGuiNumbers() {
        return guiNumbers;
    }

    public void setGuiNumbers(Integer guiNumbers) {
        this.guiNumbers = guiNumbers;
    }

    private Map<String, String> params;

    public Map<String, String> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getNumber() {
        return numbers;
    }

    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }

    public String getUnit() {
        return unit;
    }

    public void setNumber(Integer number) {
        this.numbers = number;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setSpecsValueId(Long specsValueId) {
            this.specsValueId = specsValueId;
    }

    public Long getSpecsValueId() {
            return specsValueId;
    }
    public void setSpecsId(Long specsId) {
            this.specsId = specsId;
    }

    public Long getSpecsId() {
            return specsId;
    }
    public void setSpecsValue(String specsValue) {
            this.specsValue = specsValue;
    }

    public String getSpecsValue() {
            return specsValue;
    }
    public void setCreateBy(String createBy) {
            this.createBy = createBy;
    }

    public String getCreateBy() {
            return createBy;
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

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return "SpecsValue{" +
                "specsValueId=" + specsValueId +
                ", specsId=" + specsId +
                ", specsValue='" + specsValue + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", numbers=" + numbers +
                ", unit='" + unit + '\'' +
                ", type=" + type +
                '}';
    }
}
