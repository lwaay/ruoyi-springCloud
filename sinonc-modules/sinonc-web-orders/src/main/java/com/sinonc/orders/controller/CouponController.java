package com.sinonc.orders.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.sinonc.orders.domain.Coupon;
import com.sinonc.orders.service.CouponService;
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
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 优惠券Controller
 *
 * @author ruoyi
 * @date 2022-03-30
 */
@RestController
@RequestMapping("/coupon")
public class CouponController extends BaseController {

    @Autowired
    private CouponService couponService;

    /**
     * 查询优惠券列表
     */
    @PreAuthorize(hasPermi = "orders:coupon:list")
    @GetMapping("/list")
    public TableDataInfo list(Coupon odCoupon) {
        startPage();
        List<Coupon> list = couponService.listCoupon(odCoupon);
        return getDataTable(list);
    }

    /**
     * 导出优惠券列表
     */
    @PreAuthorize(hasPermi = "orders:coupon:export")
    @Log(title = "优惠券", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Coupon odCoupon) throws IOException {
        List<Coupon> list = couponService.listCoupon(odCoupon);
        ExcelUtil<Coupon> util = new ExcelUtil<Coupon>(Coupon.class);
        util.exportExcel(response, list, "coupon");
    }

    /**
     * 获取优惠券详细信息
     */
    @PreAuthorize(hasPermi = "orders:coupon:query")
    @GetMapping(value = "/{couponId}")
    public AjaxResult getInfo(@PathVariable("couponId") Long couponId) {
        return AjaxResult.success(couponService.getCouponById(couponId));
    }

    /**
     * 新增优惠券
     */
    @PreAuthorize(hasPermi = "orders:coupon:add")
    @Log(title = "优惠券", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Coupon odCoupon) {
        return toAjax(couponService.addCoupon(odCoupon));
    }

    /**
     * 修改优惠券
     */
    @PreAuthorize(hasPermi = "orders:coupon:edit")
    @Log(title = "优惠券", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Coupon odCoupon) {
        return toAjax(couponService.updateCoupon(odCoupon));
    }

    /**
     * 删除优惠券
     */
    @PreAuthorize(hasPermi = "orders:coupon:remove")
    @Log(title = "优惠券", businessType = BusinessType.DELETE)
    @DeleteMapping("/{couponIds}")
    public AjaxResult remove(@PathVariable String couponIds) {
        return toAjax(couponService.deleteCouponByIds(couponIds));
    }
}
