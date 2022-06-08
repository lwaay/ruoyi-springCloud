package com.sinonc.origins.api.factory;

import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.origins.api.RemotePmBusinessService;
import com.sinonc.origins.api.domain.PmBusiness;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhangxinlong
 * @apiNote TODO
 * @date 2020/11/7  10:14
 */
public class RemotePmBusinessFallbackFactory implements FallbackFactory<RemotePmBusinessService> {

    private static final Logger log = LoggerFactory.getLogger(RemotePmBusinessFallbackFactory.class);

    @Override
    public RemotePmBusinessService create(Throwable throwable) {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemotePmBusinessService() {

            @Override
            public R<AjaxResult> addPmBusiness(PmBusiness pmBusiness) {
                return R.fail("添加经营主体信息失败");
            }

            @Override
            public R<PmBusiness> getInfo(Long busiId) {
                return R.fail("获取经营主体信息失败");
            }
        };
    }

}
