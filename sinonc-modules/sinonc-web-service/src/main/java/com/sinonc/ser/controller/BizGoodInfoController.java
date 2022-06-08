package com.sinonc.ser.controller;

import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.service.domain.BizGoodInfo;
import com.sinonc.ser.dto.GoodMemberDto;
import com.sinonc.ser.service.IBizGoodInfoService;
import com.sinonc.system.api.RemoteUserService;
import com.sinonc.system.api.domain.WxUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品信息（主）Controller
 *
 * @author ruoyi
 * @date 2020-07-29
 */
@RestController
@RequestMapping("/biz/goodinfo")
public class BizGoodInfoController extends BaseController {

    @Autowired
    private IBizGoodInfoService bizGoodInfoService;

    /**
     * 查询供应商品信息列表
     */
    @PreAuthorize(hasPermi = "base:guildhall:edit")
    @PostMapping("/supplyList")
    @ResponseBody
    public TableDataInfo supplyList(BizGoodInfo bizGoodInfo) {
        startPage();
        bizGoodInfo.setInfoType("2");
        bizGoodInfo.setSaleAble("0");
        List<BizGoodInfo> list = bizGoodInfoService.selectBizGoodInfoList(bizGoodInfo);
        return getDataTable(list);
    }

    /**
     * 查询商品信息（主）列表
     */
    @PreAuthorize(hasPermi = "service:info:list")
    @GetMapping("/list")
    public TableDataInfo list(BizGoodInfo bizGoodInfo) {
        startPage();
        List<BizGoodInfo> list = bizGoodInfoService.selectBizGoodInfoList(bizGoodInfo);
        return getDataTable(list);
    }

    /**
     * 导出商品信息（主）列表
     */
    @PreAuthorize(hasPermi = "service:info:export")
    @Log(title = "商品信息（主）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizGoodInfo bizGoodInfo) throws IOException {
        List<BizGoodInfo> list = bizGoodInfoService.selectBizGoodInfoList(bizGoodInfo);
        ExcelUtil<BizGoodInfo> util = new ExcelUtil<BizGoodInfo>(BizGoodInfo. class);
        util.exportExcel(response, list, "info");
    }

    /**
     * 获取商品信息（主）详细信息
     */
    @PreAuthorize(hasPermi = "service:info:query")
    @GetMapping(value = "/{infoId}")
    public AjaxResult getInfo(@PathVariable("infoId") Long infoId) {
        return AjaxResult.success(bizGoodInfoService.selectBizGoodInfoById(infoId));
    }

    /**
     * 新增商品信息（主）
     */
    @PreAuthorize(hasPermi = "service:info:add")
    @Log(title = "商品信息（主）", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizGoodInfo bizGoodInfo) {
        return toAjax(bizGoodInfoService.insertBizGoodInfo(bizGoodInfo));
    }

    /**
     * 修改商品信息（主）
     */
    @PreAuthorize(hasPermi = "service:info:edit")
    @Log(title = "商品信息（主）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizGoodInfo bizGoodInfo) {
        return toAjax(bizGoodInfoService.updateBizGoodInfo(bizGoodInfo));
    }

    /**
     * 删除商品信息（主）
     */
    @PreAuthorize(hasPermi = "service:info:remove")
    @Log(title = "商品信息（主）", businessType = BusinessType.DELETE)
    @DeleteMapping("/{infoIds}")
    public AjaxResult remove(@PathVariable String infoIds) {
        return toAjax(bizGoodInfoService.deleteBizGoodInfoByIds(infoIds));
    }

}
