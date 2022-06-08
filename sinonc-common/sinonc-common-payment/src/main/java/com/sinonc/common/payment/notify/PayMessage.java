package com.sinonc.common.payment.notify;

import lombok.Getter;
import lombok.Setter;

/**
 * 支付通知消息
 */
@Getter
@Setter
public class PayMessage {

    public static final int TYPE_PAY = 0;
    public static final int TYPE_REFUND = 1;

    /**
     * 支付方式，1，支付宝，0，微信
     */
    private Integer payType;

    /**
     * 交易类型，0，支付，1，退款
     */
    private Integer tradeType;

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 平台商户号
     */
    private String sellerId;

    /**
     * 平台交易结果
     */
    private String tradeResult;

    /**
     * 买家ID
     */
    private String buyerId;

    /**
     * 支付平台流水号
     */
    private String outTrade;

    /**
     * 系统内部订单号
     */
    private String orderNo;

    /**
     * 订单总金额
     */
    private String amount;

    /**
     * 实收金额（减扣交易平台服务费）
     */
    private String receiptAmount;

    /**
     * 平台交易费
     */
    private String tradeFee;

    /**
     * 自定义回传参数
     */
    private String params;

}
