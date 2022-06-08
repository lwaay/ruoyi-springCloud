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
import com.sinonc.service.domain.BizGoodBuy;
import com.sinonc.ser.dto.BizGoodBuyBackDto;
import com.sinonc.ser.service.IBizGoodBuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 采购Controller
 *
 * @author ruoyi
 * @date 2020-07-29
 */
@RestController
@RequestMapping("/biz/goodbuy")
public class BizGoodBuyController extends BaseController {

    @Autowired
    private IBizGoodBuyService bizGoodBuyService;

    @Autowired
    private RemoteAreaCodeService areaCodeService;

    /**
     * 查询采购列表
     */
    @PreAuthorize(hasPermi = "service:goodbuy:list")
    @GetMapping("/list")
    public TableDataInfo list(BizGoodBuy bizGoodBuy) {
        startPage();
        List<BizGoodBuyBackDto> list = bizGoodBuyService.selectBizGoodBuyList(bizGoodBuy);
        list.forEach(x->{
            String addressName = areaCodeService.changeAddressName(x.getShipAddress()).getData();
            if(StringUtils.isNotEmpty(addressName)){
                x.setShipAddressName(addressName);
            }
        });
        return getDataTable(list);
    }

    /**
     * 导出采购列表
     */
    @PreAuthorize(hasPermi = "service:goodbuy:export")
    @Log(title = "采购", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizGoodBuy bizGoodBuy) throws IOException {
        List<BizGoodBuyBackDto> list = bizGoodBuyService.selectBizGoodBuyList(bizGoodBuy);
        ExcelUtil<BizGoodBuyBackDto> util = new ExcelUtil<BizGoodBuyBackDto>(BizGoodBuyBackDto. class);
        util.exportExcel(response, list, "goodbuy");
    }

    /**
     * 获取采购详细信息
     */
    @PreAuthorize(hasPermi = "service:goodbuy:query")
    @GetMapping(value = "/{buyId}")
    public AjaxResult getInfo(@PathVariable("buyId") Long buyId) {
        return AjaxResult.success(bizGoodBuyService.selectBizGoodBuyById(buyId));
    }

    /**
     * 新增采购
     */
    @PreAuthorize(hasPermi = "service:goodbuy:add")
    @Log(title = "采购", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizGoodBuy bizGoodBuy) {
        return toAjax(bizGoodBuyService.insertBizGoodBuy(bizGoodBuy));
    }

    /**
     * 修改采购
     */
    @PreAuthorize(hasPermi = "service:goodbuy:edit")
    @Log(title = "采购", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizGoodBuy bizGoodBuy) {
        return toAjax(bizGoodBuyService.updateBizGoodBuy(bizGoodBuy));
    }

    /**
     * 删除采购
     */
    @PreAuthorize(hasPermi = "service:goodbuy:remove")
    @Log(title = "采购", businessType = BusinessType.DELETE)
    @DeleteMapping("/{buyIds}")
    public AjaxResult remove(@PathVariable Long[] buyIds) {
        return toAjax(bizGoodBuyService.deleteBizGoodBuyByIds(buyIds));
    }
}
