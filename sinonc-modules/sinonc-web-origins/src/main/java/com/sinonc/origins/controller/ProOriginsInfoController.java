package com.sinonc.origins.controller;

import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.origins.api.domain.ProOriginsInfo;
import com.sinonc.origins.dto.ProOriginsInfoDto;
import com.sinonc.origins.service.IProOriginsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 溯源信息Controller
 *
 * @author ruoyi
 * @date 2020-10-23
 */
@RestController
@RequestMapping("/info")
public class ProOriginsInfoController extends BaseController {
    @Autowired
    private IProOriginsInfoService proOriginsInfoService;

    /**
     * 查询溯源信息列表
     */
    @PreAuthorize(hasPermi = "origins:info:list")
    @GetMapping("/list")
    public TableDataInfo list(ProOriginsInfoDto proOriginsInfoDto) {
        startPage();
        List<ProOriginsInfoDto> list = proOriginsInfoService.selectProOriginsInfoDtoList(proOriginsInfoDto);
        return getDataTable(list);
    }

    /**
     * 导出溯源信息列表
     */
    @PreAuthorize(hasPermi = "origins:info:export")
    @Log(title = "溯源信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProOriginsInfo proOriginsInfo) throws IOException {
        List<ProOriginsInfo> list = proOriginsInfoService.selectProOriginsInfoList(proOriginsInfo);
        ExcelUtil<ProOriginsInfo> util = new ExcelUtil<ProOriginsInfo>(ProOriginsInfo.class);
        util.exportExcel(response, list, "info");
    }

    /**
     * 获取溯源信息详细信息
     */
    @PreAuthorize(hasPermi = "origins:info:query")
    @GetMapping(value = "/{originsId}")
    public AjaxResult getInfo(@PathVariable("originsId") Long originsId) {
        return AjaxResult.success(proOriginsInfoService.selectProOriginsInfoDtoById(originsId));
    }

    /**
     * 新增溯源信息
     */
    @PreAuthorize(hasPermi = "origins:info:add")
    @Log(title = "溯源信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProOriginsInfo proOriginsInfo) {
        return toAjax(proOriginsInfoService.insertProOriginsInfo(proOriginsInfo));
    }

    /**
     * 第三方新增溯源信息
     */
    @Log(title = "溯源信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult addByOpen(@RequestBody ProOriginsInfo proOriginsInfo) {
        return toAjax(proOriginsInfoService.insertProOriginsInfo(proOriginsInfo));
    }

    /**
     * 修改溯源信息
     */
    @PreAuthorize(hasPermi = "origins:info:edit")
    @Log(title = "溯源信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProOriginsInfo proOriginsInfo) {
        return toAjax(proOriginsInfoService.updateProOriginsInfo(proOriginsInfo));
    }

    /**
     * 删除溯源信息
     */
    @PreAuthorize(hasPermi = "origins:info:remove")
    @Log(title = "溯源信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{originsIds}")
    public AjaxResult remove(@PathVariable Long[] originsIds) {
        return toAjax(proOriginsInfoService.deleteProOriginsInfoByIds(originsIds));
    }
}
