package com.sinonc.agriculture.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 会员关注信息对象 concern_info
 *
 * @author ruoyi
 * @date 2020-03-06
 */
public class ConcernInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 关注ID */
    private Long concernId;

    /** 被关注的目标，在目标表中的ID */
    @Excel(name = "被关注的目标，在目标表中的ID")
    private Long targetId;

    /** 关注的类型，0，会员；1，问题；2，话题 */
    @Excel(name = "关注的类型，0，会员；1，问题；2，话题")
    private Integer targetType;

    /** 会员ID */
    @Excel(name = "会员ID")
    private Long memberId;

    /** 用户名(服务版) */
    private String memberName;

    public void setConcernId(Long concernId)
    {
        this.concernId = concernId;
    }

    public Long getConcernId()
    {
        return concernId;
    }
    public void setTargetId(Long targetId)
    {
        this.targetId = targetId;
    }

    public Long getTargetId()
    {
        return targetId;
    }
    public void setTargetType(Integer targetType)
    {
        this.targetType = targetType;
    }

    public Integer getTargetType()
    {
        return targetType;
    }
    public void setMemberId(Long memberId)
    {
        this.memberId = memberId;
    }

    public Long getMemberId()
    {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("concernId", getConcernId())
            .append("targetId", getTargetId())
            .append("targetType", getTargetType())
            .append("memberId", getMemberId())
            .append("createTime", getCreateTime())
            .toString();
    }
}
