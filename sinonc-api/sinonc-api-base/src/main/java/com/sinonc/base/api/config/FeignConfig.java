package com.sinonc.base.api.config;

import com.sinonc.common.core.constant.CacheConstants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class FeignConfig implements RequestInterceptor {

    private static final Logger log = LoggerFactory.getLogger(FeignConfig.class);

    @Override
    public void apply(RequestTemplate requestTemplate) {
        HttpServletRequest request = getHttpServletRequest();
        if (ObjectUtils.isEmpty(request)){
            return;
        }
        requestTemplate.header("Authorization", getToken(request));
    }

    /**
     * 获取请求的request
     * @return
     */
    private HttpServletRequest getHttpServletRequest() {
        try {
            // hystrix隔离策略会导致RequestContextHolder.getRequestAttributes()返回null
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (!ObjectUtils.isEmpty(attributes)){
                return attributes.getRequest();
            }
            return null;
        } catch (Exception e) {
            log.error("feign拦截器中获取HttpServletRequest error...",e.getMessage(),e);
            return null;
        }
    }


    /**
     * 获取请求token
     */
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(CacheConstants.HEADER);
        if (StringUtils.isNotEmpty(token) && token.startsWith(CacheConstants.TOKEN_PREFIX)) {
            token = token.replace(CacheConstants.TOKEN_PREFIX, "");
        }
        return token;
    }
}