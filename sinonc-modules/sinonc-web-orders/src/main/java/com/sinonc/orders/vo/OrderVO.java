package com.sinonc.orders.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sinonc.orders.domain.Address;
import com.sinonc.orders.domain.Express;
import com.sinonc.orders.domain.Order;
import com.sinonc.orders.domain.TradeInfo;
import com.sinonc.system.api.domain.WxUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * 订单视图对象，包含订单相关信息
 */
@Data
@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderVO<T> extends Order implements Serializable {

    /**
     * 收货地址ID
     */
    @NotNull(message = "收货地址ID不能为空")
    @ApiModelProperty(value = "收货地址ID", required = true)
    private Long addressId;

    //会员ID
    @NotNull(message = "会员ID不能为空")
    @ApiModelProperty(value = "会员ID", required = true)
    private Long memberId;

    //优惠券ID
    @ApiModelProperty(value = "优惠券ID")
    private Long couponId;

    //商品列表
    @NotNull(message = "商品ID不能为空")
    @ApiModelProperty(value = "商品列表", required = true)
    private ArrayList<T> goodsVos;

    @ApiModelProperty(value = "买家")
    private WxUser buyer;

    //店铺ID
    @NotNull(message = "店铺ID不能为空")
    @ApiModelProperty(value = "店铺ID", required = true)
    private Long shopId;

    @ApiModelProperty(value = "店铺名称", required = false, hidden = true)
    private String shopName;

    @ApiModelProperty(value = "店铺头像", required = false, hidden = true)
    private String shopHead;

    @ApiModelProperty(value = "店铺手机号", required = false, hidden = true)
    private String shopMobile;

    @ApiModelProperty(value = "定金", required = false, hidden = true)
    private String earnest;

    @ApiModelProperty(value = "定金支付时间", required = false, hidden = true)
    private Date earnestTime;

    @ApiModelProperty(value = "定金支付方式", required = false, hidden = true)
    private String etPayType;

    @ApiModelProperty(value = "尾款",required = false,hidden = true)
    private String restPrice;

    @ApiModelProperty(value = "尾款支付时间", required = false, hidden = true)
    private Date restPriceTime;

    @ApiModelProperty(value = "尾款支付方式", required = false, hidden = true)
    private String restPayType;

    //备注信息
    @ApiModelProperty(value = "备注信息")
    private String remark;

    @NotNull(message = "订单类型不能为空")
    @ApiModelProperty(value = "订单类型：0，认养订单，1，交易订单,2,预订订单  3,竞拍订单", required = true)
    private Integer type;

    //运费信息
    private Express express;
    //支付信息
    private TradeInfo tradeInfo;
    //收货地址
    private Address addr;
}
