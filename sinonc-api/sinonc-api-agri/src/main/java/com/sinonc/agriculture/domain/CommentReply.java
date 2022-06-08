package com.sinonc.agriculture.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 评论回复对象 comment_reply
 *
 * @author ruoyi
 * @date 2020-03-17
 */
public class CommentReply extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    /** 评论主键ID */
    private Long growtechIdP;

    /** 用户id */
    private Long memberIdP;

    /**会员名称**/
    private String memberName;

    /** 回复内容 */
    @Excel(name = "回复内容")
    private String replyContent;

    /** 回复用户ID */
    private Long replyMemberIdP;

    /**回复会员名称**/
    private String replyMemberName;

    /** 创建人 */
    private String createUser;
    /** 主键ID */
    private Long replyId;


    public Long getGrowtechIdP() {
        return growtechIdP;
    }

    public void setGrowtechIdP(Long growtechIdP) {
        this.growtechIdP = growtechIdP;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getReplyMemberName() {
        return replyMemberName;
    }

    public void setReplyMemberName(String replyMemberName) {
        this.replyMemberName = replyMemberName;
    }

    public void setReplyId(Long replyId)
    {
        this.replyId = replyId;
    }

    public Long getReplyId()
    {
        return replyId;
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
            .append("memberIdP", getMemberIdP())
            .append("replyContent", getReplyContent())
            .append("replyMemberIdP", getReplyMemberIdP())
            .append("createTime", getCreateTime())
            .append("createUser", getCreateUser())
            .toString();
    }
}
