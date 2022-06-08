package com.sinonc.agriculture.controller;

import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.agriculture.domain.OdAdvertisement;
import com.sinonc.agriculture.service.OdAdvertisementService;

import com.sinonc.common.security.annotation.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 轮播图Controller
 *
 * @author ruoyi
 * @date 2020-04-15
 */
@Controller
@RequestMapping("/agriculture/Advertisement")
public class OdAdvertisementController extends BaseController {
    private String prefix = "agriculture/Advertisement";

    @Autowired
    private OdAdvertisementService odAdvertisementService;

    @PreAuthorize(hasPermi = "agriculture:Advertisement:view")
    @GetMapping()
    public String Advertisement() {
        return prefix + "/Advertisement";
    }

    /**
     * 查询轮播图列表
     */
    @PreAuthorize(hasPermi = "agriculture:Advertisement:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OdAdvertisement odAdvertisement) {
        startPage();
        List<OdAdvertisement> list = odAdvertisementService.getOdAdvertisementList(odAdvertisement);
        return getDataTable(list);
    }

    /**
     * 导出轮播图列表
     */
    @PreAuthorize(hasPermi = "agriculture:Advertisement:export")
    @Log(title = "轮播图", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OdAdvertisement odAdvertisement) {
        List<OdAdvertisement> list = odAdvertisementService.getOdAdvertisementList(odAdvertisement);
        ExcelUtil<OdAdvertisement> util = new ExcelUtil<OdAdvertisement>(OdAdvertisement.class);
        return util.exportExcel(list, "Advertisement");
    }

    /**
     * 新增轮播图
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存轮播图
     */
    @PreAuthorize(hasPermi = "agriculture:Advertisement:add")
    @Log(title = "轮播图", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OdAdvertisement odAdvertisement) {
        return toAjax(odAdvertisementService.addOdAdvertisement(odAdvertisement));
    }

    /**
     * 修改轮播图
     */
    @GetMapping("/edit/{adverId}")
    public String edit(@PathVariable("adverId") Long adverId, ModelMap mmap) {
        OdAdvertisement odAdvertisement = odAdvertisementService.getOdAdvertisementById(adverId);
        mmap.put("odAdvertisement", odAdvertisement);
        return prefix + "/edit";
    }

    /**
     * 修改保存轮播图
     */
    @PreAuthorize(hasPermi = "agriculture:Advertisement:edit")
    @Log(title = "轮播图", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OdAdvertisement odAdvertisement) {
        return toAjax(odAdvertisementService.updateOdAdvertisement(odAdvertisement));
    }

    /**
     * 删除轮播图
     */
    @PreAuthorize(hasPermi = "agriculture:Advertisement:remove")
    @Log(title = "轮播图", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(odAdvertisementService.deleteOdAdvertisementByIds(ids));
    }
}
