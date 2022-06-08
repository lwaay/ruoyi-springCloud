package com.sinonc.agriculture.controller;

import java.util.List;

import com.sinonc.common.security.annotation.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.agriculture.domain.GrowtechComment;
import com.sinonc.agriculture.service.GrowtechCommentService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 养殖技术评论Controller
 *
 * @author zhang.xl
 * @date 2020-03-07
 */
@Controller
@RequestMapping("/agriculture/growtechcomment")
public class GrowtechCommentController extends BaseController
{
    private String prefix = "agriculture/growtechcomment";

    @Autowired
    private GrowtechCommentService growtechCommentService;

    @PreAuthorize(hasPermi = "agriculture:growtechcomment:view")
    @GetMapping()
    public String growtechcomment()
    {
        return prefix + "/growtechcomment";
    }

    /**
     * 查询养殖技术评论列表
     */
    @PreAuthorize(hasPermi = "agriculture:growtechcomment:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GrowtechComment growtechComment)
    {
        startPage();
        List<GrowtechComment> list = growtechCommentService.selectGrowtechCommentList(growtechComment);
        return getDataTable(list);
    }

    /**
     * 导出养殖技术评论列表
     */
    @PreAuthorize(hasPermi = "agriculture:growtechcomment:export")
    @Log(title = "养殖技术评论", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GrowtechComment growtechComment)
    {
        List<GrowtechComment> list = growtechCommentService.selectGrowtechCommentList(growtechComment);
        ExcelUtil<GrowtechComment> util = new ExcelUtil<GrowtechComment>(GrowtechComment.class);
        return util.exportExcel(list, "growtechcomment");
    }

    /**
     * 新增养殖技术评论
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存养殖技术评论
     */
    @PreAuthorize(hasPermi = "agriculture:growtechcomment:add")
    @Log(title = "养殖技术评论", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GrowtechComment growtechComment)
    {
        return toAjax(growtechCommentService.insertGrowtechComment(growtechComment));
    }

    /**
     * 修改养殖技术评论
     */
    @GetMapping("/edit/{commentId}")
    public String edit(@PathVariable("commentId") Long commentId, ModelMap mmap)
    {
        GrowtechComment growtechComment = growtechCommentService.selectGrowtechCommentById(commentId);
        mmap.put("growtechComment", growtechComment);
        return prefix + "/edit";
    }

    /**
     * 修改保存养殖技术评论
     */
    @PreAuthorize(hasPermi = "agriculture:growtechcomment:edit")
    @Log(title = "养殖技术评论", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GrowtechComment growtechComment)
    {
        return toAjax(growtechCommentService.updateGrowtechComment(growtechComment));
    }

    /**
     * 删除养殖技术评论
     */
    @PreAuthorize(hasPermi = "agriculture:growtechcomment:remove")
    @Log(title = "养殖技术评论", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(growtechCommentService.deleteGrowtechCommentByIds(ids));
    }
}
