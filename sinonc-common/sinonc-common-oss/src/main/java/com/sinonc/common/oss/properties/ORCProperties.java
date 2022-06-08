package com.sinonc.common.oss.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("ocr")
public class ORCProperties {
    private String enable;
    private String key;
    private String secret;
    private String bucket;

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }
}


