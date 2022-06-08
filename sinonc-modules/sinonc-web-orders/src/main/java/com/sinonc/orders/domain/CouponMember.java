package com.sinonc.orders.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * 优惠券用户关系表 od_coupon_member
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Alias("CouponMember")
public class CouponMember {

    private static final long serialVersionUID = 1L;

    /**  */
    private Long couponMemberId;
    /** 用户id */
    private Long memberIdP;
    /** 优惠券ID */
    private Long couponIdP;
    /** 优惠券使用时间 */
    private Date useTime;
    /** 优惠券使用的订单ID */
    private Long orderIdP;
    /** 优惠券当前状态。0，未使用，1，已使用，2，已过期 */
    private Integer couponStatus;
    /** 优惠券获取方式。0，主动领取；1，商家发放；3，平台发放 */
    private Integer getType;
    /** 优惠券领取时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /** 删除标记。0，未删除；1，已删除 */
    private Integer delFlag;

    public void setCouponMemberId(Long couponMemberId) {
            this.couponMemberId = couponMemberId;
    }

    public Long getCouponMemberId() {
            return couponMemberId;
    }
    public void setMemberIdP(Long memberIdP) {
            this.memberIdP = memberIdP;
    }

    public Long getMemberIdP() {
            return memberIdP;
    }
    public void setCouponIdP(Long couponIdP) {
            this.couponIdP = couponIdP;
    }

    public Long getCouponIdP() {
            return couponIdP;
    }
    public void setUseTime(Date useTime) {
            this.useTime = useTime;
    }

    public Date getUseTime() {
            return useTime;
    }
    public void setOrderIdP(Long orderIdP) {
            this.orderIdP = orderIdP;
    }

    public Long getOrderIdP() {
            return orderIdP;
    }
    public void setCouponStatus(Integer couponStatus) {
            this.couponStatus = couponStatus;
    }

    public Integer getCouponStatus() {
            return couponStatus;
    }
    public void setGetType(Integer getType) {
            this.getType = getType;
    }

    public Integer getGetType() {
            return getType;
    }
    public void setCreateTime(Date createTime) {
            this.createTime = createTime;
    }

    public Date getCreateTime() {
            return createTime;
    }
    public void setUpdateTime(Date updateTime) {
            this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
            return updateTime;
    }
    public void setDelFlag(Integer delFlag) {
            this.delFlag = delFlag;
    }

    public Integer getDelFlag() {
            return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("couponMemberId",getCouponMemberId())
                .append("memberIdP",getMemberIdP())
                .append("couponIdP",getCouponIdP())
                .append("useTime",getUseTime())
                .append("orderIdP",getOrderIdP())
                .append("couponStatus",getCouponStatus())
                .append("getType",getGetType())
                .append("createTime",getCreateTime())
                .append("updateTime",getUpdateTime())
                .append("delFlag",getDelFlag())
                .toString();
    }
}
