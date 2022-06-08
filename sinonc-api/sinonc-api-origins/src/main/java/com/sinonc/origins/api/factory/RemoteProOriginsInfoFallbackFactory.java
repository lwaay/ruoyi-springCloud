package com.sinonc.origins.api.factory;

import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.origins.api.RemoteProOriginsInfoService;
import com.sinonc.origins.api.domain.ProOriginsInfo;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhangxinlong
 * @apiNote TODO
 * @date 2020/11/7  10:14
 */
public class RemoteProOriginsInfoFallbackFactory implements FallbackFactory<RemoteProOriginsInfoService> {

    private static final Logger log = LoggerFactory.getLogger(RemoteProOriginsInfoFallbackFactory.class);

    @Override
    public RemoteProOriginsInfoService create(Throwable throwable) {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteProOriginsInfoService() {
            @Override
            public R<AjaxResult> addProOriginsInfo(ProOriginsInfo proOriginsInfo) {
                return R.fail("添加商品溯源信息失败");
            }
        };
    }

}
