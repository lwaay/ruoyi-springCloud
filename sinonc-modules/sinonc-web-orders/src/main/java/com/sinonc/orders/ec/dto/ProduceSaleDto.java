package com.sinonc.orders.ec.dto;

import lombok.Data;

/**
 * @author zhangxinlong
 * @date 2021/8/11  17:41
 */
@Data
public class ProduceSaleDto {

    /**
     * 日期
     */
    private String date;

    /**
     * 统计值
     */
    private Double stvalue;

}
