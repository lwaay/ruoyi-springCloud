package com.sinonc.orders.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinonc.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * 订单表 od_order
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Data
@ApiModel
@Alias("Order")
public class Order extends BaseEntity{

    private static final long serialVersionUID = 1L;
    /**
     * 未付款
     */
    public static final int TRADE_STATUS_WAIT_PAY = 0;
    /**
     * 已付款
     */
    public static final int TRADE_STATUS_PAYED = 1;
    /**
     * 已付定金
     */
    public static final int TRADE_STATUS_PAYED_EARNEST = 2;
    /**
     * 已退全款
     */
    public static final int TRADE_STATUS_REFUND_ALL = 3;
    /**
     * 已退部分款项
     */
    public static final int TRADE_STATUS_REFUND_PART = 4;


    /**
     * 主键自增
     */
    @ApiModelProperty(hidden = true)
    private Long orderId;
    /**
     * 订单号
     */
    @ApiModelProperty(hidden = true)
    private String orderNo;
    /**
     * 商品总价
     */
    @ApiModelProperty(hidden = true)
    private BigDecimal goodsTotalPrice;
    /**
     * 运费总价
     */
    @ApiModelProperty(hidden = true)
    private BigDecimal expressPrice;
    /**
     * 总计店铺优惠抵扣价格
     */
    @ApiModelProperty(hidden = true)
    private BigDecimal shopTotalDiscountPrice;
    /**
     * 总计平台优惠抵扣价格
     */
    @ApiModelProperty(hidden = true)
    private BigDecimal platformTotalDiscountPrice;
    /**
     * 订单总价
     */
    @ApiModelProperty(hidden = true)
    private BigDecimal orderTotalPrice;

    /**
     * 商家自定义优惠
     */
    @ApiModelProperty(hidden = true)
    private BigDecimal shopCustomDiscountPrice;

    /**
     * 优惠券ID
     */
    @ApiModelProperty(hidden = true)
    private Long couponIdP;
    /**
     * 实付款
     */
    @ApiModelProperty(hidden = true)
    private BigDecimal actualPayment;

    /**
     * 支付状态，0，未支付，1，已支付
     */
    @ApiModelProperty(hidden = true)
    private Integer tradeStatus;
    /**
     * 退款状态
     */
    @ApiModelProperty(hidden = true)
    private Integer refundStatus;

    /**
     * 是否评价，0，未评价，1，已评价
     */
    @ApiModelProperty(hidden = true)
    private Integer evaluationStatus;
    /**
     * 支付详情表，支付信息ID
     */
    @ApiModelProperty(hidden = true)
    private Long tradeIdP;
    /**
     * 订单状态。0，代付款；1，待发货；2，待收货；3，待评价；4，交易成功；5，交易关闭；6，退款
     */
    @ApiModelProperty(hidden = true)
    private String orderStatus;
    /**
     * 订单类型。1，普通订单；0，认养订单；
     */
    @ApiModelProperty(hidden = true)
    private Integer orderType;

    /**
     * 结算状态：0，未结算，1，已结算，2，部分已结算
     */
    @ApiModelProperty(hidden = true)
    private Integer settlement;

    /**
     * 已结算金额
     */
    @ApiModelProperty(hidden = true)
    private BigDecimal settlementAmount;
    /**
     * 店铺id
     */
    @ApiModelProperty(hidden = true)
    private Long shopIdP;
    /**
     * 物流ID
     */
    @ApiModelProperty(hidden = true)
    private Long expressIdP;
    /**
     * 买家ID
     */
    @ApiModelProperty(hidden = true)
    private Long memberIdP;
    /**
     * 地址ID
     */
    @ApiModelProperty(hidden = true)
    private Long addrIdP;
    /**
     * 收货人姓名
     */
    @ApiModelProperty(hidden = true)
    private String receiver;
    /**
     * 电话号码
     */
    @ApiModelProperty(hidden = true)
    private String phone;
    /**
     * 收货地址
     */
    @ApiModelProperty(hidden = true)
    private String address;
    /**
     * 创建时间
     */
    @ApiModelProperty(hidden = true)
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 付款时间
     */
    @ApiModelProperty(hidden = true)
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    private Date tradeTime;
    /**
     * 发货时间
     */
    @ApiModelProperty(hidden = true)
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expressTime;
    /**
     * 结束（完成）时间
     */
    @ApiModelProperty(hidden = true)
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(hidden = true)
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /**
     * 自动收货时间
     */
    @ApiModelProperty(hidden = true)
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    private Date autoConfirmTime;

    /**
     * 结算时间
     */
    @ApiModelProperty(hidden = true)
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    private Date settlementTime;

    /**
     * 备注信息
     */
    @ApiModelProperty(hidden = true)
    private String remark;
    /**
     * 删除标记，0，未删除，1，已删除
     */
    @ApiModelProperty(hidden = true)
    private Integer delFlag;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("orderId", getOrderId())
                .append("orderNo", getOrderNo())
                .append("goodsTotalPrice", getGoodsTotalPrice())
                .append("expressPrice", getExpressPrice())
                .append("shopTotalDiscountPrice", getShopTotalDiscountPrice())
                .append("platformTotalDiscountPrice", getPlatformTotalDiscountPrice())
                .append("orderTotalPrice", getOrderTotalPrice())
                .append("couponIdP", getCouponIdP())
                .append("actualPayment", getActualPayment())
                .append("tradeStatus", getTradeStatus())
                .append("tradeIdP", getTradeIdP())
                .append("orderStatus", getOrderStatus())
                .append("shopIdP", getShopIdP())
                .append("expressIdP", getExpressIdP())
                .append("memberIdP", getMemberIdP())
                .append("addrIdP", getAddrIdP())
                .append("receiver", getReceiver())
                .append("phone", getPhone())
                .append("address", getAddress())
                .append("createTime", getCreateTime())
                .append("tradeTime", getTradeTime())
                .append("expressTime", getExpressTime())
                .append("updateTime", getUpdateTime())
                .append("autoConfirmTime", getAutoConfirmTime())
                .append("remark", getRemark())
                .append("delFlag", getDelFlag())
                .toString();
    }
}
