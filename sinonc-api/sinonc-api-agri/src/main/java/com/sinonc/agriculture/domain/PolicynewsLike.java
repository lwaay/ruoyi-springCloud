package com.sinonc.agriculture.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 政策信息点赞对象 policynews_like
 *
 * @author ruoyi
 * @date 2020-05-18
 */
public class PolicynewsLike extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 点赞ID */
    private Long likeId;

    /** 政策新闻id */
    @Excel(name = "政策新闻id")
    private Long newsIdP;

    /** 会员ID */
    @Excel(name = "会员ID")
    private Long memberIdP;

    public void setLikeId(Long likeId)
    {
        this.likeId = likeId;
    }

    public Long getLikeId()
    {
        return likeId;
    }
    public void setNewsIdP(Long newsIdP)
    {
        this.newsIdP = newsIdP;
    }

    public Long getNewsIdP()
    {
        return newsIdP;
    }
    public void setMemberIdP(Long memberIdP)
    {
        this.memberIdP = memberIdP;
    }

    public Long getMemberIdP()
    {
        return memberIdP;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("likeId", getLikeId())
            .append("newsIdP", getNewsIdP())
            .append("memberIdP", getMemberIdP())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
