package com.sinonc.common.sms.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 阿里云短信配置类
 * @author Administrator
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "sms")
public class AliSmsProperties {

    private String RegionId = "cn-hangzhou";

    private String accessKeyId;

    private String accessSecret;

    private String signName;

    private String templateCode;

}
