package com.sinonc.iot.controller;

import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.iot.config.property.IotConfig;
import com.sinonc.iot.domain.IotWarn;
import com.sinonc.iot.service.IotWarnService;
import com.sinonc.iot.util.WarnUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 设备告警信息Controller
 *
 * @author ruoyi
 * @date 2021-04-09
 */
@Controller
@RequestMapping("/warn/warninfo")
public class IotWarnController extends BaseController {
    private String prefix = "iot/warninfo";

    @Autowired
    private IotWarnService iotWarnService;

    @Autowired
    private IotConfig appInfo;

    @GetMapping()
    public String warninfo(ModelMap mmap) {
        List<Map> deviceInfoList = WarnUtils.getDeviceInfoList(appInfo.getAppKey(), appInfo.getAppSecret(), appInfo.getHost());
        mmap.put("deviceInfoList", deviceInfoList);
        return prefix + "/warninfo";
    }


    /**
     * 查询设备告警信息列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(IotWarn iotWarn) {
        startPage();
        List<IotWarn> list = iotWarnService.getIotWarnList(iotWarn);
        return getDataTable(list);
    }

    /**
     * 导出设备告警信息列表
     */
    @Log(title = "设备告警信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(IotWarn iotWarn) {
        List<IotWarn> list = iotWarnService.getIotWarnList(iotWarn);
        ExcelUtil<IotWarn> util = new ExcelUtil<IotWarn>(IotWarn.class);
        return util.exportExcel(list, "warninfo");
    }

    /**
     * 新增设备告警信息
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存设备告警信息
     */
    @Log(title = "设备告警信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(IotWarn iotWarn) {
        return toAjax(iotWarnService.addIotWarn(iotWarn));
    }

    /**
     * 修改设备告警信息
     */
    @GetMapping("/edit/{warnId}")
    public String edit(@PathVariable("warnId") Long warnId, ModelMap mmap) {
        IotWarn iotWarn = iotWarnService.getIotWarnById(warnId);
        mmap.put("iotWarn", iotWarn);
        return prefix + "/edit";
    }

    /**
     * 修改保存设备告警信息
     */
    @Log(title = "设备告警信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(IotWarn iotWarn) {
        return toAjax(iotWarnService.updateIotWarn(iotWarn));
    }

    /**
     * 删除设备告警信息
     */
    @Log(title = "设备告警信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(iotWarnService.deleteIotWarnByIds(ids));
    }
}
