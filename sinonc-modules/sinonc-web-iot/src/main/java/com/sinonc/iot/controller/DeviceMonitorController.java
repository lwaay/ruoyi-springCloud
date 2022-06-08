package com.sinonc.iot.controller;

import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.iot.api.domain.DeviceMonitor;
import com.sinonc.iot.service.IDeviceMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 监控设备Controller
 *
 * @author ruoyi
 * @date 2020-09-27
 */
@RestController
@RequestMapping("/monitor")
public class DeviceMonitorController extends BaseController {
    @Autowired
    private IDeviceMonitorService deviceMonitorService;

    /**
     * 查询监控设备列表
     */
    @PreAuthorize(hasPermi = "system:monitor:list")
    @GetMapping("/list")
    public TableDataInfo list(DeviceMonitor deviceMonitor) {
        startPage();
        List<DeviceMonitor> list = deviceMonitorService.selectDeviceMonitorList(deviceMonitor);
        return getDataTable(list);
    }

    /**
     * 导出监控设备列表
     */
    @PreAuthorize(hasPermi = "system:monitor:export")
    @Log(title = "监控设备", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DeviceMonitor deviceMonitor) throws IOException {
        List<DeviceMonitor> list = deviceMonitorService.selectDeviceMonitorList(deviceMonitor);
        ExcelUtil<DeviceMonitor> util = new ExcelUtil<DeviceMonitor>(DeviceMonitor.class);
        util.exportExcel(response, list, "monitor");
    }

    /**
     * 获取监控设备详细信息
     */
    @PreAuthorize(hasPermi = "system:monitor:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(deviceMonitorService.selectDeviceMonitorById(id));
    }

    /**
     * 新增监控设备
     */
    @PreAuthorize(hasPermi = "system:monitor:add")
    @Log(title = "监控设备", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DeviceMonitor deviceMonitor) {
        return toAjax(deviceMonitorService.insertDeviceMonitor(deviceMonitor));
    }

    /**
     * 修改监控设备
     */
    @PreAuthorize(hasPermi = "system:monitor:edit")
    @Log(title = "监控设备", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DeviceMonitor deviceMonitor) {
        return toAjax(deviceMonitorService.updateDeviceMonitor(deviceMonitor));
    }

    /**
     * 删除监控设备
     */
    @PreAuthorize(hasPermi = "system:monitor:remove")
    @Log(title = "监控设备", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(deviceMonitorService.deleteDeviceMonitorByIds(ids));
    }

    @GetMapping("monitorCount")
    public int getMonitorCount() {
        return deviceMonitorService.getMonitorCount();
    }

    /**
     * 同步萤石商居监控设备
     */
    @PreAuthorize(hasPermi = "system:monitor:remove")
    @Log(title = "同步萤石商居监控设备", businessType = BusinessType.DELETE)
    @GetMapping("/syncEsYs")
    public AjaxResult syncEsYs() {
        boolean rs = deviceMonitorService.syncEsYs();
        if (rs) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }
}
