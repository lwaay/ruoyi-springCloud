package com.sinonc.openapi.controller;

import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.openapi.domain.DataApiItem;
import com.sinonc.openapi.service.IDataApiItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 系统接口明细Controller
 *
 * @author huanghao
 * @date 2020-11-05
 */
@RestController
@RequestMapping("/item")
public class DataApiItemController extends BaseController {
    @Autowired
    private IDataApiItemService dataApiItemService;

    /**
     * 查询系统接口明细列表
     */
    @PreAuthorize(hasPermi = "data:item:list")
    @GetMapping("/list")
    public TableDataInfo list(DataApiItem dataApiItem) {
        startPage();
        List<DataApiItem> list = dataApiItemService.selectDataApiItemList(dataApiItem);
        return getDataTable(list);
    }

    /**
     * 导出系统接口明细列表
     */
    @PreAuthorize(hasPermi = "data:item:export")
    @Log(title = "系统接口明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DataApiItem dataApiItem) throws IOException {
        List<DataApiItem> list = dataApiItemService.selectDataApiItemList(dataApiItem);
        ExcelUtil<DataApiItem> util = new ExcelUtil<DataApiItem>(DataApiItem.class);
        util.exportExcel(response, list, "item");
    }

    /**
     * 获取系统接口明细详细信息
     */
    @PreAuthorize(hasPermi = "data:item:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(dataApiItemService.selectDataApiItemById(id));
    }

    /**
     * 新增系统接口明细
     */
    @PreAuthorize(hasPermi = "data:item:add")
    @Log(title = "系统接口明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DataApiItem dataApiItem) {
        return toAjax(dataApiItemService.insertDataApiItem(dataApiItem));
    }

    /**
     * 修改系统接口明细
     */
    @PreAuthorize(hasPermi = "data:item:edit")
    @Log(title = "系统接口明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DataApiItem dataApiItem) {
        return toAjax(dataApiItemService.updateDataApiItem(dataApiItem));
    }

    /**
     * 删除系统接口明细
     */
    @PreAuthorize(hasPermi = "data:item:remove")
    @Log(title = "系统接口明细", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(dataApiItemService.deleteDataApiItemByIds(ids));
    }
}
