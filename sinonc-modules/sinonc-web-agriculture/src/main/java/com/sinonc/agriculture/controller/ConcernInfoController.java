package com.sinonc.agriculture.controller;

import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.agriculture.domain.ConcernInfo;
import com.sinonc.agriculture.service.ConcernInfoService;

import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.system.api.RemoteUserService;
import com.sinonc.system.api.domain.WxUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 会员关注信息Controller
 *
 * @author ruoyi
 * @date 2020-03-06
 */
@Controller
@RequestMapping("/agriculture/concerninfo")
public class ConcernInfoController extends BaseController
{

    @Autowired
    private ConcernInfoService concernInfoService;

    @Autowired
    private RemoteUserService userService;

    @PreAuthorize(hasPermi = "agriculture:concerninfo:view")
    @GetMapping("/{concernId}")
    @ResponseBody
    public AjaxResult concerninfo(@PathVariable("concernId") Long concernId)
    {
        return AjaxResult.success(concernInfoService.selectConcernInfoById(concernId));
    }

    /**
     * 查询会员关注信息列表
     */
    @PreAuthorize(hasPermi = "agriculture:concerninfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ConcernInfo concernInfo)
    {
        startPage();
        List<ConcernInfo> list = concernInfoService.selectConcernInfoList(concernInfo);
        list.forEach(x->{
            WxUser user = userService.getWxUserById(x.getMemberId()).getData();
            if(user != null){
                x.setMemberName(user.getName());
            }
        });
        return getDataTable(list);
    }

    /**
     * 导出会员关注信息列表
     */
    @PreAuthorize(hasPermi = "agriculture:concerninfo:export")
    @Log(title = "会员关注信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ConcernInfo concernInfo, HttpServletResponse response) throws IOException {
        List<ConcernInfo> list = concernInfoService.selectConcernInfoList(concernInfo);
        ExcelUtil<ConcernInfo> util = new ExcelUtil<ConcernInfo>(ConcernInfo.class);
        util.exportExcel(response, list, "concerninfo");
    }

    /**
     * 新增保存会员关注信息
     */
    @PreAuthorize(hasPermi = "agriculture:concerninfo:add")
    @Log(title = "会员关注信息", businessType = BusinessType.INSERT)
    @PostMapping()
    @ResponseBody
    public AjaxResult addSave(@RequestBody ConcernInfo concernInfo)
    {
        return toAjax(concernInfoService.insertConcernInfo(concernInfo, concernInfo.getMemberId()));
    }

    /**
     * 修改保存会员关注信息
     */
    @PreAuthorize(hasPermi = "agriculture:concerninfo:edit")
    @Log(title = "会员关注信息", businessType = BusinessType.UPDATE)
    @PutMapping()
    @ResponseBody
    public AjaxResult editSave(@RequestBody ConcernInfo concernInfo)
    {
        return toAjax(concernInfoService.updateConcernInfo(concernInfo));
    }

    /**
     * 删除会员关注信息
     */
    @PreAuthorize(hasPermi = "agriculture:concerninfo:remove")
    @Log(title = "会员关注信息", businessType = BusinessType.DELETE)
    @DeleteMapping( "/{ids}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("ids") String ids)
    {
        return toAjax(concernInfoService.deleteConcernInfoByIds(ids));
    }
}
