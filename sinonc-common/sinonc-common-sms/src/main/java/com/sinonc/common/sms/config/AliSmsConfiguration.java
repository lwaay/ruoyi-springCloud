package com.sinonc.common.sms.config;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.sinonc.common.sms.config.properties.AliSmsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * 阿里云短信接口配置
 */
@Configuration
@EnableConfigurationProperties({AliSmsProperties.class})
public class AliSmsConfiguration {

    @Autowired
    private AliSmsProperties properties;

    @Bean
    public IAcsClient client() {
        DefaultProfile profile = DefaultProfile.getProfile(properties.getRegionId(), properties.getAccessKeyId(), properties.getAccessSecret());
        return new DefaultAcsClient(profile);
    }
}
