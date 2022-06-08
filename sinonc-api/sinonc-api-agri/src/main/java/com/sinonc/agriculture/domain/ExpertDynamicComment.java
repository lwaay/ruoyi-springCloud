package com.sinonc.agriculture.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 专家动态评论对象 expert_dynamic_comment
 *
 * @author ruoyi
 * @date 2020-03-31
 */
public class ExpertDynamicComment extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 评论ID
     */
    @ApiModelProperty(hidden = true)
    private Long commentId;

    /**
     * 动态ID
     */
    @ApiModelProperty(required = true)
    @NotNull(message = "动态id不能为空")
    private Long dynamicId;

    /**
     * 评论内容
     */
    @Excel(name = "评论内容")
    @ApiModelProperty(required = true)
    @NotEmpty(message = "content不能为空")
    private String content;

    /**
     * 会员ID
     */
    @ApiModelProperty(hidden = true)
    @Excel(name = "会员ID")
    private Long memberId;

    @ApiModelProperty(hidden = true)
    @Excel(name = "会员昵称")
    private String nikeName;

    /**
     * 回复指定评论的ID
     */
    @Excel(name = "回复指定评论的ID")
    private Long replyTo;

    /**
     * 系统名称
     */
    @Excel(name = "系统名称")
    private String sysName;

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public Long getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(Long dynamicId) {
        this.dynamicId = dynamicId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setReplyTo(Long replyTo) {
        this.replyTo = replyTo;
    }

    public Long getReplyTo() {
        return replyTo;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getSysName() {
        return sysName;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("commentId", getCommentId())
                .append("content", getContent())
                .append("memberId", getMemberId())
                .append("replyTo", getReplyTo())
                .append("createTime", getCreateTime())
                .append("sysName", getSysName())
                .toString();
    }
}
