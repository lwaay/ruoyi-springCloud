package com.sinonc.orders.domain;

import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * 商品评论表 od_comment_reply
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Alias("CommentReply")
public class CommentReply {

    private static final long serialVersionUID = 1L;

    /** 主键id自增 */
    private Long commentreplyId;
    /** 商品id */
    private Long goodsIdP;
    /** 用户id */
    private Long userId;
    /** 回复id */
    private Long replyId;
    /** 评论内容 */
    private String content;
    /** 评论/回复时间 */
    private Date createTime;
    /**商品/认养圈评论回复 0商品评论 1认养圈评论*/
    private Integer type;
    /****/
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setCommentreplyId(Long commentreplyId) {
            this.commentreplyId = commentreplyId;
    }

    public Long getCommentreplyId() {
            return commentreplyId;
    }
    public void setGoodsIdP(Long goodsIdP) {
            this.goodsIdP = goodsIdP;
    }

    public Long getGoodsIdP() {
            return goodsIdP;
    }
    public void setUserId(Long userId) {
            this.userId = userId;
    }

    public Long getUserId() {
            return userId;
    }
    public void setReplyId(Long replyId) {
            this.replyId = replyId;
    }

    public Long getReplyId() {
            return replyId;
    }
    public void setContent(String content) {
            this.content = content;
    }

    public String getContent() {
            return content;
    }
    public void setCreateTime(Date createTime) {
            this.createTime = createTime;
    }

    public Date getCreateTime() {
            return createTime;
    }

    @Override
    public String toString() {
        return "CommentReply{" +
                "commentreplyId=" + commentreplyId +
                ", goodsIdP=" + goodsIdP +
                ", userId=" + userId +
                ", replyId=" + replyId +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", type=" + type +
                '}';
    }
}
