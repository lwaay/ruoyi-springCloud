package com.sinonc.orders.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * 用户收藏商品对象 od_member_attention
 *
 * @author ruoyi
 * @date 2022-03-31
 */
@Data
public class OdMemberAttention extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id
     */
    @Excel(name = "用户id")
    private Long userId;

    /**
     * 关注类型
     */
    @Excel(name = "关注类型")
    private Integer attentionType;

    /**
     * 关注目标id
     */
    @Excel(name = "关注目标id")
    private Long targetId;

    /**
     * 状态
     */
    @Excel(name = "状态")
    private Integer status;

}
