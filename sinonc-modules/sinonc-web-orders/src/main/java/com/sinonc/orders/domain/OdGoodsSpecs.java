package com.sinonc.orders.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * 商品规格对象 od_goods_specs
 *
 * @author ruoyi
 * @date 2022-03-23
 */
@Data
public class OdGoodsSpecs extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id主键自增
     */
    private Long goodsSpecId;

    /**
     * 商品id
     */
    @Excel(name = "商品id")
    private Long goodsIdP;

    /**
     * 规格id
     */
    @Excel(name = "规格id")
    private Long specsIdP;

}
