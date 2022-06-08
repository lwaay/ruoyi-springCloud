package com.sinonc.orders.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.sinonc.orders.service.AdoptionCircleService;
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
import com.sinonc.orders.domain.AdoptionCircle;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 朋友圈Controller
 *
 * @author ruoyi
 * @date 2022-04-23
 */
@RestController
@RequestMapping("/circle")
public class AdoptionCircleController extends BaseController {
    @Autowired
    private AdoptionCircleService adoptionCircleService;

    /**
     * 查询朋友圈列表
     */
    @PreAuthorize(hasPermi = "orders:circle:list")
    @GetMapping("/list")
    public TableDataInfo list(AdoptionCircle adoptionCircle) {
        startPage();
        List<AdoptionCircle> list = adoptionCircleService.listAdoptionCircle(adoptionCircle);
        return getDataTable(list);
    }

    /**
     * 导出朋友圈列表
     */
    @PreAuthorize(hasPermi = "orders:circle:export")
    @Log(title = "朋友圈", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AdoptionCircle adoptionCircle) throws IOException {
        List<AdoptionCircle> list = adoptionCircleService.listAdoptionCircle(adoptionCircle);
        ExcelUtil<AdoptionCircle> util = new ExcelUtil<AdoptionCircle>(AdoptionCircle. class);
        util.exportExcel(response, list, "circle");
    }

    /**
     * 获取朋友圈详细信息
     */
    @PreAuthorize(hasPermi = "orders:circle:query")
    @GetMapping(value = "/{adoptionId}")
    public AjaxResult getInfo(@PathVariable("adoptionId") Long adoptionId) {
        return AjaxResult.success(adoptionCircleService.getAdoptionCircleById(adoptionId));
    }

    /**
     * 新增朋友圈
     */
    @PreAuthorize(hasPermi = "orders:circle:add")
    @Log(title = "朋友圈", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AdoptionCircle adoptionCircle) {
        return toAjax(adoptionCircleService.insertAdoptionCircle(adoptionCircle));
    }

    /**
     * 修改朋友圈
     */
    @PreAuthorize(hasPermi = "orders:circle:edit")
    @Log(title = "朋友圈", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AdoptionCircle adoptionCircle) {
        return toAjax(adoptionCircleService.updateAdoptionCircle(adoptionCircle));
    }

    /**
     * 删除朋友圈
     */
    @PreAuthorize(hasPermi = "orders:circle:remove")
    @Log(title = "朋友圈", businessType = BusinessType.DELETE)
    @DeleteMapping("/{adoptionIds}")
    public AjaxResult remove(@PathVariable String adoptionIds) {
        return toAjax(adoptionCircleService.deleteAdoptionCircleByIds(adoptionIds));
    }
}
