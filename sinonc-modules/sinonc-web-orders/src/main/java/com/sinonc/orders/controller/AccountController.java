package com.sinonc.orders.controller;

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
import com.sinonc.orders.domain.Account;
import com.sinonc.orders.service.AccountService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 商户账户信息Controller
 *
 * @author ruoyii
 * @date 2022-04-16
 */
@RestController
@RequestMapping("/account")
public class AccountController extends BaseController {
    @Autowired
    private AccountService accountService;

/**
 * 查询商户账户信息列表
 */
@PreAuthorize(hasPermi = "orders:account:list")
@GetMapping("/list")
        public TableDataInfo list(Account account) {
        startPage();
        List<Account> list = accountService.listAccount(account);
        return getDataTable(list);
    }

    /**
     * 导出商户账户信息列表
     */
    @PreAuthorize(hasPermi = "orders:account:export")
    @Log(title = "商户账户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Account account) throws IOException {
        List<Account> list = accountService.listAccount(account);
        ExcelUtil<Account> util = new ExcelUtil<Account>(Account. class);
        util.exportExcel(response, list, "account");
    }

    /**
     * 获取商户账户信息详细信息
     */
    @PreAuthorize(hasPermi = "orders:account:query")
    @GetMapping(value = "/{acctId}")
    public AjaxResult getInfo(@PathVariable("acctId") Long acctId) {
        return AjaxResult.success(accountService.getAccountById(acctId));
    }

    /**
     * 新增商户账户信息
     */
    @PreAuthorize(hasPermi = "orders:account:add")
    @Log(title = "商户账户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Account account) {
        return toAjax(accountService.addAccount(account));
    }

    /**
     * 修改商户账户信息
     */
    @PreAuthorize(hasPermi = "orders:account:edit")
    @Log(title = "商户账户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Account account) {
        return toAjax(accountService.updateAccount(account));
    }

    /**
     * 删除商户账户信息
     */
    @PreAuthorize(hasPermi = "orders:account:remove")
    @Log(title = "商户账户信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{acctIds}")
    public AjaxResult remove(@PathVariable String acctIds) {
        return toAjax(accountService.deleteAccountByIds(acctIds));
    }
}
