package com.sinonc.orders.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 反馈功能表 hc_app_suggest
 *
 * @author wang
 * @date 2019-11-01
 */

public class AppFeedback {

    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long feedbackId;
    /** 用户ID */
    private Long memberId;
    /** 意见类型：1 UI错误 2显示问题 3运行报错 4其他 */
    private String suggestType;
    /** 意见内容 */
    private String suggestCotent;
    /** 截图 */
    private String imageUrl;
    /** 状态:1未处理 2已处理 */
    private String suggestStatus;
    /** 回复内容 */
    private String dealWith;
    /** 创建时间 */
    private Date createTime;
    /** 回复时间 */
    private Date dealTime;
    /** 会员姓名（视图层用）*/
    private String memberName;
    /** 会员角色（视图层用）*/
    private String memberRole;

    public void setMemberId(Long memberId) {
            this.memberId = memberId;
    }

    public Long getMemberId() {
            return memberId;
    }
    public void setSuggestType(String suggestType) {
            this.suggestType = suggestType;
    }

    public String getSuggestType() {
            return suggestType;
    }
    public void setSuggestCotent(String suggestCotent) {
            this.suggestCotent = suggestCotent;
    }

    public String getSuggestCotent() {
            return suggestCotent;
    }
    public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
            return imageUrl;
    }
    public void setSuggestStatus(String suggestStatus) {
            this.suggestStatus = suggestStatus;
    }

    public String getSuggestStatus() {
            return suggestStatus;
    }
    public void setDealWith(String dealWith) {
            this.dealWith = dealWith;
    }

    public String getDealWith() {
            return dealWith;
    }
    public void setCreateTime(Date createTime) {
            this.createTime = createTime;
    }

    public Date getCreateTime() {
            return createTime;
    }
    public void setDealTime(Date dealTime) {
            this.dealTime = dealTime;
    }

    public Date getDealTime() {
            return dealTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("feedbackId",getFeedbackId())
                .append("memberId",getMemberId())
                .append("suggestType",getSuggestType())
                .append("suggestCotent",getSuggestCotent())
                .append("imageUrl",getImageUrl())
                .append("suggestStatus",getSuggestStatus())
                .append("dealWith",getDealWith())
                .append("createTime",getCreateTime())
                .append("dealTime",getDealTime())
                .toString();
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberRole() {
        return memberRole;
    }

    public void setMemberRole(String memberRole) {
        this.memberRole = memberRole;
    }

    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }
}
