package com.sinonc.orders.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SettlementDto<T> {

    private Long tradeId;
    /**
     * 结算目标对象
     */
    private T target;

    /**
     * 目标对象的实收款
     */
    private BigDecimal receiptAmount;
}
