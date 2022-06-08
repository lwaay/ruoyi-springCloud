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
import com.sinonc.orders.domain.OdCategory;
import com.sinonc.orders.service.OdCategoryService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 商品分类Controller
 *
 * @author ruoyi
 * @date 2022-04-06
 */
@RestController
@RequestMapping("/category")
public class OdCategoryController extends BaseController {
    @Autowired
    private OdCategoryService odCategoryService;

/**
 * 查询商品分类列表
 */
@PreAuthorize(hasPermi = "orders:category:list")
@GetMapping("/list")
        public TableDataInfo list(OdCategory odCategory) {
        startPage();
        List<OdCategory> list = odCategoryService.selectOdCategoryList(odCategory);
        return getDataTable(list);
    }

    /**
     * 导出商品分类列表
     */
    @PreAuthorize(hasPermi = "orders:category:export")
    @Log(title = "商品分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OdCategory odCategory) throws IOException {
        List<OdCategory> list = odCategoryService.selectOdCategoryList(odCategory);
        ExcelUtil<OdCategory> util = new ExcelUtil<OdCategory>(OdCategory. class);
        util.exportExcel(response, list, "category");
    }

    /**
     * 获取商品分类详细信息
     */
    @PreAuthorize(hasPermi = "orders:category:query")
    @GetMapping(value = "/{categoryId}")
    public AjaxResult getInfo(@PathVariable("categoryId") Long categoryId) {
        return AjaxResult.success(odCategoryService.selectOdCategoryById(categoryId));
    }

    /**
     * 新增商品分类
     */
    @PreAuthorize(hasPermi = "orders:category:add")
    @Log(title = "商品分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OdCategory odCategory) {
        return toAjax(odCategoryService.insertOdCategory(odCategory));
    }

    /**
     * 修改商品分类
     */
    @PreAuthorize(hasPermi = "orders:category:edit")
    @Log(title = "商品分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OdCategory odCategory) {
        return toAjax(odCategoryService.updateOdCategory(odCategory));
    }

    /**
     * 删除商品分类
     */
    @PreAuthorize(hasPermi = "orders:category:remove")
    @Log(title = "商品分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{categoryIds}")
    public AjaxResult remove(@PathVariable Long[] categoryIds) {
        return toAjax(odCategoryService.deleteOdCategoryByIds(categoryIds));
    }
}
