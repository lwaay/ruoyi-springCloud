package com.sinonc.orders.common;

/**
 * 订单常量类
 */
public class OrderConstant {

    //已删除
    public static final int DEL_FLAG_TRUE = 1;

    //未删除
    public static final int DEL_FLAG_FALSE = 0;

    //待付款
    public static final String STATUS_WAITING_FOR_PAYMENT = "0";

    //待发货
    public static final String STATUS_WAITING_FOR_DELIVERY = "1";

    //待确认收货
    public static final String STATUS_WAITING_FOR_CONFIRMATION = "2";

    //待确认评价
    public static final String STATUS_WAITING_FOR_EVALUATION = "3";

    //交易成功
    public static final String STATUS_TRADE_SUCCESSFUL = "4";

    //交易关闭
    public static final String STATUS_TRADE_CLOSED = "5";

    //退款中
    public static final String STATUS_WAITING_FOR_REFUND = "6";

    //退款成功
    public static final String STATUS_REFUND_SUCCESSFUL = "7";

    //认养订单
    public static final int TYPE_ADOPT = 0;

    //交易订单
    public static final int TYPE_TRADE = 1;

    //预订订单
    public static final int TYPE_BOOK = 2;

    //未结算
    public static final int UN_SETTLEMENT = 0;

    //已结算
    public static final int IS_SETTLEMENT = 1;

    //部分结算
    public static final int PART_SETTLEMENT = 2;
}
