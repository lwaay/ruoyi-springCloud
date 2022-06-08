package com.sinonc.agriculture.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * msg_session
 *
 * @author
 */
@Data
public class MsgSession implements Serializable {
    /**
     * 会话表主键
     */
    private Long msgSid;

    /**
     * 会话id
     */
    private String sessionId;

    /**
     * 会话名称
     */
    private String sessionName;

    /**
     * 会话所属会员ID
     */
    private Long memberId;

    /**
     * 未读消息数
     */
    private Integer unreadCount;

    /**
     * 最后一条消息内容
     */
    private String lastMsgContent;

    private Date createTime;

    private Date updateTime;

    /**
     * 删除标记，0，未删，1，已删
     */
    private Integer delFlag;

    private static final long serialVersionUID = 1L;
}