package com.sinonc.orders.common;

/**
 * 交易流水操作码
 */
public class TradeCodeConstant {

    /**
     * 订单收入
     */
    public static final String ORDER_IN = "0000";

    /**
     * 订单支出
     */
    public static final String ORDER_OUT = "0001";

    /**
     * 交易手续费支出
     */
    public static final String TRADE_FEE_OUT = "0101";

    /**
     * 交易手续费收入
     */
    public static final String TRADE_FEE_IN = "0100";

    /**
     * 预订定金收入
     */
    public static final String ORDER_EARNEST_IN = "0200";

    /**
     * 预订定金支出
     */
    public static final String ORDER_EARNEST_OUT = "0201";

    /**
     * 交易尾款收入
     */
    public static final String ORDER_REST_IN = "0300";

    /**
     * 交易尾款退款
     */
    public static final String ORDER_REST_OUT = "0301";

    /**
     * 账户结算收入
     */
    public static final String ACCOUNT_SETTLE_IN = "0400";

    /**
     * 账户结算支出
     */
    public static final String ACCOUNT_SETTLE_OUT = "0401";


    /**
     * 账户提现申请
     */
    public static final String ACCOUNT_ASK_FOR = "0502";

    /**
     * 账户提现
     */
    public static final String ACCOUNT_WITHDRAW = "0501";

    /**
     * 提现失败，金额回滚
     */
    public static final String ACCOUNT_ROLLBACK = "0500";
}
