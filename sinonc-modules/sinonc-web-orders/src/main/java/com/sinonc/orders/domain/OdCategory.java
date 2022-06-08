package com.sinonc.orders.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * 商品分类对象 od_category
 *
 * @author ruoyi
 * @date 2022-04-06
 */
@Data
public class OdCategory extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long categoryId;

    /**
     * 分类名称
     */
    @Excel(name = "分类名称")
    private String categoryName;

    /**
     * 父级分类id
     */
    @Excel(name = "父级分类id")
    private Long parentId;

}
