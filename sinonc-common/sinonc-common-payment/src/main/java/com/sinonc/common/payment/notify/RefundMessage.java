package com.sinonc.common.payment.notify;

import lombok.Data;

@Data
public class RefundMessage {

    /**
     * 0,微信，1，支付宝
     */
    private Integer payType;

    /**
     * 系统内部订单号
     */
    private String orderNo;

    /**
     * 系统内部退款单号
     */
    private String refundNo;

    /**
     * 支付平台交易号
     */
    private String outTradeNo;

    /**
     * 退款总金额
     */
    private String refundAmount;

    /**
     * 交易平台退还的交易费金额
     * 备注： 微信发生退款操作时，会退还交易费
     *       支付宝交易，不会退还交易手续费
     */
    private String refundFee;


    /**
     * 实际交易金额（退款金额+退还的手续费金额）
     */
    private String receiptAmount;

    /**
     * 退款原因
     */
    private String refundReason;

    /**
     * 平台交易结果
     */
    private String tradeResult;


    /**
     * 店铺ID
     */
    private String params;


}
