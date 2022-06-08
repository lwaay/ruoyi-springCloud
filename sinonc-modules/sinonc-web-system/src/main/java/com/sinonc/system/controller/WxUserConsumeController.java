package com.sinonc.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.sinonc.common.core.domain.R;
import com.sinonc.consume.api.domain.WxUserConsume;
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
import com.sinonc.system.service.IWxUserConsumeService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 消费版用户Controller
 *
 * @author ruoyi
 * @date 2022-03-28
 */
@RestController
@RequestMapping("/consume/")
public class WxUserConsumeController extends BaseController {
    @Autowired
    private IWxUserConsumeService wxUserConsumeService;

    /**
     * 获取消费版用户详细信息
     */
    @GetMapping(value = "getInfo/{id}")
    public R<WxUserConsume> getInfoById(@PathVariable("id") Long id) {
        return R.ok(wxUserConsumeService.selectWxUserConsumeById(id));
    }

    /**
     * 获取消费版用户详细信息
     */
    @GetMapping(value = "getUserByUnionId/{unionId}")
    public R<WxUserConsume> getUserByUnionId(@PathVariable("unionId") String unionId) {
        return R.ok(wxUserConsumeService.selectWxUserConsumeByUnionId(unionId));
    }

    /**
     * 获取消费版用户详细信息
     */
    @GetMapping(value = "getUserByPhone/{phone}")
    public R<WxUserConsume> getUserByPhone(@PathVariable("phone") String phone) {
        return R.ok(wxUserConsumeService.selectWxUserConsumeByPhone(phone));
    }

    /**
     * 新增消费版用户
     */
    @PostMapping("addConsume")
    public R<Integer> addConsume(@RequestBody WxUserConsume wxUserConsume) {
        return R.ok(wxUserConsumeService.insertWxUserConsume(wxUserConsume));
    }

    /**
     * 修改消费版用户
     */
    @PostMapping("updateConsume")
    public R<Integer> updateConsume(@RequestBody WxUserConsume wxUserConsume) {
        return R.ok(wxUserConsumeService.updateWxUserConsume(wxUserConsume));
    }

    /**
     * 查询消费版用户列表
     */
    @PreAuthorize(hasPermi = "system:consume:list")
    @GetMapping("/list")
    public TableDataInfo list(WxUserConsume wxUserConsume) {
        startPage();
        List<WxUserConsume> list = wxUserConsumeService.selectWxUserConsumeList(wxUserConsume);
        return getDataTable(list);
    }

    /**
     * 导出消费版用户列表
     */
    @PreAuthorize(hasPermi = "system:consume:export")
    @Log(title = "消费版用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WxUserConsume wxUserConsume) throws IOException {
        List<WxUserConsume> list = wxUserConsumeService.selectWxUserConsumeList(wxUserConsume);
        ExcelUtil<WxUserConsume> util = new ExcelUtil<WxUserConsume>(WxUserConsume.class);
        util.exportExcel(response, list, "consume");
    }

    /**
     * 获取消费版用户详细信息
     */
    @PreAuthorize(hasPermi = "system:consume:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(wxUserConsumeService.selectWxUserConsumeById(id));
    }

    /**
     * 新增消费版用户
     */
    @PreAuthorize(hasPermi = "system:consume:add")
    @Log(title = "消费版用户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxUserConsume wxUserConsume) {
        return toAjax(wxUserConsumeService.insertWxUserConsume(wxUserConsume));
    }

    /**
     * 修改消费版用户
     */
    @PreAuthorize(hasPermi = "system:consume:edit")
    @Log(title = "消费版用户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxUserConsume wxUserConsume) {
        return toAjax(wxUserConsumeService.updateWxUserConsume(wxUserConsume));
    }

    /**
     * 删除消费版用户
     */
    @PreAuthorize(hasPermi = "system:consume:remove")
    @Log(title = "消费版用户", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(wxUserConsumeService.deleteWxUserConsumeByIds(ids));
    }

}
