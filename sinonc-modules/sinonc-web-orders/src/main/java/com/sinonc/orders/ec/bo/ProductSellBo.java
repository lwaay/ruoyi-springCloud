package com.sinonc.orders.ec.bo;


import com.sinonc.orders.ec.vo.ProductSellVo;
import lombok.Data;

import java.util.List;

/**
 * 电商品类销售对比分析
 * @author lw
 */
@Data
public class ProductSellBo {

    /**
     * 品类销售额占比
     */
    private List<ProductSellVo> duckList;

    /**
     * 品类销量占比
     */
    private List<ProductSellVo> brandList;

    /**
     * 品类产品数占比
     */
    private List<ProductSellVo> goodsList;


}
