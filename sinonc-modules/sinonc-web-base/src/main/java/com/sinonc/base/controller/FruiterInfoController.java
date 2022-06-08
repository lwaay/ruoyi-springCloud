package com.sinonc.base.controller;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.sinonc.base.api.domain.BaseFarm;
import com.sinonc.base.dto.FruiterInfoDto;
import com.sinonc.base.service.IBaseFarmService;
import com.sinonc.common.core.domain.R;
import org.springframework.beans.BeanUtils;
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
import com.sinonc.base.api.domain.FruiterInfo;
import com.sinonc.base.service.IFruiterInfoService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 果树信息Controller
 *
 * @author ruoyi
 * @date 2022-02-21
 */
@RestController
@RequestMapping("/fruinfo")
public class FruiterInfoController extends BaseController {
    @Autowired
    private IFruiterInfoService fruiterInfoService;
    @Autowired
    private IBaseFarmService baseFarmService;

    /**
     * 查询果树信息列表
     */
    @PostMapping("/listFruit")
    public R<List<FruiterInfo>> listFruit(@RequestBody FruiterInfo fruiterInfo) {
        List<FruiterInfo> list = fruiterInfoService.selectFruiterInfoList(fruiterInfo);
        return R.ok(list);
    }


    /**
     * 查询果树信息列表
     */
    @PreAuthorize(hasPermi = "base:fruinfo:list")
    @GetMapping("/list")
    public TableDataInfo list(FruiterInfo fruiterInfo) {
        startPage();
        List<FruiterInfo> list = fruiterInfoService.selectFruiterInfoList(fruiterInfo);
        TableDataInfo tableDataInfo = getDataTable(list);

        List<FruiterInfoDto> fruiterInfoDtoList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            FruiterInfo tempFruiterInfo = list.get(i);
            FruiterInfoDto tempFruiterInfoDto = new FruiterInfoDto();
            BeanUtils.copyProperties(tempFruiterInfo, tempFruiterInfoDto);
            BaseFarm baseFarm = baseFarmService.selectBaseFarmById(tempFruiterInfo.getOrchId());
            if (baseFarm != null) {
                String farmName = baseFarm.getFarmName();
                tempFruiterInfoDto.setFarmName(farmName);
            }
            fruiterInfoDtoList.add(tempFruiterInfoDto);
        }
        tableDataInfo.setRows(fruiterInfoDtoList);
        return tableDataInfo;
    }

    /**
     * 导出果树信息列表
     */
    @PreAuthorize(hasPermi = "base:fruinfo:export")
    @Log(title = "果树信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FruiterInfo fruiterInfo) throws IOException {
        List<FruiterInfo> list = fruiterInfoService.selectFruiterInfoList(fruiterInfo);
        ExcelUtil<FruiterInfo> util = new ExcelUtil<FruiterInfo>(FruiterInfo.class);
        util.exportExcel(response, list, "fruinfo");
    }

    /**
     * 获取果树信息详细信息
     */
    @PreAuthorize(hasPermi = "base:fruinfo:query")
    @GetMapping(value = "/{fruId}")
    public AjaxResult getInfo(@PathVariable("fruId") Long fruId) {
        return AjaxResult.success(fruiterInfoService.selectFruiterInfoById(fruId));
    }

    /**
     * 新增果树信息
     */
    @PreAuthorize(hasPermi = "base:fruinfo:add")
    @Log(title = "果树信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FruiterInfo fruiterInfo) {
        return toAjax(fruiterInfoService.insertFruiterInfo(fruiterInfo));
    }

    /**
     * 修改果树信息
     */
    @PreAuthorize(hasPermi = "base:fruinfo:edit")
    @Log(title = "果树信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FruiterInfo fruiterInfo) {
        return toAjax(fruiterInfoService.updateFruiterInfo(fruiterInfo));
    }

    /**
     * 删除果树信息
     */
    @PreAuthorize(hasPermi = "base:fruinfo:remove")
    @Log(title = "果树信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{fruIds}")
    public AjaxResult remove(@PathVariable Long[] fruIds) {
        return toAjax(fruiterInfoService.deleteFruiterInfoByIds(fruIds));
    }


}
