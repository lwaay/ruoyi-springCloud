package com.sinonc.origins.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 朔源商品点赞对象 product_like
 *
 * @author ruoyi
 * @date 2020-10-21
 */
public class ProductLike extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 产品id
     */
    @Excel(name = "产品id")
    private Long productIdP;

    /**
     * 产品名
     */
    @Excel(name = "产品名")
    private String productName;

    /**
     * 点赞数
     */
    @Excel(name = "点赞数")
    private Long likeNum;

    public void setProductIdP(Long productIdP) {
        this.productIdP = productIdP;
    }

    public Long getProductIdP() {
        return productIdP;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setLikeNum(Long likeNum) {
        this.likeNum = likeNum;
    }

    public Long getLikeNum() {
        return likeNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("productIdP", getProductIdP())
                .append("productName", getProductName())
                .append("likeNum", getLikeNum())
                .append("createTime", getCreateTime())
                .toString();
    }
}
