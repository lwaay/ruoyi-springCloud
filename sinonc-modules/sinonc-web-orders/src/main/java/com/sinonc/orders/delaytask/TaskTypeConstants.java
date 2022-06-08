package com.sinonc.orders.delaytask;

/**
 * 延迟任务类型
 */
public class TaskTypeConstants {

    /**
     * 等待支付
     */
    public static final Integer WAIT_PAY_TASK = 0;

    /**
     * 自动确认收货
     */
    public static final Integer AUTO_CONFIRM_TASK = 1;

    /**
     * 预订活动开始
     */
    public static final Integer BOOK_START_TASK = 2;

    /**
     * 预订活动结束
     */
    public static final Integer BOOK_END_TASK = 3;

    /**
     * 竞拍活动开始
     */
    public static final Integer AUCTION_START_TASK = 4;

    /**
     * 竞拍活动结束
     */
    public static final Integer AUCTION_END_TASK = 5;


}
