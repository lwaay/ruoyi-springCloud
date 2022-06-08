package com.sinonc.orders.controller.api;

import com.github.pagehelper.PageHelper;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.PageDataInfo;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.orders.domain.Coupon;
import com.sinonc.orders.domain.CouponMember;
import com.sinonc.orders.dto.CouponDto;
import com.sinonc.orders.service.CouponMemberService;
import com.sinonc.orders.service.CouponService;
import com.sinonc.orders.vo.CouponVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 优惠券Controller
 *
 * @author ruoyi
 * @date 2022-03-30
 */
@Api(tags = "优惠卷接口API")
@RestController
@RequestMapping("/api/coupon")
public class ApiCouponController extends BaseController {

    @Autowired
    private CouponService couponService;

    @Autowired
    private CouponMemberService couponMemberService;

    /**
     * 查询优惠券列表
     */
    @ApiOperation(value = "查询未领取的优惠卷")
    @PostMapping("/list")
    public PageDataInfo list(@RequestBody CouponDto odCoupon) {
        int pageNum = odCoupon.getPageNum() == null || odCoupon.getPageSize()<1? 1:odCoupon.getPageNum();
        int pageSize = odCoupon.getPageSize() == null || odCoupon.getPageSize()<1?10:odCoupon.getPageSize();
        PageHelper.startPage(pageNum,pageSize);
        odCoupon.setMemberId(SecurityUtils.getUserId());
        List<Coupon> list = couponService.listUnReceiveCoupon(odCoupon);
        return getPageTable(list);
    }

    /**
     * 我的优惠券列表
     */
    @ApiOperation(value = "查询我的优惠卷")
    @PostMapping("/myCoupon")
    public PageDataInfo myCoupon(@RequestBody CouponDto odCoupon) {
        int pageNum = odCoupon.getPageNum() == null || odCoupon.getPageSize()<1? 1:odCoupon.getPageNum();
        int pageSize = odCoupon.getPageSize() == null || odCoupon.getPageSize()<1?10:odCoupon.getPageSize();
        PageHelper.startPage(pageNum,pageSize);
        odCoupon.setMemberId(SecurityUtils.getUserId());
        List<CouponVo> list = couponService.listReceiveCoupon(odCoupon);
        return getPageTable(list);
    }

    /**
     * 领取优惠卷
     */
    @ApiOperation(value = "领取优惠卷")
    @PostMapping("/award")
    public AjaxResult award(@RequestBody CouponMember couponMember){
        if (!Optional.ofNullable(couponMember).isPresent() || couponMember.getCouponIdP() == null){
            return AjaxResult.error("未获取优惠卷信息，领卷失败");
        }
        couponMember.setMemberIdP(SecurityUtils.getUserId());
        couponMember.setCouponStatus(0);
        couponMember.setDelFlag(0);
        couponMember.setCreateTime(new Date());
        couponMember.setGetType(0);
        return toAjax(couponMemberService.addCouponMember(couponMember));
    }

    /**
     * 根据订单明细看下可用的优惠卷
     */
    @ApiOperation(value = "根据商品id查看可用的优惠卷")
    @PostMapping("/usable")
    public AjaxResult usable(Long shopId,String goodsIds){
        List<Coupon> list = couponService.usableCoupon(shopId,goodsIds);
        return AjaxResult.success(list);
    }
}
