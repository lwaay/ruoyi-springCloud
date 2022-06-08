package com.sinonc.orders.common;

/**
 * 订单类目状态
 */
public class OrderItemConstant {

    /**
     * 订单中商品状态，正常
     */
    public static final int STATUS_NORMAL = 0;

    /**
     * 订单中商品状态，退款中
     */
    public static final int STATUS_REFUNDING = 1;

    /**
     * 订单中商品状态，退款成功
     */
    public static final int STATUS_REFUND_SUCCESSFUL = 2;

    /**
     * 订单中商品状态，退款拒绝
     */
    public static final int STATUS_REFUND_REFUSE = 3;


}
