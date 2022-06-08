package com.sinonc.base.api.factory;

import com.sinonc.base.api.RemoteResourceService;
import com.sinonc.base.api.domain.FileResource;
import com.sinonc.base.api.vo.FileResourceVo;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author huanghao
 * @apiNote 基础信息降级
 * @date 2020/10/26 9:57
 */
public class RemoteResourceFallbackFactory implements FallbackFactory<RemoteResourceService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteResourceFallbackFactory.class);

    @Override
    public RemoteResourceService create(Throwable throwable) {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteResourceService(){
            @Override
            public R<AjaxResult> addResources(List<FileResource> fileResources) {
                return R.fail("添加上传文件管理失败：" + throwable.getMessage());
            }

            @Override
            public R<AjaxResult> updateDelFlag(String ossName) {
                return R.fail("修改上传文件管理失败：" + throwable.getMessage());
            }

            @Override
            public R<AjaxResult> batchUpdateDelFlag(List<String> ossNames) {
                return R.fail("批量修改上传文件管理失败：" + throwable.getMessage());
            }

            @Override
            public R<FileResource> findByOssName(String ossName) {
                return R.fail("获取上传文件管理数据失败：" + throwable.getMessage());
            }

            @Override
            public TableDataInfo pageFileResource(FileResourceVo fileResourceVo) {
                return new TableDataInfo();
            }
        };
    }
}
