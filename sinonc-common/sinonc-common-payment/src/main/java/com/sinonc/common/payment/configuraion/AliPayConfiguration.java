package com.sinonc.common.payment.configuraion;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.sinonc.common.payment.configuraion.properties.AliPayProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 支付配置类
 */
@Configuration
@ComponentScan(basePackages = {"com.sinonc.common.payment.alipay","com.sinonc.common.payment.notify"})
@EnableConfigurationProperties({AliPayProperties.class})
@ConditionalOnProperty(prefix = "pay", name = "alipay.enable", havingValue = "true")
@Slf4j
public class AliPayConfiguration {

    @PostConstruct
    private void init(){
        log.info("----------支付宝支付组件启用-----------");
    }

    @Autowired
    private AliPayProperties aliPayProperties;

    /**
     * 创建支付宝客户端
     * @return 支付宝客户端
     */
    @Bean
    public AlipayClient alipayClient() {
        return new DefaultAlipayClient(aliPayProperties.getUrl(), aliPayProperties.getAppId(), aliPayProperties.getPrivateKey(), "json", aliPayProperties.getCharset(), aliPayProperties.getPublicKey(), aliPayProperties.getSignType());
    }

}
