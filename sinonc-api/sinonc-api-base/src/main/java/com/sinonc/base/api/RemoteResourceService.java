package com.sinonc.base.api;

import com.sinonc.base.api.config.FeignConfig;
import com.sinonc.base.api.domain.FileResource;
import com.sinonc.base.api.factory.RemoteResourceFallbackFactory;
import com.sinonc.base.api.vo.FileResourceVo;
import com.sinonc.common.core.constant.ServiceNameConstants;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author lqqu
 * @apiNote 基础上传文件服务
 * @date 2020/10/26 9:50
 */
@FeignClient(contextId = "remoteUploadService", value = ServiceNameConstants.BASE_SERVICE,
        fallbackFactory = RemoteResourceFallbackFactory.class, configuration = FeignConfig.class)
public interface RemoteResourceService {

    /**
     * 上传文件记录
     * @param fileResources 上传文件记录
     * @return 添加结果
     */
    @PostMapping("/resource/addResources")
    R<AjaxResult> addResources(@RequestBody List<FileResource> fileResources);

    /**
     * 修改上传文件记录删除状态
     * @Param ossName 上传文件名称
     * @return 修改结果
     */
    @PostMapping("/resource/updateDelFlag")
    R<AjaxResult> updateDelFlag(String ossName);

    /**
     * 批量修改上传文件记录删除状态
     * @Param ossNames 上传文件名称
     * @return 修改结果
     */
    @PostMapping("/resource/batchUpdateDelFlag")
    R<AjaxResult> batchUpdateDelFlag(@RequestBody List<String> ossNames);

    /**
     * 根据上传的文件名字，获取文件管理数据
     * @Param ossName 上传文件名称
     * @return 结果
     */
    @PostMapping("/resource/findByOssName")
    R<FileResource> findByOssName(String ossName);

    /**
     * 根据上传的客户端，分页获取上传的文件管理数据
     *
     * @return
     * @Param
     */
    @PostMapping("/resource/pageFileResource")
    TableDataInfo pageFileResource(@RequestBody FileResourceVo fileResourceVo);
}
