package com.sinonc.order.api.factory;

import com.sinonc.common.core.domain.R;
import com.sinonc.order.api.RemoteShopService;
import com.sinonc.order.api.domain.Shop;
import feign.hystrix.FallbackFactory;

import java.util.List;

/**
 * @author: lw
 * @date: 2022/4/15 16:55
 * @description:
 */
public class RemoteShopFactory implements FallbackFactory<RemoteShopService> {

    @Override
    public RemoteShopService create(Throwable throwable) {
        return new RemoteShopService() {
            @Override
            public R<List<Shop>> getShopListByEntityId(Long entityId) {
                return R.fail("获取店铺失败");
            }

            @Override
            public R<Integer> goodsCount() {
                return R.ok(0);
            }
        };
    }
}
