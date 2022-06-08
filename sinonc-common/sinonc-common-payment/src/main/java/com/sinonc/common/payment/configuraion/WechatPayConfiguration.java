package com.sinonc.common.payment.configuraion;

import com.sinonc.common.payment.configuraion.properties.WechatPayProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/3/22 10:52
 */
@Configuration
@ComponentScan(basePackages = {"com.sinonc.common.payment.wechat","com.sinonc.common.payment.notify"})
@EnableConfigurationProperties(WechatPayProperties.class)
@ConditionalOnProperty(prefix = "pay", name = "wechat.enable", havingValue = "true")
@Slf4j
public class WechatPayConfiguration {

    @PostConstruct
    private void init(){
        log.info("--------微信支付组件启用--------");
    }

    @Bean
    @ConditionalOnMissingBean(RestTemplate.class)
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
