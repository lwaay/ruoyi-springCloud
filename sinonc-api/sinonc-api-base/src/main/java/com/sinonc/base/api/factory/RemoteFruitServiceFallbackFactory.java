package com.sinonc.base.api.factory;

import com.sinonc.base.api.RemoteFruitService;
import com.sinonc.base.api.domain.AreaCode;
import com.sinonc.base.api.domain.FruiterInfo;
import com.sinonc.common.core.domain.R;
import feign.hystrix.FallbackFactory;

import java.util.List;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/2/23 14:45
 */
public class RemoteFruitServiceFallbackFactory implements FallbackFactory<RemoteFruitService> {
    @Override
    public RemoteFruitService create(Throwable cause) {
        return new RemoteFruitService() {

            @Override
            public R<List<FruiterInfo>> listFruit(FruiterInfo fruiterInfo) {
                return R.fail("查询果树失败");
            }
        };
    }
}
