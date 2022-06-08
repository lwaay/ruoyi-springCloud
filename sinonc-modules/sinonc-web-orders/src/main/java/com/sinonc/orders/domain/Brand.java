package com.sinonc.orders.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * 商品品牌表 od_brand
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Alias("Brand")
public class Brand {

    private static final long serialVersionUID = 1L;

    /** 主键id自增 */
    private Long brandId;
    /** 品牌名称 */
    private String brandName;
    /** 品牌描述 */
    private String remark;
    /** 品牌图片 */
    private String image;
    /** 用户id */
    private String createBy;
    /** 创建时间 */
    private Date createTime;
    /** 修改时间 */
    private Date updateTime;

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
    public void setRemark(String remark) {
            this.remark = remark;
    }

    public String getRemark() {
            return remark;
    }
    public void setImage(String image) {
            this.image = image;
    }

    public String getImage() {
            return image;
    }
    public void setCreateBy(String createBy) {
            this.createBy = createBy;
    }

    public String getCreateBy() {
            return createBy;
    }
    public void setCreateTime(Date createTime) {
            this.createTime = createTime;
    }

    public Date getCreateTime() {
            return createTime;
    }
    public void setUpdateTime(Date updateTime) {
            this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
            return updateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("brandId",getBrandId())
                .append("brandName",getBrandName())
                .append("remark",getRemark())
                .append("image",getImage())
                .append("createBy",getCreateBy())
                .append("createTime",getCreateTime())
                .append("updateTime",getUpdateTime())
                .toString();
    }
}
