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
import com.sinonc.base.domain.KFertilizerSchema;
import com.sinonc.base.service.IKFertilizerSchemaService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 施肥方案标准Controller
 *
 * @author ruoyi
 * @date 2022-04-19
 */
@RestController
@RequestMapping("/schema")
public class KFertilizerSchemaController extends BaseController {
    @Autowired
    private IKFertilizerSchemaService kFertilizerSchemaService;

/**
 * 查询施肥方案标准列表
 */
@PreAuthorize(hasPermi = "base:schema:list")
@GetMapping("/list")
        public TableDataInfo list(KFertilizerSchema kFertilizerSchema) {
        startPage();
        List<KFertilizerSchema> list = kFertilizerSchemaService.selectKFertilizerSchemaList(kFertilizerSchema);
        return getDataTable(list);
    }
    
    /**
     * 导出施肥方案标准列表
     */
    @PreAuthorize(hasPermi = "base:schema:export")
    @Log(title = "施肥方案标准", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KFertilizerSchema kFertilizerSchema) throws IOException {
        List<KFertilizerSchema> list = kFertilizerSchemaService.selectKFertilizerSchemaList(kFertilizerSchema);
        ExcelUtil<KFertilizerSchema> util = new ExcelUtil<KFertilizerSchema>(KFertilizerSchema. class);
        util.exportExcel(response, list, "schema");
    }

    /**
     * 获取施肥方案标准详细信息
     */
    @PreAuthorize(hasPermi = "base:schema:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(kFertilizerSchemaService.selectKFertilizerSchemaById(id));
    }

    /**
     * 新增施肥方案标准
     */
    @PreAuthorize(hasPermi = "base:schema:add")
    @Log(title = "施肥方案标准", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KFertilizerSchema kFertilizerSchema) {
        return toAjax(kFertilizerSchemaService.insertKFertilizerSchema(kFertilizerSchema));
    }

    /**
     * 修改施肥方案标准
     */
    @PreAuthorize(hasPermi = "base:schema:edit")
    @Log(title = "施肥方案标准", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KFertilizerSchema kFertilizerSchema) {
        return toAjax(kFertilizerSchemaService.updateKFertilizerSchema(kFertilizerSchema));
    }

    /**
     * 删除施肥方案标准
     */
    @PreAuthorize(hasPermi = "base:schema:remove")
    @Log(title = "施肥方案标准", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(kFertilizerSchemaService.deleteKFertilizerSchemaByIds(ids));
    }
}
