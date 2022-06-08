package com.sinonc.agriculture.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 专家动态点赞对象 expert_dynamic_like
 *
 * @author ruoyi
 * @date 2020-03-31
 */
public class ExpertDynamicLike extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long likeId;

    /**
     * 会员ID
     */
    @Excel(name = "会员ID")
    private Long memberId;

    /**
     * 专家动态ID
     */
    @Excel(name = "专家动态ID")
    private Long dynamicId;

    public void setLikeId(Long likeId) {
        this.likeId = likeId;
    }

    public Long getLikeId() {
        return likeId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setDynamicId(Long dynamicId) {
        this.dynamicId = dynamicId;
    }

    public Long getDynamicId() {
        return dynamicId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("likeId", getLikeId())
                .append("memberId", getMemberId())
                .append("dynamicId", getDynamicId())
                .append("createTime", getCreateTime())
                .toString();
    }
}
