package com.sinonc.agriculture.controller;

import java.io.IOException;
import java.util.List;

import com.sinonc.agriculture.domain.AgriQuestion;
import com.sinonc.common.security.annotation.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.agriculture.domain.MemberDict;
import com.sinonc.agriculture.service.MemberDictService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

import javax.servlet.http.HttpServletResponse;

/**
 * 会员字典Controller
 *
 * @author ruoyi
 * @date 2020-03-12
 */
@Controller
@RequestMapping("/agriculture/dict")
public class MemberDictController extends BaseController
{

    @Autowired
    private MemberDictService memberDictService;

    /**
     * 查询会员字典列表
     */
    @PreAuthorize(hasPermi = "agriculture:dict:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MemberDict memberDict)
    {
        startPage();
        List<MemberDict> list = memberDictService.getMemberDictList(memberDict);
        return getDataTable(list);
    }

    /**
     * 导出会员字典列表
     */
    @PreAuthorize(hasPermi = "agriculture:dict:export")
    @Log(title = "会员字典", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MemberDict memberDict, HttpServletResponse response) throws IOException {
        List<MemberDict> list = memberDictService.getMemberDictList(memberDict);
        ExcelUtil<MemberDict> util = new ExcelUtil<MemberDict>(MemberDict.class);
        util.exportExcel(response, list, "dict");
    }

    /**
     * 新增保存会员字典
     */
    @PreAuthorize(hasPermi = "agriculture:dict:add")
    @Log(title = "会员字典", businessType = BusinessType.INSERT)
    @PostMapping()
    @ResponseBody
    public AjaxResult addSave(@RequestBody MemberDict memberDict)
    {
        return toAjax(memberDictService.addMemberDict(memberDict));
    }

    /**
     * 获取会员字典信息
     */
    @GetMapping("/{dictId}")
    @ResponseBody
    public AjaxResult detail(@PathVariable("dictId") Long dictId)
    {
        MemberDict memberDict = memberDictService.getMemberDictById(dictId);
        return AjaxResult.success(memberDict);
    }

    /**
     * 修改保存会员字典
     */
    @PreAuthorize(hasPermi = "agriculture:dict:edit")
    @Log(title = "会员字典", businessType = BusinessType.UPDATE)
    @PutMapping()
    @ResponseBody
    public AjaxResult editSave(@RequestBody MemberDict memberDict)
    {
        return toAjax(memberDictService.updateMemberDict(memberDict));
    }

    /**
     * 删除会员字典
     */
    @PreAuthorize(hasPermi = "agriculture:dict:remove")
    @Log(title = "会员字典", businessType = BusinessType.DELETE)
    @DeleteMapping( "/{dictId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("dictId") String ids)
    {
        return toAjax(memberDictService.deleteMemberDictByIds(ids));
    }
}
