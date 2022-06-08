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
import com.sinonc.system.domain.BudistCompany;
import com.sinonc.system.service.IBudistCompanyService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 经销商Controller
 *
 * @author ruoyi
 * @date 2022-02-24
 */
@RestController
@RequestMapping("/budist")
public class BudistCompanyController extends BaseController {
    @Autowired
    private IBudistCompanyService budistCompanyService;

    /**
     * 查询经销商列表
     */
    @PreAuthorize(hasPermi = "system:budist:list")
    @GetMapping("/list")
    public TableDataInfo list(BudistCompany budistCompany) {
        startPage();
        List<BudistCompany> list = budistCompanyService.selectBudistCompanyList(budistCompany);
        return getDataTable(list);
    }

    /**
     * 导出经销商列表
     */
    @PreAuthorize(hasPermi = "system:budist:export")
    @Log(title = "经销商", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BudistCompany budistCompany) throws IOException {
        List<BudistCompany> list = budistCompanyService.selectBudistCompanyList(budistCompany);
        ExcelUtil<BudistCompany> util = new ExcelUtil<BudistCompany>(BudistCompany.class);
        util.exportExcel(response, list, "budist");
    }

    /**
     * 获取经销商详细信息
     */
    @PreAuthorize(hasPermi = "system:budist:query")
    @GetMapping(value = "/{distId}")
    public AjaxResult getInfo(@PathVariable("distId") Long distId) {
        return AjaxResult.success(budistCompanyService.selectBudistCompanyById(distId));
    }

    /**
     * 新增经销商
     */
    @PreAuthorize(hasPermi = "system:budist:add")
    @Log(title = "经销商", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BudistCompany budistCompany) {
        return toAjax(budistCompanyService.insertBudistCompany(budistCompany));
    }

    /**
     * 修改经销商
     */
    @PreAuthorize(hasPermi = "system:budist:edit")
    @Log(title = "经销商", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BudistCompany budistCompany) {
        return toAjax(budistCompanyService.updateBudistCompany(budistCompany));
    }

    /**
     * 删除经销商
     */
    @PreAuthorize(hasPermi = "system:budist:remove")
    @Log(title = "经销商", businessType = BusinessType.DELETE)
    @DeleteMapping("/{distIds}")
    public AjaxResult remove(@PathVariable Long[] distIds) {
        return toAjax(budistCompanyService.deleteBudistCompanyByIds(distIds));
    }
}
