package com.sinonc.system.api;

import com.sinonc.common.core.constant.ServiceNameConstants;
import com.sinonc.common.core.domain.R;
import com.sinonc.system.api.domain.BusinessEntity;
import com.sinonc.system.api.factory.RemoteEntityFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author: lw
 * @date: 2022/4/15 16:22
 * @description:
 */
@FeignClient(contextId = "RemoteEntityService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteEntityFallbackFactory.class)
public interface RemoteEntityService {

    /**
     * 获取主体信息
     * @param entityId
     * @return
     */
    @GetMapping("/bussentity/getEntityById/{entityId}")
    R<BusinessEntity> getEntityById(@PathVariable("entityId") Long entityId);

    /**
     * 获取百色芒果新型生产经营主体
     * @return
     */
    @GetMapping("api/buss/getNewTypeBusinessEntity")
    R<List<BusinessEntity>> selectBusinessEntityList(@PathVariable("type") String type);

}
