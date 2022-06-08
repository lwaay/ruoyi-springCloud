package com.sinonc.orders.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.sinonc.orders.domain.SpecsValue;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 商品规格详情，获取规格详情和规格值内容
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SpecsDetailVo implements Serializable {
    private Long specsId;
    private String specsName;
    private BigDecimal specsPrice;
    private BigDecimal perWeight;
    private Integer stock;
    private Integer minCount;
    private Integer maxCount;
    private BigDecimal earnest;
    private String unit;
    private String images;
    private List<SpecsValue> specsValue;
}
