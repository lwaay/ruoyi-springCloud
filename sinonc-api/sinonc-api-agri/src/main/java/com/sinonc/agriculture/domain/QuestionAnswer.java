package com.sinonc.agriculture.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 问题回答对象 question_answer
 *
 * @author ruoyi
 * @date 2020-03-10
 */
public class QuestionAnswer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**
     * null
     */
    @ApiModelProperty(hidden = true)
    private Long answerId;

    /** 会员昵称 */
    @Excel(name = "会员昵称")
    @ApiModelProperty(hidden = true)
    private String nikeName;

    /** null */
    @Excel(name = "null")
    @ApiModelProperty(hidden = true)
    private Long memberId;

    /** 问题ID */
    @Excel(name = "问题ID")
    @ApiModelProperty(required = true,value = "问题ID")
    private Long questionId;

    /** 评论内容 */
    @Excel(name = "评论内容")
    @ApiModelProperty(required = true ,value = "评论内容")
    private String content;

    /** 图片 */
    @Excel(name = "图片")
    @ApiModelProperty(required = true ,value = "图片")
    private String img;

    /** 点赞数 */
    @Excel(name = "点赞数")
    @ApiModelProperty(hidden = true)
    private Long favorCount;

    /** 反对数 */
    @Excel(name = "反对数")
    @ApiModelProperty(hidden = true)
    private Long opposeCount;

    /** 所在地行政区划编码 */
    @Excel(name = "所在地行政区划编码")
    @ApiModelProperty(required = true ,value = "所在地行政区划编码")
    private String areaCode;

    /** 所在地行政区划名称 */
    @Excel(name = "所在地行政区划名称")
    @ApiModelProperty(required = true ,value = "所在地行政区划名称")
    private String areaName;

    /** 最佳答案标记，0，非，1，是 */
    @Excel(name = "最佳答案标记，0，非，1，是")
    @ApiModelProperty(hidden = true)
    private Integer bestFlag;

    /** 回答问题的系统名称 */
    @Excel(name = "回答问题的系统名称")
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private String sysName;

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setFavorCount(Long favorCount) {
        this.favorCount = favorCount;
    }

    public Long getFavorCount() {
        return favorCount;
    }

    public void setOpposeCount(Long opposeCount) {
        this.opposeCount = opposeCount;
    }

    public Long getOpposeCount() {
        return opposeCount;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setBestFlag(Integer bestFlag) {
        this.bestFlag = bestFlag;
    }

    public Integer getBestFlag() {
        return bestFlag;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getSysName() {
        return sysName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("answerId", getAnswerId())
            .append("nikeName", getNikeName())
            .append("memberId", getMemberId())
            .append("questionId", getQuestionId())
            .append("content", getContent())
            .append("img", getImg())
            .append("favorCount", getFavorCount())
            .append("opposeCount", getOpposeCount())
            .append("areaCode", getAreaCode())
            .append("areaName", getAreaName())
            .append("bestFlag", getBestFlag())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("sysName", getSysName())
            .toString();
    }
}
