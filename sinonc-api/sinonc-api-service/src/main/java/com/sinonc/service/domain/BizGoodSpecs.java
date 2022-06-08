package com.sinonc.service.domain;

import com.sinonc.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 商品规格对象 biz_good_specs
 *
 * @author ruoyi
 * @date 2020-07-30
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel("商品规格")
public class BizGoodSpecs extends BaseEntity {

    /** 主键 */
    @ApiModelProperty(value = "主键")
    private Long specsId;

    /** 商品ID */
    @ApiModelProperty(value="商品ID",hidden = true)
    private Long infoIdP;

    /** 规格名称 */
    @ApiModelProperty(value="规格名称")
    private String specsName;

    /** 规格单价 */
    @ApiModelProperty(value="规格单价")
    private BigDecimal specsPrice;

    /** 库存 */
    @ApiModelProperty(value="库存")
    private Integer stock;

    /** 规格图片 */
    @ApiModelProperty(value="规格图片",hidden = true)
    private String imagesUrl;

    /** 是否已删除 (0 是 1不是) */
    @ApiModelProperty(value="是否已删除 ",hidden = true)
    private String delFlag;

}
