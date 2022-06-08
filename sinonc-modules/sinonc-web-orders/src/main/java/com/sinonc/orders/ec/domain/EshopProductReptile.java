package com.sinonc.orders.ec.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author huanghao
 * @apiNote 定时爬取商品配置
 * @date 2021/3/22 15:26
 */
@Data
@Builder
public class EshopProductReptile {
    private Long id;
    private Long goodsId;
    private Long cronId;
    private String goodsStore;
    private String goodsType;
    private Integer goodsOrigin;
    private Integer status;
    private Date createTime;
    private Integer cronType;
}
