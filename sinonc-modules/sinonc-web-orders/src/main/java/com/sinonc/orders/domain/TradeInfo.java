package com.sinonc.orders.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 支付表 od_trade_info
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Alias("TradeInfo")
@Data
public class TradeInfo {

    private static final long serialVersionUID = 1L;

    /**  */
    private Long tradeId;
    /** 本系统订单ID */
    private Long orderIdP;
    /** 本系统订单号 */
    private String orderNo;
    /** 本系统订单ID类型 0,od_order 普通订单 1，押金订单 */
    private Integer orderIdType;
    /** 支付平台交易流水号（由支付平台提供） */
    private String tradeNo;
    /** 支付平台返回状态码 */
    private String returnCode;
    /** 支付平台返回信息 */
    private String retrunMsg;
    /** 支付平台支付交易金额 */
    private BigDecimal totalAmount;
    /**实收金额(扣除平台手续费后的金额)*/
    private BigDecimal receiptAmount;
    /** 支付平台类型，0，微信；1，支付宝 */
    private Integer payType;
    /** 店铺id */
    private Long shopIdP;
    /** 买家用户ID */
    private Long memberId;
    /** 支付平台支付状态。0，待付款；1，完成付款；2，交易关闭 */
    private Integer payStatus;
    /** 交易类型。0，支付；1，退款 */
    private Integer tradeType;
    /**  */
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**  */
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /**  */
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    private String searchDate;
    }

