package com.sinonc.system.controller;

import com.sinonc.common.core.constant.UserConstants;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.system.domain.SysDictType;
import com.sinonc.system.service.ISysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 数据字典信息
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/dict/type")
public class SysDictTypeController extends BaseController {
    @Autowired
    private ISysDictTypeService dictTypeService;

    @PreAuthorize(hasPermi = "system:dict:list")
    @GetMapping("/list")
    public TableDataInfo list(SysDictType dictType) {
        startPage();
        List<SysDictType> list = dictTypeService.selectDictTypeList(dictType);
        return getDataTable(list);
    }

    @Log(title = "字典类型", businessType = BusinessType.EXPORT)
    @PreAuthorize(hasPermi = "system:dict:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysDictType dictType) throws IOException {
        List<SysDictType> list = dictTypeService.selectDictTypeList(dictType);
        ExcelUtil<SysDictType> util = new ExcelUtil<SysDictType>(SysDictType.class);
        util.exportExcel(response, list, "字典类型");
    }

    /**
     * 查询字典类型详细
     */
    @PreAuthorize(hasPermi = "system:dict:query")
    @GetMapping(value = "/{dictId}")
    public AjaxResult getInfo(@PathVariable Long dictId) {
        return AjaxResult.success(dictTypeService.selectDictTypeById(dictId));
    }

    /**
     * 新增字典类型
     */
    @PreAuthorize(hasPermi = "system:dict:add")
    @Log(title = "字典类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysDictType dict) {
        if (UserConstants.NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(dict))) {
            return AjaxResult.error("新增字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
        dict.setCreateBy(SecurityUtils.getUsername());
        return toAjax(dictTypeService.insertDictType(dict));
    }

    /**
     * 修改字典类型
     */
    @PreAuthorize(hasPermi = "system:dict:edit")
    @Log(title = "字典类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysDictType dict) {
        if (UserConstants.NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(dict))) {
            return AjaxResult.error("修改字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
        dict.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(dictTypeService.updateDictType(dict));
    }

    /**
     * 删除字典类型
     */
    @PreAuthorize(hasPermi = "system:dict:remove")
    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{dictIds}")
    public AjaxResult remove(@PathVariable Long[] dictIds) {
        return toAjax(dictTypeService.deleteDictTypeByIds(dictIds));
    }

    /**
     * 清空缓存
     */
    @PreAuthorize(hasPermi = "system:dict:remove")
    @Log(title = "字典类型", businessType = BusinessType.CLEAN)
    @DeleteMapping("/clearCache")
    public AjaxResult clearCache() {
        dictTypeService.clearCache();
        return AjaxResult.success();
    }

    /**
     * 获取字典选择框列表
     */
    @GetMapping("/optionselect")
    public AjaxResult optionselect() {
        List<SysDictType> dictTypes = dictTypeService.selectDictTypeAll();
        return AjaxResult.success(dictTypes);
    }
}
