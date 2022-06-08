package com.sinonc.orders.ec.controller;

import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.orders.ec.domain.EshopSelect;
import com.sinonc.orders.ec.service.IEshopSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 品牌展示Controller
 *
 * @author ruoyi
 * @date 2020-11-10
 */
@RestController
@RequestMapping("/ec/select")
public class EshopSelectController extends BaseController {
    @Autowired
    private IEshopSelectService eshopSelectService;

    /**
     * 查询品牌展示列表
     */
    @PreAuthorize(hasPermi = "ec:select:list")
    @GetMapping("/list")
    public TableDataInfo list(EshopSelect eshopSelect) {
        startPage();
        List<EshopSelect> list = eshopSelectService.selectEshopSelectList(eshopSelect);
        return getDataTable(list);
    }

    /**
     * 导出品牌展示列表
     */
    @PreAuthorize(hasPermi = "ec:select:export")
    @Log(title = "品牌展示", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EshopSelect eshopSelect) throws IOException {
        List<EshopSelect> list = eshopSelectService.selectEshopSelectList(eshopSelect);
        ExcelUtil<EshopSelect> util = new ExcelUtil<EshopSelect>(EshopSelect.class);
        util.exportExcel(response, list, "select");
    }

    /**
     * 获取品牌展示详细信息
     */
    @PreAuthorize(hasPermi = "ec:select:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(eshopSelectService.selectEshopSelectById(id));
    }

    /**
     * 新增品牌展示
     */
    @PreAuthorize(hasPermi = "ec:select:add")
    @Log(title = "品牌展示", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EshopSelect eshopSelect) {
        return toAjax(eshopSelectService.insertEshopSelect(eshopSelect));
    }

    /**
     * 修改品牌展示
     */
    @PreAuthorize(hasPermi = "ec:select:edit")
    @Log(title = "品牌展示", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EshopSelect eshopSelect) {
        return toAjax(eshopSelectService.updateEshopSelect(eshopSelect));
    }

    /**
     * 删除品牌展示
     */
    @PreAuthorize(hasPermi = "ec:select:remove")
    @Log(title = "品牌展示", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(eshopSelectService.deleteEshopSelectByIds(ids));
    }
}
