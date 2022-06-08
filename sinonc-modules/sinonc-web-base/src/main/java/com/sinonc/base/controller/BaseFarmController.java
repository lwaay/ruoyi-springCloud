package com.sinonc.base.controller;

import com.sinonc.base.api.domain.BaseFarm;
import com.sinonc.base.service.IBaseFarmService;
import com.sinonc.common.core.domain.R;
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
 * 基地信息Controller
 *
 * @author ruoyi
 * @date 2020-09-25
 */
@RestController
@RequestMapping("/farm")
public class BaseFarmController extends BaseController {
    @Autowired
    private IBaseFarmService baseFarmService;

    /**
     * 查询基地信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BaseFarm baseFarm) {
        startPage();
        List<BaseFarm> list = baseFarmService.selectBaseFarmList(baseFarm);
        return getDataTable(list);
    }

    /**
     * 查询基地信息列表不分页
     */
    @GetMapping("/farmListAll")
    public AjaxResult listFarm(BaseFarm baseFarm) {
        return  AjaxResult.success(baseFarmService.selectBaseFarmList(baseFarm));
    }


    /**
     * 查询基地信息列表
     */
    @PostMapping("/list")
    public R<List<BaseFarm>> listBaseFarm(@RequestBody BaseFarm baseFarm) {
        List<BaseFarm> list = baseFarmService.selectBaseFarmList(baseFarm);
        return R.ok(list);
    }

    /**
     * 查询基地信息列表
     */
    @GetMapping("/api")
    public List<BaseFarm> getFarmList() {
        return baseFarmService.selectBaseFarmList(new BaseFarm());
    }

    /**
     * 导出基地信息列表
     */
    @PreAuthorize(hasPermi = "system:farm:export")
    @Log(title = "基地信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BaseFarm baseFarm) throws IOException {
        List<BaseFarm> list = baseFarmService.selectBaseFarmList(baseFarm);
        ExcelUtil<BaseFarm> util = new ExcelUtil<BaseFarm>(BaseFarm.class);
        util.exportExcel(response, list, "farm");
    }

    /**
     * 获取基地信息详细信息
     */
//    @PreAuthorize(hasPermi = "system:farm:query")
    @GetMapping(value = "/{farmId}")
    public AjaxResult getInfo(@PathVariable("farmId") Long farmId) {
        return AjaxResult.success(baseFarmService.selectBaseFarmById(farmId));
    }

    @GetMapping(value = "/api/{farmId}")
    public BaseFarm getApiInfo(@PathVariable("farmId") Long farmId) {
        logger.info("获取基地信息");
        BaseFarm baseFarm = baseFarmService.selectBaseFarmById(farmId);
        logger.info("baseFarm {}", baseFarm.toString());
        return baseFarm;
    }

    /**
     * 新增基地信息
     */
    @Log(title = "基地信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseFarm baseFarm) {
        return toAjax(baseFarmService.insertBaseFarm(baseFarm));
    }

    /**
     * 修改基地信息
     */
    @PreAuthorize(hasPermi = "system:farm:edit")
    @Log(title = "基地信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseFarm baseFarm) {
        return toAjax(baseFarmService.updateBaseFarm(baseFarm));
    }

    /**
     * 删除基地信息
     */
    @PreAuthorize(hasPermi = "system:farm:remove")
    @Log(title = "基地信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{farmIds}")
    public AjaxResult remove(@PathVariable Long[] farmIds) {
        return toAjax(baseFarmService.deleteBaseFarmByIds(farmIds));
    }

    @GetMapping("/count")
    public AjaxResult getCount() {
        return AjaxResult.success(baseFarmService.getFarmCount());
    }
}
