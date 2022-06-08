package com.sinonc.system.api;

import com.sinonc.common.core.constant.ServiceNameConstants;
import com.sinonc.common.core.domain.R;
import com.sinonc.system.api.domain.SysConfig;
import com.sinonc.system.api.domain.SysDictData;
import com.sinonc.system.api.factory.RemoteConfigFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 系统参数服务
 *
 * @author lw
 */
@FeignClient(contextId = "remoteConfigService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteConfigFallbackFactory.class)
public interface RemoteConfigService {

    /**
     * 根据参数键名查询参数值
     * @param configKey
     * @return
     */
    @GetMapping(value = "/config/configKey/{configKey}")
    public R<String> getConfigKey(@PathVariable("configKey") String configKey);

    @GetMapping(value = "/api/dict/type/{dictType}")
    public R<List<SysDictData>> dictType(@PathVariable("dictType") String dictType);

}
