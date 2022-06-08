package com.sinonc.common.payment.wechat;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

/**
 * 微信支付退款请求model
 *
 */
@Data
@JacksonXmlRootElement(localName = "xml")
public class WechatRefundRequestModel {

    private String appid;

    private String mch_id;

    private String nonce_str;

    private String sign;

    private String sign_type = "MD5";

    /**
     * 商户订单号(本系统orderNo)
     */
    private String out_trade_no;

    /**
     * 商户退款单号
     */
    private String out_refund_no;

    /**
     * 订单金额
     */
    private String total_fee;

    /**
     * 退款金额
     */
    private String refund_fee;

    /**
     * 退款原因
     *
     */
    private String refund_desc;

    /**
     * 异步通知
     */
    private String notify_url;
}
