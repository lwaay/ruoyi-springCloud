package com.sinonc.orders.controller;

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
import com.sinonc.orders.domain.Specs;
import com.sinonc.orders.service.IOdSpecsService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 规格Controller
 *
 * @author ruoyi
 * @date 2022-03-25
 */
@RestController
@RequestMapping("/odSpecs")
public class OdSpecsController extends BaseController {
    @Autowired
    private IOdSpecsService odSpecsService;

    /**
     * 查询规格列表
     */
    @PreAuthorize(hasPermi = "orders:odSpecs:list")
    @GetMapping("/list")
    public TableDataInfo list(Specs odSpecs) {
        startPage();
        List<Specs> list = odSpecsService.selectOdSpecsList(odSpecs);
        return getDataTable(list);
    }

    /**
     * 导出规格列表
     */
    @PreAuthorize(hasPermi = "orders:odSpecs:export")
    @Log(title = "规格", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Specs odSpecs) throws IOException {
        List<Specs> list = odSpecsService.selectOdSpecsList(odSpecs);
        ExcelUtil<Specs> util = new ExcelUtil<Specs>(Specs.class);
        util.exportExcel(response, list, "odSpecs");
    }

    /**
     * 获取规格详细信息
     */
    @PreAuthorize(hasPermi = "orders:odSpecs:query")
    @GetMapping(value = "/{specsId}")
    public AjaxResult getInfo(@PathVariable("specsId") Long specsId) {
        return AjaxResult.success(odSpecsService.selectOdSpecsById(specsId));
    }

    /**
     * 新增规格
     */
    @PreAuthorize(hasPermi = "orders:odSpecs:add")
    @Log(title = "规格", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Specs odSpecs) {
        return toAjax(odSpecsService.insertOdSpecs(odSpecs));
    }

    /**
     * 修改规格
     */
    @PreAuthorize(hasPermi = "orders:odSpecs:edit")
    @Log(title = "规格", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Specs odSpecs) {
        return toAjax(odSpecsService.updateOdSpecs(odSpecs));
    }

    /**
     * 删除规格
     */
    @PreAuthorize(hasPermi = "orders:odSpecs:remove")
    @Log(title = "规格", businessType = BusinessType.DELETE)
    @DeleteMapping("/{specsIds}")
    public AjaxResult remove(@PathVariable Long[] specsIds) {
        return toAjax(odSpecsService.deleteOdSpecsByIds(specsIds));
    }
}
