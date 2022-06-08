package com.sinonc.orders.ec.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author huanghao
 * @apiNote 实时数据大屏
 * @date 2021/3/23 11:21
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RealTimeStatisticVo {
    private BigDecimal todaySales;
    private BigDecimal yesterdaySalesRate;
    private Long todayOrders;
    private BigDecimal yesterdayOrdersRate;
}
