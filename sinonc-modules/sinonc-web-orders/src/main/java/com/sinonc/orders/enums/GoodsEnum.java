package com.sinonc.orders.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * @author: lw
 * @date: 2022/4/8 9:49
 * @description:
 */
@Getter
@AllArgsConstructor
public enum GoodsEnum {
    TYPE_1("认养商品",0),
    TYPE_2("普通商品",1),
    TYPE_3("预订商品",2),
    TYPE_4("竞拍商品",3);

    private String name;
    private Integer value;

    public static GoodsEnum getGoodsEnum(Integer value){
        return Stream.of(GoodsEnum.values())
                .filter(p -> p.value.equals(value))
                .findAny()
                .orElse(null);
    }

}
