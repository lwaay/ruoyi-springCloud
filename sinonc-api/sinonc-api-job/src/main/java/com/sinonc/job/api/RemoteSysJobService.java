package com.sinonc.job.api;

import com.sinonc.common.core.constant.ServiceNameConstants;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.job.api.domain.SysJob;
import com.sinonc.job.api.factory.RemoteSysJobFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangxinlong
 * @date 2020/11/7  10:12
 */
@FeignClient(contextId = "remoteSysJobService", value = ServiceNameConstants.JOB_SERVICE, fallbackFactory = RemoteSysJobFallbackFactory.class)
public interface RemoteSysJobService {

    /**
     * 添加定时任务
     *
     * @param sysJob
     * @return
     */
    @PostMapping("/job")
    R<AjaxResult> addSysJob(@RequestBody SysJob sysJob);

    /**
     * 删除定时任务
     *
     * @param jobIds
     * @return
     */
    @DeleteMapping("/job/{jobIds}")
    R<AjaxResult> remove(@PathVariable("jobIds") Long[] jobIds);

    /**
     * 修改定时任务
     *
     * @param sysJob
     * @return
     */
    @PutMapping("/job")
    R<AjaxResult> editSysJob(@RequestBody SysJob sysJob);

}
