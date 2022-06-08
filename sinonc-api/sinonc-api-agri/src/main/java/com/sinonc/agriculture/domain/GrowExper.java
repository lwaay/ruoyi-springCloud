package com.sinonc.agriculture.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 会员种植经验对象 grow_exper
 *
 * @author ruoyi
 * @date 2020-04-01
 */
public class GrowExper extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long growexperId;

    /**
     * 会员ID
     */
    @Excel(name = "会员ID")
    private Long memberIdP;

    /**
     * 作物ID
     */
    @Excel(name = "作物ID")
    private Long cropIdP;

    /**
     * 作物名称
     */
    @Excel(name = "作物名称")
    private String cropName;

    /**
     * 经验值（年）
     */
    @Excel(name = "经验值", readConverterExp = "年=")
    private Integer experYear;

    public void setGrowexperId(Long growexperId) {
        this.growexperId = growexperId;
    }

    public Long getGrowexperId() {
        return growexperId;
    }

    public void setMemberIdP(Long memberIdP) {
        this.memberIdP = memberIdP;
    }

    public Long getMemberIdP() {
        return memberIdP;
    }

    public void setCropIdP(Long cropIdP) {
        this.cropIdP = cropIdP;
    }

    public Long getCropIdP() {
        return cropIdP;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public String getCropName() {
        return cropName;
    }

    public void setExperYear(Integer experYear) {
        this.experYear = experYear;
    }

    public Integer getExperYear() {
        return experYear;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("growexperId", getGrowexperId())
                .append("memberIdP", getMemberIdP())
                .append("cropIdP", getCropIdP())
                .append("cropName", getCropName())
                .append("experYear", getExperYear())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
