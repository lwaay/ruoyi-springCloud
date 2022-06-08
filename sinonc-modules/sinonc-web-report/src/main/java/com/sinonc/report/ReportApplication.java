package com.sinonc.report;

import com.sinonc.common.security.annotation.EnableCustomConfig;
import com.sinonc.common.security.annotation.EnableRyFeignClients;
import com.sinonc.common.swagger.annotation.EnableCustomSwagger2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

/**
 * 报表
 *
 * @author
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringCloudApplication
@RestController
@MapperScan(value = {"org.jeecg.modules.**.mapper.**"})
@ComponentScan(basePackages = {"com.sinonc.report.controller",
        "org.jeecg.modules.jmreport.desreport.service", "org.jeecg.modules.jmreport.dyndb", "com.baomidou.dynamic.datasource",
        "org.jeecg.modules.jmreport.config", "org.jeecg.modules.jmreport.common.util"})
public class ReportApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReportApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  报表模块启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " .-------.       ____     __        \n" +
                " |  _ _   \\      \\   \\   /  /    \n" +
                " | ( ' )  |       \\  _. /  '       \n" +
                " |(_ o _) /        _( )_ .'         \n" +
                " | (_,_).' __  ___(_ o _)'          \n" +
                " |  |\\ \\  |  ||   |(_,_)'                                               \n" +
                " |  |  \\    /  \\      /           \n" +
                " ''-'   `'-'    `-..-'              ");
    }
}
