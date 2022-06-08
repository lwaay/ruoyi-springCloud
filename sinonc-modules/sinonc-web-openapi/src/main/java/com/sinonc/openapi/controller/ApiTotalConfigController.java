package com.sinonc.openapi.controller;

import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.openapi.domain.ApiTotalConfig;
import com.sinonc.openapi.service.IApiTotalConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 接口配置Controller
 *
 * @author ruoyi
 * @date 2021-10-09
 */
@RestController
@RequestMapping("/config")
public class ApiTotalConfigController extends BaseController {
    @Autowired
    private IApiTotalConfigService apiTotalConfigService;

    /**
     * 查询接口配置列表
     */
    @PreAuthorize(hasPermi = "data:config:list")
    @GetMapping("/list")
    public TableDataInfo list(ApiTotalConfig apiTotalConfig) {
        startPage();
        List<ApiTotalConfig> list = apiTotalConfigService.selectApiTotalConfigList(apiTotalConfig);
        return getDataTable(list);
    }

    /**
     * 导出接口配置列表
     */
    @PreAuthorize(hasPermi = "data:config:export")
    @Log(title = "接口配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ApiTotalConfig apiTotalConfig) throws IOException {
        List<ApiTotalConfig> list = apiTotalConfigService.selectApiTotalConfigList(apiTotalConfig);
        ExcelUtil<ApiTotalConfig> util = new ExcelUtil<ApiTotalConfig>(ApiTotalConfig.class);
        util.exportExcel(response, list, "config");
    }

    /**
     * 获取接口配置详细信息
     */
    @PreAuthorize(hasPermi = "data:config:query")
    @GetMapping(value = "/{configId}")
    public AjaxResult getInfo(@PathVariable("configId") Long configId) {
        return AjaxResult.success(apiTotalConfigService.selectApiTotalConfigById(configId));
    }

    /**
     * 新增接口配置
     */
    @PreAuthorize(hasPermi = "data:config:add")
    @Log(title = "接口配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ApiTotalConfig apiTotalConfig) {
        return toAjax(apiTotalConfigService.insertApiTotalConfig(apiTotalConfig));
    }

    /**
     * 修改接口配置
     */
    @PreAuthorize(hasPermi = "data:config:edit")
    @Log(title = "接口配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ApiTotalConfig apiTotalConfig) {
        return toAjax(apiTotalConfigService.updateApiTotalConfig(apiTotalConfig));
    }

    /**
     * 删除接口配置
     */
    @PreAuthorize(hasPermi = "data:config:remove")
    @Log(title = "接口配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{configIds}")
    public AjaxResult remove(@PathVariable Long[] configIds) {
        return toAjax(apiTotalConfigService.deleteApiTotalConfigByIds(configIds));
    }

}
