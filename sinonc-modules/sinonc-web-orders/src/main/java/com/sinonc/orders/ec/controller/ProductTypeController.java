package com.sinonc.orders.ec.controller;

import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.orders.ec.domain.ProductType;
import com.sinonc.orders.ec.service.IProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 大屏自定义品类Controller
 *
 * @author ruoyi
 * @date 2021-03-31
 */
@RestController
@RequestMapping("/ec/productType")
public class ProductTypeController extends BaseController {
    @Autowired
    private IProductTypeService productTypeService;

    /**
     * 查询大屏自定义品类列表
     */
    @PreAuthorize(hasPermi = "ec:productType:list")
    @GetMapping("/list")
    public TableDataInfo list(ProductType productType) {
        startPage();
        List<ProductType> list = productTypeService.selectProductTypeList(productType);
        return getDataTable(list);
    }

    /**
     * 导出大屏自定义品类列表
     */
    @PreAuthorize(hasPermi = "ec:productType:export")
    @Log(title = "大屏自定义品类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProductType productType) throws IOException {
        List<ProductType> list = productTypeService.selectProductTypeList(productType);
        ExcelUtil<ProductType> util = new ExcelUtil<ProductType>(ProductType.class);
        util.exportExcel(response, list, "productType");
    }

    /**
     * 获取大屏自定义品类详细信息
     */
    @PreAuthorize(hasPermi = "ec:productType:query")
    @GetMapping(value = "/{typeId}")
    public AjaxResult getInfo(@PathVariable("typeId") Long typeId) {
        return AjaxResult.success(productTypeService.selectProductTypeById(typeId));
    }

    /**
     * 新增大屏自定义品类
     */
    @PreAuthorize(hasPermi = "ec:productType:add")
    @Log(title = "大屏自定义品类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductType productType) {
        return toAjax(productTypeService.insertProductType(productType));
    }

    /**
     * 修改大屏自定义品类
     */
    @PreAuthorize(hasPermi = "ec:productType:edit")
    @Log(title = "大屏自定义品类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductType productType) {
        return toAjax(productTypeService.updateProductType(productType));
    }

    /**
     * 删除大屏自定义品类
     */
    @PreAuthorize(hasPermi = "ec:productType:remove")
    @Log(title = "大屏自定义品类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{typeIds}")
    public AjaxResult remove(@PathVariable Long[] typeIds) {
        return toAjax(productTypeService.deleteProductTypeByIds(typeIds));
    }
}
