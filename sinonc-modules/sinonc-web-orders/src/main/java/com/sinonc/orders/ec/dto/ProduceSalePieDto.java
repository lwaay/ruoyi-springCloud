package com.sinonc.orders.ec.dto;

import lombok.Data;

/**
 * @author zhangxinlong
 * @date 2021/8/12  9:06
 */
@Data
public class ProduceSalePieDto {

    /**
     * 类型
     */
    private String produceType;

    /**
     * 统计值
     */
    private Double stvalue;
}
