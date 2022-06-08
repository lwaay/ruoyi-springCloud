package com.sinonc.agriculture.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 种养殖技术对象 grow_tech
 *
 * @author zhang.xl
 * @date 2020-03-06
 */
public class GrowTech extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long growId;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 简介 */
    private String introduction;

    /** 类型：Video 视频;Image 图文 */
    @Excel(name = "类型：Video 视频;Image 图文")
    private String shapeType;

    @Excel(name = "文章类型")
    private String techType;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 主图地址 */
    private String mainImg;

    /** 阅读量 */
    @Excel(name = "阅读量")
    private Long readCount;

    /** 分享次数 */
    @Excel(name = "分享次数")
    private Long shareCount;

    /** 视频地址 */
    private String videoUrl;

    /** 图文详情 */
    private String content;

    /** 发布时间 */
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date issueTime;

    /** 作物字典表值 */
    @Excel(name = "作物字典表值")
    private String cropsDictvalue;

    /**
     * 版块字典表值
     */
    @Excel(name = "版块字典表值")
    private String columnDictvalue;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 所属系统
     */
    private String sysName;

    public String getTechType() {
        return techType;
    }

    public void setTechType(String techType) {
        this.techType = techType;
    }

    public Long getShareCount() {
        return shareCount;
    }

    public void setShareCount(Long shareCount) {
        this.shareCount = shareCount;
    }

    public Long getParentCropId() {
        return parentCropId;
    }

    public void setParentCropId(Long parentCropId) {
        this.parentCropId = parentCropId;
    }

    private Long parentCropId;

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setGrowId(Long growId)
    {
        this.growId = growId;
    }

    public Long getGrowId()
    {
        return growId;
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
    public void setShapeType(String shapeType)
    {
        this.shapeType = shapeType;
    }

    public String getShapeType()
    {
        return shapeType;
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
    public void setVideoUrl(String videoUrl)
    {
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl()
    {
        return videoUrl;
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
    public void setCropsDictvalue(String cropsDictvalue)
    {
        this.cropsDictvalue = cropsDictvalue;
    }

    public String getCropsDictvalue()
    {
        return cropsDictvalue;
    }
    public void setColumnDictvalue(String columnDictvalue)
    {
        this.columnDictvalue = columnDictvalue;
    }

    public String getColumnDictvalue()
    {
        return columnDictvalue;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("growId", getGrowId())
            .append("title", getTitle())
            .append("introduction", getIntroduction())
            .append("shapeType", getShapeType())
            .append("userId", getUserId())
            .append("mainImg", getMainImg())
            .append("readCount", getReadCount())
            .append("videoUrl", getVideoUrl())
            .append("content", getContent())
            .append("issueTime", getIssueTime())
            .append("cropsDictvalue", getCropsDictvalue())
            .append("columnDictvalue", getColumnDictvalue())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
