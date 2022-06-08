package com.sinonc.common.payment.configuraion.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "pay.alipay")
@Data
public class AliPayProperties {

    /**
     * 支付宝网关（固定）
     */
    private String url;

    /**
     * APPID 即创建应用后生成
     */
    private String appId;

    /**
     * 开发者应用私钥，由开发者自己生成
     */
    private String privateKey;

    /**
     * 支付宝公钥，由支付宝生成
     */
    private String publicKey;

    /**
     * 请求和签名使用的字符编码格式，支持 GBK 和 UTF-8
     */
    private String charset = "UTF-8";

    /**
     * 商户生成签名字符串所使用的签名算法类型，目前支持 RSA2 和 RSA，推荐使用 RSA2
     */
    private String signType = "RSA2";

    /**
     * 支付宝同步回调地址
     */
    private String returnUrl;

    /**
     * 支付宝异步回调地址
     */
    private String notifyUrl;

    /**
     * 平台交易费率
     */
    private String tradeRate;
}
