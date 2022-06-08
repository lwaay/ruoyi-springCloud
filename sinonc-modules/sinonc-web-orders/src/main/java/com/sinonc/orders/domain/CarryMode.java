package com.sinonc.orders.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;


/**
 * 运送方式表 od_carry_mode
 *
 * @author sinonc
 * @date 2019-11-27
 */

public class CarryMode {

    private static final long serialVersionUID = 1L;

    /** 自增id */
    private Long carrymodelId;
    /** 运费模板id */
    private Long fareId;
    /** 运送地区 */
    private String regionAddr;
    /** 首件数量 */
    private Integer firstPiece;
    /** 首重重量 */
    private BigDecimal firstWeight;
    /** 首费 */
    private BigDecimal firstAmount;
    /** 续件数量 */
    private Integer secondPiece;
    /** 续重重量 */
    private BigDecimal secondWeight;
    /** 续费 */
    private BigDecimal secondAmount;
    /** 运送方式（顺丰，圆通等） */
    private String carryWay;
    /** 是否默认运送方式 */
    private Integer isDefault;

    public void setCarrymodelId(Long carrymodelId) {
            this.carrymodelId = carrymodelId;
    }

    public Long getCarrymodelId() {
            return carrymodelId;
    }
    public void setFareId(Long fareId) {
            this.fareId = fareId;
    }

    public Long getFareId() {
            return fareId;
    }
    public void setRegionAddr(String regionAddr) {
            this.regionAddr = regionAddr;
    }

    public String getRegionAddr() {
            return regionAddr;
    }
    public void setFirstPiece(Integer firstPiece) {
            this.firstPiece = firstPiece;
    }

    public Integer getFirstPiece() {
            return firstPiece;
    }
    public void setFirstWeight(BigDecimal firstWeight) {
            this.firstWeight = firstWeight;
    }

    public BigDecimal getFirstWeight() {
            return firstWeight;
    }
    public void setFirstAmount(BigDecimal firstAmount) {
            this.firstAmount = firstAmount;
    }

    public BigDecimal getFirstAmount() {
            return firstAmount;
    }
    public void setSecondPiece(Integer secondPiece) {
            this.secondPiece = secondPiece;
    }

    public Integer getSecondPiece() {
            return secondPiece;
    }
    public void setSecondWeight(BigDecimal secondWeight) {
            this.secondWeight = secondWeight;
    }

    public BigDecimal getSecondWeight() {
            return secondWeight;
    }
    public void setSecondAmount(BigDecimal secondAmount) {
            this.secondAmount = secondAmount;
    }

    public BigDecimal getSecondAmount() {
            return secondAmount;
    }
    public void setCarryWay(String carryWay) {
            this.carryWay = carryWay;
    }

    public String getCarryWay() {
            return carryWay;
    }
    public void setIsDefault(Integer isDefault) {
            this.isDefault = isDefault;
    }

    public Integer getIsDefault() {
            return isDefault;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("carrymodelId",getCarrymodelId())
                .append("fareId",getFareId())
                .append("regionAddr",getRegionAddr())
                .append("firstPiece",getFirstPiece())
                .append("firstWeight",getFirstWeight())
                .append("firstAmount",getFirstAmount())
                .append("secondPiece",getSecondPiece())
                .append("secondWeight",getSecondWeight())
                .append("secondAmount",getSecondAmount())
                .append("carryWay",getCarryWay())
                .append("isDefault",getIsDefault())
                .toString();
    }
}
