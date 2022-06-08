package com.sinonc.orders.ec.vo;

import lombok.Data;

/**
 * @author zhangxinlong
 * @date 2021/8/11  17:37
 */
@Data
public class FarmProduceSaleVo {

    /**
     * 品类
     */
    private String produceType;

    /**
     * 开始时间
     */
    private String beginDate;

    /**
     * 结束时间
     */
    private String endDate;

    /**
     * 查询类型   1 按单价   2 销量  3 收入
     */
    private String queryType;

}
