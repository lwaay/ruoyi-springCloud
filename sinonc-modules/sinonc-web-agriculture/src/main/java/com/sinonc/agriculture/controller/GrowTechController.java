package com.sinonc.agriculture.controller;

import com.github.pagehelper.PageHelper;
import com.sinonc.base.api.RemoteCorpDictService;
import com.sinonc.base.api.domain.CropDict;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.agriculture.constants.SysConstants;
import com.sinonc.agriculture.domain.GrowTech;
import com.sinonc.agriculture.domain.SectionDict;
import com.sinonc.agriculture.service.GrowTechService;
import com.sinonc.agriculture.service.SectionDictService;
import com.sinonc.agriculture.vo.GrowTechVo;

import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.common.security.service.TokenService;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 种养殖技术Controller
 *
 * @author zhang.xl
 * @date 2020-03-06
 */
@Controller
@RequestMapping("/agriculture/growtech")
public class GrowTechController extends BaseController
{
    private String prefix = "agriculture/growtech";

    @Value("${spring.profiles.active}")
    private String profile;

    @Autowired
    private GrowTechService growTechService;

    @Autowired
    private RemoteCorpDictService cropDictService;
    @Autowired
    private SectionDictService sectionDictService;

    @Autowired
    private TokenService tokenService;


    @PreAuthorize(hasPermi = "agriculture:growtech:view")
    @GetMapping()
    public String growtech()
    {
        return prefix + "/growtech";
    }

    /**
     * 查询种养殖技术列表
     */
    @PreAuthorize(hasPermi = "agriculture:growtech:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(GrowTech growTech)
    {
        startPage();
        PageHelper.orderBy("create_time desc");
        List<GrowTech> list = growTechService.selectGrowTechList(growTech);
        return getDataTable(list);
    }

    /**
     * 导出种养殖技术列表
     */
    @PreAuthorize(hasPermi = "agriculture:growtech:export")
    @Log(title = "种养殖技术", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GrowTech growTech)
    {
        List<GrowTech> list = growTechService.selectGrowTechList(growTech);
        ExcelUtil<GrowTech> util = new ExcelUtil<GrowTech>(GrowTech.class);
        return util.exportExcel(list, "growtech");
    }

    /**
     * 新增种养殖技术
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存种养殖技术
     */
    @PreAuthorize(hasPermi = "agriculture:growtech:add")
    @Log(title = "种养殖技术", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody GrowTech growTech) {
        //轻农业
        growTech.setSysName(SysConstants.SYS_NAME);
        Long userId = SecurityUtils.getUserId();
        if (userId != null) {
            growTech.setCreateBy(String.valueOf(userId));
        }
        return toAjax(growTechService.insertGrowTech(growTech));
    }

    /**
     * 修改种养殖技术
     */
    @GetMapping("/detail/{growId}")
    @ResponseBody
    public AjaxResult edit(@PathVariable("growId") Long growId)
    {
        return AjaxResult.success(growTechService.selectGrowTechById(growId));
    }

    /**
     * 修改保存种养殖技术
     */
    @PreAuthorize(hasPermi = "agriculture:growtech:edit")
    @Log(title = "种养殖技术", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestBody GrowTech growTech) {
        Long userId = SecurityUtils.getUserId();
        if (userId != null) {
            growTech.setUpdateBy(String.valueOf(userId));
        }

        return toAjax(growTechService.updateGrowTech(growTech));
    }

    /**
     * 删除种养殖技术
     */
    @PreAuthorize(hasPermi = "agriculture:growtech:remove")
    @Log(title = "种养殖技术", businessType = BusinessType.DELETE)
    @PostMapping( "/remove/{newsIds}")
    @ResponseBody
    public AjaxResult remove(@PathVariable Long[] newsIds)
    {
        return toAjax(growTechService.deleteGrowTechByIds(newsIds));
    }


    /**
     * 获取作物名称
     */
    @Log(title = "获取作物名称", businessType = BusinessType.UPDATE)
    @PostMapping("/selectCropDictById")
    @ResponseBody
    public AjaxResult selectCropDictById(Long cropId)
    {
        CropDict cropDict= (CropDict) cropDictService.getInfo(cropId).getData();
        return  AjaxResult.success(cropDict);
    }

    /**
     * 获取版块名称
     */
    @Log(title = "获取版块名称", businessType = BusinessType.UPDATE)
    @PostMapping("/selectSectionDictById")
    @ResponseBody
    public AjaxResult selectSectionDictById(Long sectionId)
    {
        SectionDict sectionDict=sectionDictService.selectSectionDictById(sectionId);
        return  AjaxResult.success(sectionDict);
    }


    @GetMapping("/share/{growId}")
    public String share(@PathVariable("growId") Long growId, ModelMap mmap) {
        GrowTechVo growTechVo=growTechService.selectGrowTechById(growId);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        growTechVo.setIssueTimeString(simpleDateFormat.format(growTechVo.getIssueTime()));
        mmap.put("growTechVo", growTechVo);

        //增加阅读量
        growTechService.summGrowTechReadCount(growId);
        //下载地址
        if(profile.equals("test")){
            mmap.put("shareNewFarmAppUrl", "http://xfqc.test.sinonc.cn/orders/newFarmAppVersion/download");
        }else {
            mmap.put("shareNewFarmAppUrl", "http://xfqc.sinonc.cn/orders/newFarmAppVersion/download");
        }

        //查询最新三条种植技术
        GrowTech growTechPara=new GrowTech();
        PageHelper.startPage(1, 3);
        List<GrowTech> list = growTechService.selectGrowTechList(growTechPara);
        mmap.put("list", list);

        return prefix + "/share";
    }


    @GetMapping("/privacy")
    public String privacy(ModelMap mmap) {
        return prefix + "/privacy";
    }

}
