package com.sinonc.agriculture.controller;

import java.util.List;

import com.sinonc.common.security.annotation.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.agriculture.domain.QuestionAnswer;
import com.sinonc.agriculture.service.QuestionAnswerService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 问题回答Controller
 *
 * @author ruoyi
 * @date 2020-03-10
 */
@RestController
@RequestMapping("/agriculture/answer")
public class QuestionAnswerController extends BaseController
{

    @Autowired
    private QuestionAnswerService questionAnswerService;

    /**
     * 查询问题回答列表
     */
    @PreAuthorize(hasPermi = "agriculture:answer:list")
    @GetMapping("/list")
    public TableDataInfo list(QuestionAnswer questionAnswer)
    {
        startPage();
        List<QuestionAnswer> list = questionAnswerService.getQuestionAnswerList(questionAnswer);
        return getDataTable(list);
    }

    /**
     * 导出问题回答列表
     */
    @PreAuthorize(hasPermi = "agriculture:answer:export")
    @Log(title = "问题回答", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(QuestionAnswer questionAnswer)
    {
        List<QuestionAnswer> list = questionAnswerService.getQuestionAnswerList(questionAnswer);
        ExcelUtil<QuestionAnswer> util = new ExcelUtil<QuestionAnswer>(QuestionAnswer.class);
        return util.exportExcel(list, "answer");
    }

    /**
     * 新增保存问题回答
     */
    @PreAuthorize(hasPermi = "agriculture:answer:add")
    @Log(title = "问题回答", businessType = BusinessType.INSERT)
    @PutMapping()
    public AjaxResult addSave(@RequestBody QuestionAnswer questionAnswer)
    {
        return toAjax(questionAnswerService.addQuestionAnswer(questionAnswer));
    }

    /**
     * 问题回答详情
     */
    @GetMapping("/{answerId}")
    public AjaxResult edit(@PathVariable("answerId") Long answerId)
    {
        QuestionAnswer questionAnswer = questionAnswerService.getQuestionAnswerById(answerId);
        return AjaxResult.success(questionAnswer);
    }

    /**
     * 修改保存问题回答
     */
    @PreAuthorize(hasPermi = "agriculture:answer:edit")
    @Log(title = "问题回答", businessType = BusinessType.UPDATE)
    @PostMapping()
    public AjaxResult editSave(@RequestBody QuestionAnswer questionAnswer)
    {
        return toAjax(questionAnswerService.updateQuestionAnswer(questionAnswer));
    }

    /**
     * 删除问题回答
     */
    @PreAuthorize(hasPermi = "agriculture:answer:remove")
    @Log(title = "问题回答", businessType = BusinessType.DELETE)
    @DeleteMapping("{ids}")
    public AjaxResult remove(@PathVariable("ids") String ids)
    {
        return toAjax(questionAnswerService.deleteQuestionAnswerByIds(ids));
    }
}
