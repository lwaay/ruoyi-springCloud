package com.sinonc.system.api.factory;

import com.sinonc.common.core.domain.R;
import com.sinonc.system.api.RemoteEntityService;
import com.sinonc.system.api.domain.BusinessEntity;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lw
 * @date: 2022/4/15 16:22
 * @description:
 */
public class RemoteEntityFallbackFactory implements FallbackFactory<RemoteEntityService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteConfigFallbackFactory.class);

    @Override
    public RemoteEntityService create(Throwable throwable) {
        log.error("系统参数服务调用失败:{}", throwable.getMessage());
        return new RemoteEntityService() {
            @Override
            public R<BusinessEntity> getEntityById(Long entityId) {
                return R.fail("获取主体信息失败");
            }

            @Override
            public R<List<BusinessEntity>> selectBusinessEntityList(String type) {
                return R.ok(new ArrayList<>());
            }
        };
    }
}
