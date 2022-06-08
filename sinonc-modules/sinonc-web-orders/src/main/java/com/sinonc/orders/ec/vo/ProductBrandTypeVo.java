package com.sinonc.orders.ec.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * @author huanghao
 * @apiNote 品牌销售额折现图
 * @date 2020/8/15 15:38
 */
@ApiModel(value = "品牌销售额折现图")
@Data
public class ProductBrandTypeVo {
    @ApiModelProperty(value = "x 轴")
    private List<String> x;
    @ApiModelProperty(value = "类别")
    private Set<String> type;
    @ApiModelProperty(value = "销售额")
    private List<List<BigDecimal>> salePrice;
}
