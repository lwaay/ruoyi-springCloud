package com.sinonc.origins.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * 产业数据(大屏)对象 display_industry_data
 *
 * @author ruoyi
 * @date 2022-04-18
 */
@Data
public class IndustryData extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 区域代码
     */
    @Excel(name = "区域代码")
    private String areaCode;

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 年份
     */
    @Excel(name = "年份")
    private String year;

    /**
     * 面积（亩）
     */
    @Excel(name = "面积（亩）")
    private String area;

    /**
     * 种植规模(亩)
     */
    @Excel(name = "种植规模(亩)")
    private String plantScale;

    /**
     * 产量(吨)
     */
    @Excel(name = "产量(吨)")
    private String yield;

    /**
     * 产值(万元)
     */
    @Excel(name = "产值(万元)")
    private String value;

    /**
     * 单产（公斤）
     */
    @Excel(name = "单产（公斤）")
    private String unitYield;

    /**
     * 单价（元/公斤）
     */
    @Excel(name = "单价（元/公斤）")
    private String unitValue;

    /**
     * 品类
     */
    @Excel(name = "品类")
    private String breed;

    /**
     * 操作人
     */
    @Excel(name = "操作人")
    private String operator;

}
