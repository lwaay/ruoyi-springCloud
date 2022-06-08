package com.sinonc.job.api.factory;

import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.job.api.RemoteSysJobService;
import com.sinonc.job.api.domain.SysJob;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zhangxinlong
 * @apiNote TODO
 * @date 2020/11/7  10:14
 */
public class RemoteSysJobFallbackFactory implements FallbackFactory<RemoteSysJobService> {

    private static final Logger log = LoggerFactory.getLogger(RemoteSysJobFallbackFactory.class);

    @Override
    public RemoteSysJobService create(Throwable throwable) {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteSysJobService() {
            @Override
            public R<AjaxResult> addSysJob(SysJob sysJob) {
                return R.fail("添加定时任务失败：" + throwable.getMessage());
            }

            @Override
            public R<AjaxResult> editSysJob(SysJob sysJob) {
                return R.fail("修改定时任务失败：" + throwable.getMessage());
            }

            @Override
            public R<AjaxResult> remove(@PathVariable("jobIds") Long[] jobIds) {
                return R.fail("删除定时任务失败：" + throwable.getMessage());
            }
        };
    }

}
