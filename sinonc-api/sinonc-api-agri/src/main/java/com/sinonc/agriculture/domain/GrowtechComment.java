package com.sinonc.agriculture.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 养殖技术评论对象 growtech_comment
 *
 * @author zhang.xl
 * @date 2020-03-09
 */
public class GrowtechComment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id自增 */
    private Long commentId;

    /** 养殖技术id */
    @Excel(name = "养殖技术id")
    private Long growtechIdP;

    /** 会员id */
    @Excel(name = "会员id")
    private Long memberIdP;

    /** 回复id */
    @Excel(name = "回复id")
    private Long replyId;

    /** 评论内容 */
    @Excel(name = "评论内容")
    private String content;

    public void setCommentId(Long commentId)
    {
        this.commentId = commentId;
    }

    public Long getCommentId()
    {
        return commentId;
    }
    public void setGrowtechIdP(Long growtechIdP)
    {
        this.growtechIdP = growtechIdP;
    }

    public Long getGrowtechIdP()
    {
        return growtechIdP;
    }
    public void setMemberIdP(Long memberIdP)
    {
        this.memberIdP = memberIdP;
    }

    public Long getMemberIdP()
    {
        return memberIdP;
    }
    public void setReplyId(Long replyId)
    {
        this.replyId = replyId;
    }

    public Long getReplyId()
    {
        return replyId;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("commentId", getCommentId())
                .append("growtechIdP", getGrowtechIdP())
                .append("memberIdP", getMemberIdP())
                .append("replyId", getReplyId())
                .append("content", getContent())
                .append("createTime", getCreateTime())
                .toString();
    }
}
