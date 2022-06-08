package com.sinonc.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.sinonc.system.api.domain.SysImportLog;
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
import com.sinonc.system.service.ISysImportLogService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 数据导入日志Controller
 *
 * @author ruoyi
 * @date 2021-08-11
 */
@RestController
@RequestMapping("/importLog")
public class SysImportLogController extends BaseController {
    @Autowired
    private ISysImportLogService sysImportLogService;

    /**
     * 查询数据导入日志列表
     */
    @PreAuthorize(hasPermi = "system:importLog:list")
    @GetMapping("/list")
    public TableDataInfo list(SysImportLog sysImportLog) {
        startPage();
        List<SysImportLog> list = sysImportLogService.selectSysImportLogList(sysImportLog);
        return getDataTable(list);
    }

    /**
     * 导出数据导入日志列表
     */
    @PreAuthorize(hasPermi = "system:importLog:export")
    @Log(title = "数据导入日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysImportLog sysImportLog) throws IOException {
        List<SysImportLog> list = sysImportLogService.selectSysImportLogList(sysImportLog);
        ExcelUtil<SysImportLog> util = new ExcelUtil<SysImportLog>(SysImportLog.class);
        util.exportExcel(response, list, "importLog");
    }

    /**
     * 获取数据导入日志详细信息
     */
    @PreAuthorize(hasPermi = "system:importLog:query")
    @GetMapping(value = "/{logId}")
    public AjaxResult getInfo(@PathVariable("logId") Long logId) {
        return AjaxResult.success(sysImportLogService.selectSysImportLogById(logId));
    }

    /**
     * 新增数据导入日志
     */
    @PreAuthorize(hasPermi = "system:importLog:add")
    @Log(title = "数据导入日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysImportLog sysImportLog) {
        return toAjax(sysImportLogService.insertSysImportLog(sysImportLog));
    }

    /**
     * 修改数据导入日志
     */
    @PreAuthorize(hasPermi = "system:importLog:edit")
    @Log(title = "数据导入日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysImportLog sysImportLog) {
        return toAjax(sysImportLogService.updateSysImportLog(sysImportLog));
    }

    /**
     * 删除数据导入日志
     */
    @PreAuthorize(hasPermi = "system:importLog:remove")
    @Log(title = "数据导入日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{logIds}")
    public AjaxResult remove(@PathVariable Long[] logIds) {
        return toAjax(sysImportLogService.deleteSysImportLogByIds(logIds));
    }
}
