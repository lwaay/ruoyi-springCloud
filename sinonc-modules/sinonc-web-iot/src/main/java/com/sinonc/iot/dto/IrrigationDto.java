package com.sinonc.iot.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/5/21 15:27
 */
@Data
public class IrrigationDto {

    private int pageNum;
    private int pageSize;

    /**
     * 主键
     */
    private Long logId;

    /**
     * 灌溉开始时间
     */
    @ApiModelProperty(value = "灌溉开始时间")
    private Date startTime;

    /**
     * 灌溉结束时间
     */
    @ApiModelProperty(value = "灌溉结束时间")
    private Date endTime;

    /**
     * 灌溉量
     */
    @ApiModelProperty(value = "灌溉量")
    private BigDecimal flow;

    /**
     * 开始总流量
     */
    @ApiModelProperty(value = "开始总流量")
    private BigDecimal startFlow;

    /**
     * 结束总流量
     */
    @ApiModelProperty(value = "结束总流量")
    private BigDecimal endFlow;

    /**
     * 开始ec值
     */
    @ApiModelProperty(value = "开始ec值")
    private Integer startEc;

    /**
     * 结束ec值
     */
    @ApiModelProperty(value = "结束ec值")
    private Integer endEc;

    /**
     * 开始ph值
     */
    @ApiModelProperty(value = "开始ph值")
    private Integer startPh;

    /**
     * 结束ph值
     */
    @ApiModelProperty(value = "结束ph值")
    private Integer endPh;

    /**
     * a肥灌溉量
     */
    @ApiModelProperty(value = "a肥灌溉量")
    private BigDecimal aFlow;

    /**
     * a肥开始总流量
     */
    @ApiModelProperty(value = "a肥开始总流量")
    private BigDecimal startAFlow;

    /**
     * a肥结束总流量
     */
    @ApiModelProperty(value = "a肥结束总流量")
    private BigDecimal endAFlow;

    /**
     * a肥比例
     */
    @ApiModelProperty(value = "a肥比例")
    private BigDecimal aRate;

    /**
     * b肥灌溉量
     */
    @ApiModelProperty(value = "b肥灌溉量")
    private BigDecimal bFlow;

    /**
     * b肥开始总流量
     */
    @ApiModelProperty(value = "b肥开始总流量")
    private BigDecimal startBFlow;

    /**
     * b肥结束总流量
     */
    @ApiModelProperty(value = "b肥结束总流量")
    private BigDecimal endBFlow;

    /**
     * b肥比例
     */
    @ApiModelProperty(value = "b肥比例")
    private BigDecimal bRate;

    /**
     * 开始吸酸管道累计吸肥量
     */
    @ApiModelProperty(value = "开始吸酸管道累计吸肥量")
    private BigDecimal startAcidAbsorb;

    /**
     * 结束吸酸管道累计吸肥量
     */
    @ApiModelProperty(value = "结束吸酸管道累计吸肥量")
    private BigDecimal endAcidAbsorb;

    /**
     * 吸酸管道累计吸肥量(本次)
     */
    @ApiModelProperty(value = "结束吸酸管道累计吸肥量")
    private BigDecimal acidAbsorbUsed;

}

