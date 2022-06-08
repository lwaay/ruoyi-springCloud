package com.sinonc.orders.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 认养点赞表 od_adopt_like
 *
 * @author sinonc
 * @date 2019-10-06
 */

public class AdoptLike {

    private static final long serialVersionUID = 1L;

    /** 点赞ID */
    private Long likeId;
    /** 认养圈id */
    private Long adoptionIdP;
    /** 会员ID */
    private Long userIdP;
    /** 创建人 */
    private String createBy;
    /** 时间时间 */
    private Date createTime;
    /** 更新人 */
    private String updateBy;
    /** 更新时间 */
    private Date updateTime;
    /**会员ID**/
    private Long uid;
    /**会员名称**/
    private String name;
    /**认养圈内容**/
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLikeId(Long likeId) {
            this.likeId = likeId;
    }

    public Long getLikeId() {
            return likeId;
    }
    public void setAdoptionIdP(Long adoptionIdP) {
            this.adoptionIdP = adoptionIdP;
    }

    public Long getAdoptionIdP() {
            return adoptionIdP;
    }
    public void setUserIdP(Long userIdP) {
            this.userIdP = userIdP;
    }

    public Long getUserIdP() {
            return userIdP;
    }
    public void setCreateBy(String createBy) {
            this.createBy = createBy;
    }

    public String getCreateBy() {
            return createBy;
    }
    public void setCreateTime(Date createTime) {
            this.createTime = createTime;
    }

    public Date getCreateTime() {
            return createTime;
    }
    public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
    }

    public String getUpdateBy() {
            return updateBy;
    }
    public void setUpdateTime(Date updateTime) {
            this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
            return updateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("likeId",getLikeId())
                .append("adoptionIdP",getAdoptionIdP())
                .append("userIdP",getUserIdP())
                .append("createBy",getCreateBy())
                .append("createTime",getCreateTime())
                .append("updateBy",getUpdateBy())
                .append("updateTime",getUpdateTime())
                .toString();
    }
}
