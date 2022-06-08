package com.sinonc.base.api;

import com.sinonc.base.api.config.FeignConfig;
import com.sinonc.base.api.domain.AreaCode;
import com.sinonc.base.api.domain.BaseFarm;
import com.sinonc.base.api.factory.RemoteBaseFarmFallbackFactory;
import com.sinonc.common.core.constant.ServiceNameConstants;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author huanghao
 * @apiNote 基础基地服务
 * @date 2020/10/26 9:50
 */
@FeignClient(contextId = "remoteBaseFarmService", value = ServiceNameConstants.BASE_SERVICE,
        fallbackFactory = RemoteBaseFarmFallbackFactory.class, configuration = FeignConfig.class)
public interface RemoteBaseFarmService {
    /**
     * 查询基地信息
     *
     * @param farmId 基地id
     * @return 基地信息
     */
    @RequestMapping(value = "/farm/api/{farmId}", method = RequestMethod.GET)
    BaseFarm getFarmInfo(@PathVariable("farmId") Long farmId);

    /**
     * 查询基地信息
     *
     * @param baseFarm 基地id
     * @return 基地信息
     */
    @RequestMapping(value = "/farm/list", method = RequestMethod.POST)
    R<List<BaseFarm>> list(@RequestBody BaseFarm baseFarm);

    /**
     * 添加基地
     *
     * @param baseFarm 基地信息
     * @return 添加结果
     */
    @RequestMapping(value = "/farm", method = RequestMethod.POST)
    R<AjaxResult> addBaseFarm(@RequestBody BaseFarm baseFarm);

    /**
     * 查询基地列表
     *
     * @return 基地信息
     */
    @RequestMapping(value = "/farm/api", method = RequestMethod.GET)
    List<BaseFarm> getFarmList();

    /**
     * 查询行政区域
     * @param areaCode
     * @return 行政区域
     */
    @RequestMapping(value = "/code/area/{areaCode}", method = RequestMethod.GET)
    AreaCode getInfo(@PathVariable("areaCode") Long areaCode);


    @RequestMapping(value = "/farm/count", method = RequestMethod.GET)
    AjaxResult getCount();

}
