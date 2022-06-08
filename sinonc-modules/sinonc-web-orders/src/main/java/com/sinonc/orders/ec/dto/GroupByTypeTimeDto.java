package com.sinonc.orders.ec.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author huanghao
 * @apiNote 按品类，时间 查询结果
 * @date 2020/8/15 16:06
 */
@Data
public class GroupByTypeTimeDto {
    /**
     * 品类
     */
    private String type;
    /**
     * 销售额
     */
    private BigDecimal salePrice;
    /**
     * 月份
     */
    private String month;
}
