package com.sinonc.iot.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppInfoConfing {

    @Value("${iot.appKey}")
    private String appId;
    @Value("${iot.appSecret}")
    private String appSecret;
    @Value("${iot.host}")
    private String iotHost;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getIotHost() {
        return iotHost;
    }

    public void setIotHost(String iotHost) {
        this.iotHost = iotHost;
    }
}
