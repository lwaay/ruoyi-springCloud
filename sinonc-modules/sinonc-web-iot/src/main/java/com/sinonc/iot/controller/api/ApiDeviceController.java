package com.sinonc.iot.controller.api;

import com.sinonc.base.api.RemoteBaseFarmService;
import com.sinonc.base.api.domain.AreaCode;
import com.sinonc.base.api.domain.BaseFarm;
import com.sinonc.common.core.constant.Constants;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.iot.api.domain.DeviceInfo;
import com.sinonc.iot.dto.DeviceInfoDto;
import com.sinonc.iot.service.IDeviceInfoService;
import com.sinonc.iot.vo.InfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: yehuiwang
 * @date: 2020/11/7 10:10
 * @description:
 */
@Api(tags = "设备接口")
@RestController
@RequestMapping("api/base/device/")
public class ApiDeviceController {

    @Resource
    private RemoteBaseFarmService remoteBaseFarmService;

    @Resource
    private IDeviceInfoService deviceInfoService;

    @ApiOperation("扫描二维码新增设备及基地")
    @PostMapping("addData")
    public AjaxResult addDevice(DeviceInfoDto deviceInfoDto) {
        if (deviceInfoDto == null) {
            return AjaxResult.error("参数异常");
        }

        BaseFarm baseFarms = remoteBaseFarmService.getFarmInfo(deviceInfoDto.getFarmId());
        if (baseFarms != null) {
            return AjaxResult.error("该基地已存在，请更换基地名称");
        }
        //基础数据
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setDeviceName(deviceInfoDto.getDeviceName());
        deviceInfo.setDeviceId(deviceInfoDto.getDeviceId());
        DeviceInfo device = deviceInfoService.getDeviceInfoByDeviceId(deviceInfoDto.getDeviceId());
        if (device != null) {
            return AjaxResult.error("该设备已存在");
        }
        deviceInfoService.insertDeviceInfo(deviceInfo);
        BaseFarm baseFarm=new BaseFarm();
        baseFarm.setAreaCode("360721");
        baseFarm.setFarmName(deviceInfoDto.getFarmName());
        baseFarm.setDeviceIds(deviceInfoDto.getDeviceId());
        baseFarm.setHasMonitor(0L);
        baseFarm.setHasIot(1L);
        baseFarm.setPictures("http://sinonc-develop-test.oss-cn-hangzhou.aliyuncs.com/f0156398-4de5-493e-bd77-750d65de46c2.png,");


        remoteBaseFarmService.addBaseFarm(baseFarm);


        return AjaxResult.success("操作成功");
    }

    @ApiOperation("获取梁平区传感器设备数据")
    @GetMapping("selectDeviceList")
    public AjaxResult selectDeviceList(){
        List<InfoVo> infoVoList = new ArrayList<>();
        List<BaseFarm> baseFarmList = remoteBaseFarmService.getFarmList();
        baseFarmList.forEach(entity ->{
            //查询行政区域
            AreaCode areaCode = remoteBaseFarmService.getInfo(Long.valueOf(entity.getAreaCode()));
            if(areaCode != null){
                List<String> ids = Arrays.asList(entity.getDeviceIds().split(","));
                for (String id : ids) {
                    DeviceInfo deviceInfo = deviceInfoService.getDeviceInfo(id);
                    if(deviceInfo != null){
                        InfoVo infoVo = new InfoVo();
                        infoVo.setFarmName(entity.getFarmName());
                        infoVo.setCountryName(areaCode.getName());
                        infoVo.setDeviceName(deviceInfo.getDeviceName());
                        infoVo.setFrom(Constants.FROM);
                        infoVo.setStatus(Constants.STATUS);
                        infoVoList.add(infoVo);
                    }
                }
            }
        });

        return AjaxResult.success(infoVoList);
    }
}
