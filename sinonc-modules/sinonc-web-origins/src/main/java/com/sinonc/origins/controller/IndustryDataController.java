package com.sinonc.origins.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.sinonc.base.api.RemoteAreaCodeService;
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
import com.sinonc.origins.domain.IndustryData;
import com.sinonc.origins.service.IIndustryDataService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 产业数据(大屏)Controller
 *
 * @author ruoyi
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/industryData")
public class IndustryDataController extends BaseController {
    @Autowired
    private IIndustryDataService industryDataService;

    @Autowired
    private RemoteAreaCodeService areaCodeService;

    /**
     * 查询产业数据(大屏)列表
     */
    @PreAuthorize(hasPermi = "origins:industryData:list")
    @GetMapping("/list")
    public TableDataInfo list(IndustryData industryData) {
        startPage();
        List<IndustryData> list = industryDataService.selectIndustryDataList(industryData);
        list.forEach(x->{
            x.setAreaName(areaCodeService.changeAddressName(x.getAreaCode()).getData());
        });
        return getDataTable(list);
    }

    /**
     * 导出产业数据(大屏)列表
     */
    @PreAuthorize(hasPermi = "origins:industryData:export")
    @Log(title = "产业数据(大屏)", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, IndustryData industryData) throws IOException {
        List<IndustryData> list = industryDataService.selectIndustryDataList(industryData);
        ExcelUtil<IndustryData> util = new ExcelUtil<IndustryData>(IndustryData.class);
        util.exportExcel(response, list, "industryData");
    }

    /**
     * 获取产业数据(大屏)详细信息
     */
    @PreAuthorize(hasPermi = "origins:industryData:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(industryDataService.selectIndustryDataById(id));
    }

    /**
     * 新增产业数据(大屏)
     */
    @PreAuthorize(hasPermi = "origins:industryData:add")
    @Log(title = "产业数据(大屏)", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody IndustryData industryData) {
        return toAjax(industryDataService.insertIndustryData(industryData));
    }

    /**
     * 修改产业数据(大屏)
     */
    @PreAuthorize(hasPermi = "origins:industryData:edit")
    @Log(title = "产业数据(大屏)", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody IndustryData industryData) {
        return toAjax(industryDataService.updateIndustryData(industryData));
    }

    /**
     * 删除产业数据(大屏)
     */
    @PreAuthorize(hasPermi = "origins:industryData:remove")
    @Log(title = "产业数据(大屏)", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(industryDataService.deleteIndustryDataByIds(ids));
    }
}
