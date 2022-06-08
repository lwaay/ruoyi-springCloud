package com.sinonc.base.controller;

import com.sinonc.base.api.domain.AreaCode;
import com.sinonc.base.service.IAreaCodeService;
import com.sinonc.base.vo.AreaListVo;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.system.api.domain.SysDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 行政区域Controller
 *
 * @author ruoyi
 * @date 2020-09-23
 */
@RestController
@RequestMapping("/code")
public class AreaCodeController extends BaseController {
    @Autowired
    private IAreaCodeService areaCodeService;

    /**
     * 查询行政区域列表
     */
    @PreAuthorize(hasPermi = "system:code:list")
    @GetMapping("/list")
    public TableDataInfo list(AreaCode areaCode) {
        startPage();
        List<AreaCode> list = areaCodeService.selectAreaCodeList(areaCode);
        return getDataTable(list);
    }

    /**
     * 导出行政区域列表
     */
    @PreAuthorize(hasPermi = "system:code:export")
    @Log(title = "行政区域", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AreaCode areaCode) throws IOException {
        List<AreaCode> list = areaCodeService.selectAreaCodeList(areaCode);
        ExcelUtil<AreaCode> util = new ExcelUtil<AreaCode>(AreaCode.class);
        util.exportExcel(response, list, "code");
    }

    /**
     * 获取部门列表
     */
    @GetMapping("/listbylike")
    public AjaxResult listbylike(AreaCode areaCode) {
        List<AreaCode> areaCodeList = areaCodeService.selectAreaCodeListLike(areaCode);
        return AjaxResult.success(areaCodeList.stream().map(AreaListVo::new).collect(Collectors.toList()));
    }


    /**
     * 获取部门下拉树列表
     */
    @GetMapping("/treeselect")
    public AjaxResult treeselect(AreaCode areaCode) {
        List<AreaCode> areaCodeList = areaCodeService.selectAreaCodeListLike(areaCode);
        return AjaxResult.success(areaCodeService.buildDeptTreeSelect(areaCodeList));
    }


    /**
     * 获取行政区域详细信息
     */
    @PreAuthorize(hasPermi = "system:code:query")
    @GetMapping(value = "/{code}")
    public AjaxResult getInfo(@PathVariable("code") Long code) {
        return AjaxResult.success(areaCodeService.selectAreaCodeById(code));
    }

    @GetMapping(value = "area/{code}")
    public AreaCode getAreaInfo(@PathVariable("code") Long code) {
        return areaCodeService.selectAreaCodeById(code);
    }

    /**
     * 新增行政区域
     */
    @PreAuthorize(hasPermi = "system:code:add")
    @Log(title = "行政区域", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AreaCode areaCode) {
        return toAjax(areaCodeService.insertAreaCode(areaCode));
    }

    /**
     * 修改行政区域
     */
    @PreAuthorize(hasPermi = "system:code:edit")
    @Log(title = "行政区域", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AreaCode areaCode) {
        return toAjax(areaCodeService.updateAreaCode(areaCode));
    }

    /**
     * 删除行政区域
     */
    @PreAuthorize(hasPermi = "system:code:remove")
    @Log(title = "行政区域", businessType = BusinessType.DELETE)
    @DeleteMapping("/{codes}")
    public AjaxResult remove(@PathVariable Long[] codes) {
        return toAjax(areaCodeService.deleteAreaCodeByIds(codes));
    }

    /**
     * 获取根据父区域代码获取行政区域数据
     */
    @GetMapping("/queryByParent")
    public AjaxResult queryByParent(Long code){
        List<AreaCode> list = areaCodeService.listAreaCodeByParentCode(code);
        return CollectionUtils.isEmpty(list)?AjaxResult.error():AjaxResult.success(list);
    }

    /**
     * 获取指定等级的行政区划
     */
    @GetMapping("/parentBaseArea")
    public  AjaxResult parentBaseArea(Integer level,String code){
        List<AreaCode> list = areaCodeService.parentBaseArea(level,code);
        return AjaxResult.success(list);
    }
}
