package com.sinonc.orders.vo;

import com.sinonc.orders.domain.Goods;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GoodsVo extends Goods implements Serializable {

    //商品ID
    @ApiModelProperty(value = "商品ID", required = true)
    private Long goodsId;

    //商品规格Id
    @ApiModelProperty(value = "商品规格Id", required = true)
    private Long specsId;

    //商品购买数量
    @ApiModelProperty(value = "商品购买数量", required = true)
    private int count;

}
