package com.sinonc.orders.domain;

import com.sinonc.common.core.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * 朋友圈表 adoption_circle
 *
 * @author sinonc
 * @date 2019-08-14
 */

public class AdoptionCircle implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 朋友圈id */
    private Long adoptionId;
    /** 用户会员id */
    @Excel(name = "用户会员id")
    private Long memberId;
    /** 朋友圈内容 */
    @NotEmpty(message="朋友圈内容不能为空！")
    @Excel(name = "朋友圈内容")
    private String content;
    /** 朋友圈图片 */
    @Excel(name = "朋友圈图片")
    private String images;
    /** 位置 */
    private String location;
    /** 创建时间 */
    private Date createTime;
    /** 后台用户ID**/
    private Long userId;
    /**用户名称*/
    private String userName;
    /**1 删除 0 可用*/
    private String isDeleted;
    /**0 是视频 1 是图片*/
    @Excel(name = "1是图片 2是视频 3是无")
    private  String isImages;

    @Excel(name = "状态", readConverterExp = "0=-正常，1-待审核，2-审核不通过")
    private String status;

    public String getIsImages() {
        return isImages;
    }

    public void setIsImages(String isImages) {
        this.isImages = isImages;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setAdoptionId(Long adoptionId) {
            this.adoptionId = adoptionId;
    }

    public Long getAdoptionId() {
            return adoptionId;
    }
    public void setMemberId(Long memberId) {
            this.memberId = memberId;
    }

    public Long getMemberId() {
            return memberId;
    }
    public void setContent(String content) {
            this.content = content;
    }

    public String getContent() {
            return content;
    }
    public void setImages(String images) {
            this.images = images;
    }

    public String getImages() {
            return images;
    }
    public void setLocation(String location) {
            this.location = location;
    }

    public String getLocation() {
            return location;
    }
    public void setCreateTime(Date createTime) {
            this.createTime = createTime;
    }

    public Date getCreateTime() {
            return createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("adoptionId",getAdoptionId())
                .append("memberId",getMemberId())
                .append("content",getContent())
                .append("images",getImages())
                .append("location",getLocation())
                .append("createTime",getCreateTime())
                .toString();
    }
}
