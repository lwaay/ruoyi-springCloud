package com.sinonc.agriculture.controller;

import java.util.List;

import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.web.domain.Ztree;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.security.annotation.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.agriculture.domain.SectionDict;
import com.sinonc.agriculture.service.SectionDictService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;

/**
 * 板块字典Controller
 *
 * @author ruoyi
 * @date 2020-03-06
 */
@Controller
@RequestMapping("/dict")
public class SectionDictController extends BaseController
{
    private String prefix = "agriculture/sectionDict";

    @Autowired
    private SectionDictService sectionDictService;

    @PreAuthorize(hasPermi = "agriculture:sectionDict:view")
    @GetMapping()
    public String sectionDict()
    {
        return prefix + "/sectionDict";
    }

    /**
     * 查询板块字典树列表
     */
    @PreAuthorize(hasPermi = "agriculture:sectionDict:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(SectionDict sectionDict)
    {
        startPage();
        return getDataTable(sectionDictService.selectSectionDictList(sectionDict));
    }

    /**
     * 导出板块字典列表
     */
    @PreAuthorize(hasPermi = "agriculture:sectionDict:export")
    @Log(title = "板块字典", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SectionDict sectionDict)
    {
        List<SectionDict> list = sectionDictService.selectSectionDictList(sectionDict);
        ExcelUtil<SectionDict> util = new ExcelUtil<SectionDict>(SectionDict.class);
        return util.exportExcel(list, "sectionDict");
    }

    /**
     * 新增板块字典
     */
    @GetMapping(value = { "/add/{sectionId}", "/add/" })
    public String add(@PathVariable(value = "sectionId", required = false) Long sectionId, ModelMap mmap)
    {
        if (StringUtils.isNotNull(sectionId))
        {
            mmap.put("sectionDict", sectionDictService.selectSectionDictById(sectionId));
        }
        return prefix + "/add";
    }

    /**
     * 新增保存板块字典
     */
    @PreAuthorize(hasPermi = "agriculture:sectionDict:add")
    @Log(title = "板块字典", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody SectionDict sectionDict)
    {
        return toAjax(sectionDictService.insertSectionDict(sectionDict));
    }

    /**
     * 修改板块字典
     */
    @GetMapping("/{sectionId}")
    @ResponseBody
    public AjaxResult edit(@PathVariable("sectionId") Long sectionId)
    {
        return AjaxResult.success(sectionDictService.selectSectionDictById(sectionId));
    }

    /**
     * 修改保存板块字典
     */
    @PreAuthorize(hasPermi = "agriculture:sectionDict:edit")
    @Log(title = "板块字典", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestBody SectionDict sectionDict)
    {
        return toAjax(sectionDictService.updateSectionDict(sectionDict));
    }

    /**
     * 删除
     */
    @PreAuthorize(hasPermi = "agriculture:sectionDict:remove")
    @Log(title = "板块字典", businessType = BusinessType.DELETE)
    @DeleteMapping("/remove/{sectionId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("sectionId") Long sectionId)
    {
        return toAjax(sectionDictService.deleteSectionDictById(sectionId));
    }

    /**
     * 选择板块字典树
     */
    @GetMapping(value = { "/selectSectionDictTree/{sectionId}", "/selectSectionDictTree/" })
    public String selectSectionDictTree(@PathVariable(value = "sectionId", required = false) Long sectionId, ModelMap mmap)
    {
        if (StringUtils.isNotNull(sectionId))
        {
            mmap.put("sectionDict", sectionDictService.selectSectionDictById(sectionId));
        }
        return prefix + "/tree";
    }

    /**
     * 加载板块字典树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = sectionDictService.selectSectionDictTree();
        return ztrees;
    }
}
