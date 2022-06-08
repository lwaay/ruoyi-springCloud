package com.sinonc.base.api;

import com.sinonc.base.api.config.FeignConfig;
import com.sinonc.base.api.domain.AreaCode;
import com.sinonc.base.api.domain.BaseFarm;
import com.sinonc.base.api.domain.FruiterInfo;
import com.sinonc.base.api.factory.RemoteAreaCodeServiceFallbackFactory;
import com.sinonc.base.api.factory.RemoteFruitServiceFallbackFactory;
import com.sinonc.common.core.constant.ServiceNameConstants;
import com.sinonc.common.core.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/2/23 14:43
 */
@FeignClient(contextId = "RemoteFruitService", value = ServiceNameConstants.BASE_SERVICE,
        fallbackFactory = RemoteFruitServiceFallbackFactory.class, configuration = FeignConfig.class)
public interface RemoteFruitService {

    /**
     *查询果树列表
     * @param fruiterInfo
     * @return
     */
    @PostMapping("/fruinfo/listFruit")
    R<List<FruiterInfo>> listFruit(@RequestBody FruiterInfo fruiterInfo);
}
