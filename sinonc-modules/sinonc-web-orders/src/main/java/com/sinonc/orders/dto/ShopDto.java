package com.sinonc.orders.dto;

import com.sinonc.order.api.domain.Shop;
import com.sinonc.orders.domain.OdGoods;
import io.swagger.models.auth.In;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zhangxinlong
 * @apiNote TODO
 * @date 2022-04-06  20:18
 */
@Data
public class ShopDto extends Shop {


    /**
     * 商品数量
     */
    private Integer odGoodCount;
    /**
     * 直供
     */
    List<OdGoods> odGoodsList;

    /**
     * 认养
     */
    List<OdGoods> ryOdGoodsList;

    /**
     * 预定
     */
    List<OdGoods> ydOdGoodsList;

    /**
     * 热销
     */
    List<OdGoods> rxOdGoodsList;

    /**
     * 门店关注
     */
    private Long  attenQua=0L;

    /**
     * 是否关注(0-是,1-否)
     */
    private String isAttention;

    /**
     * 门店访问数量
     */
    private Long  visitQua=0L;

    private BigDecimal grade=new BigDecimal("95.10");

}
