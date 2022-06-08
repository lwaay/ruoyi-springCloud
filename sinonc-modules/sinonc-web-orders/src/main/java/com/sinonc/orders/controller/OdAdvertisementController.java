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
import com.sinonc.orders.domain.OdAdvertisement;
import com.sinonc.orders.service.IOdAdvertisementService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 轮播图Controller
 *
 * @author ruoyi
 * @date 2022-03-28
 */
@RestController
@RequestMapping("/advertisement")
public class OdAdvertisementController extends BaseController {
    @Autowired
    private IOdAdvertisementService odAdvertisementService;

    /**
     * 查询轮播图列表
     */
    @PreAuthorize(hasPermi = "orders:advertisement:list")
    @GetMapping("/list")
    public TableDataInfo list(OdAdvertisement odAdvertisement) {
        startPage();
        List<OdAdvertisement> list = odAdvertisementService.selectOdAdvertisementList(odAdvertisement);
        return getDataTable(list);
    }

    /**
     * 导出轮播图列表
     */
    @PreAuthorize(hasPermi = "orders:advertisement:export")
    @Log(title = "轮播图", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OdAdvertisement odAdvertisement) throws IOException {
        List<OdAdvertisement> list = odAdvertisementService.selectOdAdvertisementList(odAdvertisement);
        ExcelUtil<OdAdvertisement> util = new ExcelUtil<OdAdvertisement>(OdAdvertisement.class);
        util.exportExcel(response, list, "advertisement");
    }

    /**
     * 获取轮播图详细信息
     */
    @PreAuthorize(hasPermi = "orders:advertisement:query")
    @GetMapping(value = "/{adverId}")
    public AjaxResult getInfo(@PathVariable("adverId") Long adverId) {
        return AjaxResult.success(odAdvertisementService.selectOdAdvertisementById(adverId));
    }

    /**
     * 新增轮播图
     */
    @PreAuthorize(hasPermi = "orders:advertisement:add")
    @Log(title = "轮播图", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OdAdvertisement odAdvertisement) {
        return toAjax(odAdvertisementService.insertOdAdvertisement(odAdvertisement));
    }

    /**
     * 修改轮播图
     */
    @PreAuthorize(hasPermi = "orders:advertisement:edit")
    @Log(title = "轮播图", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OdAdvertisement odAdvertisement) {
        return toAjax(odAdvertisementService.updateOdAdvertisement(odAdvertisement));
    }

    /**
     * 删除轮播图
     */
    @PreAuthorize(hasPermi = "orders:advertisement:remove")
    @Log(title = "轮播图", businessType = BusinessType.DELETE)
    @DeleteMapping("/{adverIds}")
    public AjaxResult remove(@PathVariable Long[] adverIds) {
        return toAjax(odAdvertisementService.deleteOdAdvertisementByIds(adverIds));
    }
}
