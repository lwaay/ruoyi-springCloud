package com.sinonc.orders.ec.controller;

import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.orders.ec.domain.EshopBrand;
import com.sinonc.orders.ec.service.IEshopBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 大屏自定义品牌Controller
 *
 * @author ruoyi
 * @date 2021-03-31
 */
@RestController
@RequestMapping("/ec/brand")
public class EshopBrandController extends BaseController {
    @Autowired
    private IEshopBrandService eshopBrandService;

    /**
     * 查询大屏自定义品牌列表
     */
    @PreAuthorize(hasPermi = "ec:brand:list")
    @GetMapping("/list")
    public TableDataInfo list(EshopBrand eshopBrand) {
        startPage();
        List<EshopBrand> list = eshopBrandService.selectEshopBrandList(eshopBrand);
        return getDataTable(list);
    }

    /**
     * 导出大屏自定义品牌列表
     */
    @PreAuthorize(hasPermi = "ec:brand:export")
    @Log(title = "大屏自定义品牌", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EshopBrand eshopBrand) throws IOException {
        List<EshopBrand> list = eshopBrandService.selectEshopBrandList(eshopBrand);
        ExcelUtil<EshopBrand> util = new ExcelUtil<EshopBrand>(EshopBrand.class);
        util.exportExcel(response, list, "brand");
    }

    /**
     * 获取大屏自定义品牌详细信息
     */
    @PreAuthorize(hasPermi = "ec:brand:query")
    @GetMapping(value = "/{brandId}")
    public AjaxResult getInfo(@PathVariable("brandId") Long brandId) {
        return AjaxResult.success(eshopBrandService.selectEshopBrandById(brandId));
    }

    /**
     * 新增大屏自定义品牌
     */
    @PreAuthorize(hasPermi = "ec:brand:add")
    @Log(title = "大屏自定义品牌", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EshopBrand eshopBrand) {
        return toAjax(eshopBrandService.insertEshopBrand(eshopBrand));
    }

    /**
     * 修改大屏自定义品牌
     */
    @PreAuthorize(hasPermi = "ec:brand:edit")
    @Log(title = "大屏自定义品牌", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EshopBrand eshopBrand) {
        return toAjax(eshopBrandService.updateEshopBrand(eshopBrand));
    }

    /**
     * 删除大屏自定义品牌
     */
    @PreAuthorize(hasPermi = "ec:brand:remove")
    @Log(title = "大屏自定义品牌", businessType = BusinessType.DELETE)
    @DeleteMapping("/{brandIds}")
    public AjaxResult remove(@PathVariable Long[] brandIds) {
        return toAjax(eshopBrandService.deleteEshopBrandByIds(brandIds));
    }
}
