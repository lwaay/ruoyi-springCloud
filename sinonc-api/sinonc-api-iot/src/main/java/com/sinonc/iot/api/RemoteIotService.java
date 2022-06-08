package com.sinonc.iot.api;

import com.sinonc.common.core.constant.ServiceNameConstants;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.iot.api.domain.DeviceInfo;
import com.sinonc.iot.api.domain.DeviceMonitor;
import com.sinonc.iot.api.domain.DeviceProperty;
import com.sinonc.iot.api.domain.DeviceType;
import com.sinonc.iot.api.dto.ProdEnviStatisticsDto;
import com.sinonc.iot.api.factory.RemoteIotFallbackFactory;
import com.sinonc.iot.api.vo.DevicePropertyVo;
import com.sinonc.iot.api.vo.ProStaticVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: yehuiwang
 * @date: 2020/10/22 9:27
 * @description:
 */
@FeignClient(contextId = "remoteIotService", value = ServiceNameConstants.IOT_SERVICE,fallbackFactory = RemoteIotFallbackFactory.class)
public interface RemoteIotService {
    /**
     * 根据设备id获取设备信息
     */
    @GetMapping("/device")
    public R<DeviceInfo> getDeviceInfo(@RequestParam("id") Long id);

    /**
     * 通过基地id查询基地信息
     */
    @GetMapping("/device/deviceCount")
    public Integer getDeviceCount();

    /**
     * 通过基地id查询基地信息
     */
    @GetMapping("/monitor/monitorCount")
    public Integer getMonitorCount();

    /**
     * 上报数据
     * @param dataPoints
     * @return
     */
//    @PostMapping("/tsdb/put")
//    public R<AjaxResult> put(@RequestBody List<DataPoint> dataPoints);

    /**
     * 查询数据
     * @param queryDataVo
     * @return
     */
//    @PostMapping("/tsdb/query")
//    public R<AjaxResult> query(@RequestBody QueryDataVo queryDataVo);

    /**
     * 获取设备数据统计
     *
     * @param proStaticVo
     * @return
     */
    @GetMapping("/api/iot/getProdEnviStatisticsDto")
    public R<ProdEnviStatisticsDto> getProStatic(@RequestBody ProStaticVo proStaticVo);

    /**
     * 根据设备id查询实时数据
     *
     * @param deviceId
     * @return
     */
    @GetMapping("/api/iot/getDataByDeviceId/{deviceId}")
    public AjaxResult realData(@PathVariable("deviceId") String deviceId);

    @GetMapping("/property/getPropertyList")
    public R<List<DevicePropertyVo>> propertyList();

    @GetMapping("/iot/api/monitor/getMonitor/{farmId}")
    public AjaxResult getMonitorList(@PathVariable("farmId") Long farmId);

    @GetMapping("/iot/api/monitor/getMonitorListByFarm/{farmId}")
    public R<List<DeviceMonitor>> getMonitorListByFarm(@PathVariable("farmId") Long farmId);

    @PostMapping("/monitor/")
    public AjaxResult addMonitor(@RequestBody DeviceMonitor deviceMonitor);

    @GetMapping("/api/iot/monitor/getMonitorDetail")
    public AjaxResult getMonitorDetail(@RequestParam("id")Long id);

    @GetMapping("/type/getDeviceType/{type}")
    public R<DeviceType> getDeviceType(@PathVariable("type")String type);

    @GetMapping("/device/getDeviceList/{id}")
    public R<List<DeviceInfo>> getDeviceList(@PathVariable("id")String typeId);

    @GetMapping("/property/getPropertyByType/{id}")
    public R<List<DeviceProperty>> getPropertyByType(@PathVariable("id")String id);

    @PostMapping("device/addDevice")
    R<Integer> insertDevice(@RequestBody DeviceInfo deviceInfo);

    @GetMapping("/iot/api/warn/iotwarn/count")
    public R<Integer> warnCount();
}
