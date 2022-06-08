package com.sinonc.orders.service.impl;

import com.sinonc.common.core.text.Convert;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.orders.controller.CouponController;
import com.sinonc.orders.domain.Coupon;
import com.sinonc.orders.dto.CouponDto;
import com.sinonc.orders.mapper.CouponMapper;
import com.sinonc.orders.service.CouponService;
import com.sinonc.orders.service.GoodsService;
import com.sinonc.orders.vo.CouponVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 优惠券 服务层实现
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Service
public class CouponServiceImpl implements CouponService {
	@Autowired
	private CouponMapper couponMapper;

	@Autowired
	private GoodsService goodsService;

	/**
     * 查询优惠券信息
     *
     * @param couponId 优惠券ID
     * @return 优惠券信息
     */
    @Override
	public Coupon getCouponById(Long couponId) {
    	Coupon coupon = couponMapper.selectCouponById(couponId);
    	if (!Optional.ofNullable(coupon).isPresent()){
    		return coupon;
		}
    	String link = coupon.getGoodsList();
    	if (StringUtils.isBlank(link)){
    		return coupon;
		}
    	coupon.setLinkGoods(Arrays.stream(link.split(",")).map(item->
    		goodsService.getGoodsById(Long.parseLong(item))).collect(Collectors.toList()));
	    return coupon;
	}

	/**
     * 查询优惠券列表
     *
     * @param coupon 优惠券信息
     * @return 优惠券集合
     */
	@Override
	public List<Coupon> listCoupon(Coupon coupon) {
	    return couponMapper.selectCouponList(coupon);
	}

	/**
	 * 查询用户未领取的优惠卷
	 */
	@Override
	public List<Coupon> listUnReceiveCoupon(CouponDto coupon){
		return couponMapper.listUnReceiveCoupon(coupon);
	}

	/**
	 * 查询用户未领取的优惠卷
	 */
	@Override
	public List<CouponVo> listReceiveCoupon(CouponDto coupon){
		return couponMapper.listReceiveCoupon(coupon);
	}

	/**
	 * 查询用户未领取的优惠卷
	 */
	public List<Coupon> usableCoupon(Long shopId,String goodsIds){
		Long memberId = SecurityUtils.getUserId();
        return couponMapper.usableCoupon(shopId,goodsIds.split(","),memberId);
	}

    /**
     * 新增优惠券
     *
     * @param coupon 优惠券信息
     * @return 结果
     */
	@Override
	public int addCoupon(Coupon coupon) {
		if (!Optional.ofNullable(coupon).isPresent()){
			return 0;
		}
		coupon.setCreateBy(SecurityUtils.getUsername());
		coupon.setCreateTime(new Date());
	    return couponMapper.insertCoupon(coupon);
	}

	/**
     * 修改优惠券
     *
     * @param coupon 优惠券信息
     * @return 结果
     */
	@Override
	public int updateCoupon(Coupon coupon) {
	    return couponMapper.updateCoupon(coupon);
	}

	/**
     * 删除优惠券对象
     * @return 结果
     */
	@Override
	public int deleteCouponByIds(String ids) {
		return couponMapper.deleteCouponByIds(Convert.toStrArray(ids));
	}

}
