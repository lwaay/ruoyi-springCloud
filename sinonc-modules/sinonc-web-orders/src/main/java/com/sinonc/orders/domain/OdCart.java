package com.sinonc.orders.domain;

import java.math.BigDecimal;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 购物车对象 od_cart
 *
 * @author ruoyi
 * @date 2022-03-28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OdCart extends BaseEntity {
    private static final long serialVersionUID = 1L;

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
     * 店铺名称
     */
    @Excel(name = "店铺名称")
    private String shopNameP;

    /**
     * 商品id
     */
    @Excel(name = "商品id")
    private Long goodsIdP;


    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品图片
     */
    @Excel(name = "商品图片")
    private String goodsImg;

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
     * 规格名称
     */
    @Excel(name = "规格名称")
    private String goodsSpecsName;

    /**
     * 商品价格
     */
    @Excel(name = "商品价格")
    private BigDecimal goodsPrice;

    /**
     * 购物车数量
     */
    @Excel(name = "购物车数量")
    private Integer cartNum;

    /**
     * 是否购买
     */
    @Excel(name = "是否购买")
    private Integer isPay;

    /**
     * 是否删除
     */
    @Excel(name = "是否删除")
    private Integer isDel;

    /**
     * 是否失效
     */
    @Excel(name = "是否失效")
    private Integer isInvalid;
}
