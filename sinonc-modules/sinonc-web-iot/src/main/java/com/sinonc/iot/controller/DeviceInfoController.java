package com.sinonc.iot.controller;

import com.sinonc.base.api.vo.BaseFarmVo;
import com.github.pagehelper.PageInfo;
import com.sinonc.base.api.RemoteBaseFarmService;
import com.sinonc.base.api.domain.AreaCode;
import com.sinonc.base.api.domain.BaseFarm;
import com.sinonc.common.core.constant.Constants;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.PageDomain;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.core.web.page.TableSupport;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.iot.api.domain.DeviceInfo;
import com.sinonc.iot.dto.ExtDeviceInfoDto;
import com.sinonc.iot.service.IDeviceInfoService;
import com.sinonc.iot.vo.DeviceInfoVo;
import com.sinonc.iot.vo.InfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 设备Controller
 *
 * @author ruoyi
 * @date 2020-09-25
 */
@RestController
@RequestMapping("/device")
public class DeviceInfoController extends BaseController {

    @Autowired
    private RemoteBaseFarmService remoteBaseFarmService;

    @Autowired
    private IDeviceInfoService deviceInfoService;

//    @ApiOperation("根据设备id查询设备实时数据")
//    @GetMapping("getDataByDeviceId")
//    public AjaxResult getData(@RequestParam("deviceId") String deviceId) {
//        DeviceInfo deviceInfo = deviceInfoService.getDeviceInfoByDeviceId(deviceId);
//        List<EchartsData> map = new ArrayList<>();
//        switch (deviceInfo.getCorpName()) {
//            case "YF":
//                map = deviceDataService.YF(deviceId);
//                break;
//        }
//
//        return AjaxResult.success(map);
//    }
    /**
     * 查询设备列表
     */
    @PreAuthorize(hasPermi = "system:info:list")
    @GetMapping("/list")
    public TableDataInfo list(DeviceInfo deviceInfo) {
        startPage();
        List<DeviceInfoVo> list = deviceInfoService.getDeviceInfoList(deviceInfo);
        return getDataTable(list);
    }

    /**
     * 导出设备列表
     */
    @PreAuthorize(hasPermi = "system:info:export")
    @Log(title = "设备", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DeviceInfo deviceInfo) throws IOException {
        List<DeviceInfo> list = deviceInfoService.selectDeviceInfoList(deviceInfo);
        ExcelUtil<DeviceInfo> util = new ExcelUtil<DeviceInfo>(DeviceInfo.class);
        util.exportExcel(response, list, "info");
    }

    /**
     * 获取设备详细信息
     */
    @PreAuthorize(hasPermi = "system:info:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(deviceInfoService.selectDeviceInfoById(id));
    }

    /**
     * 新增设备
     */
    @PreAuthorize(hasPermi = "system:info:add")
    @Log(title = "设备", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DeviceInfo deviceInfo) {
        return toAjax(deviceInfoService.insertDeviceInfo(deviceInfo));
    }

    /**
     * 修改设备
     */
    @PreAuthorize(hasPermi = "system:info:edit")
    @Log(title = "设备", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DeviceInfo deviceInfo) {
        return toAjax(deviceInfoService.updateDeviceInfo(deviceInfo));
    }

    /**
     * 删除设备
     */
    @PreAuthorize(hasPermi = "system:info:remove")
    @Log(title = "设备", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(deviceInfoService.deleteDeviceInfoByIds(ids));
    }

    @GetMapping("deviceCount")
    public int getDeviceCount() {
        return deviceInfoService.getCount();
    }

    @GetMapping("getDeviceList/{id}")
    public R<List<DeviceInfo>> getDeviceList(@PathVariable("id") String typeId) {
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setDeviceTypeId(typeId);
        List<DeviceInfo> deviceInfoList = deviceInfoService.selectDeviceInfoList(deviceInfo);
        return R.ok(deviceInfoList);
    }

    @GetMapping("api/getDeviceList/{id}")
    public List<DeviceInfo> getApiDeviceList(@PathVariable("id") String typeId) {
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setDeviceTypeId(typeId);
        return deviceInfoService.selectDeviceInfoList(deviceInfo);
    }

    @PostMapping("addDevice")
    public R<Integer> addDevice(@RequestBody DeviceInfo deviceInfo) {
        int index = deviceInfoService.insertDeviceInfo(deviceInfo);
        return R.ok(index);
    }

    /**
     * 获取梁平区物联网设备运行情况
     * @return
     */
    @GetMapping("selectDeviceList")
    public TableDataInfo selectDeviceList(InfoVo infoVo){
        List<InfoVo> infoVoList = new ArrayList<>();
        List<BaseFarm> baseFarmList = remoteBaseFarmService.getFarmList();
        baseFarmList.forEach(entity ->{
            //查询行政区域
            AreaCode areaCode = remoteBaseFarmService.getInfo(Long.valueOf(entity.getAreaCode()));
            if(areaCode != null){
                String[] ids = entity.getDeviceIds().split(",");
                for (String id : ids) {
                    DeviceInfo deviceInfo = deviceInfoService.getDeviceInfo(id);
                    if(deviceInfo != null){
                        InfoVo info = new InfoVo();
                        info.setFarmName(entity.getFarmName());
                        info.setCountryName(areaCode.getName());
                        info.setDeviceName(deviceInfo.getDeviceName());
                        info.setFrom(Constants.FROM);
                        info.setStatus(Constants.STATUS);
                        infoVoList.add(info);
                    }
                }
            }
        });

        List<InfoVo> list = new ArrayList<>();
        for (InfoVo vo : infoVoList) {
            if(StringUtils.isEmpty(infoVo.getFarmName()) && StringUtils.isEmpty(infoVo.getCountryName()) && StringUtils.isEmpty(infoVo.getDeviceName())){
                list.add(vo);
            }
            if(StringUtils.isNotEmpty(infoVo.getFarmName()) && StringUtils.isNotEmpty(infoVo.getCountryName()) && StringUtils.isNotEmpty(infoVo.getDeviceName())){
                if(vo.getFarmName().equals(infoVo.getFarmName()) && vo.getCountryName().equals(infoVo.getCountryName()) && vo.getDeviceName().equals(infoVo.getDeviceName())){
                    list.add(vo);
                }
            }
            if(StringUtils.isEmpty(infoVo.getFarmName()) && StringUtils.isNotEmpty(infoVo.getCountryName()) && StringUtils.isNotEmpty(infoVo.getDeviceName())){
                if(vo.getCountryName().equals(infoVo.getCountryName()) && vo.getDeviceName().equals(infoVo.getDeviceName())){
                    list.add(vo);
                }
            }
            if(StringUtils.isNotEmpty(infoVo.getFarmName()) && StringUtils.isNotEmpty(infoVo.getCountryName()) && StringUtils.isEmpty(infoVo.getDeviceName())){
                if(vo.getFarmName().equals(infoVo.getFarmName()) && vo.getDeviceName().equals(infoVo.getDeviceName())){
                    list.add(vo);
                }
            }
            if(StringUtils.isEmpty(infoVo.getFarmName()) && StringUtils.isEmpty(infoVo.getCountryName()) && StringUtils.isNotEmpty(infoVo.getDeviceName())){
                if(vo.getDeviceName().equals(infoVo.getDeviceName())){
                    list.add(vo);
                }
            }
            if(StringUtils.isEmpty(infoVo.getFarmName()) && StringUtils.isNotEmpty(infoVo.getCountryName()) && StringUtils.isEmpty(infoVo.getDeviceName())){
                if(vo.getCountryName().equals(infoVo.getCountryName())){
                    list.add(vo);
                }
            }
            if(StringUtils.isNotEmpty(infoVo.getFarmName()) && StringUtils.isEmpty(infoVo.getCountryName()) && StringUtils.isEmpty(infoVo.getDeviceName())){
                if(vo.getFarmName().equals(infoVo.getFarmName())){
                    list.add(vo);
                }
            }
        }
        return startPageList(list);
    }
}
