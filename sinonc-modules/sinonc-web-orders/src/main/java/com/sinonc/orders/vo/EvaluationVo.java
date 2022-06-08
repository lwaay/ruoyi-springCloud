package com.sinonc.orders.vo;

import lombok.Data;

/**
 * @author: lw
 * @date: 2022/4/7 9:55
 * @description:
 */
@Data
public class EvaluationVo {

    private Long orderId;
    private String content;
    private String images;
    private Integer goodsStar;
    private Integer expressStar;
    private Integer serviceStar;
}
