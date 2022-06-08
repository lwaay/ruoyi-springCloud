package com.sinonc.origins.api.factory;

import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.origins.api.RemoteProVisitService;
import com.sinonc.origins.api.domain.ProVisit;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhangxinlong
 * @apiNote TODO
 * @date 2020/11/7  10:14
 */
public class RemoteProVisitFallbackFactory implements FallbackFactory<RemoteProVisitService> {

    private static final Logger log = LoggerFactory.getLogger(RemoteProVisitFallbackFactory.class);

    @Override
    public RemoteProVisitService create(Throwable throwable) {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteProVisitService() {
            @Override
            public R<AjaxResult> addProVisit(ProVisit proVisit) {
                return R.fail("添加溯源查看信息失败");
            }
        };
    }

}
