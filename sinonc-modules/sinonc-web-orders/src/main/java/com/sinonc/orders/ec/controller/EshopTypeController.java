package com.sinonc.orders.ec.controller;

import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.orders.ec.domain.EshopType;
import com.sinonc.orders.ec.service.IEshopTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 自定义折线展示Controller
 *
 * @author ruoyi
 * @date 2020-11-23
 */
@RestController
@RequestMapping("/ec/type")
public class EshopTypeController extends BaseController {
    @Autowired
    private IEshopTypeService eshopTypeService;

    /**
     * 查询自定义折线展示列表
     */
    @PreAuthorize(hasPermi = "ec:type:list")
    @GetMapping("/list")
    public TableDataInfo list(EshopType eshopType) {
        startPage();
        List<EshopType> list = eshopTypeService.selectEshopTypeList(eshopType);
        return getDataTable(list);
    }

    /**
     * 导出自定义折线展示列表
     */
    @PreAuthorize(hasPermi = "ec:type:export")
    @Log(title = "自定义折线展示", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EshopType eshopType) throws IOException {
        List<EshopType> list = eshopTypeService.selectEshopTypeList(eshopType);
        ExcelUtil<EshopType> util = new ExcelUtil<EshopType>(EshopType.class);
        util.exportExcel(response, list, "type");
    }

    /**
     * 获取自定义折线展示详细信息
     */
    @PreAuthorize(hasPermi = "ec:type:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(eshopTypeService.selectEshopTypeById(id));
    }

    /**
     * 新增自定义折线展示
     */
    @PreAuthorize(hasPermi = "ec:type:add")
    @Log(title = "自定义折线展示", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EshopType eshopType) {
        return toAjax(eshopTypeService.insertEshopType(eshopType));
    }

    /**
     * 修改自定义折线展示
     */
    @PreAuthorize(hasPermi = "ec:type:edit")
    @Log(title = "自定义折线展示", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EshopType eshopType) {
        return toAjax(eshopTypeService.updateEshopType(eshopType));
    }

    /**
     * 删除自定义折线展示
     */
    @PreAuthorize(hasPermi = "ec:type:remove")
    @Log(title = "自定义折线展示", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(eshopTypeService.deleteEshopTypeByIds(ids));
    }

    /**
     * 查询所有父id为零的类型
     * @return
     */
    @GetMapping("/typeList")
    public AjaxResult typeList(){
        return AjaxResult.success(eshopTypeService.selectTypeList());
    }

    /**
     * 根据id查询所有子类
     * @return
     */
    @GetMapping("/childrenList/{id}")
    public AjaxResult childrenList(@PathVariable Long id){
        return AjaxResult.success(eshopTypeService.selectChildrenListById(id));
    }
}
