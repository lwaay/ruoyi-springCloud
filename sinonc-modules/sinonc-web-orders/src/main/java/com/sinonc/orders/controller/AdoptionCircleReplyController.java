package com.sinonc.orders.controller;

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
import com.sinonc.orders.domain.AdoptionCircleReply;
import com.sinonc.orders.service.IAdoptionCircleReplyService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 朋友圈评论Controller
 *
 * @author ruoyi
 * @date 2022-04-23
 */
@RestController
@RequestMapping("/circlereply")
public class AdoptionCircleReplyController extends BaseController {
    @Autowired
    private IAdoptionCircleReplyService adoptionCircleReplyService;

    /**
     * 查询朋友圈评论列表
     */
    @PreAuthorize(hasPermi = "orders:circlereply:list")
    @GetMapping("/list")
    public TableDataInfo list(AdoptionCircleReply adoptionCircleReply) {
        startPage();
        List<AdoptionCircleReply> list = adoptionCircleReplyService.selectAdoptionCircleReplyList(adoptionCircleReply);
        return getDataTable(list);
    }

    /**
     * 导出朋友圈评论列表
     */
    @PreAuthorize(hasPermi = "orders:circlereply:export")
    @Log(title = "朋友圈评论", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AdoptionCircleReply adoptionCircleReply) throws IOException {
        List<AdoptionCircleReply> list = adoptionCircleReplyService.selectAdoptionCircleReplyList(adoptionCircleReply);
        ExcelUtil<AdoptionCircleReply> util = new ExcelUtil<AdoptionCircleReply>(AdoptionCircleReply.class);
        util.exportExcel(response, list, "circlereply");
    }

    /**
     * 获取朋友圈评论详细信息
     */
    @PreAuthorize(hasPermi = "orders:circlereply:query")
    @GetMapping(value = "/{replyId}")
    public AjaxResult getInfo(@PathVariable("replyId") Long replyId) {
        return AjaxResult.success(adoptionCircleReplyService.selectAdoptionCircleReplyById(replyId));
    }

    /**
     * 新增朋友圈评论
     */
    @PreAuthorize(hasPermi = "orders:circlereply:add")
    @Log(title = "朋友圈评论", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AdoptionCircleReply adoptionCircleReply) {
        return toAjax(adoptionCircleReplyService.insertAdoptionCircleReply(adoptionCircleReply));
    }

    /**
     * 修改朋友圈评论
     */
    @PreAuthorize(hasPermi = "orders:circlereply:edit")
    @Log(title = "朋友圈评论", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AdoptionCircleReply adoptionCircleReply) {
        return toAjax(adoptionCircleReplyService.updateAdoptionCircleReply(adoptionCircleReply));
    }

    /**
     * 删除朋友圈评论
     */
    @PreAuthorize(hasPermi = "orders:circlereply:remove")
    @Log(title = "朋友圈评论", businessType = BusinessType.DELETE)
    @DeleteMapping("/{replyIds}")
    public AjaxResult remove(@PathVariable Long[] replyIds) {
        return toAjax(adoptionCircleReplyService.deleteAdoptionCircleReplyByIds(replyIds));
    }
}
