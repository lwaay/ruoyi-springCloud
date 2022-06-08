package com.sinonc.ser.controller;

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
import com.sinonc.service.domain.SerServiceAttr;
import com.sinonc.ser.service.ISerServiceAttrService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 社会化服务规格Controller
 *
 * @author ruoyi
 * @date 2022-02-16
 */
@RestController
@RequestMapping("/attr")
public class SerServiceAttrController extends BaseController {
    @Autowired
    private ISerServiceAttrService serServiceAttrService;

    /**
     * 查询社会化服务规格列表
     */
    @PreAuthorize(hasPermi = "system:attr:list")
    @GetMapping("/list")
    public TableDataInfo list(SerServiceAttr serServiceAttr) {
        startPage();
        List<SerServiceAttr> list = serServiceAttrService.selectSerServiceAttrList(serServiceAttr);
        return getDataTable(list);
    }

    /**
     * 导出社会化服务规格列表
     */
    @PreAuthorize(hasPermi = "system:attr:export")
    @Log(title = "社会化服务规格", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SerServiceAttr serServiceAttr) throws IOException {
        List<SerServiceAttr> list = serServiceAttrService.selectSerServiceAttrList(serServiceAttr);
        ExcelUtil<SerServiceAttr> util = new ExcelUtil<SerServiceAttr>(SerServiceAttr.class);
        util.exportExcel(response, list, "attr");
    }

    /**
     * 获取社会化服务规格详细信息
     */
    @PreAuthorize(hasPermi = "system:attr:query")
    @GetMapping(value = "/{attrId}")
    public AjaxResult getInfo(@PathVariable("attrId") Long attrId) {
        return AjaxResult.success(serServiceAttrService.selectSerServiceAttrById(attrId));
    }

    /**
     * 新增社会化服务规格
     */
    @PreAuthorize(hasPermi = "system:attr:add")
    @Log(title = "社会化服务规格", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SerServiceAttr serServiceAttr) {
        return toAjax(serServiceAttrService.insertSerServiceAttr(serServiceAttr));
    }

    /**
     * 修改社会化服务规格
     */
    @PreAuthorize(hasPermi = "system:attr:edit")
    @Log(title = "社会化服务规格", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SerServiceAttr serServiceAttr) {
        return toAjax(serServiceAttrService.updateSerServiceAttr(serServiceAttr));
    }

    /**
     * 删除社会化服务规格
     */
    @PreAuthorize(hasPermi = "system:attr:remove")
    @Log(title = "社会化服务规格", businessType = BusinessType.DELETE)
    @DeleteMapping("/{attrIds}")
    public AjaxResult remove(@PathVariable Long[] attrIds) {
        return toAjax(serServiceAttrService.deleteSerServiceAttrByIds(attrIds));
    }
}
