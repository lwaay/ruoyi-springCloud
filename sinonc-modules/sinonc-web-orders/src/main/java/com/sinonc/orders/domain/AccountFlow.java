package com.sinonc.orders.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 账户流水表 od_account_flow
 *
 * @author sinonc
 * @date 2019-10-29
 */

public class AccountFlow {

    private static final long serialVersionUID = 1L;

    public static final int OP_TYPE_OUT = 1;
    public static final int OP_TYPE_IN = 0;
    public static final int OP_TYPE_ASKFOR = 2;

    /**
     * 流水ID
     */
    private Integer acctFlowId;
    /**
     * 交易时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date tradeTime;
    /**
     * 账户ID
     */
    private Long acctIdP;
    /**
     * 店铺id
     */
    private Long shopIdP;
    /**
     * 操作类型
     */
    private Integer opType;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 操作金额
     */
    private BigDecimal opAmount;
    /**
     * 上期账户余额
     */
    private BigDecimal prevAmount;
    /**
     * 收入金额
     */
    private BigDecimal inAmount;
    /**
     * 支出金额
     */
    private BigDecimal outAmount;
    /**
     * 交易标识
     */
    private String tradeCode;
    /**
     * 说明
     */
    private String remark;

    private Map<String, String> params;
    /**
     * 提现时间最小时间
     **/
    private Date minTaskTime;
    /**
     * 提现时间最大时间
     **/
    private Date maxTaskTime;

    private String searchDate;

    public Map<String, String> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public void setAcctFlowId(Integer acctFlowId) {
        this.acctFlowId = acctFlowId;
    }

    public Integer getAcctFlowId() {
        return acctFlowId;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setAcctIdP(Long acctIdP) {
        this.acctIdP = acctIdP;
    }

    public Long getAcctIdP() {
        return acctIdP;
    }

    public void setShopIdP(Long shopIdP) {
        this.shopIdP = shopIdP;
    }

    public Long getShopIdP() {
        return shopIdP;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public void setOpType(Integer opType) {
        this.opType = opType;
    }

    public Integer getOpType() {
        return opType;
    }

    public void setOpAmount(BigDecimal opAmount) {
        this.opAmount = opAmount;
    }

    public BigDecimal getOpAmount() {
        return opAmount;
    }

    public void setPrevAmount(BigDecimal prevAmount) {
        this.prevAmount = prevAmount;
    }

    public BigDecimal getPrevAmount() {
        return prevAmount;
    }

    public void setInAmount(BigDecimal inAmount) {
        this.inAmount = inAmount;
    }

    public BigDecimal getInAmount() {
        return inAmount;
    }

    public void setOutAmount(BigDecimal outAmount) {
        this.outAmount = outAmount;
    }

    public BigDecimal getOutAmount() {
        return outAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("acctFlowId", getAcctFlowId())
                .append("tradeTime", getTradeTime())
                .append("acctIdP", getAcctIdP())
                .append("shopIdP", getShopIdP())
                .append("opType", getOpType())
                .append("opAmount", getOpAmount())
                .append("prevAmount", getPrevAmount())
                .append("inAmount", getInAmount())
                .append("outAmount", getOutAmount())
                .toString();
    }

    public String getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(String searchDate) {
        this.searchDate = searchDate;
    }

    public String getTradeCode() {
        return tradeCode;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode;
    }

    public Date getMinTaskTime() {
        return minTaskTime;
    }

    public void setMinTaskTime(Date minTaskTime) {
        this.minTaskTime = minTaskTime;
    }

    public Date getMaxTaskTime() {
        return maxTaskTime;
    }

    public void setMaxTaskTime(Date maxTaskTime) {
        this.maxTaskTime = maxTaskTime;
    }
}
