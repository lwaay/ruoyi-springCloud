package com.sinonc.iot.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/5/21 15:23
 */
@Data
public class ProIrrigationLog {

    /**
     * 主键
     */
    private Long logId;

    /**
     * 灌溉开始时间
     */
    @ApiModelProperty(value = "灌溉开始时间")
    @JsonFormat(pattern = "MM-dd HH:mm")
    @Excel(name = "灌溉开始时间", dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /**
     * 灌溉结束时间
     */
    @ApiModelProperty(value = "灌溉结束时间")
    @JsonFormat(pattern = "MM-dd HH:mm")
    @Excel(name = "灌溉结束时间", dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /**
     * 灌溉量
     */
    @ApiModelProperty(value = "灌溉量")
    @Excel(name = "灌溉量")
    private BigDecimal flow;

    /**
     * 开始总流量
     */
    @ApiModelProperty(value = "开始总流量")
    @Excel(name = "开始总流量")
    private BigDecimal startFlow;

    /**
     * 结束总流量
     */
    @ApiModelProperty(value = "结束总流量")
    @Excel(name = "结束总流量")
    private BigDecimal endFlow;

    /**
     * 开始ec值
     */
    @ApiModelProperty(value = "开始ec值")
    @Excel(name = "开始ec值")
    private Integer startEc;

    /**
     * 结束ec值
     */
    @ApiModelProperty(value = "结束ec值")
    @Excel(name = "结束ec值")
    private Integer endEc;

    /**
     * 开始ph值
     */
    @ApiModelProperty(value = "开始ph值")
    @Excel(name = "开始ph值")
    private Integer startPh;

    /**
     * 结束ph值
     */
    @ApiModelProperty(value = "结束ph值")
    @Excel(name = "结束ph值")
    private Integer endPh;

    /**
     * a肥灌溉量
     */
    @ApiModelProperty(value = "a肥灌溉量")
    @Excel(name = "a肥灌溉量")
    private BigDecimal aFlow;

    /**
     * a肥开始总流量
     */
    @ApiModelProperty(value = "a肥开始总流量")
    @Excel(name = "a肥开始总流量")
    private BigDecimal startAFlow;

    /**
     * a肥结束总流量
     */
    @ApiModelProperty(value = "a肥结束总流量")
    @Excel(name = "a肥结束总流量")
    private BigDecimal endAFlow;

    /**
     * a肥比例
     */
    @ApiModelProperty(value = "a肥比例")
    @Excel(name = "a肥比例")
    private BigDecimal aRate;

    /**
     * b肥灌溉量
     */
    @ApiModelProperty(value = "b肥灌溉量")
    @Excel(name = "b肥灌溉量")
    private BigDecimal bFlow;

    /**
     * b肥开始总流量
     */
    @ApiModelProperty(value = "b肥开始总流量")
    @Excel(name = "b肥开始总流量")
    private BigDecimal startBFlow;

    /**
     * b肥结束总流量
     */
    @ApiModelProperty(value = "b肥结束总流量")
    @Excel(name = "b肥结束总流量")
    private BigDecimal endBFlow;

    /**
     * b肥比例
     */
    @ApiModelProperty(value = "b肥比例")
    @Excel(name = "b肥比例")
    private BigDecimal bRate;

    /**
     * 开始吸酸管道累计吸肥量
     */
    @ApiModelProperty(value = "开始吸酸管道累计吸肥量")
    @Excel(name = "开始吸酸管道累计吸肥量")
    private BigDecimal startAcidAbsorb;

    /**
     * 结束吸酸管道累计吸肥量
     */
    @ApiModelProperty(value = "结束吸酸管道累计吸肥量")
    @Excel(name = "结束吸酸管道累计吸肥量")
    private BigDecimal endAcidAbsorb;

    /**
     * 吸酸管道累计吸肥量(本次)
     */
    @ApiModelProperty(value = "结束吸酸管道累计吸肥量")
    @Excel(name = "结束吸酸管道累计吸肥量")
    private BigDecimal acidAbsorbUsed;

    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
