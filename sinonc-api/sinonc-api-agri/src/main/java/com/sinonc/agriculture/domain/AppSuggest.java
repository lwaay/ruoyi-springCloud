package com.sinonc.agriculture.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 意见反馈对象 app_suggest
 *
 * @author ruoyi
 * @date 2020-03-12
 */
public class AppSuggest extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long suggestId;

    /** 用户id */
    @Excel(name = "用户id")
    @NotNull(message = "用户类型不能为空")
    private Long memberId;

    /** 意见类型：1 UI错误 2显示问题 3运行报错 4其他 */
    @Excel(name = "意见类型：1 UI错误 2显示问题 3运行报错 4其他")
    @NotEmpty(message = "意见类型不能为空")
    private String suggestType;

    /** 意见内容 */
    @Excel(name = "意见内容")
    @NotEmpty(message = "意见内容不能为空")
    private String suggestCotent;

    /** 截图 */
    @Excel(name = "截图")
    private String imageUrl;

    /** 状态:1未处理 2已处理 */
    @Excel(name = "状态:1未处理 2已处理")
    private String suggestStatus;

    /** 回复内容 */
    @Excel(name = "回复内容")
    private String dealWith;

    /** 回复时间 */
    @Excel(name = "回复时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dealTime;

    public void setSuggestId(Long suggestId)
    {
        this.suggestId = suggestId;
    }

    public Long getSuggestId()
    {
        return suggestId;
    }
    public void setMemberId(Long memberId)
    {
        this.memberId = memberId;
    }

    public Long getMemberId()
    {
        return memberId;
    }
    public void setSuggestType(String suggestType)
    {
        this.suggestType = suggestType;
    }

    public String getSuggestType()
    {
        return suggestType;
    }
    public void setSuggestCotent(String suggestCotent)
    {
        this.suggestCotent = suggestCotent;
    }

    public String getSuggestCotent()
    {
        return suggestCotent;
    }
    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }
    public void setSuggestStatus(String suggestStatus)
    {
        this.suggestStatus = suggestStatus;
    }

    public String getSuggestStatus()
    {
        return suggestStatus;
    }
    public void setDealWith(String dealWith)
    {
        this.dealWith = dealWith;
    }

    public String getDealWith()
    {
        return dealWith;
    }
    public void setDealTime(Date dealTime)
    {
        this.dealTime = dealTime;
    }

    public Date getDealTime()
    {
        return dealTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("suggestId", getSuggestId())
            .append("memberId", getMemberId())
            .append("suggestType", getSuggestType())
            .append("suggestCotent", getSuggestCotent())
            .append("imageUrl", getImageUrl())
            .append("suggestStatus", getSuggestStatus())
            .append("dealWith", getDealWith())
            .append("createTime", getCreateTime())
            .append("dealTime", getDealTime())
            .toString();
    }
}
