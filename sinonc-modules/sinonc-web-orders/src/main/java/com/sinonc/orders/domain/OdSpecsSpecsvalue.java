package com.sinonc.orders.domain;


import java.util.Date;

/**
 * 规格_规格值关联表 od_specs_specsvalue
 *
 * @author sinonc
 * @date 2019-08-07
 */

public class OdSpecsSpecsvalue {

    private static final long serialVersionUID = 1L;

    /** 主键id自增 */
    private Long specsValueId;
    /** 规格id */
    private Long specsIdP;
    /** 规格值id */
    private Long specsValueIdp;
    /** 创建时间 */
    private Date createTime;
    /** 创建人 */
    private String createBy;

    private Integer number;

    public void setSpecsValueId(Long specsValueId) {
            this.specsValueId = specsValueId;
    }

    public Long getSpecsValueId() {
            return specsValueId;
    }
    public void setSpecsIdP(Long specsIdP) {
            this.specsIdP = specsIdP;
    }

    public Long getSpecsIdP() {
            return specsIdP;
    }
    public void setSpecsValueIdp(Long specsValueIdp) {
            this.specsValueIdp = specsValueIdp;
    }

    public Long getSpecsValueIdp() {
            return specsValueIdp;
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "OdSpecsSpecsvalue{" +
                "specsValueId=" + specsValueId +
                ", specsIdP=" + specsIdP +
                ", specsValueIdp=" + specsValueIdp +
                ", createTime=" + createTime +
                ", createBy='" + createBy + '\'' +
                ", number=" + number +
                '}';
    }
}
