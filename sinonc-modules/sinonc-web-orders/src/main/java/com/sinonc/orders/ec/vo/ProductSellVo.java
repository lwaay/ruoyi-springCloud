package com.sinonc.orders.ec.vo;

import lombok.Data;

@Data
public class ProductSellVo {

    /**
     * 商品名称
     */
    private String name;

    /**
     * 销售数量
     */
    private Double sellCount;
}
