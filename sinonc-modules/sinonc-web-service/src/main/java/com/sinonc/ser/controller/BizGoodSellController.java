package com.sinonc.ser.controller;

import com.sinonc.base.api.RemoteAreaCodeService;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.ser.dto.BizGoodSellDto;
import com.sinonc.service.domain.BizGoodSell;
import com.sinonc.ser.dto.BizGoodSellBackDto;
import com.sinonc.ser.service.IBizGoodSellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 供应Controller
 *
 * @author ruoyi
 * @date 2020-07-30
 */
@RestController
@RequestMapping("/biz/goodsell")
public class BizGoodSellController extends BaseController {

    @Autowired
    private IBizGoodSellService bizGoodSellService;

    @Autowired
    private RemoteAreaCodeService areaCodeService;

    /**
     * 查询供应列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BizGoodSell bizGoodSell) {
        startPage();
        List<BizGoodSellBackDto> list = bizGoodSellService.selectBizGoodSellList(bizGoodSell);
        list.forEach(x->{
            String addressName = areaCodeService.changeAddressName(x.getShipAddress()).getData();
            if(StringUtils.isNotEmpty(addressName)){
                x.setShipAddressName(addressName);
            }
        });
        return getDataTable(list);
    }

    /**
     * 导出供应列表
     */
    @PreAuthorize(hasPermi = "biz:goodsell:export")
    @Log(title = "供应", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizGoodSell bizGoodSell) throws IOException {
        List<BizGoodSellBackDto> list = bizGoodSellService.selectBizGoodSellList(bizGoodSell);
        ExcelUtil<BizGoodSellBackDto> util = new ExcelUtil<BizGoodSellBackDto>(BizGoodSellBackDto. class);
        util.exportExcel(response, list, "goodsell");
    }

    /**
     * 获取供应详细信息
     */
    @PreAuthorize(hasPermi = "biz:goodsell:query")
    @GetMapping(value = "/{sellId}")
    public AjaxResult getInfo(@PathVariable("sellId") Long sellId) {
        return AjaxResult.success(bizGoodSellService.selectBizGoodSellById(sellId));
    }

    /**
     * 新增供应
     */
    @PreAuthorize(hasPermi = "biz:goodsell:add")
    @Log(title = "供应", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizGoodSell bizGoodSell) {
        return toAjax(bizGoodSellService.insertBizGoodSell(bizGoodSell));
    }

    /**
     * 修改供应
     */
    @PreAuthorize(hasPermi = "biz:goodsell:edit")
    @Log(title = "供应", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizGoodSell bizGoodSell) {
        return toAjax(bizGoodSellService.updateBizGoodSell(bizGoodSell));
    }

    /**
     * 删除供应
     */
    @PreAuthorize(hasPermi = "biz:goodsell:remove")
    @Log(title = "供应", businessType = BusinessType.DELETE)
    @DeleteMapping("/{sellIds}")
    public AjaxResult remove(@PathVariable Long[] sellIds) {
        return toAjax(bizGoodSellService.deleteBizGoodSellByIds(sellIds));
    }
}
