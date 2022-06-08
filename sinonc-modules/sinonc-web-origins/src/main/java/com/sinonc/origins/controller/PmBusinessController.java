package com.sinonc.origins.controller;

import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.origins.api.domain.PmBusiness;
import com.sinonc.origins.service.IPmBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 经营主体Controller
 *
 * @author ruoyi
 * @date 2020-10-21
 */
@RestController
@RequestMapping("/business")
public class PmBusinessController extends BaseController {
    @Autowired
    private IPmBusinessService pmBusinessService;

    /**
     * 查询经营主体列表
     */
    @PreAuthorize(hasPermi = "origins:business:list")
    @GetMapping("/list")
    public TableDataInfo list(PmBusiness pmBusiness) {
        startPage();
        List<PmBusiness> list = pmBusinessService.selectPmBusinessList(pmBusiness);
        return getDataTable(list);
    }

    /**
     * 导出经营主体列表
     */
    @PreAuthorize(hasPermi = "origins:business:export")
    @Log(title = "经营主体", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PmBusiness pmBusiness) throws IOException {
        List<PmBusiness> list = pmBusinessService.selectPmBusinessList(pmBusiness);
        ExcelUtil<PmBusiness> util = new ExcelUtil<PmBusiness>(PmBusiness.class);
        util.exportExcel(response, list, "business");
    }

    /**
     * 获取经营主体详细信息
     */
    @PreAuthorize(hasPermi = "origins:business:query")
    @GetMapping(value = "/{busiId}")
    public AjaxResult getInfo(@PathVariable("busiId") Long busiId) {
        return AjaxResult.success(pmBusinessService.selectPmBusinessById(busiId));
    }

    /**
     * 获取经营主体详细信息
     */
    @GetMapping(value = "/getPmBusinessById/{busiId}")
    public R<PmBusiness> getPmBusinessById(@PathVariable("busiId") Long busiId) {
        PmBusiness pmBusiness = pmBusinessService.selectPmBusinessById(busiId);
        return R.ok(pmBusiness);
    }

    /**
     * 新增经营主体
     */
    @PreAuthorize(hasPermi = "origins:business:add")
    @Log(title = "经营主体", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PmBusiness pmBusiness) {
        return toAjax(pmBusinessService.insertPmBusiness(pmBusiness));
    }

    /**
     * 第三方新增经营主体
     */
    @Log(title = "经营主体", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult addByOpen(@RequestBody PmBusiness pmBusiness) {
        return toAjax(pmBusinessService.insertPmBusiness(pmBusiness));
    }

    /**
     * 修改经营主体
     */
    @PreAuthorize(hasPermi = "origins:business:edit")
    @Log(title = "经营主体", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PmBusiness pmBusiness) {
        return toAjax(pmBusinessService.updatePmBusiness(pmBusiness));
    }

    /**
     * 删除经营主体
     */
    @PreAuthorize(hasPermi = "origins:business:remove")
    @Log(title = "经营主体", businessType = BusinessType.DELETE)
    @DeleteMapping("/{busiIds}")
    public AjaxResult remove(@PathVariable Long[] busiIds) {
        return toAjax(pmBusinessService.deletePmBusinessByIds(busiIds));
    }

    /**
     * 审核经营主体
     */
    @PreAuthorize(hasPermi = "origins:business:edit")
    @Log(title = "审核经营主体", businessType = BusinessType.UPDATE)
    @PostMapping("/audit")
    public AjaxResult auditPmBusiness(@RequestBody PmBusiness pmBusiness){
        return toAjax(pmBusinessService.auditPmBusiness(pmBusiness));
    }

    @GetMapping("/getAllPmBusi")
    public AjaxResult getAllPmBusi(){
        List<PmBusiness> list = pmBusinessService.getAllPmBusi();
        return AjaxResult.success(list);
    }
}
