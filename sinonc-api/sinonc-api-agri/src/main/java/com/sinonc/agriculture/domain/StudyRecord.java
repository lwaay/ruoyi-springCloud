package com.sinonc.agriculture.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 学习记录对象 study_record
 *
 * @author ruoyi
 * @date 2022-02-24
 */
public class StudyRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long recordId;

    /**
     * 外链id
     */
    @Excel(name = "外链id")
    private Long externalId;

    /**
     * 用户id
     */
    @Excel(name = "用户id")
    private Long userId;

    /**
     * 用户名称
     */
    @Excel(name = "用户名称")
    private String userName;

    /**
     * 类型
     */
    @Excel(name = "类型")
    private String type;

    /**
     * 标题
     */
    @Excel(name = "标题")
    private String title;

    /**
     * 图片
     */
    @Excel(name = "图片")
    private String img;

    /**
     * 浏览时间
     */
    @Excel(name = "浏览时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date recordTime;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setExternalId(Long externalId) {
        this.externalId = externalId;
    }

    public Long getExternalId() {
        return externalId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("recordId", getRecordId())
                .append("externalId", getExternalId())
                .append("userId", getUserId())
                .append("type", getType())
                .append("title", getTitle())
                .append("img", getImg())
                .append("recordTime", getRecordTime())
                .toString();
    }
}
