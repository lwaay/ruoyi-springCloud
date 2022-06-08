package com.sinonc.orders.dto;


import com.sinonc.common.core.annotation.Excel;
import lombok.Data;

/**
 * 购物车对象 od_cart
 *
 * @author ruoyi
 * @date 2022-03-28
 */
@Data
public class CartDto{

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id
     */
    @Excel(name = "用户id")
    private Long memberId;

    /**
     * 商品店铺id
     */
    @Excel(name = "商品店铺id")
    private Long shopIdP;

    /**
     * 商品id
     */
    @Excel(name = "商品id")
    private Long goodsIdP;

    /**
     * 商品类型
     */
    @Excel(name = "商品类型")
    private Integer goodsType;

    /**
     * 商品规格id
     */
    @Excel(name = "商品规格id")
    private Long goodsSpecsIdP;

    /**
     * 购物车数量
     */
    @Excel(name = "购物车数量")
    private Integer cartNum;
}
