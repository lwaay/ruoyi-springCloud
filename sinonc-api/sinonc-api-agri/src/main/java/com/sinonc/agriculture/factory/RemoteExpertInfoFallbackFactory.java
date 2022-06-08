package com.sinonc.agriculture.factory;

import com.sinonc.agriculture.RemoteExpertInfoService;
import com.sinonc.common.core.domain.R;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/3/4 16:52
 */
@Component
public class RemoteExpertInfoFallbackFactory implements FallbackFactory<RemoteExpertInfoService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteExpertInfoFallbackFactory.class);

    @Override
    public RemoteExpertInfoService create(Throwable cause) {
        log.error("用户服务调用失败:{}", cause.getMessage());
        return new RemoteExpertInfoService() {
            @Override
            public R<Map<String, Object>> expertStatus(Long memberId) {
                Map<String, Object> map = new HashMap<>();
                map.put("status", "1");
                return R.ok(map);
            }

            @Override
            public R<Integer> expertCount() {
                return R.ok(0);
            }

            @Override
            public R<Integer> articleCount() {
                return R.ok(0);
            }
        };
    }
}
