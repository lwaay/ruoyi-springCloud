package com.sinonc.orders.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * 规格属性对象 od_specs_value
 *
 * @author ruoyi
 * @date 2022-03-25
 */
@Data
public class OdSpecsValue extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键自增
     */
    private Long specsValueId;

    /**
     * 规格id
     */
    @Excel(name = "规格id")
    private Long specsId;

    /**
     * 规格属性值
     */
    @Excel(name = "规格属性值")
    private String specsValue;

    /**
     * 数量
     */
    @Excel(name = "数量")
    private Integer numbers;

    /**
     * 单位
     */
    @Excel(name = "单位")
    private String unit;

    /**
     * 配送类型：0，物品配送，1，其他
     */
    @Excel(name = "配送类型：0，物品配送，1，其他")
    private Integer type;

    /**
     * 店铺ID
     */
    @Excel(name = "店铺ID")
    private Long shopId;

}
