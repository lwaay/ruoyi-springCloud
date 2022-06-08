package com.sinonc.agriculture.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 政策新闻评论回复对象 policynews_comment
 *
 * @author ruoyi
 * @date 2020-05-18
 */
public class PolicynewsComment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long replyId;

    /** 政策新闻主键ID */
    @Excel(name = "政策新闻主键ID")
    private Long policynewsIdP;

    /** 用户id */
    @Excel(name = "用户id")
    private Long memberIdP;

    /** 回复内容 */
    @Excel(name = "回复内容")
    private String replyContent;

    /** 回复上级ID */
    @Excel(name = "回复上级ID")
    private Long replyMemberIdP;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createUser;

    public void setReplyId(Long replyId)
    {
        this.replyId = replyId;
    }

    public Long getReplyId()
    {
        return replyId;
    }
    public void setPolicynewsIdP(Long policynewsIdP)
    {
        this.policynewsIdP = policynewsIdP;
    }

    public Long getPolicynewsIdP()
    {
        return policynewsIdP;
    }
    public void setMemberIdP(Long memberIdP)
    {
        this.memberIdP = memberIdP;
    }

    public Long getMemberIdP()
    {
        return memberIdP;
    }
    public void setReplyContent(String replyContent)
    {
        this.replyContent = replyContent;
    }

    public String getReplyContent()
    {
        return replyContent;
    }
    public void setReplyMemberIdP(Long replyMemberIdP)
    {
        this.replyMemberIdP = replyMemberIdP;
    }

    public Long getReplyMemberIdP()
    {
        return replyMemberIdP;
    }
    public void setCreateUser(String createUser)
    {
        this.createUser = createUser;
    }

    public String getCreateUser()
    {
        return createUser;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("replyId", getReplyId())
            .append("policynewsIdP", getPolicynewsIdP())
            .append("memberIdP", getMemberIdP())
            .append("replyContent", getReplyContent())
            .append("replyMemberIdP", getReplyMemberIdP())
            .append("createTime", getCreateTime())
            .append("createUser", getCreateUser())
            .toString();
    }
}
