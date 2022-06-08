package com.sinonc.origins.controller;

import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.origins.domain.ProBrand;
import com.sinonc.origins.service.IProBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 商品品牌Controller
 *
 * @author ruoyi
 * @date 2020-10-23
 */
@RestController
@RequestMapping("/brand")
public class ProBrandController extends BaseController {
    @Autowired
    private IProBrandService proBrandService;

    /**
     * 查询商品品牌列表
     */
    @PreAuthorize(hasPermi = "origins:brand:list")
    @GetMapping("/list")
    public TableDataInfo list(ProBrand proBrand) {
        startPage();
        List<ProBrand> list = proBrandService.selectProBrandList(proBrand);
        return getDataTable(list);
    }


    /**
     * 查询商品品牌列表
     */
    @PreAuthorize(hasPermi = "origins:brand:list")
    @GetMapping("/brandList")
    public AjaxResult brandList(ProBrand proBrand) {
        return AjaxResult.success(proBrandService.selectProBrandList(proBrand));
    }

    /**
     * 导出商品品牌列表
     */
    @PreAuthorize(hasPermi = "origins:brand:export")
    @Log(title = "商品品牌", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProBrand proBrand) throws IOException {
        List<ProBrand> list = proBrandService.selectProBrandList(proBrand);
        ExcelUtil<ProBrand> util = new ExcelUtil<ProBrand>(ProBrand.class);
        util.exportExcel(response, list, "brand");
    }

    /**
     * 获取商品品牌详细信息
     */
    @PreAuthorize(hasPermi = "origins:brand:query")
    @GetMapping(value = "/{brandId}")
    public AjaxResult getInfo(@PathVariable("brandId") Long brandId) {
        return AjaxResult.success(proBrandService.selectProBrandById(brandId));
    }

    /**
     * 新增商品品牌
     */
    @PreAuthorize(hasPermi = "origins:brand:add")
    @Log(title = "商品品牌", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProBrand proBrand) {
        return toAjax(proBrandService.insertProBrand(proBrand));
    }

    /**
     * 修改商品品牌
     */
    @PreAuthorize(hasPermi = "origins:brand:edit")
    @Log(title = "商品品牌", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProBrand proBrand) {
        return toAjax(proBrandService.updateProBrand(proBrand));
    }

    /**
     * 删除商品品牌
     */
    @PreAuthorize(hasPermi = "origins:brand:remove")
    @Log(title = "商品品牌", businessType = BusinessType.DELETE)
    @DeleteMapping("/{brandIds}")
    public AjaxResult remove(@PathVariable Long[] brandIds) {
        return toAjax(proBrandService.deleteProBrandByIds(brandIds));
    }
}
