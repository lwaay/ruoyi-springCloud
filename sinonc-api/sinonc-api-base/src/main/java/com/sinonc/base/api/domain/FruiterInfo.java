package com.sinonc.base.api.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import lombok.Builder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 果树信息对象 fruiter_info
 *
 * @author ruoyi
 * @date 2022-02-21
 */
public class FruiterInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long fruId;

    /**
     * 果园主键
     */
    @Excel(name = "果园主键 ")
    private Long orchId;

    /**
     * 果树名称
     */
    @Excel(name = "果树名称")
    private String fruName;

    /**
     * 种植品种
     */
    @Excel(name = "种植品种")
    private String fruType;

    /**
     * 种植株数（株）
     */
    @Excel(name = "种植株数", readConverterExp = "株=")
    private Integer plantNumber;

    /**
     * 种植面积（亩）
     */
    @Excel(name = "种植面积", readConverterExp = "亩=")
    private BigDecimal plantArea;

    /**
     * 果树图片，多个,隔开
     */
    @Excel(name = "果树图片，多个,隔开")
    private String fruPic;

    /**
     * 种植年份
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "种植年份", width = 30, dateFormat = "yyyy-MM-dd")
    private Date plantYear;

    public void setFruId(Long fruId) {
        this.fruId = fruId;
    }

    public Long getFruId() {
        return fruId;
    }

    public void setOrchId(Long orchId) {
        this.orchId = orchId;
    }

    public Long getOrchId() {
        return orchId;
    }

    public void setFruName(String fruName) {
        this.fruName = fruName;
    }

    public String getFruName() {
        return fruName;
    }

    public void setFruType(String fruType) {
        this.fruType = fruType;
    }

    public String getFruType() {
        return fruType;
    }

    public void setPlantNumber(Integer plantNumber) {
        this.plantNumber = plantNumber;
    }

    public Integer getPlantNumber() {
        return plantNumber;
    }

    public void setPlantArea(BigDecimal plantArea) {
        this.plantArea = plantArea;
    }

    public BigDecimal getPlantArea() {
        return plantArea;
    }

    public void setFruPic(String fruPic) {
        this.fruPic = fruPic;
    }

    public String getFruPic() {
        return fruPic;
    }

    public void setPlantYear(Date plantYear) {
        this.plantYear = plantYear;
    }

    public Date getPlantYear() {
        return plantYear;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("fruId", getFruId())
                .append("orchId", getOrchId())
                .append("fruName", getFruName())
                .append("fruType", getFruType())
                .append("plantNumber", getPlantNumber())
                .append("plantArea", getPlantArea())
                .append("fruPic", getFruPic())
                .append("plantYear", getPlantYear())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
