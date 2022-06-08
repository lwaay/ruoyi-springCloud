package com.sinonc.base.api;

import com.sinonc.base.api.config.FeignConfig;
import com.sinonc.base.api.domain.CropDict;
import com.sinonc.base.api.factory.RemoteCorpDictFallbackFactory;
import com.sinonc.common.core.constant.ServiceNameConstants;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/2/15 14:55
 */
@FeignClient(contextId = "remoteCorpDictService", value = ServiceNameConstants.BASE_SERVICE,
        fallbackFactory = RemoteCorpDictFallbackFactory.class, configuration = FeignConfig.class)
public interface RemoteCorpDictService {

    @GetMapping(value = "/api/dict/{cropId}")
    public R<CropDict> getInfo(@PathVariable("cropId") Long cropId);

    @GetMapping(value = "/api/dict/getchild/{parentId}")
    public R<Long[]> getchildId(@PathVariable("parentId") Long parentId);

    @GetMapping("/dict/tree")
    public AjaxResult treeCropDict();

    @GetMapping("/dict/alllist")
    public TableDataInfo listWithoutSplit(CropDict cropDict);
}
