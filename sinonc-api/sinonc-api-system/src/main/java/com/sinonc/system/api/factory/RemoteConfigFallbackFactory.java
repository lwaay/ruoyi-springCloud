package com.sinonc.system.api.factory;

import com.sinonc.common.core.domain.R;
import com.sinonc.system.api.RemoteConfigService;
import com.sinonc.system.api.domain.SysConfig;
import com.sinonc.system.api.domain.SysDictData;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 系统参数降级处理
 * @author lw
 */
@Component
public class RemoteConfigFallbackFactory implements FallbackFactory<RemoteConfigService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteConfigFallbackFactory.class);

    @Override
    public RemoteConfigService create(Throwable throwable) {
        log.error("系统参数服务调用失败:{}", throwable.getMessage());
        return new RemoteConfigService() {
            @Override
            public R<String> getConfigKey(String configKey) {
                return R.fail("获取系统参数失败！");
            }

            @Override
            public R<List<SysDictData>> dictType(String dictType) {
                return R.fail("获取字典为空");
            }
        };
    }
}
