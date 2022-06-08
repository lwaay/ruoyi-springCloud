package com.sinonc.agriculture.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 专家评分对象 accpet_score
 *
 * @author ruoyi
 * @date 2020-07-15
 */
public class AccpetScore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 专家主键 */
    @Excel(name = "专家主键")
    private Long expertId;

    /** 专家姓名 */
    @Excel(name = "专家姓名")
    private String expertName;

    /** 评论用户ID */
    @Excel(name = "评论用户ID")
    private Long memberId;

    /** 平均分 */
    @Excel(name = "平均分")
    private String scoreValue;

    /** 服务态度得分 */
    @Excel(name = "服务态度得分")
    private String mannerScore;

    /** 响应速度得分 */
    @Excel(name = "响应速度得分")
    private String speedScore;

    /** 技术能力得分 */
    @Excel(name = "技术能力得分")
    private String abilityScore;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setExpertId(Long expertId)
    {
        this.expertId = expertId;
    }

    public Long getExpertId()
    {
        return expertId;
    }
    public void setMemberId(Long memberId)
    {
        this.memberId = memberId;
    }

    public Long getMemberId()
    {
        return memberId;
    }
    public void setScoreValue(String scoreValue)
    {
        this.scoreValue = scoreValue;
    }

    public String getScoreValue()
    {
        return scoreValue;
    }
    public void setMannerScore(String mannerScore)
    {
        this.mannerScore = mannerScore;
    }

    public String getMannerScore()
    {
        return mannerScore;
    }
    public void setSpeedScore(String speedScore)
    {
        this.speedScore = speedScore;
    }

    public String getSpeedScore()
    {
        return speedScore;
    }
    public void setAbilityScore(String abilityScore)
    {
        this.abilityScore = abilityScore;
    }

    public String getAbilityScore()
    {
        return abilityScore;
    }

    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("expertId", getExpertId())
            .append("memberId", getMemberId())
            .append("scoreValue", getScoreValue())
            .append("mannerScore", getMannerScore())
            .append("speedScore", getSpeedScore())
            .append("abilityScore", getAbilityScore())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .toString();
    }
}
