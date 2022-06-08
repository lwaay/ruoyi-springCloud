package com.sinonc.base.api.factory;

import com.sinonc.base.api.RemoteCorpDictService;
import com.sinonc.base.api.domain.CropDict;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import feign.hystrix.FallbackFactory;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/2/15 14:56
 */


public class RemoteCorpDictFallbackFactory implements FallbackFactory<RemoteCorpDictService> {
    @Override
    public RemoteCorpDictService create(Throwable throwable) {
        return new RemoteCorpDictService() {

            @Override
            public R<CropDict> getInfo(Long cropId) {
                return R.fail("获取农作物字典信息失败：" + throwable.getMessage());
            }

            @Override
            public R<Long[]> getchildId(Long parentId) {
                return R.fail("获取农作物字典信息失败：" + throwable.getMessage());
            }

            @Override
            public AjaxResult treeCropDict() {
                return null;
            }

            @Override
            public TableDataInfo listWithoutSplit(CropDict cropDict) {
                return null;
            }
        };
    }
}
