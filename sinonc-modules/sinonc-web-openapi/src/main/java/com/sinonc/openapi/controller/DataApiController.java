package com.sinonc.openapi.controller;

import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.openapi.domain.DataApi;
import com.sinonc.openapi.service.IDataApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 第三方数据接入管理Controller
 *
 * @author huanghao
 * @date 2020-10-21
 */
@RestController
@RequestMapping("/openApi")
public class DataApiController extends BaseController {
    @Autowired
    private IDataApiService dataApiService;

    /**
     * 查询第三方数据接入管理列表
     */
    @PreAuthorize(hasPermi = "data:openApi:list")
    @GetMapping("/list")
    public TableDataInfo list(DataApi dataApi) {
        startPage();
        List<DataApi> list = dataApiService.selectDataApiList(dataApi);
        return getDataTable(list);
    }

    /**
     * 导出第三方数据接入管理列表
     */
    @PreAuthorize(hasPermi = "data:openApi:export")
    @Log(title = "第三方数据接入管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DataApi dataApi) throws IOException {
        List<DataApi> list = dataApiService.selectDataApiList(dataApi);
        ExcelUtil<DataApi> util = new ExcelUtil<DataApi>(DataApi.class);
        util.exportExcel(response, list, "openApi");
    }

    /**
     * 获取第三方数据接入管理详细信息
     */
    @PreAuthorize(hasPermi = "data:openApi:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(dataApiService.selectDataApiById(id));
    }

    /**
     * 新增第三方数据接入管理
     */
    @PreAuthorize(hasPermi = "data:openApi:add")
    @Log(title = "第三方数据接入管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DataApi dataApi, HttpServletRequest request) {
        return toAjax(dataApiService.insertDataApi(dataApi, request));
    }

    /**
     * 修改第三方数据接入管理
     */
    @PreAuthorize(hasPermi = "data:openApi:edit")
    @Log(title = "第三方数据接入管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DataApi dataApi) {
        return toAjax(dataApiService.updateDataApi(dataApi));
    }

    /**
     * 删除第三方数据接入管理
     */
    @PreAuthorize(hasPermi = "data:openApi:remove")
    @Log(title = "第三方数据接入管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(dataApiService.deleteDataApiByIds(ids));
    }
}
