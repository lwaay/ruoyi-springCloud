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
import com.sinonc.base.domain.KFertilizerSkill;
import com.sinonc.base.service.IKFertilizerSkillService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 施肥技术Controller
 *
 * @author ruoyi
 * @date 2022-04-19
 */
@RestController
@RequestMapping("/skill")
public class KFertilizerSkillController extends BaseController {
    @Autowired
    private IKFertilizerSkillService kFertilizerSkillService;

/**
 * 查询施肥技术列表
 */
@PreAuthorize(hasPermi = "base:skill:list")
@GetMapping("/list")
        public TableDataInfo list(KFertilizerSkill kFertilizerSkill) {
        startPage();
        List<KFertilizerSkill> list = kFertilizerSkillService.selectKFertilizerSkillList(kFertilizerSkill);
        return getDataTable(list);
    }
    
    /**
     * 导出施肥技术列表
     */
    @PreAuthorize(hasPermi = "base:skill:export")
    @Log(title = "施肥技术", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KFertilizerSkill kFertilizerSkill) throws IOException {
        List<KFertilizerSkill> list = kFertilizerSkillService.selectKFertilizerSkillList(kFertilizerSkill);
        ExcelUtil<KFertilizerSkill> util = new ExcelUtil<KFertilizerSkill>(KFertilizerSkill. class);
        util.exportExcel(response, list, "skill");
    }

    /**
     * 获取施肥技术详细信息
     */
    @PreAuthorize(hasPermi = "base:skill:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(kFertilizerSkillService.selectKFertilizerSkillById(id));
    }

    /**
     * 新增施肥技术
     */
    @PreAuthorize(hasPermi = "base:skill:add")
    @Log(title = "施肥技术", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KFertilizerSkill kFertilizerSkill) {
        return toAjax(kFertilizerSkillService.insertKFertilizerSkill(kFertilizerSkill));
    }

    /**
     * 修改施肥技术
     */
    @PreAuthorize(hasPermi = "base:skill:edit")
    @Log(title = "施肥技术", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KFertilizerSkill kFertilizerSkill) {
        return toAjax(kFertilizerSkillService.updateKFertilizerSkill(kFertilizerSkill));
    }

    /**
     * 删除施肥技术
     */
    @PreAuthorize(hasPermi = "base:skill:remove")
    @Log(title = "施肥技术", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(kFertilizerSkillService.deleteKFertilizerSkillByIds(ids));
    }
}
