package com.sinonc.base.api;

import com.sinonc.base.api.config.FeignConfig;
import com.sinonc.base.api.domain.BaseFarm;
import com.sinonc.base.api.domain.FileResource;
import com.sinonc.base.api.factory.RemoteBaseApiFallbackFactory;
import com.sinonc.base.api.factory.RemoteResourceFallbackFactory;
import com.sinonc.base.api.vo.FileResourceVo;
import com.sinonc.common.core.constant.ServiceNameConstants;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author lqqu
 * @apiNote 接口配置功能
 * @date 2020/10/26 9:50
 */
@FeignClient(contextId = "remoteBaseApiService", value = ServiceNameConstants.BASE_SERVICE,
        fallbackFactory = RemoteBaseApiFallbackFactory.class, configuration = FeignConfig.class)
public interface RemoteBaseApiService {

    /**
     * 查询数据表
     */
    @RequestMapping(value = "/baseApiConfig/selectTable", method = RequestMethod.GET)
    R<List<Map<String,String>>> selectTable();

    /**
     * 查询数据列
     */
    @RequestMapping(value = "/baseApiConfig/selectColumn/{tableNames}", method = RequestMethod.GET)
    R<List<Map<String,String>>> selectColumn(@PathVariable("tableNames") String tableNames);

    /**
     * 查询数据
     */
    /**
     * 查询数据列
     */
    @RequestMapping(value = "/baseApiConfig/queryList", method = RequestMethod.POST)
    R<List<Map<String,String>>> list(@RequestBody Map<String,String> query);
}
