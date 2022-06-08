package com.sinonc.origins.api;

import com.sinonc.common.core.constant.ServiceNameConstants;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.origins.api.domain.PmBusiness;
import com.sinonc.origins.api.factory.RemotePmBusinessFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author zhangxinlong
 * @date 2020/11/7  10:12
 */
@FeignClient(contextId = "remotePmBusinessService", value = ServiceNameConstants.ORIGINS_SERVICE,
        fallbackFactory = RemotePmBusinessFallbackFactory.class)
public interface RemotePmBusinessService {

    /**
     * 经营主体信息
     * @param pmBusiness
     * @return
     */
    @PostMapping("/business/add")
    R<AjaxResult> addPmBusiness(@RequestBody PmBusiness pmBusiness);

    /**
     * 获取经营主体信息
     */
    @GetMapping("/business/getPmBusinessById/{busiId}")
    R<PmBusiness> getInfo(@PathVariable("busiId") Long busiId);

}
