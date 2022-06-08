package com.sinonc.orders.ec.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author huanghao
 * @apiNote 订单侠接口返回销售数据
 * @date 2021/3/16 14:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSaleDto {
    /**
     * 实时月销量，商品被拍下后计数
     */
    private Integer sale;
    /**
     * 交易成功销量，商品交易成功后计数
     */
    private Integer cout;
    /**
     * 剩余库存
     */
    private Integer stock;
}
