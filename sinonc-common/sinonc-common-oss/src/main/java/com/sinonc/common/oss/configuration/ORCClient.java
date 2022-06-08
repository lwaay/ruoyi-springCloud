package com.sinonc.common.oss.configuration;

import com.aliyun.ocr20191230.Client;
import com.aliyun.teaopenapi.models.Config;
import com.sinonc.common.oss.properties.ORCProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "ocr", name = "enable", havingValue = "true")
public class ORCClient {

    @Autowired
    private ORCProperties properties;

    @Bean
    public com.aliyun.ocr20191230.Client initClient() throws Exception{
        Config config = new Config().setAccessKeyId(properties.getKey()).setAccessKeySecret(properties.getSecret());
        // 访问的域名
        config.endpoint = properties.getBucket();
        return new Client(config);
    }
}
