package com.sinonc.orders.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 商户账户表 od_account
 *
 * @author sinonc
 * @date 2019-10-29
 */

public class Account {

    private static final long serialVersionUID = 1L;

    /** 账户自增ID */
    private Long acctId;
    /** 店铺ID */
    private Long shopIdP;
    /** 店铺名称 */
    private String shopName;
    /** 账户总金额 */
    private BigDecimal acctAmount;
    /** 可用金额 */
    private BigDecimal acctUsable;
    /** 冻结金额 */
    private BigDecimal acctLock;
    /** 累计收入 */
    private BigDecimal incomeAmount;
    /** 累计支出 */
    private BigDecimal outAmount;
    /** 更新时间 */
    private Date updateTime;

    /**
     * 银行卡开户行
     */
    private String bankName;

    /**
     * 银行卡号
     */
    private String bankCardId;

    /**
     * 持卡人姓名
     */
    private String bankUser;

    private Map<String, String> params;

    public Map<String, String> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }

    public void setAcctId(Long acctId) {
            this.acctId = acctId;
    }

    public Long getAcctId() {
            return acctId;
    }
    public void setShopIdP(Long shopIdP) {
            this.shopIdP = shopIdP;
    }

    public Long getShopIdP() {
            return shopIdP;
    }
    public void setShopName(String shopName) {
            this.shopName = shopName;
    }

    public String getShopName() {
            return shopName;
    }
    public void setAcctAmount(BigDecimal acctAmount) {
            this.acctAmount = acctAmount;
    }

    public BigDecimal getAcctAmount() {
            return acctAmount;
    }
    public void setAcctUsable(BigDecimal acctUsable) {
            this.acctUsable = acctUsable;
    }

    public BigDecimal getAcctUsable() {
            return acctUsable;
    }
    public void setAcctLock(BigDecimal acctLock) {
            this.acctLock = acctLock;
    }

    public BigDecimal getAcctLock() {
            return acctLock;
    }
    public void setIncomeAmount(BigDecimal incomeAmount) {
            this.incomeAmount = incomeAmount;
    }

    public BigDecimal getIncomeAmount() {
            return incomeAmount;
    }
    public void setOutAmount(BigDecimal outAmount) {
            this.outAmount = outAmount;
    }

    public BigDecimal getOutAmount() {
            return outAmount;
    }
    public void setUpdateTime(Date updateTime) {
            this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
            return updateTime;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCardId() {
        return bankCardId;
    }

    public void setBankCardId(String bankCardId) {
        this.bankCardId = bankCardId;
    }

    public String getBankUser() {
        return bankUser;
    }

    public void setBankUser(String bankUser) {
        this.bankUser = bankUser;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("acctId",getAcctId())
                .append("shopIdP",getShopIdP())
                .append("shopName",getShopName())
                .append("acctAmount",getAcctAmount())
                .append("acctUsable",getAcctUsable())
                .append("acctLock",getAcctLock())
                .append("incomeAmount",getIncomeAmount())
                .append("outAmount",getOutAmount())
                .append("updateTime",getUpdateTime())
                .toString();
    }
}
