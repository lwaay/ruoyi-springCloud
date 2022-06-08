package com.sinonc.orders.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 运费模板表 od_fare_template
 *
 * @author sinonc
 * @date 2019-11-27
 */

public class FareTemplate {

    private static final long serialVersionUID = 1L;

    /** 主键自增 */
    private Long fareId;
    /** 模板名称 */
    private String name;
    /** 店铺id */
    private Long shopId;
    /** 发货时间 */
    @JsonFormat(pattern = "yyyy年MM月dd日",timezone = "GMT+8")
    private Date dispatchTime;
    /** 是否包邮 */
    private Integer isInclpostage;
    /** 计价方式(1:按件 2:按重量) */
    private Integer valuaTionmodel;
    /** 创建时间 */
    private Date createTime;
    /** 修改时间 */
    private Date updateTime;
    /**规格ID**/
    private String specsId;

    public String getSpecsId() {
        return specsId;
    }

    public void setSpecsId(String specsId) {
        this.specsId = specsId;
    }

    public void setFareId(Long fareId) {
            this.fareId = fareId;
    }

    public Long getFareId() {
            return fareId;
    }
    public void setName(String name) {
            this.name = name;
    }

    public String getName() {
            return name;
    }
    public void setShopId(Long shopId) {
            this.shopId = shopId;
    }

    public Long getShopId() {
            return shopId;
    }
    public void setDispatchTime(Date dispatchTime) {
            this.dispatchTime = dispatchTime;
    }

    public Date getDispatchTime() {
            return dispatchTime;
    }
    public void setIsInclpostage(Integer isInclpostage) {
            this.isInclpostage = isInclpostage;
    }

    public Integer getIsInclpostage() {
            return isInclpostage;
    }
    public void setValuaTionmodel(Integer valuaTionmodel) {
            this.valuaTionmodel = valuaTionmodel;
    }

    public Integer getValuaTionmodel() {
            return valuaTionmodel;
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
                .append("fareId",getFareId())
                .append("name",getName())
                .append("shopId",getShopId())
                .append("dispatchTime",getDispatchTime())
                .append("isInclpostage",getIsInclpostage())
                .append("valuaTionmodel",getValuaTionmodel())
                .append("createTime",getCreateTime())
                .append("updateTime",getUpdateTime())
                .toString();
    }
}
