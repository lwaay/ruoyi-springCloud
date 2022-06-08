package com.sinonc.origins.controller;

import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.origins.domain.ProductLike;
import com.sinonc.origins.service.IProductLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 朔源商品点赞Controller
 *
 * @author ruoyi
 * @date 2020-10-21
 */
@RestController
@RequestMapping("/like")
public class ProductLikeController extends BaseController {
    @Autowired
    private IProductLikeService productLikeService;

    /**
     * 查询朔源商品点赞列表
     */
    @PreAuthorize(hasPermi = "origins:like:list")
    @GetMapping("/list")
        public TableDataInfo list(ProductLike productLike) {
        startPage();
        List<ProductLike> list = productLikeService.selectProductLikeList(productLike);
        return getDataTable(list);
    }

    /**
     * 导出朔源商品点赞列表
     */
    @PreAuthorize(hasPermi = "origins:like:export")
    @Log(title = "朔源商品点赞", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProductLike productLike) throws IOException {
        List<ProductLike> list = productLikeService.selectProductLikeList(productLike);
        ExcelUtil<ProductLike> util = new ExcelUtil<ProductLike>(ProductLike. class);
        util.exportExcel(response, list, "like");
    }

    /**
     * 获取朔源商品点赞详细信息
     */
    @PreAuthorize(hasPermi = "origins:like:query")
    @GetMapping(value = "/get/{productIdP}")
    public AjaxResult getInfo(@PathVariable("productIdP") Long productIdP) {
        return AjaxResult.success(productLikeService.selectProductLikeById(productIdP));
    }

    /**
     * 新增朔源商品点赞
     */
    @PreAuthorize(hasPermi = "origins:like:add")
    @Log(title = "朔源商品点赞", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductLike productLike) {
        return toAjax(productLikeService.insertProductLike(productLike));
    }

    /**
     * 修改朔源商品点赞
     */
    @PreAuthorize(hasPermi = "origins:like:edit")
    @Log(title = "朔源商品点赞", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductLike productLike) {
        return toAjax(productLikeService.updateProductLike(productLike));
    }

    /**
     * 删除朔源商品点赞
     */
    @PreAuthorize(hasPermi = "origins:like:remove")
    @Log(title = "朔源商品点赞", businessType = BusinessType.DELETE)
    @DeleteMapping("/{productIdPs}")
    public AjaxResult remove(@PathVariable Long[] productIdPs) {
        return toAjax(productLikeService.deleteProductLikeByIds(productIdPs));
    }
}
