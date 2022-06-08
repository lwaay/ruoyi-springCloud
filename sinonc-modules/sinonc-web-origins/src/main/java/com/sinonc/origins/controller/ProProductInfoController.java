package com.sinonc.origins.controller;

import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.origins.api.domain.ProProductInfo;
import com.sinonc.origins.dto.ProProductInfoDto;
import com.sinonc.origins.service.IProProductInfoService;
import com.sinonc.origins.vo.ProProductInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 产品信息Controller
 *
 * @author zhangxl
 * @date 2020-10-21
 */
@RestController
@RequestMapping("/product")
public class ProProductInfoController extends BaseController {
    @Autowired
    private IProProductInfoService proProductInfoService;

    /**
     * 查询产品信息列表
     */
    @PreAuthorize(hasPermi = "origins:product:list")
    @GetMapping("/list")
    public TableDataInfo list(ProProductInfo proProductInfo) {
        startPage();
        List<ProProductInfo> list = proProductInfoService.selectProProductInfoList(proProductInfo);
        return getDataTable(list);
    }

    /**
     * 查询产品信息列表
     */
    @GetMapping("/listProductInfo")
    public TableDataInfo listProductInfo(ProProductInfoDto proProductInfoDto) {
        startPage();
        List<ProProductInfoDto> list = proProductInfoService.listProProductInfo(proProductInfoDto);
        return getDataTable(list);
    }

    /**
     * 查询所有产品信息
     */
    @PreAuthorize(hasPermi = "origins:product:selectProductInfos")
    @GetMapping("/selectProductInfos")
    @ResponseBody
    public AjaxResult selectProductInfos() {
        List<ProProductInfo> list = proProductInfoService.selectProductInfos();
        return AjaxResult.success(list);
    }

    /**
     * 导出产品信息列表
     */
    @PreAuthorize(hasPermi = "origins:product:export")
    @Log(title = "产品信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProProductInfo proProductInfo) throws IOException {
        List<ProProductInfo> list = proProductInfoService.selectProProductInfoList(proProductInfo);
        ExcelUtil<ProProductInfo> util = new ExcelUtil<ProProductInfo>(ProProductInfo. class);
        util.exportExcel(response, list, "product");
    }

    /**
     * 获取产品信息详细信息
     */
    @PreAuthorize(hasPermi = "origins:product:query")
    @GetMapping(value = "/{productId}")
    public AjaxResult getInfo(@PathVariable("productId") Long productId) {
        return AjaxResult.success(proProductInfoService.selectProProductInfoById(productId));
    }

    /**
     * 新增产品信息
     */
    @PreAuthorize(hasPermi = "origins:product:add")
    @Log(title = "产品信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProProductInfoVo proProductInfoVo) {
        return toAjax(proProductInfoService.insertProProductInfo(proProductInfoVo));
    }

    /**
     * 第三方新增产品信息
     */
    @Log(title = "产品信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult addByOpen(@RequestBody ProProductInfoVo proProductInfoVo) {
        return toAjax(proProductInfoService.insertProProductInfo(proProductInfoVo));
    }

    /**
     * 修改产品信息
     */
    @PreAuthorize(hasPermi = "origins:product:edit")
    @Log(title = "产品信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProProductInfoVo proProductInfo) {
        return toAjax(proProductInfoService.updateProProductInfo(proProductInfo));
    }

    /**
     * 删除产品信息
     */
    @PreAuthorize(hasPermi = "origins:product:remove")
    @Log(title = "产品信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{productIds}")
    public AjaxResult remove(@PathVariable Long[] productIds) {
        return toAjax(proProductInfoService.deleteProProductInfoByIds(productIds));
    }
}
