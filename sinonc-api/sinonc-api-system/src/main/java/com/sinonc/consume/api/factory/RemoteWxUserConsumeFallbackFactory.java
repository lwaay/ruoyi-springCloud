package com.sinonc.consume.api.factory;

import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.consume.api.RemoteWxUserConsumeService;
import com.sinonc.consume.api.domain.WxUserConsume;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * @author: lw
 * @date: 2022/3/28 8:48
 * @description:
 */
@Component
public class RemoteWxUserConsumeFallbackFactory implements FallbackFactory<RemoteWxUserConsumeService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteWxUserConsumeFallbackFactory.class);

    @Override
    public RemoteWxUserConsumeService create(Throwable throwable) {
        log.error("消费版用户模块调用失败:{}", throwable.getMessage());
        return new RemoteWxUserConsumeService() {

            @Override
            public R<Integer> updateConsume(@RequestBody WxUserConsume wxUserConsume) {
                return R.fail("修改微信用户失败："+ throwable.getMessage());
            }
            @Override
            public R<Integer> addConsume(@RequestBody WxUserConsume wxUserConsume) {
                return R.fail("添加微信用户失败："+ throwable.getMessage());
            }
            @Override
            public R<WxUserConsume> getUserByUnionId(String unionId) {
                return R.fail("获取微信用户失败："+ throwable.getMessage());
            }

            @Override
            public R<WxUserConsume> getUserById(Long id) {
                return R.fail("获取微信用户失败："+ throwable.getMessage());
            }

            @Override
            public R<WxUserConsume> getUserByPhone(String phone) {
                return R.fail("获取微信用户失败："+ throwable.getMessage());
            }
        };
    }
}
