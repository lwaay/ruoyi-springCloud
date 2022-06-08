package com.sinonc.agriculture.controller;

import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.agriculture.domain.ExpertDynamic;
import com.sinonc.agriculture.service.ExpertDynamicService;

import com.sinonc.common.security.annotation.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 专家动态Controller
 *
 * @author ruoyi
 * @date 2020-03-31
 */
@Controller
@RequestMapping("/agriculture/expertDynamic")
public class ExpertDynamicController extends BaseController {
    private String prefix = "agriculture/expertDynamic";

    @Autowired
    private ExpertDynamicService expertDynamicService;

    @PreAuthorize(hasPermi = "agriculture:expertDynamic:view")
    @GetMapping()
    public String expertDynamic() {
        return prefix + "/expertDynamic";
    }

    /**
     * 查询专家动态列表
     */
    @PreAuthorize(hasPermi = "agriculture:expertDynamic:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ExpertDynamic expertDynamic) {
        startPage();
        List<ExpertDynamic> list = expertDynamicService.getExpertDynamicList(expertDynamic);
        return getDataTable(list);
    }

    /**
     * 导出专家动态列表
     */
    @PreAuthorize(hasPermi = "agriculture:expertDynamic:export")
    @Log(title = "专家动态", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ExpertDynamic expertDynamic) {
        List<ExpertDynamic> list = expertDynamicService.getExpertDynamicList(expertDynamic);
        ExcelUtil<ExpertDynamic> util = new ExcelUtil<ExpertDynamic>(ExpertDynamic.class);
        return util.exportExcel(list, "expertDynamic");
    }

    /**
     * 新增专家动态
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存专家动态
     */
    @PreAuthorize(hasPermi = "agriculture:expertDynamic:add")
    @Log(title = "专家动态", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ExpertDynamic expertDynamic) {
        return toAjax(expertDynamicService.addExpertDynamic(expertDynamic));
    }

    /**
     * 修改专家动态
     */
    @GetMapping("/edit/{dynamicId}")
    public String edit(@PathVariable("dynamicId") Long dynamicId, ModelMap mmap) {
        ExpertDynamic expertDynamic = expertDynamicService.getExpertDynamicById(dynamicId);
        mmap.put("expertDynamic", expertDynamic);
        return prefix + "/edit";
    }

    /**
     * 修改保存专家动态
     */
    @PreAuthorize(hasPermi = "agriculture:expertDynamic:edit")
    @Log(title = "专家动态", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ExpertDynamic expertDynamic) {
        return toAjax(expertDynamicService.updateExpertDynamic(expertDynamic));
    }

    /**
     * 删除专家动态
     */
    @PreAuthorize(hasPermi = "agriculture:expertDynamic:remove")
    @Log(title = "专家动态", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(expertDynamicService.deleteExpertDynamicByIds(ids));
    }
}
