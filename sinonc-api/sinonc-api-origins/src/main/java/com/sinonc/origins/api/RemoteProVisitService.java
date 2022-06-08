package com.sinonc.origins.api;

import com.sinonc.common.core.constant.ServiceNameConstants;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.origins.api.domain.ProVisit;
import com.sinonc.origins.api.factory.RemoteProVisitFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author zhangxinlong
 * @date 2020/11/7  10:12
 */
@FeignClient(contextId = "remoteProVisitService", value = ServiceNameConstants.ORIGINS_SERVICE,
        fallbackFactory = RemoteProVisitFallbackFactory.class)
public interface RemoteProVisitService {

    /**
     * 经营主体信息
     * @param proVisit
     * @return
     */
    @PostMapping("/visit/add")
    R<AjaxResult> addProVisit(@RequestBody ProVisit proVisit);

}
