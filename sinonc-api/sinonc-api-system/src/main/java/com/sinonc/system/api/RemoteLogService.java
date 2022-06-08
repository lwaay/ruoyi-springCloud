package com.sinonc.system.api;

import com.sinonc.common.core.constant.ServiceNameConstants;
import com.sinonc.common.core.domain.R;
import com.sinonc.system.api.domain.SysImportLog;
import com.sinonc.system.api.domain.SysOperLog;
import com.sinonc.system.api.factory.RemoteLogFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 日志服务
 *
 * @author ruoyi
 */
@FeignClient(contextId = "remoteLogService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteLogFallbackFactory.class)
public interface RemoteLogService {
    /**
     * 保存系统日志
     *
     * @param sysOperLog 日志实体
     * @return 结果
     */
    @PostMapping("/operlog")
    R<Boolean> saveLog(@RequestBody SysOperLog sysOperLog);

    /**
     * 保存导入日志
     *
     * @param sysImportLog 日志实体
     * @return 结果
     */
    @PostMapping("/importLog")
    R<Boolean> saveImportLog(@RequestBody SysImportLog sysImportLog);

    /**
     * 保存访问记录
     *
     * @param username 用户名称
     * @param status   状态
     * @param message  消息
     * @return 结果
     */
    @PostMapping("/logininfor")
    R<Boolean> saveLogininfor(@RequestParam("username") String username, @RequestParam("status") String status,
                              @RequestParam("message") String message, @RequestParam("ip") String ip);

    /**
     * 获取每日错误登录次数
     */
    @PostMapping("/logininfor/failLogin")
    R<Integer> failLoginCount(@RequestParam("username") String username);
}
