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
import com.sinonc.orders.domain.OdMemberAttention;
import com.sinonc.orders.service.IOdMemberAttentionService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 用户收藏商品Controller
 *
 * @author ruoyi
 * @date 2022-03-31
 */
@RestController
@RequestMapping("/follow")
public class OdMemberAttentionController extends BaseController {
    @Autowired
    private IOdMemberAttentionService odMemberAttentionService;

    /**
     * 查询用户收藏商品列表
     */
    @PreAuthorize(hasPermi = "orders:follow:list")
    @GetMapping("/list")
    public TableDataInfo list(OdMemberAttention odMemberAttention) {
        startPage();
        List<OdMemberAttention> list = odMemberAttentionService.selectOdMemberAttentionList(odMemberAttention);
        return getDataTable(list);
    }

    /**
     * 导出用户收藏商品列表
     */
    @PreAuthorize(hasPermi = "orders:follow:export")
    @Log(title = "用户收藏商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OdMemberAttention odMemberAttention) throws IOException {
        List<OdMemberAttention> list = odMemberAttentionService.selectOdMemberAttentionList(odMemberAttention);
        ExcelUtil<OdMemberAttention> util = new ExcelUtil<OdMemberAttention>(OdMemberAttention.class);
        util.exportExcel(response, list, "follow");
    }

    /**
     * 获取用户收藏商品详细信息
     */
    @PreAuthorize(hasPermi = "orders:follow:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(odMemberAttentionService.selectOdMemberAttentionById(id));
    }

    /**
     * 新增用户收藏商品
     */
    @PreAuthorize(hasPermi = "orders:follow:add")
    @Log(title = "用户收藏商品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OdMemberAttention odMemberAttention) {
        return toAjax(odMemberAttentionService.insertOdMemberAttention(odMemberAttention));
    }

    /**
     * 修改用户收藏商品
     */
    @PreAuthorize(hasPermi = "orders:follow:edit")
    @Log(title = "用户收藏商品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OdMemberAttention odMemberAttention) {
        return toAjax(odMemberAttentionService.updateOdMemberAttention(odMemberAttention));
    }

    /**
     * 删除用户收藏商品
     */
    @PreAuthorize(hasPermi = "orders:follow:remove")
    @Log(title = "用户收藏商品", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(odMemberAttentionService.deleteOdMemberAttentionByIds(ids));
    }
}
