package com.sinonc.orders.service.impl;

import com.sinonc.common.core.exception.BusinessException;
import com.sinonc.common.core.text.Convert;
import com.sinonc.orders.domain.Coupon;
import com.sinonc.orders.domain.CouponMember;
import com.sinonc.orders.mapper.CouponMapper;
import com.sinonc.orders.mapper.CouponMemberMapper;
import com.sinonc.orders.service.CouponMemberService;
import com.sinonc.orders.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 优惠券用户关系 服务层实现
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Service
public class CouponMemberServiceImpl implements CouponMemberService {

	@Autowired
	private CouponMemberMapper couponMemberMapper;

	@Autowired
	private CouponMapper couponMapper;

	/**
     * 查询优惠券用户关系信息
     *
     * @param couponMemberId 优惠券用户关系ID
     * @return 优惠券用户关系信息
     */
    @Override
	public CouponMember getCouponMemberById(Long couponMemberId) {
	    return couponMemberMapper.selectCouponMemberById(couponMemberId);
	}

	/**
     * 查询优惠券用户关系列表
     *
     * @param couponMember 优惠券用户关系信息
     * @return 优惠券用户关系集合
     */
	@Override
	public List<CouponMember> listCouponMember(CouponMember couponMember) {
	    return couponMemberMapper.selectCouponMemberList(couponMember);
	}

    /**
     * 新增优惠券用户关系
     *
     * @param couponMember 优惠券用户关系信息
     * @return 结果
     */
	@Override
	@Transactional
	public int addCouponMember(CouponMember couponMember) {
		//重复领取
		Long repeat = couponMemberMapper.repeatCoupon(couponMember.getCouponIdP(),couponMember.getMemberIdP());
		if (repeat >0){
			throw new BusinessException("请勿重复领取购物券");
		}
		int res = couponMemberMapper.insertCouponMember(couponMember);
		if (res < 1){
			return res;
		}
		//乐观锁
		Coupon coupon = couponMapper.selectCouponById(couponMember.getCouponIdP());
		if (!Optional.ofNullable(coupon).isPresent()){
			throw new BusinessException("获取优惠卷信息失败，无法领卷");
		}
		res = couponMapper.optimisticUpdateSurplus(couponMember.getCouponIdP(),coupon.getSurplus()-1,coupon.getSurplus());
		if (res < 1){
			throw new BusinessException("扣减优惠卷库存失败，请重新领卷");
		}
	    return res;
	}

	/**
     * 修改优惠券用户关系
     *
     * @param couponMember 优惠券用户关系信息
     * @return 结果
     */
	@Override
	public int updateCouponMember(CouponMember couponMember) {
	    return couponMemberMapper.updateCouponMember(couponMember);
	}

	/**
     * 删除优惠券用户关系对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteCouponMemberByIds(String ids) {
		return couponMemberMapper.deleteCouponMemberByIds(Convert.toStrArray(ids));
	}

}
