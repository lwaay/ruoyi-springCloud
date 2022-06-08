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
import com.sinonc.base.domain.KPesticideNorm;
import com.sinonc.base.service.IKPesticideNormService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 病虫害施药标准Controller
 *
 * @author ruoyi
 * @date 2022-04-19
 */
@RestController
@RequestMapping("/norm")
public class KPesticideNormController extends BaseController {
    @Autowired
    private IKPesticideNormService kPesticideNormService;

    /**
     * 查询病虫害施药标准列表
     */
    @PreAuthorize(hasPermi = "base:norm:list")
    @GetMapping("/list")
    public TableDataInfo list(KPesticideNorm kPesticideNorm) {
        startPage();
        List<KPesticideNorm> list = kPesticideNormService.selectKPesticideNormList(kPesticideNorm);
        return getDataTable(list);
    }

    /**
     * 导出病虫害施药标准列表
     */
    @PreAuthorize(hasPermi = "base:norm:export")
    @Log(title = "病虫害施药标准", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KPesticideNorm kPesticideNorm) throws IOException {
        List<KPesticideNorm> list = kPesticideNormService.selectKPesticideNormList(kPesticideNorm);
        ExcelUtil<KPesticideNorm> util = new ExcelUtil<KPesticideNorm>(KPesticideNorm.class);
        util.exportExcel(response, list, "norm");
    }

    /**
     * 获取病虫害施药标准详细信息
     */
    @PreAuthorize(hasPermi = "base:norm:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(kPesticideNormService.selectKPesticideNormById(id));
    }

    /**
     * 新增病虫害施药标准
     */
    @PreAuthorize(hasPermi = "base:norm:add")
    @Log(title = "病虫害施药标准", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KPesticideNorm kPesticideNorm) {
        return toAjax(kPesticideNormService.insertKPesticideNorm(kPesticideNorm));
    }

    /**
     * 修改病虫害施药标准
     */
    @PreAuthorize(hasPermi = "base:norm:edit")
    @Log(title = "病虫害施药标准", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KPesticideNorm kPesticideNorm) {
        return toAjax(kPesticideNormService.updateKPesticideNorm(kPesticideNorm));
    }

    /**
     * 删除病虫害施药标准
     */
    @PreAuthorize(hasPermi = "base:norm:remove")
    @Log(title = "病虫害施药标准", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(kPesticideNormService.deleteKPesticideNormByIds(ids));
    }
}
