package com.sinonc.orders.dto;

import com.sinonc.orders.domain.Auction;
import com.sinonc.orders.domain.BookGoods;
import com.sinonc.orders.domain.Goods;
import com.sinonc.orders.domain.Specs;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 后台普通商品表单提交数据对象
 */
@Getter
@Setter
public class GoodsDto {
    /**
     * 商品
     */
    private Goods goods;

    /**
     * 规格
     */
    private List<Specs> specsList;

    /**
     * 预订信息
     */
    private BookGoods book;

    /**
     * 竞拍商品
     */
    private Auction auction;

}
