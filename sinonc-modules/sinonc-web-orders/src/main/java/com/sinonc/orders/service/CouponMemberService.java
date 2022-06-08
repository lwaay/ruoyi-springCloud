package com.sinonc.orders.service;

import com.sinonc.orders.domain.CouponMember;

import java.util.List;

/**
 * 优惠券用户关系 服务层
 *
 * @author sinonc
 * @date 2019-07-25
 */
public interface CouponMemberService {

	/**
     * 查询优惠券用户关系信息
     *
     * @param couponMemberId 优惠券用户关系ID
     * @return 优惠券用户关系信息
     */
	public CouponMember getCouponMemberById(Long couponMemberId);

	/**
     * 查询优惠券用户关系列表
     *
     * @param couponMember 优惠券用户关系信息
     * @return 优惠券用户关系集合
     */
	public List<CouponMember> listCouponMember(CouponMember couponMember);

	/**
     * 新增优惠券用户关系
     *
     * @param couponMember 优惠券用户关系信息
     * @return 结果
     */
	public int addCouponMember(CouponMember couponMember);

	/**
     * 修改优惠券用户关系
     *
     * @param couponMember 优惠券用户关系信息
     * @return 结果
     */
	public int updateCouponMember(CouponMember couponMember);

	/**
     * 删除优惠券用户关系信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCouponMemberByIds(String ids);

}
