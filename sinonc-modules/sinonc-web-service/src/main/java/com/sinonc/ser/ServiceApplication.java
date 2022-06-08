package com.sinonc.ser;

import com.sinonc.common.security.annotation.EnableCustomConfig;
import com.sinonc.common.security.annotation.EnableRyFeignClients;
import com.sinonc.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 系统模块
 *
 * @author ruoyi
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringCloudApplication
public class ServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  社会化服务模块启动成功   ლ(´ڡ`ლ)ﾞ ");
    }
}
