package com.sinonc.agriculture.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 种养殖技术点赞对象 growtech_like
 *
 * @author zhang.xl
 * @date 2020-03-07
 */
public class GrowtechLike extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 点赞ID */
    private Long likeId;

    /** 种养殖技术id */
    @Excel(name = "种养殖技术id")
    private Long growtechIdP;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("likeId", getLikeId())
            .append("growtechIdP", getGrowtechIdP())
            .append("memberIdP", getMemberIdP())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
