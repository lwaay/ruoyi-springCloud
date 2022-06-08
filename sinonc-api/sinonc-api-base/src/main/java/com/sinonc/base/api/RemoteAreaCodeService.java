package com.sinonc.base.api;

import com.sinonc.base.api.config.FeignConfig;
import com.sinonc.base.api.domain.AreaCode;
import com.sinonc.base.api.factory.RemoteAreaCodeServiceFallbackFactory;
import com.sinonc.common.core.constant.ServiceNameConstants;
import com.sinonc.common.core.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/2/23 14:43
 */
@FeignClient(contextId = "remoteAreaCodeService", value = ServiceNameConstants.BASE_SERVICE,
        fallbackFactory = RemoteAreaCodeServiceFallbackFactory.class, configuration = FeignConfig.class)
public interface RemoteAreaCodeService {

    @PostMapping("api/base/area/list")
    R<List<AreaCode>> list(@RequestBody AreaCode areaCode);

    @PostMapping("api/base/area/getaddressname")
    R<String> changeAddressName(@RequestParam("addressCode") String addressCode);
}
