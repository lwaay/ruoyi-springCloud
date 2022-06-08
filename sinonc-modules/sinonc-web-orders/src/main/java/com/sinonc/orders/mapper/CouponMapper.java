package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.Coupon;
import com.sinonc.orders.dto.CouponDto;
import com.sinonc.orders.vo.CouponVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 优惠券 数据层
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Mapper
public interface CouponMapper {
	/**
     * 查询优惠券信息
     *
     * @param couponId 优惠券ID
     * @return 优惠券信息
     */
	public Coupon selectCouponById(Long couponId);

	/**
     * 查询优惠券列表
     *
     * @param coupon 优惠券信息
     * @return 优惠券集合
     */
	public List<Coupon> selectCouponList(Coupon coupon);

	/**
	 * 查询未领取优惠券列表
	 * @param coupon 优惠券信息
	 * @return 优惠券集合
	 */
	public List<Coupon> listUnReceiveCoupon(CouponDto coupon);

	/**
	 * 查询未领取优惠券列表
	 * @param coupon 优惠券信息
	 * @return 优惠券集合
	 */
	public List<CouponVo> listReceiveCoupon(CouponDto coupon);

	/**
     * 新增优惠券
     *
     * @param coupon 优惠券信息
     * @return 结果
     */
	public int insertCoupon(Coupon coupon);

	/**
     * 修改优惠券
     *
     * @param coupon 优惠券信息
     * @return 结果
     */
	public int updateCoupon(Coupon coupon);

	/**
     * 删除优惠券
     *
     * @param couponId 优惠券ID
     * @return 结果
     */
	public int deleteCouponById(Long couponId);

	/**
     * 批量删除优惠券
     *
     * @param couponIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteCouponByIds(String[] couponIds);

	/**
	 * 查询用户未领取的优惠卷
	 */
	public List<Coupon> usableCoupon(@Param("shopId") Long shopId,@Param("goodsIds") String[] goodsIds,@Param("memberId") Long memberId);

	/**
	 * 修改优惠卷库存
	 */
	public int optimisticUpdateSurplus(@Param("couponId") Long couponId,@Param("surplus") Integer surplus,@Param("version")  Integer version);
}
