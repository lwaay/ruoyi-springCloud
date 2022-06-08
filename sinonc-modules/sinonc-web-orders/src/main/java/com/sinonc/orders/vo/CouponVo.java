package com.sinonc.orders.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinonc.common.core.web.domain.BaseEntity;
import com.sinonc.orders.domain.CouponMember;
import com.sinonc.orders.domain.Goods;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 优惠券表 od_coupon
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Data
public class CouponVo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**  */
    private Long couponId;
    /** 优惠券名称 */
    private String couponName;
    /** 创建者id */
    private String createBy;
    /** 优惠券创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /** 优惠券生效时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    /** 优惠券失效时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date invalidTime;
    /**  */
    private String updateBy;
    /**  */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /** 优惠券金额 */
    private BigDecimal couponAmount;
    /** 最低使用价格 */
    private BigDecimal minimumPrice;
    /** 优惠券类型。0，店铺通用券；1，店铺指定商品券；2，平台通用券；3，平台指定商品券 */
    private Integer couponType;
    /** 优惠券说明 */
    private String explain;
    /** 能够使用券的指定商品列表（json数组格式存储），若为全品类，则为空 */
    private String goodsList;
    /** 优惠券总数量 */
    private Integer number;
    /** 优惠券所属店铺，若为平台券，则值为0 */
    private Long shopIdP;
    /** 标记删除，0，存在，1，删除 */
    private Integer delFlag;

    private Long memberId;

    private Integer surplus;

    //领卷人
    private CouponMember award;
}
