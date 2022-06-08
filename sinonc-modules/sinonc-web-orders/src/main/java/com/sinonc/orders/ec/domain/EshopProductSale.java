package com.sinonc.orders.ec.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author huanghao
 * @apiNote 电商实时数据
 * @date 2021/3/16 14:18
 */
@Data
public class EshopProductSale {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long goodsId;
    /**
     * 实时月销量，商品被拍下后计数
     */
    private Integer sale;
    /**
     * 交易成功销量，商品交易成功后计数
     */
    private Integer cout;
    /**
     * 剩余库存
     */
    private Integer stock;

    private String goodsStore;

    private String goodsType;

    private Integer goodsOrigin;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    private EshopProduct eshopProduct;
}
