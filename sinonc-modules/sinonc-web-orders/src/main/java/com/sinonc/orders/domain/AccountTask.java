package com.sinonc.orders.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 账户提现表 od_account_task
 *
 * @author sinonc
 * @date 2019-11-15
 */

public class AccountTask {

    private static final long serialVersionUID = 1L;

    /**  */
    private Long acctTaskId;
    /**后台用户ID**/
    private Long userIdP;
    /** 店铺ID */
    private Long shopIdP;
    /** 提现时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date taskTime;
    /** 提现金额 */
    private BigDecimal taskAmount;
    /** 实到金额 */
    private BigDecimal trueAmount;
    /** 0,银行卡 1,支付宝 2,微信 */
    private Integer taskType;
    /** 开户银行 */
    private String bankName;
    /** 银行卡号 */
    private String bankNumber;
    /** 账户名 */
    private String bankAcct;
    /** 支付宝账号 */
    private String alipayAcct;
    /** 支付宝实名 */
    private String alipayName;
    /** 微信账号 */
    private String wxAcct;
    /** 微信呢称 */
    private String wxName;
    /** 0未提交 1提交审核中 2审核通过 3审核驳回 */
    private Integer taskFlag;
    /** 审核人 */
    private String auditBy;
    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date auditTime;
    /** 创建时间 */
    private Date createTime;
    /** 创建人 */
    private String createBy;
    /**店铺名称**/
    private String shopName;
    /**提现时间最小时间**/
    private Date minTaskTime;
    /**提现时间最大时间**/
    private Date maxTaskTime;
    /**
     * 提现支付方式 1 支付宝 2微信 3人工
     */
    private Integer payTaskType;
    /**
     * 手机验证码
     */
    private String phoneCode;
    /**
     * 复核人电话
     * @return
     */
    private String phoneNumber;
    /**
     * 人工转账截图
     */
    private String carryImages;

    public String getCarryImages() {
        return carryImages;
    }

    public void setCarryImages(String carryImages) {
        this.carryImages = carryImages;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    private Map<String,String> params;
    public Map<String, String> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }


    public Long getUserIdP() {
        return userIdP;
    }

    public void setUserIdP(Long userIdP) {
        this.userIdP = userIdP;
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

    public String getShopName() {
        return shopName;
    }



    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setAcctTaskId(Long acctTaskId) {
            this.acctTaskId = acctTaskId;
    }

    public Long getAcctTaskId() {
            return acctTaskId;
    }
    public void setShopIdP(Long shopIdP) {
            this.shopIdP = shopIdP;
    }

    public Long getShopIdP() {
            return shopIdP;
    }
    public void setTaskTime(Date taskTime) {
            this.taskTime = taskTime;
    }

    public Date getTaskTime() {
            return taskTime;
    }
    public void setTaskAmount(BigDecimal taskAmount) {
            this.taskAmount = taskAmount;
    }

    public BigDecimal getTaskAmount() {
            return taskAmount;
    }
    public void setTrueAmount(BigDecimal trueAmount) {
            this.trueAmount = trueAmount;
    }

    public BigDecimal getTrueAmount() {
            return trueAmount;
    }
    public void setTaskType(Integer taskType) {
            this.taskType = taskType;
    }

    public Integer getTaskType() {
            return taskType;
    }
    public void setBankName(String bankName) {
            this.bankName = bankName;
    }

    public String getBankName() {
            return bankName;
    }
    public void setBankNumber(String bankNumber) {
            this.bankNumber = bankNumber;
    }

    public String getBankNumber() {
            return bankNumber;
    }
    public void setBankAcct(String bankAcct) {
            this.bankAcct = bankAcct;
    }

    public String getBankAcct() {
            return bankAcct;
    }
    public void setAlipayAcct(String alipayAcct) {
            this.alipayAcct = alipayAcct;
    }

    public String getAlipayAcct() {
            return alipayAcct;
    }
    public void setAlipayName(String alipayName) {
            this.alipayName = alipayName;
    }

    public String getAlipayName() {
            return alipayName;
    }
    public void setWxAcct(String wxAcct) {
            this.wxAcct = wxAcct;
    }

    public String getWxAcct() {
            return wxAcct;
    }
    public void setWxName(String wxName) {
            this.wxName = wxName;
    }

    public String getWxName() {
            return wxName;
    }
    public void setTaskFlag(Integer taskFlag) {
            this.taskFlag = taskFlag;
    }

    public Integer getTaskFlag() {
            return taskFlag;
    }
    public void setAuditBy(String auditBy) {
            this.auditBy = auditBy;
    }

    public String getAuditBy() {
            return auditBy;
    }
    public void setAuditTime(Date auditTime) {
            this.auditTime = auditTime;
    }

    public Date getAuditTime() {
            return auditTime;
    }
    public void setCreateTime(Date createTime) {
            this.createTime = createTime;
    }

    public Date getCreateTime() {
            return createTime;
    }
    public void setCreateBy(String createBy) {
            this.createBy = createBy;
    }

    public String getCreateBy() {
            return createBy;
    }

    public Integer getPayTaskType() {
        return payTaskType;
    }

    public void setPayTaskType(Integer payTaskType) {
        this.payTaskType = payTaskType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("acctTaskId",getAcctTaskId())
                .append("shopIdP",getShopIdP())
                .append("taskTime",getTaskTime())
                .append("taskAmount",getTaskAmount())
                .append("trueAmount",getTrueAmount())
                .append("taskType",getTaskType())
                .append("bankName",getBankName())
                .append("bankNumber",getBankNumber())
                .append("bankAcct",getBankAcct())
                .append("alipayAcct",getAlipayAcct())
                .append("alipayName",getAlipayName())
                .append("wxAcct",getWxAcct())
                .append("wxName",getWxName())
                .append("taskFlag",getTaskFlag())
                .append("auditBy",getAuditBy())
                .append("auditTime",getAuditTime())
                .append("createTime",getCreateTime())
                .append("createBy",getCreateBy())
                .append("getPayTaskType",getPayTaskType())
                .append("getCarryImages",getCarryImages())
                .toString();
    }
}
