package com.sinonc.agriculture.controller;

import com.sinonc.agriculture.domain.ExpertInfo;
import com.sinonc.agriculture.service.ExpertInfoService;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 专家消息Controller
 *
 * @author ruoyi
 * @date 2022-03-07
 */
@RestController
@RequestMapping("/expertInfo")
public class ExpertInfoController extends BaseController {
    @Autowired
    private ExpertInfoService expertInfoService;

    /**
     * 查询专家消息列表
     */
    @PreAuthorize(hasPermi = "agriculture:expertInfo:list")
    @GetMapping("/list")
    public TableDataInfo list(ExpertInfo expertInfo) {
        startPage();
        List<ExpertInfo> list = expertInfoService.selectExpertInfoList(expertInfo);
        return getDataTable(list);
    }

    /**
     * 导出专家消息列表
     */
    @PreAuthorize(hasPermi = "agriculture:expertInfo:export")
    @Log(title = "专家消息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ExpertInfo expertInfo) throws IOException {
        List<ExpertInfo> list = expertInfoService.selectExpertInfoList(expertInfo);
        ExcelUtil<ExpertInfo> util = new ExcelUtil<ExpertInfo>(ExpertInfo. class);
        util.exportExcel(response, list, "expertInfo");
    }

    /**
     * 获取专家消息详细信息
     */
    @PreAuthorize(hasPermi = "agriculture:expertInfo:query")
    @GetMapping(value = "/{expertId}")
    public AjaxResult getInfo(@PathVariable("expertId") Long expertId) {
        return AjaxResult.success(expertInfoService.selectExpertInfoById(expertId));
    }

    /**
     * 新增专家消息
     */
    @PreAuthorize(hasPermi = "agriculture:expertInfo:add")
    @Log(title = "专家消息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ExpertInfo expertInfo) {
        return toAjax(expertInfoService.insertExpertInfo(expertInfo));
    }

    /**
     * 修改专家消息
     */
    @PreAuthorize(hasPermi = "agriculture:expertInfo:edit")
    @Log(title = "专家消息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ExpertInfo expertInfo) {
        return toAjax(expertInfoService.updateExpertInfo(expertInfo));
    }

    /**
     * 审核专家消息
     */
    @PreAuthorize(hasPermi = "agriculture:expertInfo:edit")
    @Log(title = "专家消息", businessType = BusinessType.UPDATE)
    @PostMapping("/audit")
    public AjaxResult audit(@RequestBody ExpertInfo expertInfo) {
        return toAjax(expertInfoService.auditExpertInfo(expertInfo));
    }

    /**
     * 删除专家消息
     */
    @PreAuthorize(hasPermi = "agriculture:expertInfo:remove")
    @Log(title = "专家消息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{expertIds}")
    public AjaxResult remove(@PathVariable String expertIds) {
        return toAjax(expertInfoService.deleteExpertInfoByIds(expertIds));
    }
}
