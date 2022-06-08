package com.sinonc.ser.vo;

import com.sinonc.service.domain.BizGoodSell;
import com.sinonc.service.domain.BizGoodSpecs;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author zhangxl
 */
@Data
public class BizGoodSellVo extends BizGoodSell {

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long paraInfoId;

    /**
     * 供应商品ID
     */
    @ApiModelProperty(value = "供应商品ID")
    private Long paraSellId;


    /**
     * 会员ID
     */
    @ApiModelProperty(value = "会员ID")
    private Long memberIdP;

    @ApiModelProperty(value = "规格列表")
    private List<BizGoodSpecs> bizGoodSpecsList;

    /**
     * 商品名称
     */
    @ApiModelProperty(value="商品名称")
    private String infoName;

    /** 副标题 */
    @ApiModelProperty(value="副标题")
    private String subtitle;

    /** 品种 */
    @ApiModelProperty(value="品种")
    private String breed;

    /** 图片地址 */
    @ApiModelProperty(value="商品主图")
    private String imageUrl;

    /** 图文描述 */
    @ApiModelProperty(value="图文描述")
    private String content;

    /** 图片详情多个,隔开 */
    @ApiModelProperty(value = "图片详情多个,隔开")
    private String detailImages;

    /**
     * 上下架 (0上架 1下架)
     */
    @ApiModelProperty(value = "上下架 (0上架 1下架)")
    private String saleAble;
}
