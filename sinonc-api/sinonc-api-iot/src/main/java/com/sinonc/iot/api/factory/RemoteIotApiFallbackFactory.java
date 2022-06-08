package com.sinonc.iot.api.factory;

import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.iot.api.RemoteIotApiService;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @author lqqu
 * @apiNote 基础信息降级
 * @date 2021/10/11 9:57
 */
public class RemoteIotApiFallbackFactory implements FallbackFactory<RemoteIotApiService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteIotApiFallbackFactory.class);

    @Override
    public RemoteIotApiService create(Throwable throwable) {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteIotApiService() {

            @Override
            public R<List<Map<String,String>>> selectTable() {
                return R.fail();
            }

            @Override
            public R<List<Map<String,String>>> selectColumn(String tableNames) {
                return R.fail();
            }

            @Override
            public R<List<Map<String,String>>> list(Map<String,String> query) {
                return R.fail();
            }
        };
    }
}
