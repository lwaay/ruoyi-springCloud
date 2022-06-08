package com.sinonc.agriculture.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 会员字典对象 member_dict
 *
 * @author ruoyi
 * @date 2020-03-12
 */
public class MemberDict extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 字典id */
    private Long dictId;

    /** 会员ID */
    @Excel(name = "会员ID")
    private Long memberId;

    /** 字典类型 */
    @Excel(name = "字典类型")
    private String dictType;

    /** 字典值 */
    @Excel(name = "字典值")
    private String dictValue;

    public void setDictId(Long dictId)
    {
        this.dictId = dictId;
    }

    public Long getDictId()
    {
        return dictId;
    }
    public void setMemberId(Long memberId)
    {
        this.memberId = memberId;
    }

    public Long getMemberId()
    {
        return memberId;
    }
    public void setDictType(String dictType)
    {
        this.dictType = dictType;
    }

    public String getDictType()
    {
        return dictType;
    }
    public void setDictValue(String dictValue)
    {
        this.dictValue = dictValue;
    }

    public String getDictValue()
    {
        return dictValue;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("dictId", getDictId())
            .append("memberId", getMemberId())
            .append("dictType", getDictType())
            .append("dictValue", getDictValue())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
