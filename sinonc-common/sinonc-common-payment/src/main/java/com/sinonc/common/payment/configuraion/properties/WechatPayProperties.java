package com.sinonc.common.payment.configuraion.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "pay.wechat")
@Data
public class WechatPayProperties {

    /**
     * 支付网关地址
     */
    private String url;

    /**
     * 退款网关
     */
    private String refundUrl;

    /**
     *  APP应用ID
     */
    private String appid;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户平台设置的密钥key
     */
    private String key;

    /**
     * 支付证书路径
     */
    private String certPath;

    /**
     * 微信商户支付申请时App名称
     */
    private String name;

    /**
     * 支付回调地址
     */
    private String notifyUrl;

    /**
     * 平台交易费率
     */
    private String tradeRate;
}
