package com.sinonc.base.controller;

import com.sinonc.base.api.domain.CropDict;
import com.sinonc.base.service.ICropDictService;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.system.api.vo.TreeSelectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 作物字典Controller
 *
 * @author ruoyi
 * @date 2020-09-27
 */
@RestController
@RequestMapping("/dict")
public class CropDictController extends BaseController {
    @Autowired
    private ICropDictService cropDictService;

    /**
     * 查询作物字典列表
     */
    @PreAuthorize(hasPermi = "system:dict:list")
    @GetMapping("/list")
    public TableDataInfo list(CropDict cropDict) {
        startPage();
        List<CropDict> list = cropDictService.selectCropDictList(cropDict);
        return getDataTable(list);
    }

    /**
     * 查询作物字典列表
     */
    @PreAuthorize(hasPermi = "system:dict:list")
    @GetMapping("/alllist")
    public TableDataInfo listWithoutSplit(CropDict cropDict) {
        List<CropDict> list = cropDictService.selectCropDictList(cropDict);
        return getDataTable(list);
    }

    /**
     * 导出作物字典列表
     */
    @PreAuthorize(hasPermi = "system:dict:export")
    @Log(title = "作物字典", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CropDict cropDict) throws IOException {
        List<CropDict> list = cropDictService.selectCropDictList(cropDict);
        ExcelUtil<CropDict> util = new ExcelUtil<CropDict>(CropDict.class);
        util.exportExcel(response, list, "dict");
    }

    /**
     * 新增作物字典
     */
    @PreAuthorize(hasPermi = "system:dict:add")
    @Log(title = "作物字典", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CropDict cropDict) {
        return toAjax(cropDictService.insertCropDict(cropDict));
    }

    /**
     * 修改作物字典
     */
    @PreAuthorize(hasPermi = "system:dict:edit")
    @Log(title = "作物字典", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CropDict cropDict) {
        return toAjax(cropDictService.updateCropDict(cropDict));
    }

    /**
     * 删除作物字典
     */
    @PreAuthorize(hasPermi = "system:dict:remove")
    @Log(title = "作物字典", businessType = BusinessType.DELETE)
    @DeleteMapping("/{cropIds}")
    public AjaxResult remove(@PathVariable Long[] cropIds) {
        return toAjax(cropDictService.deleteCropDictByIds(cropIds));
    }

    /**
     * 获取作物字典树
     */
    @GetMapping("/tree")
    public AjaxResult treeCropDict(){
        List<TreeSelectVo> list = cropDictService.treeCropDict();
        return CollectionUtils.isEmpty(list)?AjaxResult.error():AjaxResult.success(list);
    }
}
