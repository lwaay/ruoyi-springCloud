package com.sinonc.orders.controller;

import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.orders.domain.OdGoods;
import com.sinonc.orders.dto.BookGoodsDto;
import com.sinonc.orders.service.IOdGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 预订商品Controller
 *
 * @author ruoyi
 * @date 2022-04-01
 */
@RestController
@RequestMapping("/bookGoods")
public class BookGoodsController extends BaseController {
    @Autowired
    private IOdGoodsService odGoodsService;

    /**
     * 查询商品列表
     */
    @PreAuthorize(hasPermi = "orders:bookGoods:list")
    @GetMapping("/list")
    public TableDataInfo list(OdGoods odGoods) {
        startPage();
        List<OdGoods> list = odGoodsService.selectOdGoodsList(odGoods);
        return getDataTable(list);
    }

    /**
     * 导出商品列表
     */
    @PreAuthorize(hasPermi = "orders:bookGoods:export")
    @Log(title = "商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OdGoods odGoods) throws IOException {
        List<OdGoods> list = odGoodsService.selectOdGoodsList(odGoods);
        ExcelUtil<OdGoods> util = new ExcelUtil<OdGoods>(OdGoods.class);
        util.exportExcel(response, list, "goods");
    }

    /**
     * 获取商品详细信息
     */
    @PreAuthorize(hasPermi = "orders:bookGoods:query")
    @GetMapping(value = "detail/{goodsId}")
    public AjaxResult getInfo(@PathVariable("goodsId") Long goodsId) throws Exception {
        return AjaxResult.success(odGoodsService.selectOdGoodsDtoById(goodsId));
    }

    /**
     * 新增商品
     */
    @PreAuthorize(hasPermi = "orders:bookGoods:add")
    @Log(title = "商品", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody BookGoodsDto bookGoodsDto) {
        try {
            return toAjax(odGoodsService.addBookGoods(bookGoodsDto));
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 修改商品
     */
    @PreAuthorize(hasPermi = "orders:bookGoods:edit")
    @Log(title = "商品", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody BookGoodsDto bookGoodsDto) throws Exception {
        int rs = odGoodsService.updateBookGoods(bookGoodsDto);
        return toAjax(rs);
    }

    /**
     * 删除商品
     */
    @PreAuthorize(hasPermi = "orders:bookGoods:remove")
    @Log(title = "商品", businessType = BusinessType.DELETE)
    @DeleteMapping("remove/{goodsIds}")
    public AjaxResult remove(@PathVariable Long[] goodsIds) {
        return toAjax(odGoodsService.deleteOdGoodsByIds(goodsIds));
    }
}
