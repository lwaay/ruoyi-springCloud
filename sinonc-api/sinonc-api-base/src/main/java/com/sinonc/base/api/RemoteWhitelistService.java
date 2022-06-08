package com.sinonc.base.api;


import com.sinonc.base.api.factory.RemoteWhitelistFallbackFactory;
import com.sinonc.common.core.constant.ServiceNameConstants;
import com.sinonc.common.core.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * @author lqqu
 * @apiNote 白名单
 * @date 2020/10/26 9:50
 */
@FeignClient(contextId = "remoteWhitelistService", value = ServiceNameConstants.BASE_SERVICE,
        fallbackFactory = RemoteWhitelistFallbackFactory.class)
public interface RemoteWhitelistService {

    /**
     * 根据ip查询是否在白名单
     * @param ip
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/data/isWhite/{ip}")
    R<Boolean> isWhite(@PathVariable("ip") String ip);

}
