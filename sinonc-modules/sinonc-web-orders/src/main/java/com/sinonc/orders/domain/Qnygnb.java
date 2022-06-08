package com.sinonc.orders.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 轻农业信丰脐橙果农版用户关联表 od_qnygnb
 *
 * @author sinonc
 * @date 2019-12-11
 */

public class Qnygnb {

    private static final long serialVersionUID = 1L;

    /** 轻农业信丰脐橙果农版用户关联表id */
    private Long qnyId;
    /** 店铺id */
    private Long shopId;
    /** 问题id */
    private Long questionId;
    /** 用户id */
    private Long userId;
    /** 创建时间 */
    private Date createTime;
    /** 专家id */
    private Long expretId;

    public void setQnyId(Long qnyId) {
            this.qnyId = qnyId;
    }

    public Long getQnyId() {
            return qnyId;
    }
    public void setShopId(Long shopId) {
            this.shopId = shopId;
    }

    public Long getShopId() {
            return shopId;
    }
    public void setQuestionId(Long questionId) {
            this.questionId = questionId;
    }

    public Long getQuestionId() {
            return questionId;
    }
    public void setUserId(Long userId) {
            this.userId = userId;
    }

    public Long getUserId() {
            return userId;
    }
    public void setCreateTime(Date createTime) {
            this.createTime = createTime;
    }

    public Date getCreateTime() {
            return createTime;
    }
    public void setExpretId(Long expretId) {
            this.expretId = expretId;
    }

    public Long getExpretId() {
            return expretId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("qnyId",getQnyId())
                .append("shopId",getShopId())
                .append("questionId",getQuestionId())
                .append("userId",getUserId())
                .append("createTime",getCreateTime())
                .append("expretId",getExpretId())
                .toString();
    }
}
