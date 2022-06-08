package com.sinonc.orders.vo;

import com.sinonc.orders.domain.Address;
import com.sinonc.orders.domain.OrderItem;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 运费计算视图用
 * @anthor wang
 */
@Data
public class FareVo  implements Serializable {

    private List<OrderItem> items;

    private Long addressId;

    private Address address;
}
