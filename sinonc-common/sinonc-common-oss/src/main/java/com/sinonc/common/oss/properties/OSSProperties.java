package com.sinonc.common.oss.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("oss")
public class OSSProperties {

    /**
     * 是否开启OSS
     */
    private boolean enable;
    //oss名称：aliyun 或者 minio
    private String name;
    //接入key： aliyun为key-id; minio为accessKey
    private String key;
    //接入密钥：aliyun为key-secret； minio为accessSecret
    private String secret;
    private String endpoint;
    private String bucket;
    private String upUrl;
    private String maxSize;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getUpUrl() {
        return upUrl;
    }

    public void setUpUrl(String upUrl) {
        this.upUrl = upUrl;
    }

    public String getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(String maxSize) {
        this.maxSize = maxSize;
    }
}


