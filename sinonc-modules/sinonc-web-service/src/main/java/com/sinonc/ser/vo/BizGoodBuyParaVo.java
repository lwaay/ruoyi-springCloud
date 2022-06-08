package com.sinonc.ser.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhangxinlong
 */
@Data
@ApiModel(value = "采购商品参数")
public class BizGoodBuyParaVo {

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
    @ApiModelProperty(value="会员ID，当查询我自己的求购时传入")
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
     * 优选(1优选 2普通)
     */
    @ApiModelProperty(value = "优选(1优选 2普通)")
    private String preference;

    /**
     * 最小采购数量
     */
    @ApiModelProperty(value = "最小采购重量")
    private Integer minBuyWeight;

    /**
     * 最大采购数量
     */
    @ApiModelProperty(value = "最大采购重量")
    private Integer maxBuyWeight;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String infoName;
}
