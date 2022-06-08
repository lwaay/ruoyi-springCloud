package com.sinonc.origins.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;

/**
 * 商品品牌对象 pro_brand
 *
 * @author ruoyi
 * @date 2020-10-23
 */
public class ProBrand extends BaseEntity {
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

    /**
     * 品牌描述
     */
    @Excel(name = "品牌描述")
    private String remark;

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

}
