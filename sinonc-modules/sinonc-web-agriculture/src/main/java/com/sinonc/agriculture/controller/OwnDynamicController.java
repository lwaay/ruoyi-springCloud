package com.sinonc.agriculture.controller;

import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.agriculture.domain.OwnDynamic;
import com.sinonc.agriculture.service.OwnDynamicService;

import com.sinonc.common.security.annotation.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 我的动态Controller
 *
 * @author ruoyi
 * @date 2020-03-24
 */
@Controller
@RequestMapping("/agriculture/dynamic")
public class OwnDynamicController extends BaseController {
    private String prefix = "agriculture/dynamic";

    @Autowired
    private OwnDynamicService ownDynamicService;

    @PreAuthorize(hasPermi = "agriculture:dynamic:view")
    @GetMapping()
    public String dynamic() {
        return prefix + "/dynamic";
    }

    /**
     * 查询我的动态列表
     */
    @PreAuthorize(hasPermi = "agriculture:dynamic:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OwnDynamic ownDynamic) {
        startPage();
        List<OwnDynamic> list = ownDynamicService.getOwnDynamicList(ownDynamic);
        return getDataTable(list);
    }

    /**
     * 导出我的动态列表
     */
    @PreAuthorize(hasPermi = "agriculture:dynamic:export")
    @Log(title = "我的动态", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OwnDynamic ownDynamic) {
        List<OwnDynamic> list = ownDynamicService.getOwnDynamicList(ownDynamic);
        ExcelUtil<OwnDynamic> util = new ExcelUtil<OwnDynamic>(OwnDynamic.class);
        return util.exportExcel(list, "dynamic");
    }

    /**
     * 新增我的动态
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存我的动态
     */
    @PreAuthorize(hasPermi = "agriculture:dynamic:add")
    @Log(title = "我的动态", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OwnDynamic ownDynamic) {
        return toAjax(ownDynamicService.addOwnDynamic(ownDynamic));
    }

    /**
     * 修改我的动态
     */
    @GetMapping("/edit/{owndynaId}")
    public String edit(@PathVariable("owndynaId") Long owndynaId, ModelMap mmap) {
        OwnDynamic ownDynamic = ownDynamicService.getOwnDynamicById(owndynaId);
        mmap.put("ownDynamic", ownDynamic);
        return prefix + "/edit";
    }

    /**
     * 修改保存我的动态
     */
    @PreAuthorize(hasPermi = "agriculture:dynamic:edit")
    @Log(title = "我的动态", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OwnDynamic ownDynamic) {
        return toAjax(ownDynamicService.updateOwnDynamic(ownDynamic));
    }

    /**
     * 删除我的动态
     */
    @PreAuthorize(hasPermi = "agriculture:dynamic:remove")
    @Log(title = "我的动态", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(ownDynamicService.deleteOwnDynamicByIds(ids));
    }
}
