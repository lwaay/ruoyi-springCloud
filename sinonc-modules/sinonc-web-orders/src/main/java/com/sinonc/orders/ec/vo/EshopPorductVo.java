package com.sinonc.orders.ec.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author huanghao
 * @apiNote 电商农产品本月排行榜
 * @date 2020/8/15 11:41
 */
@Data
@ApiModel("电商农产品本月排行榜")
@AllArgsConstructor
public class EshopPorductVo {
   @ApiModelProperty(value = "来源")
   private String from;
   @ApiModelProperty(value = "名称")
   private String product;
   @ApiModelProperty(value = "价格")
   private BigDecimal price;
   @ApiModelProperty(value = "销量")
   private Integer orderNum;
   @ApiModelProperty(value = "销量额")
   private BigDecimal salePrice;
   @ApiModelProperty(value = "销售时间")
   private Date saleTime;

}
