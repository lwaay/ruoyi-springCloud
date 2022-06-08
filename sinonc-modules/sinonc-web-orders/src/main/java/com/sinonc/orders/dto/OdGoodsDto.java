package com.sinonc.orders.dto;

import com.sinonc.order.api.domain.Shop;
import com.sinonc.orders.domain.OdGoods;
import com.sinonc.orders.domain.Specs;
import lombok.Data;

import java.util.List;

/**
 * @author zhangxinlong
 * @apiNote TODO
 * @date 2022-04-06  16:42
 */
@Data
public class OdGoodsDto extends OdGoods {

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 店铺名称
     */
    private String shopName;


    /**
     * 销量
     */
    private Long salesVolume=0L;

    /**
     * 规格
     */
    private List<Specs> specsList;

    /**
     * 是否收藏(0-是,1-否)
     */
    private String isAttention;

    /**
     * 店铺
     */
    Shop shop;
}
