package com.sinonc.agriculture.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 农业问题对象 agri_question
 *
 * @author ruoyi
 * @date 2020-03-07
 */
public class AgriQuestion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    @ApiModelProperty(hidden = true)
    private Long questionId;

    /** 会员ID */
    @ApiModelProperty(hidden = true)
    private Long memberId;

    /** 作物ID */
    @ApiModelProperty(required = true)
    @Excel(name = "作物ID")
    private Long cropId;

    /** 版块ID */
    @ApiModelProperty(required = true)
    @NotNull(message = "版块ID不能为空")
    @Excel(name = "版块ID")
    private Long sectionId;

    /** 问题 */
    @NotEmpty(message = "问题内容不能为空")
    @Excel(name = "问题")
    @ApiModelProperty(required = true)
    private String ask;

    @Excel(name = "问题描述")
    private String describe;

    /** 图片 */
    @Excel(name = "图片")
    private String img;

    /** 行政区划编码 */
    @Excel(name = "行政区划编码")
    private String areaCode;

    /** 行政区划名称 */
    @Excel(name = "行政区划名称")
    private String areaName;

    /** 阅读数 */
    @Excel(name = "阅读数")
    @ApiModelProperty(hidden = true)
    private Integer readCount;

    @Excel(name = "回答数")
    @ApiModelProperty(hidden = true)
    private Integer answerCount;

    /** 问题状态 */
    @Excel(name = "问题状态")
    @ApiModelProperty(hidden = true)
    private Integer status;

    /** 删除标记 */
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Integer delFlag;

    /** 提问的系统名称 */
    @Excel(name = "提问的系统名称")
    @ApiModelProperty(required = true)
    private String sysName;

    @Excel(name = "最后回答时间")
    @ApiModelProperty(hidden = true)
    private Date lastAnswerTime;

    public void setQuestionId(Long questionId)
    {
        this.questionId = questionId;
    }

    public Long getQuestionId()
    {
        return questionId;
    }
    public void setMemberId(Long memberId)
    {
        this.memberId = memberId;
    }

    public Long getMemberId()
    {
        return memberId;
    }
    public void setCropId(Long cropId)
    {
        this.cropId = cropId;
    }

    public Long getCropId()
    {
        return cropId;
    }
    public void setSectionId(Long sectionId)
    {
        this.sectionId = sectionId;
    }

    public Long getSectionId()
    {
        return sectionId;
    }
    public void setAsk(String ask)
    {
        this.ask = ask;
    }

    public String getAsk()
    {
        return ask;
    }
    public void setImg(String img)
    {
        this.img = img;
    }

    public String getImg()
    {
        return img;
    }
    public void setAreaCode(String areaCode)
    {
        this.areaCode = areaCode;
    }

    public String getAreaCode()
    {
        return areaCode;
    }
    public void setAreaName(String areaName)
    {
        this.areaName = areaName;
    }

    public String getAreaName()
    {
        return areaName;
    }
    public void setReadCount(Integer readCount)
    {
        this.readCount = readCount;
    }

    public Integer getReadCount()
    {
        return readCount;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }
    public void setDelFlag(Integer delFlag)
    {
        this.delFlag = delFlag;
    }

    public Integer getDelFlag()
    {
        return delFlag;
    }
    public void setSysName(String sysName)
    {
        this.sysName = sysName;
    }

    public String getSysName()
    {
        return sysName;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Integer getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(Integer answerCount) {
        this.answerCount = answerCount;
    }

    public Date getLastAnswerTime() {
        return lastAnswerTime;
    }

    public void setLastAnswerTime(Date lastAnswerTime) {
        this.lastAnswerTime = lastAnswerTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("questionId", getQuestionId())
            .append("memberId", getMemberId())
            .append("cropId", getCropId())
            .append("sectionId", getSectionId())
            .append("ask", getAsk())
            .append("img", getImg())
            .append("areaCode", getAreaCode())
            .append("areaName", getAreaName())
            .append("readCount", getReadCount())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("sysName", getSysName())
            .append("describe", getDescribe())
            .append("answerCount", getAnswerCount())
            .append("lastAnswerTime", getLastAnswerTime())
            .toString();
    }
}
