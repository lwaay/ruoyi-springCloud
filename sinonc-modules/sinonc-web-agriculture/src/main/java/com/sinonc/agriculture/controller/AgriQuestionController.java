package com.sinonc.agriculture.controller;

import com.sinonc.agriculture.domain.AgriQuestion;
import com.sinonc.agriculture.service.AgriQuestionService;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 农业问题Controller
 *
 * @author ruoyi
 * @date 2020-03-07
 */
@Controller
@RequestMapping("/agriculture/agriQuestion")
public class AgriQuestionController extends BaseController
{

    @Autowired
    private AgriQuestionService agriQuestionService;

    /**
     * 查询农业问题列表
     */
    @PreAuthorize(hasPermi = "agriculture:agriQuestion:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AgriQuestion agriQuestion)
    {
        startPage();
        List<AgriQuestion> list = agriQuestionService.getAgriQuestionList(agriQuestion);
        return getDataTable(list);
    }

    /**
     * 导出农业问题列表
     */
    @PreAuthorize(hasPermi = "agriculture:agriQuestion:export")
    @Log(title = "农业问题", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AgriQuestion agriQuestion) throws IOException {
        List<AgriQuestion> list = agriQuestionService.getAgriQuestionList(agriQuestion);
        ExcelUtil<AgriQuestion> util = new ExcelUtil<AgriQuestion>(AgriQuestion.class);
        util.exportExcel(response, list, "agriQuestion");
    }

    /**
     * 新增保存农业问题
     */
    @PreAuthorize(hasPermi = "agriculture:agriQuestion:add")
    @Log(title = "农业问题", businessType = BusinessType.INSERT)
    @PostMapping()
    @ResponseBody
    public AjaxResult addSave(@RequestBody AgriQuestion agriQuestion)
    {
        return toAjax(agriQuestionService.addAgriQuestion(agriQuestion, agriQuestion.getMemberId()));
    }

    /**
     * 获取农业问题信息
     */
    @GetMapping("/{questionId}")
    @ResponseBody
    public AjaxResult detail(@PathVariable("questionId") Long questionId)
    {
        AgriQuestion agriQuestion = agriQuestionService.getAgriQuestionById(questionId);
        return AjaxResult.success(agriQuestion);
    }

    /**
     * 修改保存农业问题
     */
    @PreAuthorize(hasPermi = "agriculture:agriQuestion:edit")
    @Log(title = "农业问题", businessType = BusinessType.UPDATE)
    @PutMapping()
    @ResponseBody
    public AjaxResult editSave(@RequestBody AgriQuestion agriQuestion)
    {
        return toAjax(agriQuestionService.updateAgriQuestion(agriQuestion));
    }

    /**
     * 删除农业问题
     */
    @PreAuthorize(hasPermi = "agriculture:agriQuestion:remove")
    @Log(title = "农业问题", businessType = BusinessType.DELETE)
    @DeleteMapping( "/{questionId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("questionId") String ids)
    {
        return toAjax(agriQuestionService.deleteAgriQuestionByIds(ids));
    }
}
