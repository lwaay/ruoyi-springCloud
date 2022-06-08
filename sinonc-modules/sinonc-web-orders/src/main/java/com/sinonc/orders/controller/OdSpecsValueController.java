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
import com.sinonc.orders.domain.OdSpecsValue;
import com.sinonc.orders.service.IOdSpecsValueService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 规格属性Controller
 *
 * @author ruoyi
 * @date 2022-03-25
 */
@RestController
@RequestMapping("/specsValue")
public class OdSpecsValueController extends BaseController {
    @Autowired
    private IOdSpecsValueService odSpecsValueService;

    /**
     * 查询规格属性列表
     */
    @PreAuthorize(hasPermi = "orders:specsValue:list")
    @GetMapping("/list")
    public TableDataInfo list(OdSpecsValue odSpecsValue) {
        startPage();
        List<OdSpecsValue> list = odSpecsValueService.selectOdSpecsValueList(odSpecsValue);
        return getDataTable(list);
    }

    /**
     * 导出规格属性列表
     */
    @PreAuthorize(hasPermi = "orders:specsValue:export")
    @Log(title = "规格属性", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OdSpecsValue odSpecsValue) throws IOException {
        List<OdSpecsValue> list = odSpecsValueService.selectOdSpecsValueList(odSpecsValue);
        ExcelUtil<OdSpecsValue> util = new ExcelUtil<OdSpecsValue>(OdSpecsValue.class);
        util.exportExcel(response, list, "specsValue");
    }

    /**
     * 获取规格属性详细信息
     */
    @PreAuthorize(hasPermi = "orders:specsValue:query")
    @GetMapping(value = "/{specsValueId}")
    public AjaxResult getInfo(@PathVariable("specsValueId") Long specsValueId) {
        return AjaxResult.success(odSpecsValueService.selectOdSpecsValueById(specsValueId));
    }

    /**
     * 新增规格属性
     */
    @PreAuthorize(hasPermi = "orders:specsValue:add")
    @Log(title = "规格属性", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OdSpecsValue odSpecsValue) {
        return toAjax(odSpecsValueService.insertOdSpecsValue(odSpecsValue));
    }

    /**
     * 修改规格属性
     */
    @PreAuthorize(hasPermi = "orders:specsValue:edit")
    @Log(title = "规格属性", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OdSpecsValue odSpecsValue) {
        return toAjax(odSpecsValueService.updateOdSpecsValue(odSpecsValue));
    }

    /**
     * 删除规格属性
     */
    @PreAuthorize(hasPermi = "orders:specsValue:remove")
    @Log(title = "规格属性", businessType = BusinessType.DELETE)
    @DeleteMapping("/{specsValueIds}")
    public AjaxResult remove(@PathVariable Long[] specsValueIds) {
        return toAjax(odSpecsValueService.deleteOdSpecsValueByIds(specsValueIds));
    }
}
