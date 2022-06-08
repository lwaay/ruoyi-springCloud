package com.sinonc.common.payment.wechat;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

/**
 * 保存调用微信统一下单接口后响应的数据
 */
@Data
@JacksonXmlRootElement(localName = "xml")
public class WechatPayResponseModel {

    /**
     * 返回状态码
     */
    @JacksonXmlProperty(localName = "return_code")
    private String returnCode;

    /**
     * 返回信息
     */
    @JacksonXmlProperty(localName = "return_msg")
    private String returnMsg;

    /**
     * 应用APPID
     */
    @JacksonXmlProperty(localName = "appid")
    private String appid;

    /**
     * 商户号
     */
    @JacksonXmlProperty(localName = "mch_id")
    private String mchId;

    /**
     * 设备号
     */
    @JacksonXmlProperty(localName = "device_info")
    private String deviceInfo;

    /**
     * 随机字符串
     */
    @JacksonXmlProperty(localName = "nonce_str")
    private String nonce_str;

    /**
     * 签名
     */
    @JacksonXmlProperty(localName = "sign")
    private String sign;

    /**
     * 业务结果
     */
    @JacksonXmlProperty(localName = "result_code")
    private String resultCode;

    /**
     * 错误代码
     */

    @JacksonXmlProperty(localName = "err_code")
    private String errCode;

    /**
     * 错误代码描述
     */
    @JacksonXmlProperty(localName = "err_code_des")
    private String errCodeDes;


    /*
      以下字段在returnCode 和resultCode都为SUCCESS的时候有返回
     */

    /**
     * 交易类型
     */
    @JacksonXmlProperty(localName = "trade_type")
    private String tradeType;

    /**
     * 预支付交易会话标识
     */
    @JacksonXmlProperty(localName = "prepay_id")
    private String prepayId;

}
