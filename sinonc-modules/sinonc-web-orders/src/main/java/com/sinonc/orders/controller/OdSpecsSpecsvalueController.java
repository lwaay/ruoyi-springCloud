package com.sinonc.orders.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.sinonc.orders.service.OdSpecsSpecsvalueService;
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
import com.sinonc.orders.domain.OdSpecsSpecsvalue;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 规格_规格值关联Controller
 *
 * @author ruoyi
 * @date 2022-03-25
 */
@RestController
@RequestMapping("/odSpecsValue")
public class OdSpecsSpecsvalueController extends BaseController {
    @Autowired
    private OdSpecsSpecsvalueService odSpecsSpecsvalueService;

    /**
     * 查询规格_规格值关联列表
     */
    @PreAuthorize(hasPermi = "orders:odSpecsValue:list")
    @GetMapping("/list")
    public TableDataInfo list(OdSpecsSpecsvalue odSpecsSpecsvalue) {
        startPage();
        List<OdSpecsSpecsvalue> list = odSpecsSpecsvalueService.listOdSpecsSpecsvalue(odSpecsSpecsvalue);
        return getDataTable(list);
    }

    /**
     * 导出规格_规格值关联列表
     */
    @PreAuthorize(hasPermi = "orders:odSpecsValue:export")
    @Log(title = "规格_规格值关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OdSpecsSpecsvalue odSpecsSpecsvalue) throws IOException {
        List<OdSpecsSpecsvalue> list = odSpecsSpecsvalueService.listOdSpecsSpecsvalue(odSpecsSpecsvalue);
        ExcelUtil<OdSpecsSpecsvalue> util = new ExcelUtil<OdSpecsSpecsvalue>(OdSpecsSpecsvalue.class);
        util.exportExcel(response, list, "odSpecsValue");
    }

    /**
     * 获取规格_规格值关联详细信息
     */
    @PreAuthorize(hasPermi = "orders:odSpecsValue:query")
    @GetMapping(value = "/{specsvalueid}")
    public AjaxResult getInfo(@PathVariable("specsvalueid") Long specsvalueid) {
        return AjaxResult.success(odSpecsSpecsvalueService.getOdSpecsSpecsvalueById(specsvalueid));
    }

    /**
     * 新增规格_规格值关联
     */
    @PreAuthorize(hasPermi = "orders:odSpecsValue:add")
    @Log(title = "规格_规格值关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OdSpecsSpecsvalue odSpecsSpecsvalue) {
        return toAjax(odSpecsSpecsvalueService.addOdSpecsSpecsvalue(odSpecsSpecsvalue));
    }

    /**
     * 修改规格_规格值关联
     */
    @PreAuthorize(hasPermi = "orders:odSpecsValue:edit")
    @Log(title = "规格_规格值关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OdSpecsSpecsvalue odSpecsSpecsvalue) {
        return toAjax(odSpecsSpecsvalueService.updateOdSpecsSpecsvalue(odSpecsSpecsvalue));
    }

    /**
     * 删除规格_规格值关联
     */
    @PreAuthorize(hasPermi = "orders:odSpecsValue:remove")
    @Log(title = "规格_规格值关联", businessType = BusinessType.DELETE)
    @DeleteMapping("/{specsvalueids}")
    public AjaxResult remove(@PathVariable Long[] specsvalueids) {
        return toAjax(odSpecsSpecsvalueService.deleteOdSpecsSpecsvalueByIds(specsvalueids));
    }
}
