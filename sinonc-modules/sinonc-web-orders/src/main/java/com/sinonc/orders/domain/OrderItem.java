package com.sinonc.orders.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单项目表 od_order_item
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Data
@Alias("OrderItem")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItem {

    private static final long serialVersionUID = 1L;

    /**  */
    private Long orderItemId;
    /** 订单ID */
    private Long orderIdP;
    /** 商品ID */
    private Long goodsIdP;
    /** 商品名称 */
    private String goodsName;
    /** 商品缩略图 */
    private String goodsImg;
    /** 商品类型，0，认养商品；1，普通商品 */
    private Integer goodsType;
    /**  */
    private Long goodsSpecsIdP;
    /** 规格名称 */
    private String goodsSpecsName;
    /** 商品单价 */
    private BigDecimal goodsPrice;
    /** 商品数量 */
    private Integer goodsCount;
    /** 店铺商品折扣价格 */
    private BigDecimal goodsShopDiscountPrice;
    /** 平台商品折扣价格 */
    private BigDecimal goodsPlatformDiscountPrice;
    /** 商品单品实际总价 */
    private BigDecimal goodsTotalPrice;
    /** 发生退款操作时，退款记录id */
    private Long goodsRefundIdP;
    /** 订单中商品的状态，0，正常，2，退款中，3，退款成功，4，退款拒绝 */
    private Integer goodsOrderStatus;
    /** 用户id */
    private Long memberIdP;
    /** 店铺ID */
    private Long shopIdP;
    /**  */
    private Date createTime;
    /**  */
    private Date updateTime;
    /**订单状态**/
    private Integer orderStatus;
    /**每份重量**/
    private BigDecimal perWeight;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("orderItemId",getOrderItemId())
                .append("orderIdP",getOrderIdP())
                .append("goodsIdP",getGoodsIdP())
                .append("goodsName",getGoodsName())
                .append("goodsImg",getGoodsImg())
                .append("goodsType",getGoodsType())
                .append("goodsSpecsIdP",getGoodsSpecsIdP())
                .append("goodsSpecsName",getGoodsSpecsName())
                .append("goodsPrice",getGoodsPrice())
                .append("goodsCount",getGoodsCount())
                .append("goodsShopDiscountPrice",getGoodsShopDiscountPrice())
                .append("goodsPlatformDiscountPrice",getGoodsPlatformDiscountPrice())
                .append("goodsTotalPrice",getGoodsTotalPrice())
                .append("goodsRefundIdP",getGoodsRefundIdP())
                .append("goodsOrderStatus",getGoodsOrderStatus())
                .append("memberIdP",getMemberIdP())
                .append("shopIdP",getShopIdP())
                .append("createTime",getCreateTime())
                .append("updateTime",getUpdateTime())
                .toString();
    }
}
