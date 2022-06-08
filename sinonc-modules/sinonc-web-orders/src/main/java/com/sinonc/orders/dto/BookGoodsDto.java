package com.sinonc.orders.dto;

import com.sinonc.orders.domain.Auction;
import com.sinonc.orders.domain.BookGoods;
import com.sinonc.orders.domain.OdGoods;
import com.sinonc.orders.domain.Specs;
import lombok.Data;

import java.util.List;

/**
 * @author: lw
 * @date: 2022/4/7 14:27
 * @description:
 */
@Data
public class BookGoodsDto {

    private OdGoods odGoods;

    /**
     * 规格
     */
    private List<Specs> specsList;

    /**
     * 预订商品
     */
    private BookGoods bookGoods;

    /**
     * 竞拍商品
     */
    private Auction auction;

}
