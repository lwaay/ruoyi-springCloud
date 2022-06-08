package com.sinonc.orders.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 商品和预订活动表 od_book_goods
 *
 * @author sinonc
 * @date 2019-09-25
 */

public class BookGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long bgId;
    /** 商品ID */
    private Long goodsId;
    /** 店铺ID */
    private Long shopId;
    /** 定金金额 */
    private BigDecimal earnestPrice;
    /** 预订开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date startTime;
    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date endTime;
    /** 创建时间 */
    private Date createTime;
    /** 创建者 */
    private String createBy;
    /** 更新时间 */
    private Date updateTime;
    /** 更新者 */
    private String updateBy;

    /**预订活动状态*/
    private Integer status;
    /** 删除标记 */
    private String delFlag;

    private Map<String, String> params;

    public Map<String, String> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }


    public void setBgId(Long bgId) {
            this.bgId = bgId;
    }

    public Long getBgId() {
            return bgId;
    }
    public void setGoodsId(Long goodsId) {
            this.goodsId = goodsId;
    }

    public Long getGoodsId() {
            return goodsId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public void setEarnestPrice(BigDecimal earnestPrice) {
            this.earnestPrice = earnestPrice;
    }

    public BigDecimal getEarnestPrice() {
            return earnestPrice;
    }
    public void setStartTime(Date startTime) {
            this.startTime = startTime;
    }

    public Date getStartTime() {
            return startTime;
    }
    public void setEndTime(Date endTime) {
            this.endTime = endTime;
    }

    public Date getEndTime() {
            return endTime;
    }
    public void setCreateTime(Date createTime) {
            this.createTime = createTime;
    }

    public Date getCreateTime() {
            return createTime;
    }
    public void setCreateBy(String createBy) {
            this.createBy = createBy;
    }

    public String getCreateBy() {
            return createBy;
    }
    public void setUpdateTime(Date updateTime) {
            this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
            return updateTime;
    }
    public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
    }

    public String getUpdateBy() {
            return updateBy;
    }
    public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDelFlag() {
            return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("bgId",getBgId())
                .append("goodsId",getGoodsId())
                .append("shopId",getShopId())
                .append("earnestPrice",getEarnestPrice())
                .append("startTime",getStartTime())
                .append("endTime",getEndTime())
                .append("createTime",getCreateTime())
                .append("createBy",getCreateBy())
                .append("updateTime",getUpdateTime())
                .append("updateBy",getUpdateBy())
                .append("delFlag",getDelFlag())
                .toString();
    }
}
