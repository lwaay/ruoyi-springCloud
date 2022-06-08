package com.sinonc.base.api.factory;

import com.sinonc.base.api.RemoteWhitelistService;
import com.sinonc.common.core.domain.R;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhangxinlong
 * @apiNote TODO
 * @date 2020/11/7  10:14
 */
public class RemoteWhitelistFallbackFactory implements FallbackFactory<RemoteWhitelistService> {

    private static final Logger log = LoggerFactory.getLogger(RemoteWhitelistFallbackFactory.class);

    @Override
    public RemoteWhitelistService create(Throwable throwable) {
        log.error("查询白名单失败:{}", throwable.getMessage());
        return new RemoteWhitelistService() {
            @Override
            public R<Boolean> isWhite(String ip) {
                return R.fail("获取白名单列表失败！");
            }
        };
    }

}
