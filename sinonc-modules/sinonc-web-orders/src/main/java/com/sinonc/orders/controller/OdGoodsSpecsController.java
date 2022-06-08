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
import com.sinonc.orders.domain.OdGoodsSpecs;
import com.sinonc.orders.service.IOdGoodsSpecsService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 商品规格Controller
 *
 * @author ruoyi
 * @date 2022-03-23
 */
@RestController
@RequestMapping("/specs")
public class OdGoodsSpecsController extends BaseController {
    @Autowired
    private IOdGoodsSpecsService odGoodsSpecsService;

    /**
     * 查询商品规格列表
     */
    @PreAuthorize(hasPermi = "orders:specs:list")
    @GetMapping("/list")
    public TableDataInfo list(OdGoodsSpecs odGoodsSpecs) {
        startPage();
        List<OdGoodsSpecs> list = odGoodsSpecsService.selectOdGoodsSpecsList(odGoodsSpecs);
        return getDataTable(list);
    }

    /**
     * 导出商品规格列表
     */
    @PreAuthorize(hasPermi = "orders:specs:export")
    @Log(title = "商品规格", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OdGoodsSpecs odGoodsSpecs) throws IOException {
        List<OdGoodsSpecs> list = odGoodsSpecsService.selectOdGoodsSpecsList(odGoodsSpecs);
        ExcelUtil<OdGoodsSpecs> util = new ExcelUtil<OdGoodsSpecs>(OdGoodsSpecs.class);
        util.exportExcel(response, list, "specs");
    }

    /**
     * 获取商品规格详细信息
     */
    @PreAuthorize(hasPermi = "orders:specs:query")
    @GetMapping(value = "/{goodsSpecId}")
    public AjaxResult getInfo(@PathVariable("goodsSpecId") Long goodsSpecId) {
        return AjaxResult.success(odGoodsSpecsService.selectOdGoodsSpecsById(goodsSpecId));
    }

    /**
     * 新增商品规格
     */
    @PreAuthorize(hasPermi = "orders:specs:add")
    @Log(title = "商品规格", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OdGoodsSpecs odGoodsSpecs) {
        return toAjax(odGoodsSpecsService.insertOdGoodsSpecs(odGoodsSpecs));
    }

    /**
     * 修改商品规格
     */
    @PreAuthorize(hasPermi = "orders:specs:edit")
    @Log(title = "商品规格", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OdGoodsSpecs odGoodsSpecs) {
        return toAjax(odGoodsSpecsService.updateOdGoodsSpecs(odGoodsSpecs));
    }

    /**
     * 删除商品规格
     */
    @PreAuthorize(hasPermi = "orders:specs:remove")
    @Log(title = "商品规格", businessType = BusinessType.DELETE)
    @DeleteMapping("/{goodsSpecIds}")
    public AjaxResult remove(@PathVariable Long[] goodsSpecIds) {
        return toAjax(odGoodsSpecsService.deleteOdGoodsSpecsByIds(goodsSpecIds));
    }
}
