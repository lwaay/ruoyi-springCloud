package com.sinonc.orders.ec.schedule;

import lombok.Data;

/**
 * @author huanghao
 * @apiNote 时间表达式
 * @date 2021/3/11 15:08
 */
@Data
public class Cron {
    /**
     * cron type == 1 是 每一天执行一次
     */
    public static final Integer DEFAULT_REPTILE = 1;
    private Long id;

    private String cron;

    private Integer type;

    private String description;
}
