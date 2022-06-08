package com.sinonc.origins.controller;

import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.origins.api.domain.ProVisit;
import com.sinonc.origins.service.IProVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 朔源访问Controller
 *
 * @author ruoyi
 * @date 2020-10-21
 */
@RestController
@RequestMapping("/visit")
public class ProVisitController extends BaseController {
    @Autowired
    private IProVisitService proVisitService;

    /**
     * 查询朔源访问列表
     */
    @PreAuthorize(hasPermi = "origins:visit:list")
    @GetMapping("/list")
    public TableDataInfo list(ProVisit proVisit) {
        startPage();
        List<ProVisit> list = proVisitService.selectProVisitList(proVisit);
        return getDataTable(list);
    }

    /**
     * 导出朔源访问列表
     */
    @PreAuthorize(hasPermi = "origins:visit:export")
    @Log(title = "朔源访问", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProVisit proVisit) throws IOException {
        List<ProVisit> list = proVisitService.selectProVisitList(proVisit);
        ExcelUtil<ProVisit> util = new ExcelUtil<ProVisit>(ProVisit.class);
        util.exportExcel(response, list, "visit");
    }

    /**
     * 获取朔源访问详细信息
     */
    @PreAuthorize(hasPermi = "origins:visit:query")
    @GetMapping(value = "/{visitId}")
    public AjaxResult getInfo(@PathVariable("visitId") Long visitId) {
        return AjaxResult.success(proVisitService.selectProVisitById(visitId));
    }


    /**
     * 新增朔源访问
     */
    @PreAuthorize(hasPermi = "origins:visit:add")
    @Log(title = "朔源访问", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProVisit proVisit) {
        return toAjax(proVisitService.insertProVisit(proVisit));
    }

    /**
     * 新增朔源访问
     */
    @Log(title = "朔源访问", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult addByOpen(@RequestBody ProVisit proVisit) {
        return toAjax(proVisitService.insertProVisit(proVisit));
    }

    /**
     * 修改朔源访问
     */
    @PreAuthorize(hasPermi = "origins:visit:edit")
    @Log(title = "朔源访问", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProVisit proVisit) {
        return toAjax(proVisitService.updateProVisit(proVisit));
    }

    /**
     * 删除朔源访问
     */
    @PreAuthorize(hasPermi = "origins:visit:remove")
    @Log(title = "朔源访问", businessType = BusinessType.DELETE)
    @DeleteMapping("/{visitIds}")
    public AjaxResult remove(@PathVariable Long[] visitIds) {
        return toAjax(proVisitService.deleteProVisitByIds(visitIds));
    }
}
