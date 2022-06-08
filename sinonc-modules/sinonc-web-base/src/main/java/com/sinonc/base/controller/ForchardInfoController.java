package com.sinonc.base.controller;

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
import com.sinonc.base.domain.ForchardInfo;
import com.sinonc.base.service.IForchardInfoService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 果园信息Controller
 *
 * @author ruoyi
 * @date 2022-02-21
 */
@RestController
@RequestMapping("/forchinfo")
public class ForchardInfoController extends BaseController {
    @Autowired
    private IForchardInfoService forchardInfoService;

    /**
     * 查询果园信息列表
     */
    @PreAuthorize(hasPermi = "base:forchinfo:list")
    @GetMapping("/list")
    public TableDataInfo list(ForchardInfo forchardInfo) {
        startPage();
        List<ForchardInfo> list = forchardInfoService.selectForchardInfoList(forchardInfo);
        return getDataTable(list);
    }

    /**
     * 导出果园信息列表
     */
    @PreAuthorize(hasPermi = "base:forchinfo:export")
    @Log(title = "果园信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ForchardInfo forchardInfo) throws IOException {
        List<ForchardInfo> list = forchardInfoService.selectForchardInfoList(forchardInfo);
        ExcelUtil<ForchardInfo> util = new ExcelUtil<ForchardInfo>(ForchardInfo.class);
        util.exportExcel(response, list, "forchinfo");
    }

    /**
     * 获取果园信息详细信息
     */
    @PreAuthorize(hasPermi = "base:forchinfo:query")
    @GetMapping(value = "/{orchId}")
    public AjaxResult getInfo(@PathVariable("orchId") Long orchId) {
        return AjaxResult.success(forchardInfoService.selectForchardInfoById(orchId));
    }

    /**
     * 新增果园信息
     */
    @PreAuthorize(hasPermi = "base:forchinfo:add")
    @Log(title = "果园信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ForchardInfo forchardInfo) {
        return toAjax(forchardInfoService.insertForchardInfo(forchardInfo));
    }

    /**
     * 修改果园信息
     */
    @PreAuthorize(hasPermi = "base:forchinfo:edit")
    @Log(title = "果园信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ForchardInfo forchardInfo) {
        return toAjax(forchardInfoService.updateForchardInfo(forchardInfo));
    }

    /**
     * 删除果园信息
     */
    @PreAuthorize(hasPermi = "base:forchinfo:remove")
    @Log(title = "果园信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{orchIds}")
    public AjaxResult remove(@PathVariable Long[] orchIds) {
        return toAjax(forchardInfoService.deleteForchardInfoByIds(orchIds));
    }
}
