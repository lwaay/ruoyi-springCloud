package com.sinonc.orders.vo;

import com.sinonc.orders.domain.CommentReply;
import lombok.Data;

/**
 * 用户评论回复VO
 */
@Data
public class CommentReplyUserVo extends CommentReply{

    private Long uid;

    private Long commentreplyId;

    private String commentUserName;

    private String replyUserName;

    private String userName;




}
