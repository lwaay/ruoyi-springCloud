package com.sinonc.orders.domain;

import java.math.BigDecimal;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * 商品对象 od_goods
 *
 * @author ruoyi
 * @date 2022-04-01
 */
@Data
public class OdGoods extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id自增
     */
    private Long goodsId;

    /**
     * 品牌id
     */
    @Excel(name = "品牌id")
    private Long brandId;

    /**
     * 店铺id
     */
    @Excel(name = "店铺id")
    private Long shopId;

    /** 商品分类id */
    @Excel(name = "商品分类id")
    private Long categoryId;

    /**
     * 基地id,果园
     */
    @Excel(name = "基地id,果园")
    private Long farmId;

    /**
     * 果树id
     */
    @Excel(name = "果树id")
    private Long fruId;

    /**
     * 运费模板主键
     */
    @Excel(name = "运费模板主键")
    private Long fareIdP;

    /** 溯源产品ID */
    @Excel(name = "溯源产品ID")
    private Long productId;

    /**
     * 商品名称
     */
    @Excel(name = "商品名称")
    private String name;

    /**
     * 商品图片
     */
    @Excel(name = "商品图片")
    private String image;

    /**
     * 商品视频
     */
    @Excel(name = "商品视频")
    private String video;

    /**
     * 商品类型:0,认养产品;1,普通产品;2,预订商品
     */
    @Excel(name = "商品类型:0,认养产品;1,普通产品;2,预订商品")
    private Integer type;

    /**
     * 商品图文描述
     */
    @Excel(name = "商品图文描述")
    private String content;

    /**
     * 上下架：0上架 1下架
     */
    @Excel(name = "上下架：0上架 1下架")
    private Integer saleAble;

    /**
     * 商品折扣（用于展示，非实际折扣）
     */
    @Excel(name = "商品折扣", readConverterExp = "用=于展示，非实际折扣")
    private String discount;

    /**
     * 折扣价（用于展示，非实际价格）
     */
    @Excel(name = "折扣价", readConverterExp = "用=于展示，非实际价格")
    private BigDecimal finalPrice;

    /**
     * 商品原价（用于展示，非实际价格）
     */
    @Excel(name = "商品原价", readConverterExp = "用=于展示，非实际价格")
    private BigDecimal costPrice;

    /**
     * 规格id，若存在多个规格，则用逗号分隔
     */
    @Excel(name = "规格id，若存在多个规格，则用逗号分隔")
    private String specsIds;

    /**
     * 0为未推荐，1为已推荐
     */
    @Excel(name = "0为未推荐，1为已推荐")
    private String isRecommend;

    /**
     * 删除标志 0 正常  1 已删除
     */
    private String delFlag;

    /**
     * 限定查询多少条
     */
    private Integer limitsize;

    private Long sumGoodsCount;

}
