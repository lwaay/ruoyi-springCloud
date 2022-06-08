package com.sinonc.agriculture.controller;

import java.io.IOException;
import java.util.List;

import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.sinonc.agriculture.domain.AccpetScore;
import com.sinonc.agriculture.service.AccpetScoreService;

import javax.servlet.http.HttpServletResponse;

/**
 * 专家评分Controller
 *
 * @author ruoyi
 * @date 2020-07-15
 */
@Controller
@RequestMapping("/agriculture/score")
public class AccpetScoreController extends BaseController
{

    @Autowired
    private AccpetScoreService accpetScoreService;

    @GetMapping("/{id}")
    @ResponseBody
    public AjaxResult score(@PathVariable("id") Long id)
    {
        AccpetScore accpetScore = accpetScoreService.getAccpetScoreById(id);
        return AjaxResult.success(accpetScore);
    }

    /**
     * 查询专家评分列表
     */
    @PreAuthorize(hasPermi = "agriculture:score:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AccpetScore accpetScore)
    {
        startPage();
        List<AccpetScore> list = accpetScoreService.getAccpetScoreList(accpetScore);
        return getDataTable(list);
    }

    /**
     * 导出专家评分列表
     */
    @PreAuthorize(hasPermi = "agriculture:score:export")
    @Log(title = "专家评分", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AccpetScore accpetScore, HttpServletResponse response) throws IOException {
        List<AccpetScore> list = accpetScoreService.getAccpetScoreList(accpetScore);
        ExcelUtil<AccpetScore> util = new ExcelUtil<AccpetScore>(AccpetScore.class);
        util.exportExcel(response, list, "score");
    }

    /**
     * 新增保存专家评分
     */
    @PreAuthorize(hasPermi = "agriculture:score:add")
    @Log(title = "专家评分", businessType = BusinessType.INSERT)
    @PostMapping()
    @ResponseBody
    public AjaxResult addSave(@RequestBody AccpetScore accpetScore)
    {
        return toAjax(accpetScoreService.addAccpetScore(accpetScore));
    }

    /**
     * 修改保存专家评分
     */
    @PreAuthorize(hasPermi = "agriculture:score:edit")
    @Log(title = "专家评分", businessType = BusinessType.UPDATE)
    @PutMapping()
    @ResponseBody
    public AjaxResult editSave(@RequestBody AccpetScore accpetScore)
    {
        return toAjax(accpetScoreService.updateAccpetScore(accpetScore));
    }

    /**
     * 删除专家评分
     */
    @PreAuthorize(hasPermi = "agriculture:score:remove")
    @Log(title = "专家评分", businessType = BusinessType.DELETE)
    @DeleteMapping( "/{ids}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("ids") String ids)
    {
        return toAjax(accpetScoreService.deleteAccpetScoreByIds(ids));
    }
}
