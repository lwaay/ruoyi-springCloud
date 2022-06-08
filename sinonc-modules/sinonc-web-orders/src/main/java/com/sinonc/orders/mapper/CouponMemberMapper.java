package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.CouponMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 优惠券用户关系 数据层
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Mapper
public interface CouponMemberMapper {
	/**
     * 查询优惠券用户关系信息
     *
     * @param couponMemberId 优惠券用户关系ID
     * @return 优惠券用户关系信息
     */
	public CouponMember selectCouponMemberById(Long couponMemberId);

	/**
     * 查询优惠券用户关系列表
     *
     * @param couponMember 优惠券用户关系信息
     * @return 优惠券用户关系集合
     */
	public List<CouponMember> selectCouponMemberList(CouponMember couponMember);

	/**
     * 新增优惠券用户关系
     *
     * @param couponMember 优惠券用户关系信息
     * @return 结果
     */
	public int insertCouponMember(CouponMember couponMember);

	/**
     * 修改优惠券用户关系
     *
     * @param couponMember 优惠券用户关系信息
     * @return 结果
     */
	public int updateCouponMember(CouponMember couponMember);

	/**
     * 删除优惠券用户关系
     *
     * @param couponMemberId 优惠券用户关系ID
     * @return 结果
     */
	public int deleteCouponMemberById(Long couponMemberId);

	/**
     * 批量删除优惠券用户关系
     *
     * @param couponMemberIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteCouponMemberByIds(String[] couponMemberIds);

	/**
	 * 获取是否重复领取
	 */
	public Long repeatCoupon(@Param("couponId") Long couponId,@Param("memberId") Long memberId);
}
