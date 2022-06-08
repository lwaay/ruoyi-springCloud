package com.sinonc.openapi;

import com.sinonc.common.security.annotation.EnableCustomConfig;
import com.sinonc.common.security.annotation.EnableRyFeignClients;
import com.sinonc.common.swagger.annotation.EnableCustomSwagger2;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/5/10 9:15
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringCloudApplication
@EnableEncryptableProperties //开启加密注解
public class OpenApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(OpenApiApplication.class, args);
        System.out.println(" 对外开放接口接入服务 启动成功");
    }
}
