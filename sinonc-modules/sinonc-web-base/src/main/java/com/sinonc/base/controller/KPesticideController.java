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
import com.sinonc.base.domain.KPesticide;
import com.sinonc.base.service.IKPesticideService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 药剂Controller
 *
 * @author ruoyi
 * @date 2022-04-19
 */
@RestController
@RequestMapping("/pesticide")
public class KPesticideController extends BaseController {
    @Autowired
    private IKPesticideService kPesticideService;

    /**
     * 查询药剂列表
     */
    @PreAuthorize(hasPermi = "base:pesticide:list")
    @GetMapping("/list")
    public TableDataInfo list(KPesticide kPesticide) {
        startPage();
        List<KPesticide> list = kPesticideService.selectKPesticideList(kPesticide);
        return getDataTable(list);
    }

    /**
     * 导出药剂列表
     */
    @PreAuthorize(hasPermi = "base:pesticide:export")
    @Log(title = "药剂", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KPesticide kPesticide) throws IOException {
        List<KPesticide> list = kPesticideService.selectKPesticideList(kPesticide);
        ExcelUtil<KPesticide> util = new ExcelUtil<KPesticide>(KPesticide.class);
        util.exportExcel(response, list, "pesticide");
    }

    /**
     * 获取药剂详细信息
     */
    @PreAuthorize(hasPermi = "base:pesticide:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(kPesticideService.selectKPesticideById(id));
    }

    /**
     * 新增药剂
     */
    @PreAuthorize(hasPermi = "base:pesticide:add")
    @Log(title = "药剂", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KPesticide kPesticide) {
        return toAjax(kPesticideService.insertKPesticide(kPesticide));
    }

    /**
     * 修改药剂
     */
    @PreAuthorize(hasPermi = "base:pesticide:edit")
    @Log(title = "药剂", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KPesticide kPesticide) {
        return toAjax(kPesticideService.updateKPesticide(kPesticide));
    }

    /**
     * 删除药剂
     */
    @PreAuthorize(hasPermi = "base:pesticide:remove")
    @Log(title = "药剂", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(kPesticideService.deleteKPesticideByIds(ids));
    }
}
