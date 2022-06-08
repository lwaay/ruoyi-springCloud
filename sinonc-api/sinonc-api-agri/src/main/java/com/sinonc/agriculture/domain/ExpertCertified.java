package com.sinonc.agriculture.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 专家资格证明对象 expert_certified
 *
 * @author ruoyi
 * @date 2020-04-03
 */
public class ExpertCertified extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 资格证明ID
     */
    @ApiModelProperty(hidden = true)
    private Long certifiedId;

    /**
     * 专家ID
     */
    @ApiModelProperty(hidden = true)
    @Excel(name = "专家ID")
    private Long expertId;

    /**
     * 会员ID
     */
    @Excel(name = "会员ID")
    @ApiModelProperty(hidden = true)
    private Long memberId;

    /**
     * 资格证名称
     */
    @Excel(name = "资格证名称")
    private String certifiedName;

    /**
     * 资格证图片
     */
    @ApiModelProperty(required = true)
    @Excel(name = "资格证图片")
    private String certifiedImg;

    public void setCertifiedId(Long certifiedId) {
        this.certifiedId = certifiedId;
    }

    public Long getCertifiedId() {
        return certifiedId;
    }

    public void setExpertId(Long expertId) {
        this.expertId = expertId;
    }

    public Long getExpertId() {
        return expertId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setCertifiedName(String certifiedName) {
        this.certifiedName = certifiedName;
    }

    public String getCertifiedName() {
        return certifiedName;
    }

    public void setCertifiedImg(String certifiedImg) {
        this.certifiedImg = certifiedImg;
    }

    public String getCertifiedImg() {
        return certifiedImg;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("certifiedId", getCertifiedId())
                .append("expertId", getExpertId())
                .append("memberId", getMemberId())
                .append("certifiedName", getCertifiedName())
                .append("certifiedImg", getCertifiedImg())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
