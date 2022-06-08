package com.sinonc.orders.ec.vo;

import lombok.Builder;
import lombok.Data;


/**
 * @author huanghao
 * @apiNote 实时数据开启商品类目
 * @date 2021/3/22 15:13
 */
@Data
@Builder
public class RealTimeProductCategoryVo {
    private Long goodsId;
    private String productType;
}
