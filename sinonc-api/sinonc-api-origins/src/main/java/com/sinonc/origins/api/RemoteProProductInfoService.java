package com.sinonc.origins.api;

import com.sinonc.common.core.constant.ServiceNameConstants;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.origins.api.domain.ProProductInfo;
import com.sinonc.origins.api.factory.RemoteProProductInfoFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author zhangxinlong
 * @date 2020/11/7  10:12
 */
@FeignClient(contextId = "remoteProProductInfoService", value = ServiceNameConstants.ORIGINS_SERVICE,
        fallbackFactory = RemoteProProductInfoFallbackFactory.class)
public interface RemoteProProductInfoService {

    /**
     * 经营主体信息
     * @param pmBusiness
     * @return
     */
    @PostMapping("/product/add")
    R<AjaxResult> addProProductInfo(@RequestBody ProProductInfo pmBusiness);

}
