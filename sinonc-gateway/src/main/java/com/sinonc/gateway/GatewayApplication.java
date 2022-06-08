package com.sinonc.gateway;

import com.sinonc.common.core.constant.Constants;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.redis.service.RedisService;
import com.sinonc.common.security.annotation.EnableRyFeignClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.annotation.PostConstruct;

/**
 * 网关启动程序
 *
 * @author ruoyi
 */
@EnableRyFeignClients
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class GatewayApplication {
    @Autowired
    private RedisService redisService;

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
        System.out.println("sinonc gateway start success");
    }

    @PostConstruct
    private void initWhiteList() {
        String isOpen = redisService.getCacheObject("is_open_login");
        if (StringUtils.isEmpty(isOpen)) {
            // 默认不打开大屏登陆鉴权
            redisService.setCacheObject("is_open_login", "0");
            isOpen = "0";
        }
        String statusStr = Constants.IS_OPEN.equals(isOpen) ? "启动" : "关闭";
        System.out.println("当前大屏登陆鉴权状态:" + statusStr);
    }
}
