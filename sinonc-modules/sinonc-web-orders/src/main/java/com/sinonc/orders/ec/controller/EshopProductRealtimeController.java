package com.sinonc.orders.ec.controller;

import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.orders.ec.domain.EshopProductRealtime;
import com.sinonc.orders.ec.service.IEshopProductRealtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 实时爬取电商数据Controller
 *
 * @author hhao
 * @date 2021-03-24
 */
@RestController
@RequestMapping("/ec/realtime")
public class EshopProductRealtimeController extends BaseController {
    @Autowired
    private IEshopProductRealtimeService eshopProductRealtimeService;

    /**
     * 查询实时爬取电商数据列表
     */
    @PreAuthorize(hasPermi = "ec:realtime:list")
    @GetMapping("/list")
    public TableDataInfo list(EshopProductRealtime eshopProductRealtime) {
        startPage();
        List<EshopProductRealtime> list = eshopProductRealtimeService.selectEshopProductRealtimeList(eshopProductRealtime);
        return getDataTable(list);
    }

    /**
     * 导出实时爬取电商数据列表
     */
    @PreAuthorize(hasPermi = "ec:realtime:export")
    @Log(title = "实时爬取电商数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EshopProductRealtime eshopProductRealtime) throws IOException {
        List<EshopProductRealtime> list = eshopProductRealtimeService.selectEshopProductRealtimeList(eshopProductRealtime);
        ExcelUtil<EshopProductRealtime> util = new ExcelUtil<EshopProductRealtime>(EshopProductRealtime.class);
        util.exportExcel(response, list, "realtime");
    }

    /**
     * 获取实时爬取电商数据详细信息
     */
    @PreAuthorize(hasPermi = "ec:realtime:query")
    @GetMapping(value = "/{eshopId}")
    public AjaxResult getInfo(@PathVariable("eshopId") Long eshopId) {
        return AjaxResult.success(eshopProductRealtimeService.selectEshopProductRealtimeById(eshopId));
    }

    /**
     * 新增实时爬取电商数据
     */
    @PreAuthorize(hasPermi = "ec:realtime:add")
    @Log(title = "实时爬取电商数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EshopProductRealtime eshopProductRealtime) {
        return toAjax(eshopProductRealtimeService.insertEshopProductRealtime(eshopProductRealtime));
    }

    /**
     * 修改实时爬取电商数据
     */
    @PreAuthorize(hasPermi = "ec:realtime:edit")
    @Log(title = "实时爬取电商数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EshopProductRealtime eshopProductRealtime) {
        return toAjax(eshopProductRealtimeService.updateEshopProductRealtime(eshopProductRealtime));
    }

    /**
     * 删除实时爬取电商数据
     */
    @PreAuthorize(hasPermi = "ec:realtime:remove")
    @Log(title = "实时爬取电商数据", businessType = BusinessType.DELETE)
    @DeleteMapping("/{eshopIds}")
    public AjaxResult remove(@PathVariable Long[] eshopIds) {
        return toAjax(eshopProductRealtimeService.deleteEshopProductRealtimeByIds(eshopIds));
    }
}
