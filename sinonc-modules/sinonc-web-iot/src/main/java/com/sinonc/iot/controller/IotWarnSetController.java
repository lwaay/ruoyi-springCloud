package com.sinonc.iot.controller;

import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.sms.SmsService;
import com.sinonc.iot.config.property.IotConfig;
import com.sinonc.iot.domain.IotWarnSet;
import com.sinonc.iot.service.IotWarnSetService;
import com.sinonc.iot.util.WarnUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 设备告警条件设置Controller
 *
 * @author ruoyi
 * @date 2021-04-09
 */
@Controller
@RequestMapping("/warn/warnset")
public class IotWarnSetController extends BaseController {
    private String prefix = "iot/warnset";

    @Autowired
    private IotWarnSetService iotWarnSetService;

    @Autowired
    private IotConfig appInfo;


    @GetMapping()
    public String warnset(ModelMap mmap) {
        List<Map> deviceInfoList = WarnUtils.getDeviceInfoList(appInfo.getAppKey(), appInfo.getAppSecret(), appInfo.getHost());
        mmap.put("deviceInfoList", deviceInfoList);
        return prefix + "/warnset";
    }

    /**
     * 查询设备告警条件设置列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(IotWarnSet iotWarnSet) {
        startPage();
        List<IotWarnSet> list = iotWarnSetService.getIotWarnSetList(iotWarnSet);
        return getDataTable(list);
    }

    @GetMapping("getDeviceType")
    @ResponseBody
    public AjaxResult getDeviceType(String deviceId) {
        String typeUrl = appInfo.getHost() + "/api/iot/devicedata/getDeviceType?deviceId=" + deviceId;
        AjaxResult ajaxResult = WarnUtils.getBodyAjaxResult(appInfo.getAppKey(), appInfo.getAppSecret(), typeUrl);
        return ajaxResult;
    }

    @GetMapping("getMetrics")
    @ResponseBody
    public AjaxResult getMetrics(String deviceTypeId) {
        String typeUrl = appInfo.getHost() + "/api/iot/devicedata/getMetrics?deviceTypeId=" + deviceTypeId;
        AjaxResult ajaxResult = WarnUtils.getBodyAjaxResult(appInfo.getAppKey(), appInfo.getAppSecret(), typeUrl);
        return ajaxResult;
    }

    /**
     * 导出设备告警条件设置列表
     */
    @Log(title = "设备告警条件设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(IotWarnSet iotWarnSet) {
        List<IotWarnSet> list = iotWarnSetService.getIotWarnSetList(iotWarnSet);
        ExcelUtil<IotWarnSet> util = new ExcelUtil<IotWarnSet>(IotWarnSet.class);
        return util.exportExcel(list, "warnset");
    }

    /**
     * 新增设备告警条件设置
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        List<Map> deviceInfoList = WarnUtils.getDeviceInfoList(appInfo.getAppKey(), appInfo.getAppSecret(),appInfo.getHost());
        mmap.put("deviceInfoList", deviceInfoList);
        return prefix + "/add";
    }

    /**
     * 新增保存设备告警条件设置
     */
    @Log(title = "设备告警条件设置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(IotWarnSet iotWarnSet) {
        return toAjax(iotWarnSetService.addIotWarnSet(iotWarnSet));
    }

    /**
     * 修改设备告警条件设置
     */
    @GetMapping("/edit/{warnsetId}")
    public String edit(@PathVariable("warnsetId") Long warnsetId, ModelMap mmap) {
        IotWarnSet iotWarnSet = iotWarnSetService.getIotWarnSetById(warnsetId);
        String typeUrl = appInfo.getHost() + "/api/iot/third/getDeviceInfo?deviceIds=" + iotWarnSet.getDeviceId();
        AjaxResult ajaxResult = WarnUtils.getBodyAjaxResult(appInfo.getAppKey(), appInfo.getAppSecret(), typeUrl);
        List<Map> deviceInfoList = (List<Map>) ajaxResult.get("data");
        Map map = deviceInfoList.get(0);
        iotWarnSet.setDeviceName((String) map.get("deviceName"));
        mmap.put("iotWarnSet", iotWarnSet);
        return prefix + "/edit";
    }

    /**
     * 修改保存设备告警条件设置
     */
    @Log(title = "设备告警条件设置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(IotWarnSet iotWarnSet) {
        return toAjax(iotWarnSetService.updateIotWarnSet(iotWarnSet));
    }

    /**
     * 修改状态
     */
    @Log(title = "修改状态", businessType = BusinessType.UPDATE)
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(IotWarnSet iotWarnSet) {
        return toAjax(iotWarnSetService.updateIotWarnSet(iotWarnSet));
    }

    /**
     * 删除设备告警条件设置
     */
    @Log(title = "设备告警条件设置", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(iotWarnSetService.deleteIotWarnSetByIds(ids));
    }

}
