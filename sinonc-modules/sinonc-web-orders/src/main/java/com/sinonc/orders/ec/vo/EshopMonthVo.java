package com.sinonc.orders.ec.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author huanghao
 * @apiNote 电商月销售统计
 * @date 2020/8/15 11:41
 */
@Data
@ApiModel("电商月销售统计")
public class EshopMonthVo {

    /**
     * 电商平台类型
     */
    private String name;

    /**
     * 销售额
     */
    private Double sellPrice;

    /**
     * 销售数量
     */
    private Double sellCount;

    /**
     * 店铺数量
     */
    private Integer shopCount;

    /**
     * 最新月份
     */
    private String month;
}
