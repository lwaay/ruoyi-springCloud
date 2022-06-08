package com.sinonc.orders.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * 朋友圈点赞对象 adoption_circle_like
 *
 * @author ruoyi
 * @date 2022-04-23
 */
@Data
public class AdoptionCircleLike extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 朋友圈动态id
     */
    @Excel(name = "朋友圈动态id")
    private Long adoptionId;

    /**
     * 会员id
     */
    @Excel(name = "会员id")
    private Long memberId;

    /**
     * 是否删除（0-否，1-是）
     */
    private String delFlg;

}
