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
import com.sinonc.base.domain.KInsect;
import com.sinonc.base.service.IKInsectService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 病虫害Controller
 *
 * @author ruoyi
 * @date 2022-04-19
 */
@RestController
@RequestMapping("/insect")
public class KInsectController extends BaseController {
    @Autowired
    private IKInsectService kInsectService;

/**
 * 查询病虫害列表
 */
@PreAuthorize(hasPermi = "base:insect:list")
@GetMapping("/list")
        public TableDataInfo list(KInsect kInsect) {
        startPage();
        List<KInsect> list = kInsectService.selectKInsectList(kInsect);
        return getDataTable(list);
    }
    
    /**
     * 导出病虫害列表
     */
    @PreAuthorize(hasPermi = "base:insect:export")
    @Log(title = "病虫害", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KInsect kInsect) throws IOException {
        List<KInsect> list = kInsectService.selectKInsectList(kInsect);
        ExcelUtil<KInsect> util = new ExcelUtil<KInsect>(KInsect. class);
        util.exportExcel(response, list, "insect");
    }

    /**
     * 获取病虫害详细信息
     */
    @PreAuthorize(hasPermi = "base:insect:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(kInsectService.selectKInsectById(id));
    }

    /**
     * 新增病虫害
     */
    @PreAuthorize(hasPermi = "base:insect:add")
    @Log(title = "病虫害", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KInsect kInsect) {
        return toAjax(kInsectService.insertKInsect(kInsect));
    }

    /**
     * 修改病虫害
     */
    @PreAuthorize(hasPermi = "base:insect:edit")
    @Log(title = "病虫害", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KInsect kInsect) {
        return toAjax(kInsectService.updateKInsect(kInsect));
    }

    /**
     * 删除病虫害
     */
    @PreAuthorize(hasPermi = "base:insect:remove")
    @Log(title = "病虫害", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(kInsectService.deleteKInsectByIds(ids));
    }
}
