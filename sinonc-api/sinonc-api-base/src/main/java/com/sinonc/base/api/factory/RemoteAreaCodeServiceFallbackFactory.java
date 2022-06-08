package com.sinonc.base.api.factory;

import com.sinonc.base.api.RemoteAreaCodeService;
import com.sinonc.base.api.domain.AreaCode;
import com.sinonc.common.core.domain.R;
import feign.hystrix.FallbackFactory;

import java.util.List;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/2/23 14:45
 */
public class RemoteAreaCodeServiceFallbackFactory implements FallbackFactory<RemoteAreaCodeService> {
    @Override
    public RemoteAreaCodeService create(Throwable cause) {
        return new RemoteAreaCodeService() {
            @Override
            public R<List<AreaCode>> list(AreaCode areaCode) {
                return R.fail();
            }

            @Override
            public R changeAddressName(String addressCode) {
                return R.fail();
            }
        };
    }
}
