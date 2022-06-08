package com.sinonc.gateway.config;

import com.sinonc.gateway.handler.ValidateCodeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

/**
 * 路由配置信息
 *
 * @author ruoyi
 */
@Configuration
public class RouterFunctionConfiguration {
    @Autowired
    private ValidateCodeHandler validateCodeHandler;
    private static final String TEST_PROFILE = "test";
    private static final String PROD_PROFILE = "prod";

    @Value("${spring.profiles.active}")
    private String profile;

    @SuppressWarnings("rawtypes")
    @Bean
    public RouterFunction routerFunction() {
        String code = "/code";
        if (TEST_PROFILE.equals(profile) || PROD_PROFILE.equals(profile)) {
            code = "/api/code";
        }
        return RouterFunctions.route(
                RequestPredicates.GET(code).and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                validateCodeHandler);
    }
}
