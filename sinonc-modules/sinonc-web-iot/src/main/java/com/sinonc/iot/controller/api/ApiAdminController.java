package com.sinonc.iot.controller.api;


import com.sinonc.base.api.RemoteBaseFarmService;
import com.sinonc.base.api.domain.BaseFarm;
import com.sinonc.common.core.exception.BusinessException;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.iot.api.domain.DeviceInfo;
import com.sinonc.iot.api.domain.DeviceMonitor;
import com.sinonc.iot.api.domain.DeviceType;
import com.sinonc.iot.service.IDeviceInfoService;
import com.sinonc.iot.service.IDeviceMonitorService;
import com.sinonc.iot.service.IDeviceTypeService;
import com.sinonc.system.api.RemoteUserService;
import com.sinonc.system.api.domain.SysUser;
import io.minio.Result;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author hhao
 * 独立物联网控制 app 登陆
 */
@RestController
@RequestMapping("/iot/adminpro")
@Log
@CrossOrigin(origins = "*", maxAge = 3600)
public class ApiAdminController {

    @Autowired
    private RemoteUserService userService;
    @Autowired
    private RemoteBaseFarmService baseFarmService;
    @Autowired
    private IDeviceMonitorService deviceMonitorService;
    @Autowired
    private IDeviceInfoService deviceInfoService;
    @Autowired
    private IDeviceTypeService deviceTypeService;


    /**
     * app 获取基地列表
     *
     * @param entityId
     */
    @GetMapping("/farm")
    public AjaxResult getBase(Long entityId) {
        try {
            BaseFarm baseFarm = new BaseFarm();
            baseFarm.setEntityId(entityId);
            List<BaseFarm> list = baseFarmService.list(baseFarm).getData();
            return AjaxResult.success(list);
        } catch (BusinessException e) {
            log.info(e.getMessage());
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * app 获取基地监控列表
     *
     * @param entityId
     */
    @GetMapping("/getFarmMonitor")
    public AjaxResult getFarmMonitor(Long entityId) {

        List<DeviceMonitor> list = allDeviceMonitorByUser(entityId);
        return AjaxResult.success(list);
    }


    public List<DeviceMonitor> allDeviceMonitorByUser(Long entityId) {
        List<DeviceMonitor> list = new ArrayList<>();

        BaseFarm paraBaseFarm = new BaseFarm();
        paraBaseFarm.setEntityId(entityId);
        List<BaseFarm> farms = baseFarmService.list(paraBaseFarm).getData();
        if (CollectionUtils.isEmpty(farms)) {
            return list;
        }
        List<Long> monitorIds = farms.stream().filter(item -> StringUtils.isNotEmpty(item.getMonitorIds()))
                .map(baseFarm -> baseFarm.getMonitorIds().split(",")).flatMap(Arrays::stream)
                .map(Long::parseLong).distinct().collect(Collectors.toList());
        if (CollectionUtils.isEmpty(monitorIds)){
            return list;
        }
        monitorIds.forEach(id ->{
            DeviceMonitor monitor = deviceMonitorService.selectDeviceMonitorById(id);
            if (monitor == null) {
                return;
            }
            list.add(monitor);
        });
        return list;
    }

    /**
     * app 获取基地物联网设备列表
     *
     * @param entityId
     */
    @GetMapping("/getFarmDevice")
    public AjaxResult getFarmDevice(Long entityId) {


        String[] ids = deviceInfoService.getUserAllDeviceIds(entityId);
        List<DeviceInfo> deviceInfos = new ArrayList<>();
        for (String deviceId : ids) {
            DeviceInfo deviceInfo = deviceInfoService.getDeviceInfo(deviceId);
            if (deviceInfo != null) {
                DeviceType deviceTypeById = deviceTypeService.selectDeviceTypeById(Long.valueOf(deviceInfo.getDeviceTypeId()));
                deviceInfo.setDeviceTypeDesc(deviceTypeById.getRemark());
                deviceInfos.add(deviceInfo);
            }
        }
        return AjaxResult.success(deviceInfos);
    }


    /**
     * app 统计本用户设备总数
     *
     * @param entityId
     */
    @GetMapping("/countFarmDevice")
    public AjaxResult countFarmDevice(Long entityId) {
        String[] ids = deviceInfoService.getUserAllDeviceIds(entityId);
        return AjaxResult.success(ids.length);
    }



}
