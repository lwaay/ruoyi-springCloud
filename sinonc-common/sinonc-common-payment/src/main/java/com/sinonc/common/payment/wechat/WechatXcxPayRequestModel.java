package com.sinonc.common.payment.wechat;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

/**
 * 微信小程序支付请求类
 */
@Data
public class WechatXcxPayRequestModel extends WechatPayRequestModel {

    /**
     * 用户标识
     */
    @JacksonXmlProperty(localName = "openid")
    private String openid;

}
