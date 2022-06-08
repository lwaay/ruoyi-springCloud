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
import com.sinonc.system.domain.BusinessCert;
import com.sinonc.system.service.IBusinessCertService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 农业经营主体证件信息 Controller
 *
 * @author ruoyi
 * @date 2022-02-16
 */
@RestController
@RequestMapping("/busscert")
public class BusinessCertController extends BaseController {
    @Autowired
    private IBusinessCertService businessCertService;

    /**
     * 查询农业经营主体证件信息 列表
     */
    @PreAuthorize(hasPermi = "system:busscert:list")
    @GetMapping("/list")
    public TableDataInfo list(BusinessCert businessCert) {
        startPage();
        List<BusinessCert> list = businessCertService.selectBusinessCertList(businessCert);
        return getDataTable(list);
    }

    /**
     * 导出农业经营主体证件信息 列表
     */
    @PreAuthorize(hasPermi = "system:busscert:export")
    @Log(title = "农业经营主体证件信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BusinessCert businessCert) throws IOException {
        List<BusinessCert> list = businessCertService.selectBusinessCertList(businessCert);
        ExcelUtil<BusinessCert> util = new ExcelUtil<BusinessCert>(BusinessCert.class);
        util.exportExcel(response, list, "busscert");
    }

    /**
     * 获取农业经营主体证件信息 详细信息
     */
    @PreAuthorize(hasPermi = "system:busscert:query")
    @GetMapping(value = "/{certId}")
    public AjaxResult getInfo(@PathVariable("certId") Long certId) {
        return AjaxResult.success(businessCertService.selectBusinessCertById(certId));
    }

    /**
     * 新增农业经营主体证件信息
     */
    @PreAuthorize(hasPermi = "system:busscert:add")
    @Log(title = "农业经营主体证件信息 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusinessCert businessCert) {
        return toAjax(businessCertService.insertBusinessCert(businessCert));
    }

    /**
     * 修改农业经营主体证件信息
     */
    @PreAuthorize(hasPermi = "system:busscert:edit")
    @Log(title = "农业经营主体证件信息 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusinessCert businessCert) {
        return toAjax(businessCertService.updateBusinessCert(businessCert));
    }

    /**
     * 删除农业经营主体证件信息
     */
    @PreAuthorize(hasPermi = "system:busscert:remove")
    @Log(title = "农业经营主体证件信息 ", businessType = BusinessType.DELETE)
    @DeleteMapping("/{certIds}")
    public AjaxResult remove(@PathVariable Long[] certIds) {
        return toAjax(businessCertService.deleteBusinessCertByIds(certIds));
    }
}
