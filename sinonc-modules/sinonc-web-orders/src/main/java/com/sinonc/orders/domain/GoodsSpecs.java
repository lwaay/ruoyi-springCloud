package com.sinonc.orders.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * 商品规格表 od_goods_specs
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Alias("GoodsSpecs")
public class GoodsSpecs {

    private static final long serialVersionUID = 1L;

    /** id主键自增 */
    private Long goodsSpecId;
    /** 商品id */
    private Long goodsIdP;
    /** 规格id */
    private Long specsIdP;
    /** 用户id */
    private String createBy;
    /** 创建时间 */
    private Date createTime;
    /** 修改时间 */
    private Date updateTime;

    public void setGoodsSpecId(Long goodsSpecId) {
            this.goodsSpecId = goodsSpecId;
    }

    public Long getGoodsSpecId() {
            return goodsSpecId;
    }
    public void setGoodsIdP(Long goodsIdP) {
            this.goodsIdP = goodsIdP;
    }

    public Long getGoodsIdP() {
            return goodsIdP;
    }
    public void setSpecsIdP(Long specsIdP) {
            this.specsIdP = specsIdP;
    }

    public Long getSpecsIdP() {
            return specsIdP;
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
                .append("goodsSpecId",getGoodsSpecId())
                .append("goodsIdP",getGoodsIdP())
                .append("specsIdP",getSpecsIdP())
                .append("createBy",getCreateBy())
                .append("createTime",getCreateTime())
                .append("updateTime",getUpdateTime())
                .toString();
    }
}
