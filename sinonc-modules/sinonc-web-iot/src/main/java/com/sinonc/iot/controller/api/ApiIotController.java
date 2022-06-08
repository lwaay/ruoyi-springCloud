package com.sinonc.iot.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.sinonc.base.api.RemoteBaseFarmService;
import com.sinonc.base.api.domain.BaseFarm;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.exception.BusinessException;
import com.sinonc.common.core.text.Convert;
import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.redis.service.RedisService;
import com.sinonc.iot.api.domain.DeviceInfo;
import com.sinonc.iot.api.domain.DeviceMonitor;
import com.sinonc.iot.api.dto.ProdEnviStatisticsDto;
import com.sinonc.iot.api.vo.ProStaticVo;
import com.sinonc.iot.dto.ExtDeviceInfoDto;
import com.sinonc.iot.service.DeviceDataService;
import com.sinonc.iot.service.IDeviceInfoService;
import com.sinonc.iot.service.IDeviceMonitorService;
import com.sinonc.iot.utils.OKHttp;
import com.sinonc.iot.utils.OKHttpResponse;
import com.sinonc.iot.vo.DeviceDataVo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author: yehuiwang
 * @date: 2020/10/22 9:59
 * @description:
 */
@RestController
@RequestMapping("api/iot")
@Slf4j
public class ApiIotController {


    @Value("${ys.appKey}")
    private String appKey;

    @Value("${ys.appSecret}")
    private String secret;

    @Autowired
    private RemoteBaseFarmService remoteBaseFarmService;

    @Autowired
    private IDeviceInfoService deviceInfoService;

    @Autowired
    private DeviceDataService deviceDataService;

    @Autowired
    private IDeviceMonitorService monitorService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public static final String LPQ_DEVICE_TYPE = "lpq";
    public static final String SGNC = "SGNC";
    public static final String LLK ="llk";

    @ApiOperation("获取设备数据统计")
    @PostMapping("/getProdEnviStatisticsDto")
    public R<ProdEnviStatisticsDto> getProdEnviStatisticsDto(@RequestBody ProStaticVo proStaticVo){
        ProdEnviStatisticsDto prodEnviStatisticsDto =deviceDataService.getProdEnviStatisticsDto(proStaticVo);
        return R.ok(prodEnviStatisticsDto);
    }

    /**
     * 根据基地id查询设备列表
     *
     * @return
     */
    @GetMapping("getDeviceListByFarmId/{id}")
    public AjaxResult getDeviceListByFarmId(@PathVariable("id") Long id) {
        BaseFarm baseFarm = remoteBaseFarmService.getFarmInfo(id);
        log.info("R baseFarm {}", baseFarm.toString());
        String deviceIds = baseFarm.getDeviceIds();
        if (StringUtils.isEmpty(deviceIds)) {
            return AjaxResult.success("该基地无设备数据");
        }
        String[] ids = deviceIds.split(",");
        List<DeviceInfo> deviceInfoList = deviceInfoService.getDeviceListByIds(ids);
        return AjaxResult.success("success", deviceInfoList);
    }

    @PostConstruct
    public void test(){
        System.out.println(appKey);
        System.out.println(secret);
        System.out.println("end");
    }

    /**
     * 查询外部设备列表
     *
     * @return
     */
    @GetMapping("getExtDeviceInfoDtoList")
    public AjaxResult getExtDeviceInfoDtoList() throws IOException {
        List<ExtDeviceInfoDto> extDeviceInfoDtoList = deviceInfoService.getExtDeviceInfoDtoList();
        return AjaxResult.success("success", extDeviceInfoDtoList);
    }

    @ApiOperation("根据基地id获取监控列表")
    @GetMapping("getMonitorListByBaseId/{farmId}")
    public AjaxResult getMonitorListByBaseId(@PathVariable("farmId") Long farmId) {
        BaseFarm farmInfo = remoteBaseFarmService.getFarmInfo(farmId);
        log.info("R baseFarm {}", farmInfo.toString());
        String id = farmInfo.getMonitorIds();
        Long[] ids = Convert.toLongArray(id);
        List<DeviceMonitor> deviceInfos = new ArrayList<>();
        for (Long deviceId : ids) {
            DeviceMonitor monitor = monitorService.selectDeviceMonitorById(deviceId);
            if (monitor.getEzopen() != null) {
                if(StringUtils.isEmpty(monitor.getAppKey()) && StringUtils.isEmpty(monitor.getAppSecret())){
                    monitor.setAppKey(appKey);
                    monitor.setAppSecret(secret);
                }
                monitor.setToken(getToken(monitor.getAppKey(),monitor.getAppSecret()));
            }
            //脱敏
            monitor.setAppKey("");
            monitor.setAppSecret("");
            deviceInfos.add(monitor);
        }
        return AjaxResult.success(deviceInfos);
    }

    public String getToken(String appKey,String appSecret) {
        if (Boolean.TRUE.equals(redisTemplate.hasKey(appKey))) {
            return redisTemplate.opsForValue().get(appKey);
        }
        Map<String, String> querys = new HashMap<>();
        String url = "https://open.ys7.com/api/lapp/token/get";
        querys.put("appKey", appKey);
        querys.put("appSecret", appSecret);
        OKHttpResponse response = OKHttp.instance().post(url, null, null, querys);
        JSONObject jsonObject = JSONObject.parseObject(response.getContent());
        JSONObject data = jsonObject.getJSONObject("data");
        redisTemplate.opsForValue().set(appKey, data.getString("accessToken"), 5L, TimeUnit.MINUTES);
        return data.getString("accessToken");
    }
}
