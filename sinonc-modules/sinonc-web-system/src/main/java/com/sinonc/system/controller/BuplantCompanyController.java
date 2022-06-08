package com.sinonc.system.controller;

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
import com.sinonc.system.domain.BuplantCompany;
import com.sinonc.system.service.IBuplantCompanyService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 种植户（生产商）Controller
 *
 * @author ruoyi
 * @date 2022-02-24
 */
@RestController
@RequestMapping("/buplant")
public class BuplantCompanyController extends BaseController {
    @Autowired
    private IBuplantCompanyService buplantCompanyService;

    /**
     * 查询种植户（生产商）列表
     */
    @PreAuthorize(hasPermi = "system:buplant:list")
    @GetMapping("/list")
    public TableDataInfo list(BuplantCompany buplantCompany) {
        startPage();
        List<BuplantCompany> list = buplantCompanyService.selectBuplantCompanyList(buplantCompany);
        return getDataTable(list);
    }

    /**
     * 导出种植户（生产商）列表
     */
    @PreAuthorize(hasPermi = "system:buplant:export")
    @Log(title = "种植户（生产商）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BuplantCompany buplantCompany) throws IOException {
        List<BuplantCompany> list = buplantCompanyService.selectBuplantCompanyList(buplantCompany);
        ExcelUtil<BuplantCompany> util = new ExcelUtil<BuplantCompany>(BuplantCompany.class);
        util.exportExcel(response, list, "buplant");
    }

    /**
     * 获取种植户（生产商）详细信息
     */
    @PreAuthorize(hasPermi = "system:buplant:query")
    @GetMapping(value = "/{plantId}")
    public AjaxResult getInfo(@PathVariable("plantId") Long plantId) {
        return AjaxResult.success(buplantCompanyService.selectBuplantCompanyById(plantId));
    }

    /**
     * 新增种植户（生产商）
     */
    @PreAuthorize(hasPermi = "system:buplant:add")
    @Log(title = "种植户（生产商）", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BuplantCompany buplantCompany) {
        return toAjax(buplantCompanyService.insertBuplantCompany(buplantCompany));
    }

    /**
     * 修改种植户（生产商）
     */
    @PreAuthorize(hasPermi = "system:buplant:edit")
    @Log(title = "种植户（生产商）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BuplantCompany buplantCompany) {
        return toAjax(buplantCompanyService.updateBuplantCompany(buplantCompany));
    }

    /**
     * 删除种植户（生产商）
     */
    @PreAuthorize(hasPermi = "system:buplant:remove")
    @Log(title = "种植户（生产商）", businessType = BusinessType.DELETE)
    @DeleteMapping("/{plantIds}")
    public AjaxResult remove(@PathVariable Long[] plantIds) {
        return toAjax(buplantCompanyService.deleteBuplantCompanyByIds(plantIds));
    }
}
