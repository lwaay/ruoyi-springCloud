package com.sinonc.base.api.factory;

import com.sinonc.base.api.RemoteBaseApiService;
import com.sinonc.base.api.RemoteBaseFarmService;
import com.sinonc.base.api.domain.AreaCode;
import com.sinonc.base.api.domain.BaseFarm;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.web.domain.AjaxResult;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author huanghao
 * @apiNote 基础信息降级
 * @date 2020/10/26 9:57
 */
public class RemoteBaseApiFallbackFactory implements FallbackFactory<RemoteBaseApiService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteBaseApiFallbackFactory.class);

    @Override
    public RemoteBaseApiService create(Throwable throwable) {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteBaseApiService() {

            @Override
            public R<List<Map<String,String>>> selectTable() {
                return R.fail();
            }

            @Override
            public R<List<Map<String,String>>> selectColumn(String tableNamas) {
                return R.fail();
            }

            @Override
            public R<List<Map<String,String>>> list(Map<String,String> query) {
                return R.fail();
            }
        };
    }
}
