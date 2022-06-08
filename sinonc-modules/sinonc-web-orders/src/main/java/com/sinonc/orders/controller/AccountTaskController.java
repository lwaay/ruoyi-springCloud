package com.sinonc.orders.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.sinonc.orders.service.AccountTaskService;
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
import com.sinonc.orders.domain.AccountTask;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 账户提现信息Controller
 *
 * @author ruoyi
 * @date 2022-04-16
 */
@RestController
@RequestMapping("/task")
public class AccountTaskController extends BaseController {
    @Autowired
    private AccountTaskService accountTaskService;

/**
 * 查询账户提现信息列表
 */
@PreAuthorize(hasPermi = "orders:task:list")
@GetMapping("/list")
        public TableDataInfo list(AccountTask accountTask) {
        startPage();
        List<AccountTask> list = accountTaskService.listAccountTask(accountTask);
        return getDataTable(list);
    }

    /**
     * 导出账户提现信息列表
     */
    @PreAuthorize(hasPermi = "orders:task:export")
    @Log(title = "账户提现信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AccountTask accountTask) throws IOException {
        List<AccountTask> list = accountTaskService.listAccountTask(accountTask);
        ExcelUtil<AccountTask> util = new ExcelUtil<AccountTask>(AccountTask. class);
        util.exportExcel(response, list, "task");
    }

    /**
     * 获取账户提现信息详细信息
     */
    @PreAuthorize(hasPermi = "orders:task:query")
    @GetMapping(value = "/{acctTaskId}")
    public AjaxResult getInfo(@PathVariable("acctTaskId") Long acctTaskId) {
        return AjaxResult.success(accountTaskService.getAccountTaskById(acctTaskId));
    }

    /**
     * 新增账户提现信息
     */
    @PreAuthorize(hasPermi = "orders:task:add")
    @Log(title = "账户提现信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AccountTask accountTask) {
        return toAjax(accountTaskService.addAccountTask(accountTask));
    }

    /**
     * 修改账户提现信息
     */
    @PreAuthorize(hasPermi = "orders:task:edit")
    @Log(title = "账户提现信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AccountTask accountTask) {
        return toAjax(accountTaskService.updateAccountTask(accountTask));
    }

    /**
     * 删除账户提现信息
     */
    @PreAuthorize(hasPermi = "orders:task:remove")
    @Log(title = "账户提现信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{acctTaskIds}")
    public AjaxResult remove(@PathVariable String acctTaskIds) {
        return toAjax(accountTaskService.deleteAccountTaskByIds(acctTaskIds));
    }
}
