package com.sinonc.agriculture.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 专家动态对象 expert_dynamic
 *
 * @author ruoyi
 * @date 2020-03-31
 */
public class ExpertDynamic extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 动态ID
     */
    @ApiModelProperty(name = "动态ID，新增不需要，更新必填")
    private Long dynamicId;

    /**
     * 作物ID
     */
    @NotNull(message = "cropId不能为空")
    @ApiModelProperty(required = true)
    private Long cropId;

    /**
     * 板块ID
     */
    @NotNull(message = "sectionId不能为空")
    @ApiModelProperty(required = true)
    private Long sectionId;

    /**
     * 会员ID
     */
    @Excel(name = "会员ID")
    @ApiModelProperty(hidden = true)
    private Long memberId;

    /**
     * 动态标题
     */
    @Excel(name = "动态标题")
    @ApiModelProperty(required = true)
    @NotEmpty(message = "动态标题不能为空")
    private String title;

    /**
     * 动态内容
     */
    @Excel(name = "动态内容")
    @ApiModelProperty(required = true)
    @NotEmpty(message = "动态内容不能为空")
    private String content;

    /**
     * 阅读数
     */
    @Excel(name = "阅读数")
    @ApiModelProperty(hidden = true)
    private Long readCount;

    /**
     * 图片地址，分割
     */
    @Excel(name = "图片地址，分割")
    private String img;

    /**
     * 点赞数
     */
    @Excel(name = "点赞数")
    @ApiModelProperty(hidden = true)
    private Long likeCount;

    /**
     * 评论数
     */
    @Excel(name = "评论数")
    @ApiModelProperty(hidden = true)
    private Long commentCount;

    /**
     * 来源系统名称
     */
    @Excel(name = "来源系统名称")
    @ApiModelProperty(required = true)
    @NotEmpty(message = "sysName 不能为空")
    private String sysName;

    public void setDynamicId(Long dynamicId) {
        this.dynamicId = dynamicId;
    }

    public Long getDynamicId() {
        return dynamicId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setReadCount(Long readCount) {
        this.readCount = readCount;
    }

    public Long getReadCount() {
        return readCount;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getSysName() {
        return sysName;
    }

    public Long getCropId() {
        return cropId;
    }

    public void setCropId(Long cropId) {
        this.cropId = cropId;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("dynamicId", getDynamicId())
                .append("memberId", getMemberId())
                .append("title", getTitle())
                .append("content", getContent())
                .append("readCount", getReadCount())
                .append("img", getImg())
                .append("likeCount", getLikeCount())
                .append("commentCount", getCommentCount())
                .append("sysName", getSysName())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
