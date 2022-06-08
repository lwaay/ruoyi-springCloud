package com.sinonc.orders.ec.controller;

import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.orders.ec.domain.EshopProductSale;
import com.sinonc.orders.ec.service.IEshopProductSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 实时产品销量Controller
 *
 * @author ruoyi
 * @date 2021-04-13
 */
@RestController
@RequestMapping("/ec/sale")
public class EshopProductSaleController extends BaseController {
    @Autowired
    private IEshopProductSaleService eshopProductSaleService;

    /**
     * 查询实时产品销量列表
     */
    @PreAuthorize(hasPermi = "system:sale:list")
    @GetMapping("/list")
    public TableDataInfo list(EshopProductSale eshopProductSale) {
        startPage();
        List<EshopProductSale> list = eshopProductSaleService.selectEshopProductSaleList(eshopProductSale);
        return getDataTable(list);
    }

    /**
     * 导出实时产品销量列表
     */
    @PreAuthorize(hasPermi = "system:sale:export")
    @Log(title = "实时产品销量", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EshopProductSale eshopProductSale) throws IOException {
        List<EshopProductSale> list = eshopProductSaleService.selectEshopProductSaleList(eshopProductSale);
        ExcelUtil<EshopProductSale> util = new ExcelUtil<EshopProductSale>(EshopProductSale.class);
        util.exportExcel(response, list, "sale");
    }

    /**
     * 获取实时产品销量详细信息
     */
    @PreAuthorize(hasPermi = "system:sale:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(eshopProductSaleService.selectEshopProductSaleById(id));
    }

    /**
     * 新增实时产品销量
     */
    @PreAuthorize(hasPermi = "system:sale:add")
    @Log(title = "实时产品销量", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EshopProductSale eshopProductSale) {
        return toAjax(eshopProductSaleService.insertEshopProductSale(eshopProductSale));
    }

    /**
     * 修改实时产品销量
     */
    @PreAuthorize(hasPermi = "system:sale:edit")
    @Log(title = "实时产品销量", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EshopProductSale eshopProductSale) {
        return toAjax(eshopProductSaleService.updateEshopProductSale(eshopProductSale));
    }

    /**
     * 删除实时产品销量
     */
    @PreAuthorize(hasPermi = "system:sale:remove")
    @Log(title = "实时产品销量", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(eshopProductSaleService.deleteEshopProductSaleByIds(ids));
    }
}
