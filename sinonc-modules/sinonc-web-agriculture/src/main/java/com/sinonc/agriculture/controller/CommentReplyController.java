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
import com.sinonc.agriculture.domain.CommentReply;
import com.sinonc.agriculture.service.CommentReplyService;

import javax.servlet.http.HttpServletResponse;

/**
 * 评论回复Controller
 *
 * @author ruoyi
 * @date 2020-03-17
 */
@Controller
@RequestMapping("/agriculture/commentreply")
public class CommentReplyController extends BaseController
{

    @Autowired
    private CommentReplyService commentReplyService;

    @GetMapping("/{growtechIdP}")
    @ResponseBody
    public AjaxResult commentreply(@PathVariable("growtechIdP") Long growtechIdP)
    {
        CommentReply commentReply = commentReplyService.getCommentReplyById(growtechIdP);
        return AjaxResult.success(commentReply);
    }

    /**
     * 查询评论回复列表
     */
    @PreAuthorize(hasPermi = "agriculture:commentreply:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CommentReply commentReply)
    {
        startPage();
        List<CommentReply> list = commentReplyService.getCommentReplyList(commentReply);
        return getDataTable(list);
    }

    /**
     * 导出评论回复列表
     */
    @PreAuthorize(hasPermi = "agriculture:commentreply:export")
    @Log(title = "评论回复", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(CommentReply commentReply, HttpServletResponse response) throws IOException {
        List<CommentReply> list = commentReplyService.getCommentReplyList(commentReply);
        ExcelUtil<CommentReply> util = new ExcelUtil<CommentReply>(CommentReply.class);
        util.exportExcel(response, list, "commentreply");
    }

    /**
     * 新增保存评论回复
     */
    @PreAuthorize(hasPermi = "agriculture:commentreply:add")
    @Log(title = "评论回复", businessType = BusinessType.INSERT)
    @PostMapping()
    @ResponseBody
    public AjaxResult addSave(@RequestBody CommentReply commentReply)
    {
        return toAjax(commentReplyService.addCommentReply(commentReply));
    }

    /**
     * 修改保存评论回复
     */
    @PreAuthorize(hasPermi = "agriculture:commentreply:edit")
    @Log(title = "评论回复", businessType = BusinessType.UPDATE)
    @PutMapping()
    @ResponseBody
    public AjaxResult editSave(@RequestBody CommentReply commentReply)
    {
        return toAjax(commentReplyService.updateCommentReply(commentReply));
    }

    /**
     * 删除评论回复
     */
    @PreAuthorize(hasPermi = "agriculture:commentreply:remove")
    @Log(title = "评论回复", businessType = BusinessType.DELETE)
    @DeleteMapping( "/{ids}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("ids") String ids)
    {
        return toAjax(commentReplyService.deleteCommentReplyByIds(ids));
    }
}
