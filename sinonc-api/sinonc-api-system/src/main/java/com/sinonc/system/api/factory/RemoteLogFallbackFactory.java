package com.sinonc.system.api.factory;

import com.sinonc.common.core.domain.R;
import com.sinonc.system.api.RemoteLogService;
import com.sinonc.system.api.domain.SysImportLog;
import com.sinonc.system.api.domain.SysOperLog;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 日志服务降级处理
 *
 * @author ruoyi
 */
@Component
public class RemoteLogFallbackFactory implements FallbackFactory<RemoteLogService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteLogFallbackFactory.class);

    @Override
    public RemoteLogService create(Throwable throwable) {
        log.error("日志服务调用失败:{}", throwable.getMessage());
        return new RemoteLogService() {
            @Override
            public R<Boolean> saveLog(SysOperLog sysOperLog) {
                return null;
            }

            @Override
            public R<Boolean> saveImportLog(@RequestBody SysImportLog sysImportLog){
                return null;
            }

            @Override
            public R<Boolean> saveLogininfor(String username, String status, String message, String ip) {
                return null;
            }

            @Override
            public R<Integer> failLoginCount(String username) {
                return R.fail("获取登录用户登录次数失败");
            }
        };

    }
}
