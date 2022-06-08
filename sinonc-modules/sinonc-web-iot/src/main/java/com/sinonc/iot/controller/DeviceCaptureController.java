package com.sinonc.iot.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.iot.domain.DeviceCapture;
import com.sinonc.iot.service.IDeviceCaptureService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 摄像抓拍Controller
 *
 * @author ruoyi
 * @date 2022-05-09
 */
@RestController
@RequestMapping("/capture")
public class DeviceCaptureController extends BaseController {
    @Autowired
    private IDeviceCaptureService deviceCaptureService;

/**
 * 查询摄像抓拍列表
 */
@PreAuthorize(hasPermi = "iot:capture:list")
@GetMapping("/list")
        public TableDataInfo list(DeviceCapture deviceCapture) {
        startPage();
        List<DeviceCapture> list = deviceCaptureService.selectDeviceCaptureList(deviceCapture);
        return getDataTable(list);
    }
    
    /**
     * 导出摄像抓拍列表
     */
    @PreAuthorize(hasPermi = "iot:capture:export")
    @Log(title = "摄像抓拍", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DeviceCapture deviceCapture) throws IOException {
        List<DeviceCapture> list = deviceCaptureService.selectDeviceCaptureList(deviceCapture);
        ExcelUtil<DeviceCapture> util = new ExcelUtil<DeviceCapture>(DeviceCapture. class);
        util.exportExcel(response, list, "capture");
    }

    /**
     * 获取摄像抓拍详细信息
     */
    @PreAuthorize(hasPermi = "iot:capture:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(deviceCaptureService.selectDeviceCaptureById(id));
    }

    /**
     * 新增摄像抓拍
     */
    @PreAuthorize(hasPermi = "iot:capture:add")
    @Log(title = "摄像抓拍", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DeviceCapture deviceCapture) {
        return toAjax(deviceCaptureService.insertDeviceCapture(deviceCapture));
    }

    /**
     * 修改摄像抓拍
     */
    @PreAuthorize(hasPermi = "iot:capture:edit")
    @Log(title = "摄像抓拍", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DeviceCapture deviceCapture) {
        return toAjax(deviceCaptureService.updateDeviceCapture(deviceCapture));
    }

    /**
     * 删除摄像抓拍
     */
    @PreAuthorize(hasPermi = "iot:capture:remove")
    @Log(title = "摄像抓拍", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(deviceCaptureService.deleteDeviceCaptureByIds(ids));
    }
}
