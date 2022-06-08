package com.sinonc.system.api;

import com.sinonc.common.core.constant.ServiceNameConstants;
import com.sinonc.common.core.domain.R;
import com.sinonc.system.api.domain.SysDictData;
import com.sinonc.system.api.factory.RemoteDictFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author: lw
 * @date: 2022/4/24 14:46
 * @description:
 */
@FeignClient(contextId = "remoteDiceService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteDictFactory.class)
public interface RemoteDictService {

    /**
     * 查询字典数据
     * @param dictType
     * @return
     */
    @GetMapping("/api/dict/type/{dictType}")
    public R<List<SysDictData>> getLabelBy(@PathVariable("dictType") String dictType);

}
