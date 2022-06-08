package com.sinonc.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.sinonc.common.core.domain.R;
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
import com.sinonc.system.api.domain.BusinessEntity;
import com.sinonc.system.service.IBusinessEntityService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 农业经营主体基础信息Controller
 *
 * @author ruoyi
 * @date 2022-02-16
 */
@RestController
@RequestMapping("/bussentity")
public class BusinessEntityController extends BaseController {
    @Autowired
    private IBusinessEntityService businessEntityService;

    /**
     * 获取农业经营主体基础信息详细信息
     */
    @GetMapping(value = "/getEntityById/{entityId}")
    public R<BusinessEntity> getEntityById(@PathVariable("entityId") Long entityId) {
        return R.ok(businessEntityService.selectBusinessEntityById(entityId));
    }

    /**
     * 查询农业经营主体基础信息列表
     */
    @PreAuthorize(hasPermi = "system:bussentity:list")
    @GetMapping("/list")
    public TableDataInfo list(BusinessEntity businessEntity) {
        startPage();
        List<BusinessEntity> list = businessEntityService.selectBusinessEntityList(businessEntity);
        return getDataTable(list);
    }

    /**
     * 查询农业经营主体基础信息列表
     */
    @GetMapping("/listAll")
    public AjaxResult listAll() {
        List<BusinessEntity> list = businessEntityService.selectBusinessEntityList(new BusinessEntity());
        return AjaxResult.success(list);
    }

    /**
     * 导出农业经营主体基础信息列表
     */
    @PreAuthorize(hasPermi = "system:bussentity:export")
    @Log(title = "农业经营主体基础信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BusinessEntity businessEntity) throws IOException {
        List<BusinessEntity> list = businessEntityService.selectBusinessEntityList(businessEntity);
        ExcelUtil<BusinessEntity> util = new ExcelUtil<BusinessEntity>(BusinessEntity.class);
        util.exportExcel(response, list, "bussentity");
    }

    /**
     * 获取农业经营主体基础信息详细信息
     */
    @PreAuthorize(hasPermi = "system:bussentity:query")
    @GetMapping(value = "/{entityId}")
    public AjaxResult getInfo(@PathVariable("entityId") Long entityId) {
        return AjaxResult.success(businessEntityService.selectBusinessEntityById(entityId));
    }

    /**
     * 新增农业经营主体基础信息
     */
    @PreAuthorize(hasPermi = "system:bussentity:add")
    @Log(title = "农业经营主体基础信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusinessEntity businessEntity) {
        return toAjax(businessEntityService.insertBusinessEntity(businessEntity));
    }

    /**
     * 修改农业经营主体基础信息
     */
    @PreAuthorize(hasPermi = "system:bussentity:edit")
    @Log(title = "农业经营主体基础信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusinessEntity businessEntity) {
        return toAjax(businessEntityService.updateBusinessEntity(businessEntity));
    }

    /**
     * 删除农业经营主体基础信息
     */
    @PreAuthorize(hasPermi = "system:bussentity:remove")
    @Log(title = "农业经营主体基础信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{entityIds}")
    public AjaxResult remove(@PathVariable Long[] entityIds) {
        return toAjax(businessEntityService.deleteBusinessEntityByIds(entityIds));
    }
}
