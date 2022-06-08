package com.sinonc.origins.controller;

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
import com.sinonc.origins.domain.ProInput;
import com.sinonc.origins.service.IProInputService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 农产品投入品信息Controller
 *
 * @author ruoyi
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/input")
public class ProInputController extends BaseController {
    @Autowired
    private IProInputService proInputService;

    /**
     * 查询农产品投入品信息列表
     */
    @PreAuthorize(hasPermi = "origins:input:list")
    @GetMapping("/list")
    public TableDataInfo list(ProInput proInput) {
        startPage();
        List<ProInput> list = proInputService.selectProInputList(proInput);
        return getDataTable(list);
    }

    /**
     * 导出农产品投入品信息列表
     */
    @PreAuthorize(hasPermi = "origins:input:export")
    @Log(title = "农产品投入品信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProInput proInput) throws IOException {
        List<ProInput> list = proInputService.selectProInputList(proInput);
        ExcelUtil<ProInput> util = new ExcelUtil<ProInput>(ProInput.class);
        util.exportExcel(response, list, "input");
    }

    /**
     * 获取农产品投入品信息详细信息
     */
    @PreAuthorize(hasPermi = "origins:input:query")
    @GetMapping(value = "/{inputId}")
    public AjaxResult getInfo(@PathVariable("inputId") Long inputId) {
        return AjaxResult.success(proInputService.selectProInputById(inputId));
    }

    /**
     * 新增农产品投入品信息
     */
    @PreAuthorize(hasPermi = "origins:input:add")
    @Log(title = "农产品投入品信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProInput proInput) {
        return toAjax(proInputService.insertProInput(proInput));
    }

    /**
     * 修改农产品投入品信息
     */
    @PreAuthorize(hasPermi = "origins:input:edit")
    @Log(title = "农产品投入品信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProInput proInput) {
        return toAjax(proInputService.updateProInput(proInput));
    }

    /**
     * 删除农产品投入品信息
     */
    @PreAuthorize(hasPermi = "origins:input:remove")
    @Log(title = "农产品投入品信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{inputIds}")
    public AjaxResult remove(@PathVariable Long[] inputIds) {
        return toAjax(proInputService.deleteProInputByIds(inputIds));
    }
}
