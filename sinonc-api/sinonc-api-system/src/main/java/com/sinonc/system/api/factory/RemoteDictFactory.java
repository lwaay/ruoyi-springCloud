package com.sinonc.system.api.factory;

import com.sinonc.common.core.domain.R;
import com.sinonc.system.api.RemoteDictService;
import com.sinonc.system.api.domain.SysDictData;
import feign.hystrix.FallbackFactory;

import java.util.List;

/**
 * @author: lw
 * @date: 2022/4/24 14:48
 * @description:
 */
public class RemoteDictFactory implements FallbackFactory<RemoteDictService> {

    @Override
    public RemoteDictService create(Throwable throwable) {
        return new RemoteDictService() {
            @Override
            public R<List<SysDictData>> getLabelBy(String dictType) {
               return R.fail("获取字典数据失败");
            }
        };
    }
}
