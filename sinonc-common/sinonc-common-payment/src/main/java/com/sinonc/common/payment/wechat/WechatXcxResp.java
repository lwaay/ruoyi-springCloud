package com.sinonc.common.payment.wechat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 微信小程序支付响应数据
 */
@Data
public class WechatXcxResp {

    /**
     * 小程序ID	appId	是	String	wxd678efh567hg6787	微信分配的小程序ID
     * 时间戳	timeStamp	是	String	1490840662	时间戳从1970年1月1日00:00:00至今的秒数,即当前的时间
     * 随机串	nonceStr	是	String	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	随机字符串，不长于32位。推荐随机数生成算法
     * 数据包	package	是	String	prepay_id=wx2017033010242291fcfe0db70013231072	统一下单接口返回的 prepay_id 参数值，提交格式如：prepay_id=wx2017033010242291fcfe0db70013231072
     * 签名方式	signType	是	String	MD5	签名类型，默认为MD5，支持HMAC-SHA256和MD5。注意此处需与统一下单的签名类型一致
     * 签名   paySign  是  具体签名方案参见微信公众号支付帮助文档;
     */

    /**
     * 小程序APPID
     */
    private String appId;

    private Long timeStamp;

    private String nonceStr;

    @JsonProperty("package")
    private String Package;

    private String signType = "MD5";

    private String paySign;
}
