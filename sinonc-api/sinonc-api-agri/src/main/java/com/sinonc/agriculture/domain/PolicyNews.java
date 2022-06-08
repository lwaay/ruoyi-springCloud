package com.sinonc.agriculture.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 政策新闻对象 policy_news
 *
 * @author zhang.xl
 * @date 2020-03-05
 */
public class PolicyNews extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long newsId;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 简介 */
    @Excel(name = "简介")
    private String introduction;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 主图 */
    private String mainImg;

    /** 阅读量 */
    @Excel(name = "阅读量")
    private Long readCount;

    /** 图文详情 */
    private String content;

    /**
     * 发布时间
     */
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date issueTime;

    /**
     * 用户名称
     */
    @Excel(name = "用户名称")
    private String userName;

    /**
     * 新闻类型
     **/
    private String newsType;

    /**
     * 所属系统
     */
    private String sysName;

    private List pictures;

    /**
     * 签名
     */
    private String sign;

    /**
     * 时间戳
     */
    private Date timestamp;

    /**
     * 发布日期格式化
     */
    private String issueTimeString;

    public String getIssueTimeString() {
        return issueTimeString;
    }

    public void setIssueTimeString(String issueTimeString) {
        this.issueTimeString = issueTimeString;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public List getPictures() {
        return pictures;
    }

    public void setPictures(List pictures) {
        this.pictures = pictures;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setNewsId(Long newsId)
    {
        this.newsId = newsId;
    }

    public Long getNewsId()
    {
        return newsId;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setIntroduction(String introduction)
    {
        this.introduction = introduction;
    }

    public String getIntroduction()
    {
        return introduction;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setMainImg(String mainImg)
    {
        this.mainImg = mainImg;
    }

    public String getMainImg()
    {
        return mainImg;
    }
    public void setReadCount(Long readCount)
    {
        this.readCount = readCount;
    }

    public Long getReadCount()
    {
        return readCount;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setIssueTime(Date issueTime)
    {
        this.issueTime = issueTime;
    }

    public Date getIssueTime()
    {
        return issueTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("newsId", getNewsId())
            .append("title", getTitle())
            .append("introduction", getIntroduction())
            .append("userId", getUserId())
            .append("mainImg", getMainImg())
            .append("readCount", getReadCount())
            .append("content", getContent())
            .append("issueTime", getIssueTime())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
