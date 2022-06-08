package com.sinonc.origins.api.factory;

import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.origins.api.RemoteProProductInfoService;
import com.sinonc.origins.api.domain.ProProductInfo;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhangxinlong
 * @apiNote TODO
 * @date 2020/11/7  10:14
 */
public class RemoteProProductInfoFallbackFactory implements FallbackFactory<RemoteProProductInfoService> {

    private static final Logger log = LoggerFactory.getLogger(RemoteProProductInfoFallbackFactory.class);

    @Override
    public RemoteProProductInfoService create(Throwable throwable) {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteProProductInfoService() {
            @Override
            public R<AjaxResult> addProProductInfo(ProProductInfo pmBusiness) {
                return R.fail("添加商品信息失败");
            }
        };
    }

}
