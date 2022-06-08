package com.sinonc.orders.common;

/**
 * 优惠券常量类
 */
public class CouponConstant {

    //已删除
    public static final int DEL_FLAG_TRUE = 1;

    //未删除
    public static final int DEL_FLAG_FALSE = 0;

    //领取方式，主动领取
    public static final int GET_TYPE_SELF = 0;

    //领取方式，店铺发放
    public static final int GET_TYPE_SHOP = 1;

    //领取方式，平台发放
    public static final int GET_TYPE_PLATFORM = 2;

    //状态,已使用
    public static final int STATUS_USED = 1;

    //状态，未使用
    public static final int STATUS_UNUSE = 0;

    //状态，已过期
    public static final int STATUS_EXPIRED = 2;

    //优惠券类型 0，店铺通用券；1，店铺指定商品券；2，平台通用券；3，平台指定商品券
    public static final int TYPE_SHOP_COMMON = 0;

    public static final int TYPE_SHOP_SPECIALLY = 1;

    public static final int TYPE_PLATFORM_COMMON = 2;

    public static final int TYPE_PLATFORM_SPECIALLY = 3;
}
