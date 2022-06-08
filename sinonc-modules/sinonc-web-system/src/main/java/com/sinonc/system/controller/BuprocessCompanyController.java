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
import com.sinonc.system.domain.BuprocessCompany;
import com.sinonc.system.service.IBuprocessCompanyService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 加工农企Controller
 *
 * @author ruoyi
 * @date 2022-02-24
 */
@RestController
@RequestMapping("/buprocess")
public class BuprocessCompanyController extends BaseController {
    @Autowired
    private IBuprocessCompanyService buprocessCompanyService;

    /**
     * 查询加工农企列表
     */
    @PreAuthorize(hasPermi = "system:buprocess:list")
    @GetMapping("/list")
    public TableDataInfo list(BuprocessCompany buprocessCompany) {
        startPage();
        List<BuprocessCompany> list = buprocessCompanyService.selectBuprocessCompanyList(buprocessCompany);
        return getDataTable(list);
    }

    /**
     * 导出加工农企列表
     */
    @PreAuthorize(hasPermi = "system:buprocess:export")
    @Log(title = "加工农企", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BuprocessCompany buprocessCompany) throws IOException {
        List<BuprocessCompany> list = buprocessCompanyService.selectBuprocessCompanyList(buprocessCompany);
        ExcelUtil<BuprocessCompany> util = new ExcelUtil<BuprocessCompany>(BuprocessCompany.class);
        util.exportExcel(response, list, "buprocess");
    }

    /**
     * 获取加工农企详细信息
     */
    @PreAuthorize(hasPermi = "system:buprocess:query")
    @GetMapping(value = "/{proceId}")
    public AjaxResult getInfo(@PathVariable("proceId") Long proceId) {
        return AjaxResult.success(buprocessCompanyService.selectBuprocessCompanyById(proceId));
    }

    /**
     * 新增加工农企
     */
    @PreAuthorize(hasPermi = "system:buprocess:add")
    @Log(title = "加工农企", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BuprocessCompany buprocessCompany) {
        return toAjax(buprocessCompanyService.insertBuprocessCompany(buprocessCompany));
    }

    /**
     * 修改加工农企
     */
    @PreAuthorize(hasPermi = "system:buprocess:edit")
    @Log(title = "加工农企", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BuprocessCompany buprocessCompany) {
        return toAjax(buprocessCompanyService.updateBuprocessCompany(buprocessCompany));
    }

    /**
     * 删除加工农企
     */
    @PreAuthorize(hasPermi = "system:buprocess:remove")
    @Log(title = "加工农企", businessType = BusinessType.DELETE)
    @DeleteMapping("/{proceIds}")
    public AjaxResult remove(@PathVariable Long[] proceIds) {
        return toAjax(buprocessCompanyService.deleteBuprocessCompanyByIds(proceIds));
    }
}
