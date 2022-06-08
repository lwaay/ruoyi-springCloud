package com.sinonc.iot.api;

import com.sinonc.common.core.constant.ServiceNameConstants;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.iot.api.factory.RemoteIotApiFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * @author lqqu
 * @apiNote 接口配置功能
 * @date 2020/10/26 9:50
 */
@FeignClient(contextId = "remoteIotApiService", value = ServiceNameConstants.IOT_SERVICE,
        fallbackFactory = RemoteIotApiFallbackFactory.class)
public interface RemoteIotApiService {

    /**
     * 查询数据表
     */
    @RequestMapping(value = "/iotApiConfig/selectTable/{moduleType}", method = RequestMethod.GET)
    R<List<Map<String,String>>> selectTable();

    /**
     * 查询数据列
     */
    @RequestMapping(value = "/iotApiConfig/selectColumn/{tableNames}", method = RequestMethod.GET)
    R<List<Map<String,String>>> selectColumn(@PathVariable("tableNames")String tableNames);

    /**
     * 查询数据
     */
    /**
     * 查询数据列
     */
    @RequestMapping(value = "/iotApiConfig/list", method = RequestMethod.POST)
    R<List<Map<String,String>>> list(@RequestBody Map<String,String> query);
}
