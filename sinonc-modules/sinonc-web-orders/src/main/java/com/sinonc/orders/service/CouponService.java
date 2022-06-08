package com.sinonc.orders.service;

import com.sinonc.orders.domain.Coupon;
import com.sinonc.orders.dto.CouponDto;
import com.sinonc.orders.vo.CouponVo;

import java.util.List;

/**
 * 优惠券 服务层
 *
 * @author sinonc
 * @date 2019-07-25
 */
public interface CouponService {

	/**
     * 查询优惠券信息
     *
     * @param couponId 优惠券ID
     * @return 优惠券信息
     */
	public Coupon getCouponById(Long couponId);

	/**
     * 查询优惠券列表
     *
     * @param coupon 优惠券信息
     * @return 优惠券集合
     */
	public List<Coupon> listCoupon(Coupon coupon);

	/**
	 * 查询用户未领取的优惠卷
	 */
	public List<Coupon> listUnReceiveCoupon(CouponDto coupon);

	/**
	 * 查询用户未领取的优惠卷
	 */
	public List<CouponVo> listReceiveCoupon(CouponDto coupon);

	/**
	 * 查询用户未领取的优惠卷
	 */
	public List<Coupon> usableCoupon(Long shopId,String goodsIds);

	/**
     * 新增优惠券
     *
     * @param coupon 优惠券信息
     * @return 结果
     */
	public int addCoupon(Coupon coupon);

	/**
     * 修改优惠券
     *
     * @param coupon 优惠券信息
     * @return 结果
     */
	public int updateCoupon(Coupon coupon);

	/**
     * 删除优惠券信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCouponByIds(String ids);

}
