package com.sinonc.common.payment.wechat;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

/**
 * 响应App，让APP调起微信支付
 */
@Data
@JacksonXmlRootElement(localName = "xml")
public class WechatAppResp {
    /**
     * 应用ID	appid	String(32)	是	wx8888888888888888	微信开放平台审核通过的应用APPID
     * 商户号	partnerid	String(32)	是	1900000109	微信支付分配的商户号
     * 预支付交易会话ID	prepayid	String(32)	是	WX1217752501201407033233368018	微信返回的支付交易会话ID
     * 扩展字段	package	String(128)	是	Sign=WXPay	暂填写固定值Sign=WXPay
     * 随机字符串	noncestr	String(32)	是	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	随机字符串，不长于32位。推荐随机数生成算法
     * 时间戳	timestamp	String(10)	是	1412000000	时间戳，请见接口规则-参数规定
     * 签名	sign	String(32)	是	C380BEC2BFD727A4B6845133519F3AD6	签名，详见签名生成算法注意：签名方式一定要与统一下单接口使用的一致
     */

    @JacksonXmlProperty(localName ="appid")
    private String appid;
    @JacksonXmlProperty(localName ="partnerid")
    private String partnerid;
    @JacksonXmlProperty(localName ="prepayid")
    private String prepayid;
    @JacksonXmlProperty(localName ="package")
    private String Package="Sign=WXPay";
    @JacksonXmlProperty(localName ="noncestr")
    private String noncestr;
    @JacksonXmlProperty(localName ="sign")
    private String sign;
    @JacksonXmlProperty(localName ="timestamp")
    private Long timestamp;


}
