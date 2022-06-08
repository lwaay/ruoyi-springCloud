package com.sinonc.orders.domain;

import com.sinonc.order.api.domain.Shop;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/3/29 17:42
 */
@Data
public class AccountTaskStatisticData implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 提现次数 */
    private int crashOutCount;

    /** 当日统计订单金额 */
    private double payAccountNow;

    /** 昨日统计访问量 */
    private long yesterdayVisitCount;

    /** 成功提现次数 */
    private int crashOutSuccessCount;

    /** 成功提现总金额 */
    private BigDecimal crashOutSuccessAmount;

    /** 可用金额金额 */
    private BigDecimal availAmount;

    /** 总金额 */
    private BigDecimal totalAmount;

    /** 店铺信息 */
    private Shop shop;
}
