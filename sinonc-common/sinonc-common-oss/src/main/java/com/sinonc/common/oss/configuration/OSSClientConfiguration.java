package com.sinonc.common.oss.configuration;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.sinonc.common.oss.properties.OSSProperties;
import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "oss", name = "enable", havingValue = "true")
public class OSSClientConfiguration {

    @Autowired
    private OSSProperties ossProperties;

    @Bean
    @ConditionalOnProperty(prefix = "oss", name = "name", havingValue = "aliyun")
    public OSS ossClient() {
        return new OSSClientBuilder().build(ossProperties.getUpUrl(), ossProperties.getKey(), ossProperties.getSecret());
    }

    @Bean
    @ConditionalOnProperty(prefix = "oss", name = "name", havingValue = "minio")
    public MinioClient minioClient() throws InvalidPortException, InvalidEndpointException {
        return new MinioClient(ossProperties.getEndpoint(), ossProperties.getKey(), ossProperties.getSecret());
    }
}
