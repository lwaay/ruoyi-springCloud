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
import com.sinonc.agriculture.domain.GrowtechLike;
import com.sinonc.agriculture.service.GrowtechLikeService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 种养殖技术点赞Controller
 *
 * @author zhang.xl
 * @date 2020-03-07
 */
@Controller
@RequestMapping("/agriculture/growtechlike")
public class GrowtechLikeController extends BaseController
{
    private String prefix = "agriculture/growtechlike";

    @Autowired
    private GrowtechLikeService growtechLikeService;

    @PreAuthorize(hasPermi = "agriculture:growtechlike:view")
    @GetMapping()
    public String growtechlike()
    {
        return prefix + "/growtechlike";
    }

    /**
     * 查询种养殖技术点赞列表
     */
    @PreAuthorize(hasPermi = "agriculture:growtechlike:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GrowtechLike growtechLike)
    {
        startPage();
        List<GrowtechLike> list = growtechLikeService.selectGrowtechLikeList(growtechLike);
        return getDataTable(list);
    }

    /**
     * 导出种养殖技术点赞列表
     */
    @PreAuthorize(hasPermi = "agriculture:growtechlike:export")
    @Log(title = "种养殖技术点赞", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GrowtechLike growtechLike)
    {
        List<GrowtechLike> list = growtechLikeService.selectGrowtechLikeList(growtechLike);
        ExcelUtil<GrowtechLike> util = new ExcelUtil<GrowtechLike>(GrowtechLike.class);
        return util.exportExcel(list, "growtechlike");
    }

    /**
     * 新增种养殖技术点赞
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存种养殖技术点赞
     */
    @PreAuthorize(hasPermi = "agriculture:growtechlike:add")
    @Log(title = "种养殖技术点赞", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GrowtechLike growtechLike)
    {
        return toAjax(growtechLikeService.insertGrowtechLike(growtechLike));
    }

    /**
     * 修改种养殖技术点赞
     */
    @GetMapping("/edit/{likeId}")
    public String edit(@PathVariable("likeId") Long likeId, ModelMap mmap)
    {
        GrowtechLike growtechLike = growtechLikeService.selectGrowtechLikeById(likeId);
        mmap.put("growtechLike", growtechLike);
        return prefix + "/edit";
    }

    /**
     * 修改保存种养殖技术点赞
     */
    @PreAuthorize(hasPermi = "agriculture:growtechlike:edit")
    @Log(title = "种养殖技术点赞", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GrowtechLike growtechLike)
    {
        return toAjax(growtechLikeService.updateGrowtechLike(growtechLike));
    }

    /**
     * 删除种养殖技术点赞
     */
    @PreAuthorize(hasPermi = "agriculture:growtechlike:remove")
    @Log(title = "种养殖技术点赞", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(growtechLikeService.deleteGrowtechLikeByIds(ids));
    }
}
