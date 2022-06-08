package com.sinonc.agriculture.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 我的动态对象 own_dynamic
 *
 * @author ruoyi
 * @date 2020-03-24
 */
public class OwnDynamic extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 动态主键
     */
    private Long owndynaId;

    /**
     * 会员ID
     */
    @Excel(name = "会员ID")
    private Long memberIdP;

    /**
     * 目标表主键
     */
    @Excel(name = "目标表主键")
    private Long targetId;

    /**
     * 类型，1、提问的问题  2、关注的问题 3、回答的问题 4、养殖技术评论
     */
    @Excel(name = "类型，1、提问的问题  2、关注的问题 3、回答的问题 4、养殖技术评论")
    private Integer type;

    /**
     * 动态内容,json数据
     */
    @Excel(name = "动态内容,json数据")
    private String dynaContent;

    public void setOwndynaId(Long owndynaId) {
        this.owndynaId = owndynaId;
    }

    public Long getOwndynaId() {
        return owndynaId;
    }

    public void setMemberIdP(Long memberIdP) {
        this.memberIdP = memberIdP;
    }

    public Long getMemberIdP() {
        return memberIdP;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setDynaContent(String dynaContent) {
        this.dynaContent = dynaContent;
    }

    public String getDynaContent() {
        return dynaContent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("owndynaId", getOwndynaId())
                .append("memberIdP", getMemberIdP())
                .append("targetId", getTargetId())
                .append("type", getType())
                .append("dynaContent", getDynaContent())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
