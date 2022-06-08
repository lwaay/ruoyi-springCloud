package com.sinonc.base.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * 病虫害出现周期对象 k_insect_period
 *
 * @author ruoyi
 * @date 2022-04-19
 */
@Data
public class KInsectPeriod extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 月份（1~12）
     */
    @Excel(name = "月份", readConverterExp = "1=~12")
    private Long periodMonth;

    /**
     * 月份描述
     */
    @Excel(name = "月份描述")
    private String description;

    /**
     * 节气描述
     */
    @Excel(name = "节气描述")
    private String solarTime;

    /**
     * 病虫周期（01-病虫潜伏越冬期，02-病虫潜伏期，03-病虫高峰期、04-病虫严重期）
     */
    @Excel(name = "病虫周期", readConverterExp = "0=1-病虫潜伏越冬期，02-病虫潜伏期，03-病虫高峰期、04-病虫严重期")
    private String period;

    /**
     * 常见病虫，以逗号分隔
     */
    @Excel(name = "常见病虫，以逗号分隔")
    private String insectCode;

    /**
     * 常用方案
     */
    @Excel(name = "常用方案")
    private String commSchema;

    /**
     * 创建人
     */
    private String createUser;

}
