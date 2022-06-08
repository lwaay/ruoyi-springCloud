package com.sinonc.common.payment.wechat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

/**
 * 支付订单信息参数模型
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JacksonXmlRootElement(localName = "xml")
public class WechatPayRequestModel {

    /**
     * 应用ID
     */
//    @JacksonXmlCData //需要CDATA标签包裹
    @JacksonXmlProperty(localName = "appid")
    private String appid;

    /**
     * 商户号
     */
    @JacksonXmlProperty(localName ="mch_id" )
    private String mch_id;

    /**
     * 随机字符串
     */
    @JacksonXmlProperty(localName ="nonce_str" )
    private String nonce_str;

    /**
     * 签名
     */
    @JacksonXmlProperty(localName ="sign")
    private String sign;

    /**
     * 签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
     */
    @JacksonXmlProperty(localName ="sign_type")
    private String sign_type = "MD5";

    /**
     * 商品描述交易字段格式根据不同的应用场景按照以下格式：
     *
     * APP——需传入应用市场上的APP名字-实际商品名称，天天爱消除-游戏充值。
     */
    @JacksonXmlProperty(localName ="body")
    private String body;

    /**
     * 商户系统内部订单号
     */
    @JacksonXmlProperty(localName ="out_trade_no")
    private String out_trade_no;

    /**
     * 订单总金额，单位为分
     */
    @JacksonXmlProperty(localName ="total_fee")
    private String total_fee;

    /**
     * 终端IP
     */
    @JacksonXmlProperty(localName ="spbill_create_ip")
    private String spbill_create_ip;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "notify_url")
    private String notify_url;

    /**
     * 交易类型
     */
    @JacksonXmlProperty(localName ="trade_type")
    private String trade_type = "APP";

    /**
     * 商家自定义数据
     */
    @JacksonXmlProperty(localName = "attach")
    private String attach;
}
