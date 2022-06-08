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
import com.sinonc.system.domain.BuserviceCompany;
import com.sinonc.system.service.IBuserviceCompanyService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 服务商 Controller
 *
 * @author ruoyi
 * @date 2022-02-24
 */
@RestController
@RequestMapping("/buservice")
public class BuserviceCompanyController extends BaseController {
    @Autowired
    private IBuserviceCompanyService buserviceCompanyService;

    /**
     * 查询服务商 列表
     */
    @PreAuthorize(hasPermi = "system:buservice:list")
    @GetMapping("/list")
    public TableDataInfo list(BuserviceCompany buserviceCompany) {
        startPage();
        List<BuserviceCompany> list = buserviceCompanyService.selectBuserviceCompanyList(buserviceCompany);
        return getDataTable(list);
    }

    /**
     * 导出服务商 列表
     */
    @PreAuthorize(hasPermi = "system:buservice:export")
    @Log(title = "服务商 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BuserviceCompany buserviceCompany) throws IOException {
        List<BuserviceCompany> list = buserviceCompanyService.selectBuserviceCompanyList(buserviceCompany);
        ExcelUtil<BuserviceCompany> util = new ExcelUtil<BuserviceCompany>(BuserviceCompany.class);
        util.exportExcel(response, list, "buservice");
    }

    /**
     * 获取服务商 详细信息
     */
    @PreAuthorize(hasPermi = "system:buservice:query")
    @GetMapping(value = "/{serviceId}")
    public AjaxResult getInfo(@PathVariable("serviceId") Long serviceId) {
        return AjaxResult.success(buserviceCompanyService.selectBuserviceCompanyById(serviceId));
    }

    /**
     * 新增服务商
     */
    @PreAuthorize(hasPermi = "system:buservice:add")
    @Log(title = "服务商 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BuserviceCompany buserviceCompany) {
        return toAjax(buserviceCompanyService.insertBuserviceCompany(buserviceCompany));
    }

    /**
     * 修改服务商
     */
    @PreAuthorize(hasPermi = "system:buservice:edit")
    @Log(title = "服务商 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BuserviceCompany buserviceCompany) {
        return toAjax(buserviceCompanyService.updateBuserviceCompany(buserviceCompany));
    }

    /**
     * 删除服务商
     */
    @PreAuthorize(hasPermi = "system:buservice:remove")
    @Log(title = "服务商 ", businessType = BusinessType.DELETE)
    @DeleteMapping("/{serviceIds}")
    public AjaxResult remove(@PathVariable Long[] serviceIds) {
        return toAjax(buserviceCompanyService.deleteBuserviceCompanyByIds(serviceIds));
    }
}
