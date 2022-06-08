package com.sinonc.orders.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @anthor wang
 */
@Data
public class SendDto {

    @NotNull(message = "pls send orderNo by refund")
    private Long orderIdP;
    @NotNull(message = "pls send rfAmount by refund")
    private Long shopIdP;
    @NotNull(message = "pls send reason by refund")
    private String expressName;
    @NotNull(message = "pls send reason by refund")
    private String expressNo;
    @NotNull(message = "pls send memberIdP by refund")
    private Long memberIdP;
    @NotNull(message = "发货类型没有指定")
    private Long expressBy;

}
