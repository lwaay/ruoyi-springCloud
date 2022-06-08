package com.sinonc.base.api.factory;

import com.sinonc.base.api.RemoteBaseFarmService;
import com.sinonc.base.api.domain.AreaCode;
import com.sinonc.base.api.domain.BaseFarm;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.web.domain.AjaxResult;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huanghao
 * @apiNote 基础信息降级
 * @date 2020/10/26 9:57
 */
public class RemoteBaseFarmFallbackFactory implements FallbackFactory<RemoteBaseFarmService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteBaseFarmFallbackFactory.class);

    @Override
    public RemoteBaseFarmService create(Throwable throwable) {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteBaseFarmService() {
            @Override
            public BaseFarm getFarmInfo(Long farmId) {
                return new BaseFarm();
            }

            @Override
            public R<List<BaseFarm>> list(BaseFarm baseFarm) {
                return R.fail("获取基地失败");
            }

            @Override
            public AjaxResult getCount() {
                return AjaxResult.error("获取基地总数失败");
            }

            @Override
            public R<AjaxResult> addBaseFarm(BaseFarm baseFarm) {
                return R.fail("添加基地信息失败：" + throwable.getMessage());
            }

            @Override
            public List<BaseFarm> getFarmList(){
                return new ArrayList<>();
            }

            @Override
            public AreaCode getInfo(Long areaCode){
                return new AreaCode();
            }
        };
    }
}
