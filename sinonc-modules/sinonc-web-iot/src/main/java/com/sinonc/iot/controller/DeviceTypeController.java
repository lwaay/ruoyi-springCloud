package com.sinonc.iot.controller;

import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.iot.api.domain.DeviceType;
import com.sinonc.iot.service.IDeviceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 设备类型Controller
 *
 * @author ruoyi
 * @date 2020-11-05
 */
@RestController
@RequestMapping("/type")
public class DeviceTypeController extends BaseController {
    @Autowired
    private IDeviceTypeService deviceTypeService;

    /**
     * 查询设备类型列表
     */
    @PreAuthorize(hasPermi = "system:type:list")
    @GetMapping("/list")
    public TableDataInfo list(DeviceType deviceType) {
        startPage();
        List<DeviceType> list = deviceTypeService.selectDeviceTypeList(deviceType);
        return getDataTable(list);
    }

    /**
     * 导出设备类型列表
     */
    @PreAuthorize(hasPermi = "system:type:export")
    @Log(title = "设备类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DeviceType deviceType) throws IOException {
        List<DeviceType> list = deviceTypeService.selectDeviceTypeList(deviceType);
        ExcelUtil<DeviceType> util = new ExcelUtil<DeviceType>(DeviceType.class);
        util.exportExcel(response, list, "type");
    }

    /**
     * 获取设备类型详细信息
     */
    @PreAuthorize(hasPermi = "system:type:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(deviceTypeService.selectDeviceTypeById(id));
    }

    /**
     * 新增设备类型
     */
    @PreAuthorize(hasPermi = "system:type:add")
    @Log(title = "设备类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DeviceType deviceType) {
        return toAjax(deviceTypeService.insertDeviceType(deviceType));
    }

    /**
     * 修改设备类型
     */
    @PreAuthorize(hasPermi = "system:type:edit")
    @Log(title = "设备类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DeviceType deviceType) {
        return toAjax(deviceTypeService.updateDeviceType(deviceType));
    }

    /**
     * 删除设备类型
     */
    @PreAuthorize(hasPermi = "system:type:remove")
    @Log(title = "设备类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(deviceTypeService.deleteDeviceTypeByIds(ids));
    }

    @GetMapping("typeList")
    public R<List<DeviceType>> typeList() {
        List<DeviceType> deviceTypes = deviceTypeService.getAllType();
        return R.ok(deviceTypes);
    }

    @GetMapping("getDeviceType/{type}")
    public R<DeviceType> getDeviceType(@PathVariable("type") String type) {
        DeviceType deviceType = deviceTypeService.getDeviceType(type);
        return R.ok(deviceType);
    }

    @GetMapping("/api/getDeviceType/{type}")
    public DeviceType getApiDeviceType(@PathVariable("type") String type) {
        return deviceTypeService.getDeviceType(type);
    }
}
