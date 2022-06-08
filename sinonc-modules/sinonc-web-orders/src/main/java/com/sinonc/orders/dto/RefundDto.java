package com.sinonc.orders.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @anthor wang
 */
@Data
public class RefundDto {

    @NotNull(message = "pls send orderNo by refund")
    private String orderNo;
    @NotNull(message = "pls send rfAmount by refund")
    private BigDecimal rfAmount;
    @NotNull(message = "pls send reason by refund")
    private String reason;

}
