package com.sinonc.base.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sinonc.base.domain.Checkup;
import com.sinonc.base.service.CheckupService;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 土壤、果蔬检测Controller
 *
 * @author ruoyi
 * @date 2022-04-24
 */
@RestController
@RequestMapping("/checkup")
public class CheckupController extends BaseController {
    @Autowired
    private CheckupService checkupService;

    /**
     * 查询土壤、果蔬检测列表
     */
    @PreAuthorize(hasPermi = "base:checkup:list")
    @GetMapping("/list")
    public TableDataInfo list(Checkup checkup) {
        startPage();
        List<Checkup> list = checkupService.listCheckup(checkup);
        return getDataTable(list);
    }

    /**
     * 导出土壤、果蔬检测列表
     */
    @PreAuthorize(hasPermi = "base:checkup:export")
    @Log(title = "土壤、果蔬检测", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Checkup checkup) throws IOException {
        List<Checkup> list = checkupService.listCheckup(checkup);
        ExcelUtil<Checkup> util = new ExcelUtil<Checkup>(Checkup.class);
        util.exportExcel(response, list, "checkup");
    }

    /**
     * 获取土壤、果蔬检测详细信息
     */
    @PreAuthorize(hasPermi = "base:checkup:query")
    @GetMapping(value = "/{checkId}")
    public AjaxResult getInfo(@PathVariable("checkId") Long checkId) {
        try {
            return AjaxResult.success(checkupService.getCheckupById(checkId));
        } catch (JsonProcessingException e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 新增土壤、果蔬检测
     */
    @PreAuthorize(hasPermi = "base:checkup:add")
    @Log(title = "土壤、果蔬检测", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Checkup checkup) {
        try {
            return toAjax(checkupService.addCheckup(checkup));
        } catch (IOException e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 修改土壤、果蔬检测
     */
    @PreAuthorize(hasPermi = "base:checkup:edit")
    @Log(title = "土壤、果蔬检测", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Checkup checkup) {
        try {
            return toAjax(checkupService.updateCheckup(checkup));
        } catch (IOException e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 删除土壤、果蔬检测
     */
    @PreAuthorize(hasPermi = "base:checkup:remove")
    @Log(title = "土壤、果蔬检测", businessType = BusinessType.DELETE)
    @DeleteMapping("/{checkIds}")
    public AjaxResult remove(@PathVariable Long[] checkIds) {
        return toAjax(checkupService.deleteCheckupByIds(checkIds));
    }
}
