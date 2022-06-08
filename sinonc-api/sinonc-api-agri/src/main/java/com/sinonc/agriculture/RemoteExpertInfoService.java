package com.sinonc.agriculture;

import com.sinonc.agriculture.factory.RemoteExpertInfoFallbackFactory;
import com.sinonc.common.core.constant.ServiceNameConstants;
import com.sinonc.common.core.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/3/4 16:48
 */
@FeignClient(contextId = "remoteExpertInfoService", value = ServiceNameConstants.LIGHT_AGRIC_SERVICE, fallbackFactory = RemoteExpertInfoFallbackFactory.class)
public interface RemoteExpertInfoService {

    @GetMapping(value = "/api/agriculture/expertinfo/auditStatus")
    public R<Map<String, Object>> expertStatus(@RequestParam("memberId") Long memberId);

    @GetMapping(value = "/api/agriculture/expertinfo/count")
    public R<Integer> expertCount();

    @GetMapping("/agriculture/api/article/count")
    public R<Integer> articleCount();
}
