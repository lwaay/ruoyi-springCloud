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
import com.sinonc.base.domain.OrchardTrend;
import com.sinonc.base.service.IOrchardTrendService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 果园动态Controller
 *
 * @author ruoyi
 * @date 2022-04-27
 */
@RestController
@RequestMapping("/trend")
public class OrchardTrendController extends BaseController {
    @Autowired
    private IOrchardTrendService orchardTrendService;

/**
 * 查询果园动态列表
 */
@PreAuthorize(hasPermi = "base:trend:list")
@GetMapping("/list")
        public TableDataInfo list(OrchardTrend orchardTrend) {
        startPage();
        List<OrchardTrend> list = orchardTrendService.selectOrchardTrendList(orchardTrend);
        return getDataTable(list);
    }
    
    /**
     * 导出果园动态列表
     */
    @PreAuthorize(hasPermi = "base:trend:export")
    @Log(title = "果园动态", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OrchardTrend orchardTrend) throws IOException {
        List<OrchardTrend> list = orchardTrendService.selectOrchardTrendList(orchardTrend);
        ExcelUtil<OrchardTrend> util = new ExcelUtil<OrchardTrend>(OrchardTrend. class);
        util.exportExcel(response, list, "trend");
    }

    /**
     * 获取果园动态详细信息
     */
    @PreAuthorize(hasPermi = "base:trend:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(orchardTrendService.selectOrchardTrendById(id));
    }

    /**
     * 新增果园动态
     */
    @PreAuthorize(hasPermi = "base:trend:add")
    @Log(title = "果园动态", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OrchardTrend orchardTrend) {
        return toAjax(orchardTrendService.insertOrchardTrend(orchardTrend));
    }

    /**
     * 修改果园动态
     */
    @PreAuthorize(hasPermi = "base:trend:edit")
    @Log(title = "果园动态", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OrchardTrend orchardTrend) {
        return toAjax(orchardTrendService.updateOrchardTrend(orchardTrend));
    }

    /**
     * 删除果园动态
     */
    @PreAuthorize(hasPermi = "base:trend:remove")
    @Log(title = "果园动态", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(orchardTrendService.deleteOrchardTrendByIds(ids));
    }
}
