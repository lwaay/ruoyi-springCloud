package com.sinonc.orders.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 朋友圈评论对象 adoption_circle_reply
 *
 * @author ruoyi
 * @date 2022-04-23
 */
@Data
public class AdoptionCircleReply extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long replyId;

    /**
     * 回复内容
     */
    @Excel(name = "回复内容")
    @NotNull(message = "评论内容不能为空")
    private String content;

    /**
     * 朋友圈动态id
     */
    @Excel(name = "朋友圈动态id")
    @NotNull(message = "动态id不能为空")
    private Long adoptionId;

    /**
     * 会员id
     */
    @Excel(name = "会员id")
    private Long memberId;

    /**
     * 回复对象(选填)
     */
    @Excel(name = "回复对象id")
    private Long targetId;

    /**
     * 显示用户名
     */
    private String username;

    /**
     * 显示回复对象用户名
     */
    private String targetName;

    /**
     * 是否删除（0-否，1-是）
     */
    private String delFlg;

}
