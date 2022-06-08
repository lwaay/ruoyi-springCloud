package com.sinonc.origins.api;

import com.sinonc.common.core.constant.ServiceNameConstants;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.origins.api.domain.ProOriginsInfo;
import com.sinonc.origins.api.factory.RemoteProOriginsInfoFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author zhangxinlong
 * @date 2020/11/7  10:12
 */
@FeignClient(contextId = "remoteProOriginsInfoService", value = ServiceNameConstants.ORIGINS_SERVICE,
        fallbackFactory = RemoteProOriginsInfoFallbackFactory.class)
public interface RemoteProOriginsInfoService {

    /**
     * 经营主体信息
     * @param proOriginsInfo
     * @return
     */
    @PostMapping("/info/add")
    R<AjaxResult> addProOriginsInfo(@RequestBody ProOriginsInfo proOriginsInfo);
}
