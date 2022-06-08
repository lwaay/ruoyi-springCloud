package com.sinonc.base.controller;

import com.sinonc.base.domain.CropInfo;
import com.sinonc.base.service.ICropInfoService;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 脐橙信息Controller
 *
 * @author ruoyi
 * @date 2020-09-27
 */
@RestController
@RequestMapping("/info")
public class CropInfoController extends BaseController {
    @Autowired
    private ICropInfoService cropInfoService;

    /**
     * 查询脐橙信息列表
     */
    @PreAuthorize(hasPermi = "system:info:list")
    @GetMapping("/list")
    public TableDataInfo list(CropInfo cropInfo) {
        startPage();
        List<CropInfo> list = cropInfoService.selectCropInfoList(cropInfo);
        return getDataTable(list);
    }

    /**
     * 导出脐橙信息列表
     */
    @PreAuthorize(hasPermi = "system:info:export")
    @Log(title = "脐橙信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CropInfo cropInfo) throws IOException {
        List<CropInfo> list = cropInfoService.selectCropInfoList(cropInfo);
        ExcelUtil<CropInfo> util = new ExcelUtil<CropInfo>(CropInfo.class);
        util.exportExcel(response, list, "info");
    }

    /**
     * 获取脐橙信息详细信息
     */
    @PreAuthorize(hasPermi = "system:info:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(cropInfoService.selectCropInfoById(id));
    }

    /**
     * 新增脐橙信息
     */
    @PreAuthorize(hasPermi = "system:info:add")
    @Log(title = "脐橙信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CropInfo cropInfo) {
        return toAjax(cropInfoService.insertCropInfo(cropInfo));
    }

    /**
     * 修改脐橙信息
     */
    @PreAuthorize(hasPermi = "system:info:edit")
    @Log(title = "脐橙信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CropInfo cropInfo) {
        return toAjax(cropInfoService.updateCropInfo(cropInfo));
    }

    /**
     * 删除脐橙信息
     */
    @PreAuthorize(hasPermi = "system:info:remove")
    @Log(title = "脐橙信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(cropInfoService.deleteCropInfoByIds(ids));
    }
}
