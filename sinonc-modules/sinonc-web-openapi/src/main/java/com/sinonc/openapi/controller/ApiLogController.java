package com.sinonc.openapi.controller;

import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.openapi.domain.ApiLog;
import com.sinonc.openapi.service.IApiLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 调用日志Controller
 *
 * @author hhao
 * @date 2020-11-23
 */
@RestController
@RequestMapping("/log")
public class ApiLogController extends BaseController {
    @Autowired
    private IApiLogService apiLogService;

    /**
     * 查询调用日志列表
     */
    @PreAuthorize(hasPermi = "data:log:list")
    @GetMapping("/list")
    public TableDataInfo list(ApiLog apiLog) {
        startPage();
        List<ApiLog> list = apiLogService.selectApiLogList(apiLog);
        return getDataTable(list);
    }

    /**
     * 导出调用日志列表
     */
    @PreAuthorize(hasPermi = "data:log:export")
    @Log(title = "调用日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ApiLog apiLog) throws IOException {
        List<ApiLog> list = apiLogService.selectApiLogList(apiLog);
        ExcelUtil<ApiLog> util = new ExcelUtil<ApiLog>(ApiLog.class);
        util.exportExcel(response, list, "log");
    }

    /**
     * 获取调用日志详细信息
     */
    @PreAuthorize(hasPermi = "data:log:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(apiLogService.selectApiLogById(id));
    }

    /**
     * 新增调用日志
     */
    @PreAuthorize(hasPermi = "data:log:add")
    @Log(title = "调用日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ApiLog apiLog) {
        return toAjax(apiLogService.insertApiLog(apiLog));
    }

    /**
     * 修改调用日志
     */
    @PreAuthorize(hasPermi = "data:log:edit")
    @Log(title = "调用日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ApiLog apiLog) {
        return toAjax(apiLogService.updateApiLog(apiLog));
    }

    /**
     * 删除调用日志
     */
    @PreAuthorize(hasPermi = "data:log:remove")
    @Log(title = "调用日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(apiLogService.deleteApiLogByIds(ids));
    }
}
