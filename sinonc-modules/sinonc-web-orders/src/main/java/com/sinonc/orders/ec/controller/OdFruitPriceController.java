package com.sinonc.orders.ec.controller;

import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.orders.ec.domain.OdFruitPrice;
import com.sinonc.orders.ec.service.IOdFruitPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 水果价格Controller
 *
 * @author ruoyi
 * @date 2020-09-27
 */
@RestController
@RequestMapping("/ec/price")
public class OdFruitPriceController extends BaseController {
    @Autowired
    private IOdFruitPriceService odFruitPriceService;

    /**
     * 查询水果价格列表
     */
    @PreAuthorize(hasPermi = "system:price:list")
    @GetMapping("/list")
    public TableDataInfo list(OdFruitPrice odFruitPrice) {
        startPage();
        List<OdFruitPrice> list = odFruitPriceService.selectOdFruitPriceList(odFruitPrice);
        return getDataTable(list);
    }

    /**
     * 导出水果价格列表
     */
    @PreAuthorize(hasPermi = "system:price:export")
    @Log(title = "水果价格", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OdFruitPrice odFruitPrice) throws IOException {
        List<OdFruitPrice> list = odFruitPriceService.selectOdFruitPriceList(odFruitPrice);
        ExcelUtil<OdFruitPrice> util = new ExcelUtil<OdFruitPrice>(OdFruitPrice.class);
        util.exportExcel(response, list, "price");
    }

    /**
     * 获取水果价格详细信息
     */
    @PreAuthorize(hasPermi = "system:price:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(odFruitPriceService.selectOdFruitPriceById(id));
    }

    /**
     * 新增水果价格
     */
    @PreAuthorize(hasPermi = "system:price:add")
    @Log(title = "水果价格", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OdFruitPrice odFruitPrice) {
        return toAjax(odFruitPriceService.insertOdFruitPrice(odFruitPrice));
    }

    /**
     * 修改水果价格
     */
    @PreAuthorize(hasPermi = "system:price:edit")
    @Log(title = "水果价格", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OdFruitPrice odFruitPrice) {
        return toAjax(odFruitPriceService.updateOdFruitPrice(odFruitPrice));
    }

    /**
     * 删除水果价格
     */
    @PreAuthorize(hasPermi = "system:price:remove")
    @Log(title = "水果价格", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(odFruitPriceService.deleteOdFruitPriceByIds(ids));
    }
}
