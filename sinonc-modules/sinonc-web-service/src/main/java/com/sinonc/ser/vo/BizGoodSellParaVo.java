package com.sinonc.ser.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhangxinlong
 */
@Data
@ApiModel(value = "供应商品参数")
public class BizGoodSellParaVo {

    /**
     * 第几页
     */
    @ApiModelProperty(value="第几页")
    private Integer pageNum;

    /**
     * 分页大小
     */
    @ApiModelProperty(value="分页大小")
    private Integer pageSize;

    @ApiModelProperty(value = "排序方式")
    private String orderBy;

    /** 会员ID */
    @ApiModelProperty(value="会员ID")
    private Long memberIdP;

    /**
     * 品类多个,隔开
     */
    @ApiModelProperty(value = "品类多个,隔开")
    private String breeds;

    /**
     * 货源地址多个,隔开
     */
    @ApiModelProperty(value = "货源地址多个,隔开")
    private String shipAddress;

    /**
     * 商品规格多个,隔开
     */
    @ApiModelProperty(value = "商品规格多个,隔开")
    private String goodSpecs;

    /**
     * 热卖(1热卖 2普通)
     */
    @ApiModelProperty(value = "热卖(1热卖 2普通)")
    private String passion;

    /**
     * 价格区间最大值
     */
    @ApiModelProperty(value = "价格区间最大值")
    private BigDecimal maxPrice;

    /**
     * 价格区间最小值
     */
    @ApiModelProperty(value = "价格区间最小值")
    private BigDecimal minPrice;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String infoName;

}
