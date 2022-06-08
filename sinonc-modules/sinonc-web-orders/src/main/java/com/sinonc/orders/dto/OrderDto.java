package com.sinonc.orders.dto;

import com.sinonc.orders.domain.Order;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @anthor wang
 */
@Data
public class OrderDto extends Order {
    //查询时间
    private String searchDate;

    //主体id
    private Long entityId;
}
