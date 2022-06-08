package com.sinonc.iot.controller;

import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.iot.api.vo.DevicePropertyVo;
import com.sinonc.iot.api.domain.DeviceProperty;
import com.sinonc.iot.service.IDevicePropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 设备属性Controller
 *
 * @author ruoyi
 * @date 2020-11-05
 */
@RestController
@RequestMapping("/property")
public class DevicePropertyController extends BaseController {
    @Autowired
    private IDevicePropertyService devicePropertyService;

    /**
     * 查询设备属性列表
     */
    @PreAuthorize(hasPermi = "system:property:list")
    @GetMapping("/list")
    public TableDataInfo list(DeviceProperty deviceProperty) {
        startPage();
        List<DevicePropertyVo> list = devicePropertyService.getAllProperty(deviceProperty);
        return getDataTable(list);
    }

    @GetMapping("/getPropertyList")
    public R<List<DevicePropertyVo>> list(){
        DeviceProperty deviceProperty=new DeviceProperty();
//        deviceProperty.setDeviceTypeId("8");
        return R.ok(devicePropertyService.getAllProperty(deviceProperty));
    }

    /**
     * 导出设备属性列表
     */
    @PreAuthorize(hasPermi = "system:property:export")
    @Log(title = "设备属性", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DeviceProperty deviceProperty) throws IOException {
        List<DeviceProperty> list = devicePropertyService.selectDevicePropertyList(deviceProperty);
        ExcelUtil<DeviceProperty> util = new ExcelUtil<DeviceProperty>(DeviceProperty.class);
        util.exportExcel(response, list, "property");
    }

    /**
     * 获取设备属性详细信息
     */
    @PreAuthorize(hasPermi = "system:property:query")
    @GetMapping(value = "/{propertyId}")
    public AjaxResult getInfo(@PathVariable("propertyId") Long propertyId) {
        return AjaxResult.success(devicePropertyService.selectDevicePropertyById(propertyId));
    }

    /**
     * 新增设备属性
     */
    @PreAuthorize(hasPermi = "system:property:add")
    @Log(title = "设备属性", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DeviceProperty deviceProperty) {
        return toAjax(devicePropertyService.insertDeviceProperty(deviceProperty));
    }

    /**
     * 修改设备属性
     */
    @PreAuthorize(hasPermi = "system:property:edit")
    @Log(title = "设备属性", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DeviceProperty deviceProperty) {
        return toAjax(devicePropertyService.updateDeviceProperty(deviceProperty));
    }

    /**
     * 删除设备属性
     */
    @PreAuthorize(hasPermi = "system:property:remove")
    @Log(title = "设备属性", businessType = BusinessType.DELETE)
    @DeleteMapping("/{propertyIds}")
    public AjaxResult remove(@PathVariable Long[] propertyIds) {
        return toAjax(devicePropertyService.deleteDevicePropertyByIds(propertyIds));
    }

    @GetMapping("getPropertyByType/{id}")
    public R<List<DeviceProperty>> getPropertyByType(@PathVariable("id")String id){
        List<DeviceProperty> devicePropertyList=devicePropertyService.getPropertyByType(id);
        return R.ok(devicePropertyList);
    }
}
