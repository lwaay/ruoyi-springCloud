package com.sinonc.agriculture.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * member_notice
 * @author
 */
@Data
public class MemberNotice implements Serializable {
    /**
     * 通知ID
     */
    private Long noticeId;

    /**
     * 会员ID
     */
    private Long memberId;

    /**
     * 通知类型，0，文字通知，1，回答邀请，2，会员动态，3，问题动态
     */
    private Integer type;

    /**
     * 阅读标志 0，未读，1，已读
     */
    private Integer readFlag;

    private Date createTime;

    private Date updateTime;

    /**
     * 删除标记
     */
    private Integer delFlg;

    /**
     * 通知内容
     */
    private String noticeContent;

    private static final long serialVersionUID = 1L;
}
