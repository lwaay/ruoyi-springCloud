package com.sinonc.orders.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * 商品品牌对象 od_brand
 *
 * @author ruoyi
 * @date 2022-03-25
 */
@Data
public class OdBrand extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id自增
     */
    private Long brandId;

    /**
     * 品牌名称
     */
    @Excel(name = "品牌名称")
    private String brandName;

    /**
     * 品牌图片
     */
    @Excel(name = "品牌图片")
    private String image;

}
