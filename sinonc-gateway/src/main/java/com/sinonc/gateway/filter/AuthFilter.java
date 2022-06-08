package com.sinonc.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sinonc.common.core.constant.CacheConstants;
import com.sinonc.common.core.constant.Constants;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.redis.service.RedisService;
import com.sinonc.gateway.config.properties.IgnoreWhiteProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Enumeration;
import java.util.concurrent.TimeUnit;

/**
 * 网关鉴权
 *
 * @author ruoyi
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered {
    private static final Logger log = LoggerFactory.getLogger(AuthFilter.class);

    private final static long EXPIRE_TIME = Constants.TOKEN_EXPIRE * 60;

    // 排除过滤的 uri 地址，nacos自行添加
    @Autowired
    private IgnoreWhiteProperties ignoreWhite;

    @Resource(name = "stringRedisTemplate")
    private ValueOperations<String, String> sops;

    @Autowired
    private RedisService redisService;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();

        log.info("filter url:" + url);

        String isOpen = redisService.getCacheObject("is_open_login");
        if (StringUtils.isEmpty(isOpen)) {
            redisService.setCacheObject("is_open_login", "0", -1L, TimeUnit.SECONDS);
            isOpen = "0";
        }
        // 跳过不需要验证的路径
        if (StringUtils.matches(url, Constants.IS_OPEN.equals(isOpen) ? ignoreWhite.getWhites() : ignoreWhite.getWhitesWithView())) {
            log.info("跳过不需要验证的路径:***********************************");
            return chain.filter(exchange);
        }
        String token = getToken(exchange.getRequest());
        if (StringUtils.isBlank(token)) {
            return setUnauthorizedResponse(exchange, "令牌不能为空");
        }
        String userStr = sops.get(getTokenKey(token));
        if (StringUtils.isNull(userStr)) {
            return setUnauthorizedResponse(exchange, "登录状态已过期");
        }
        JSONObject obj = JSONObject.parseObject(userStr);
        String userid = obj.getString("userid");
        String username = obj.getString("username");
        String entity = obj.getString("entityId");
        boolean isAdmin = false;
        JSONArray roles = obj.getJSONArray("roles");
        if (!CollectionUtils.isEmpty(roles)){
            isAdmin = roles.stream().anyMatch(fil->fil.equals("admin"));
        }
        if (StringUtils.isBlank(userid) || StringUtils.isBlank(username)) {
            return setUnauthorizedResponse(exchange, "令牌验证失败");
        }

        // 设置过期时间
        redisService.expire(getTokenKey(token), EXPIRE_TIME);
        // 设置用户信息到请求
        ServerHttpRequest mutableReq = exchange.getRequest().mutate().header(CacheConstants.DETAILS_USER_ID, userid)
                .header(CacheConstants.DETAILS_USERNAME, username).header(CacheConstants.DETAILS_ENTITY,entity)
                .header(CacheConstants.DETAILS_ISADMIN, String.valueOf(isAdmin)).build();
        ServerWebExchange mutableExchange = exchange.mutate().request(mutableReq).build();

        return chain.filter(mutableExchange);
    }

    private Mono<Void> setUnauthorizedResponse(ServerWebExchange exchange, String msg) {
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        response.setStatusCode(HttpStatus.OK);

        //log.error("[鉴权异常处理]请求路径:{}", exchange.getRequest().getPath());

        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            return bufferFactory.wrap(JSON.toJSONBytes(R.fail(401, msg)));
        }));
    }

    private String getTokenKey(String token) {
        return CacheConstants.LOGIN_TOKEN_KEY + token;
    }

    /**
     * 获取请求token
     */
    private String getToken(ServerHttpRequest request) {
        String token = request.getHeaders().getFirst(CacheConstants.HEADER);
        if (StringUtils.isNotEmpty(token) && token.startsWith(CacheConstants.TOKEN_PREFIX)) {
            token = token.replace(CacheConstants.TOKEN_PREFIX, "");
        }
        if (StringUtils.isBlank(token)) {
            token = request.getHeaders().getFirst(CacheConstants.JI_MU_TOKEN);
        }
        //积木报表获取token
        if (token == null) {
            String uri = request.getURI().toString();
            int index = uri.indexOf("=");
            if (index > 0) {
                token = uri.substring(uri.indexOf("=") + 1, uri.length());
            }
        }
        //积木报表列表从cookie忠获取token
        if (token == null) {
            String cookie = request.getHeaders().getFirst("Cookie");
            if (cookie != null) {
                int index = cookie.indexOf("Token=");
                token = cookie.substring(index + 6, index + 42);
            }
        }
        return token;
    }

    @Override
    public int getOrder() {
        return -200;
    }
}
