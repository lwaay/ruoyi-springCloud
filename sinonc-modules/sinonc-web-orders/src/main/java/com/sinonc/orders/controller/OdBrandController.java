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
import com.sinonc.orders.domain.OdBrand;
import com.sinonc.orders.service.IOdBrandService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 商品品牌Controller
 *
 * @author ruoyi
 * @date 2022-03-25
 */
@RestController
@RequestMapping("/brand")
public class OdBrandController extends BaseController {
    @Autowired
    private IOdBrandService odBrandService;

    /**
     * 查询商品品牌列表
     */
    @PreAuthorize(hasPermi = "orders:brand:list")
    @GetMapping("/list")
    public TableDataInfo list(OdBrand odBrand) {
        startPage();
        List<OdBrand> list = odBrandService.selectOdBrandList(odBrand);
        return getDataTable(list);
    }

    /**
     * 导出商品品牌列表
     */
    @PreAuthorize(hasPermi = "orders:brand:export")
    @Log(title = "商品品牌", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OdBrand odBrand) throws IOException {
        List<OdBrand> list = odBrandService.selectOdBrandList(odBrand);
        ExcelUtil<OdBrand> util = new ExcelUtil<OdBrand>(OdBrand.class);
        util.exportExcel(response, list, "brand");
    }

    /**
     * 获取商品品牌详细信息
     */
    @PreAuthorize(hasPermi = "orders:brand:query")
    @GetMapping(value = "/{brandId}")
    public AjaxResult getInfo(@PathVariable("brandId") Long brandId) {
        return AjaxResult.success(odBrandService.selectOdBrandById(brandId));
    }

    /**
     * 新增商品品牌
     */
    @PreAuthorize(hasPermi = "orders:brand:add")
    @Log(title = "商品品牌", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OdBrand odBrand) {
        return toAjax(odBrandService.insertOdBrand(odBrand));
    }

    /**
     * 修改商品品牌
     */
    @PreAuthorize(hasPermi = "orders:brand:edit")
    @Log(title = "商品品牌", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OdBrand odBrand) {
        return toAjax(odBrandService.updateOdBrand(odBrand));
    }

    /**
     * 删除商品品牌
     */
    @PreAuthorize(hasPermi = "orders:brand:remove")
    @Log(title = "商品品牌", businessType = BusinessType.DELETE)
    @DeleteMapping("/{brandIds}")
    public AjaxResult remove(@PathVariable Long[] brandIds) {
        return toAjax(odBrandService.deleteOdBrandByIds(brandIds));
    }
}
