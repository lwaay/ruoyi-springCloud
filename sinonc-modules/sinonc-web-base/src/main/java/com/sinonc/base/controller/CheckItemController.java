package com.sinonc.base.controller;

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
import com.sinonc.base.domain.CheckItem;
import com.sinonc.base.service.ICheckItemService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 检验项目Controller
 *
 * @author ruoyi
 * @date 2022-04-24
 */
@RestController
@RequestMapping("/item")
public class CheckItemController extends BaseController {
    @Autowired
    private ICheckItemService checkItemService;

    /**
     * 查询检验项目列表
     */
    @PreAuthorize(hasPermi = "base:item:list")
    @GetMapping("/list")
    public TableDataInfo list(CheckItem checkItem) {
        startPage();
        List<CheckItem> list = checkItemService.selectCheckItemList(checkItem);
        return getDataTable(list);
    }

    /**
     * 导出检验项目列表
     */
    @PreAuthorize(hasPermi = "base:item:export")
    @Log(title = "检验项目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CheckItem checkItem) throws IOException {
        List<CheckItem> list = checkItemService.selectCheckItemList(checkItem);
        ExcelUtil<CheckItem> util = new ExcelUtil<CheckItem>(CheckItem.class);
        util.exportExcel(response, list, "item");
    }

    /**
     * 获取检验项目详细信息
     */
    @PreAuthorize(hasPermi = "base:item:query")
    @GetMapping(value = "/{checkItemId}")
    public AjaxResult getInfo(@PathVariable("checkItemId") Long checkItemId) {
        return AjaxResult.success(checkItemService.selectCheckItemById(checkItemId));
    }

    /**
     * 新增检验项目
     */
    @PreAuthorize(hasPermi = "base:item:add")
    @Log(title = "检验项目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CheckItem checkItem) {
        return toAjax(checkItemService.insertCheckItem(checkItem));
    }

    /**
     * 修改检验项目
     */
    @PreAuthorize(hasPermi = "base:item:edit")
    @Log(title = "检验项目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CheckItem checkItem) {
        return toAjax(checkItemService.updateCheckItem(checkItem));
    }

    /**
     * 删除检验项目
     */
    @PreAuthorize(hasPermi = "base:item:remove")
    @Log(title = "检验项目", businessType = BusinessType.DELETE)
    @DeleteMapping("/{checkItemIds}")
    public AjaxResult remove(@PathVariable Long[] checkItemIds) {
        return toAjax(checkItemService.deleteCheckItemByIds(checkItemIds));
    }
}
