package com.sinonc.orders.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.sinonc.orders.service.AccountFlowService;
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
import com.sinonc.orders.domain.AccountFlow;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 账户流水Controller
 *
 * @author ruoyi
 * @date 2022-04-16
 */
@RestController
@RequestMapping("/flow")
public class AccountFlowController extends BaseController {
    @Autowired
    private AccountFlowService accountFlowService;

/**
 * 查询账户流水列表
 */
@PreAuthorize(hasPermi = "orders:flow:list")
@GetMapping("/list")
        public TableDataInfo list(AccountFlow accountFlow) {
        startPage();
        List<AccountFlow> list = accountFlowService.listAccountFlow(accountFlow);
        return getDataTable(list);
    }

    /**
     * 导出账户流水列表
     */
    @PreAuthorize(hasPermi = "orders:flow:export")
    @Log(title = "账户流水", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AccountFlow accountFlow) throws IOException {
        List<AccountFlow> list = accountFlowService.listAccountFlow(accountFlow);
        ExcelUtil<AccountFlow> util = new ExcelUtil<AccountFlow>(AccountFlow. class);
        util.exportExcel(response, list, "flow");
    }

    /**
     * 获取账户流水详细信息
     */
    @PreAuthorize(hasPermi = "orders:flow:query")
    @GetMapping(value = "/{acctFlowId}")
    public AjaxResult getInfo(@PathVariable("acctFlowId") Integer acctFlowId) {
        return AjaxResult.success(accountFlowService.getAccountFlowById(acctFlowId));
    }

    /**
     * 新增账户流水
     */
    @PreAuthorize(hasPermi = "orders:flow:add")
    @Log(title = "账户流水", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AccountFlow accountFlow) {
        return toAjax(accountFlowService.addAccountFlow(accountFlow));
    }

    /**
     * 修改账户流水
     */
    @PreAuthorize(hasPermi = "orders:flow:edit")
    @Log(title = "账户流水", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AccountFlow accountFlow) {
        return toAjax(accountFlowService.updateAccountFlow(accountFlow));
    }

    /**
     * 删除账户流水
     */
    @PreAuthorize(hasPermi = "orders:flow:remove")
    @Log(title = "账户流水", businessType = BusinessType.DELETE)
    @DeleteMapping("/{acctFlowIds}")
    public AjaxResult remove(@PathVariable String acctFlowIds) {
        return toAjax(accountFlowService.deleteAccountFlowByIds(acctFlowIds));
    }
}
