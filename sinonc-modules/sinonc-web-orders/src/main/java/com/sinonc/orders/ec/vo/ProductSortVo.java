package com.sinonc.orders.ec.vo;


import lombok.Data;

import java.util.List;

/**
 * @author huanghao
 * @apiNote 按条件排序
 * @date 2020/8/15 11:33
 */
@Data
public class ProductSortVo {
    /**
     * 1 销售量 2 品牌 3 订单数
     */
    private String key = "1";
    /**
     * 日期 例如：2020-03
     */
//    @NotNull(message = "日期不能为空")
    private String date;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 品类id集
     */
    private Long[] typeIds;

    /**
     * 字符集合
     */
    private String[] typeList;

    /**
     * 商品品类字符串
     */
    private List<String> typeNames;

    /**
     * 维度：4商品，5店铺
     */
    private String dimension;

    /**
     * 是否过滤产地为梁平
     */
    private String isFilter;

    /**
     * 品类id
     */
    private Long typeId;
}
