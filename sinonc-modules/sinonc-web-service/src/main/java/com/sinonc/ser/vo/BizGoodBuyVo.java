package com.sinonc.ser.vo;

import com.sinonc.service.domain.BizGoodBuy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author zhangxinlong
 */
@Data
@ApiModel(value = "采购商品")
public class BizGoodBuyVo extends BizGoodBuy {

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long paraInfoId;

    /**
     * 采购商品ID
     */
    @ApiModelProperty(value = "采购商品ID")
    private Long paraBuyId;

    /**
     * 商品名称（求购标题）
     */
    @NotNull(message = "商品名称不能为空")
    @ApiModelProperty(value = "求购标题")
    private String infoName;

    /**
     * 品种
     */
    @NotNull(message = "品种不能为空")
    @ApiModelProperty(value = "求购品种")
    private String breed;

    /**
     * 上下架 (0上架 1下架)
     */
    @ApiModelProperty(value = "上下架 (0上架 1下架)")
    private String saleAble;

}
