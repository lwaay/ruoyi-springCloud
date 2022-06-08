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
import com.sinonc.base.domain.KInsectPeriod;
import com.sinonc.base.service.IKInsectPeriodService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 病虫害出现周期Controller
 *
 * @author ruoyi
 * @date 2022-04-19
 */
@RestController
@RequestMapping("/period")
public class KInsectPeriodController extends BaseController {
    @Autowired
    private IKInsectPeriodService kInsectPeriodService;

/**
 * 查询病虫害出现周期列表
 */
@PreAuthorize(hasPermi = "base:period:list")
@GetMapping("/list")
        public TableDataInfo list(KInsectPeriod kInsectPeriod) {
        startPage();
        List<KInsectPeriod> list = kInsectPeriodService.selectKInsectPeriodList(kInsectPeriod);
        return getDataTable(list);
    }
    
    /**
     * 导出病虫害出现周期列表
     */
    @PreAuthorize(hasPermi = "base:period:export")
    @Log(title = "病虫害出现周期", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KInsectPeriod kInsectPeriod) throws IOException {
        List<KInsectPeriod> list = kInsectPeriodService.selectKInsectPeriodList(kInsectPeriod);
        ExcelUtil<KInsectPeriod> util = new ExcelUtil<KInsectPeriod>(KInsectPeriod. class);
        util.exportExcel(response, list, "period");
    }

    /**
     * 获取病虫害出现周期详细信息
     */
    @PreAuthorize(hasPermi = "base:period:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(kInsectPeriodService.selectKInsectPeriodById(id));
    }

    /**
     * 新增病虫害出现周期
     */
    @PreAuthorize(hasPermi = "base:period:add")
    @Log(title = "病虫害出现周期", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KInsectPeriod kInsectPeriod) {
        return toAjax(kInsectPeriodService.insertKInsectPeriod(kInsectPeriod));
    }

    /**
     * 修改病虫害出现周期
     */
    @PreAuthorize(hasPermi = "base:period:edit")
    @Log(title = "病虫害出现周期", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KInsectPeriod kInsectPeriod) {
        return toAjax(kInsectPeriodService.updateKInsectPeriod(kInsectPeriod));
    }

    /**
     * 删除病虫害出现周期
     */
    @PreAuthorize(hasPermi = "base:period:remove")
    @Log(title = "病虫害出现周期", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(kInsectPeriodService.deleteKInsectPeriodByIds(ids));
    }
}
