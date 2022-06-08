package com.sinonc.iot.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/1/24 16:08
 */
@Configuration
@ConfigurationProperties("iot")
public class IotConfig {

    //host
    private String host;
    //appKey
    private String appKey;
    //appSecret
    private String appSecret;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
