package com.sinonc.base.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 果园动态对象 orchard_trend
 *
 * @author ruoyi
 * @date 2022-04-27
 */
@Data
public class OrchardTrend extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 果园（基地）id
     */
    @Excel(name = "果园", readConverterExp = "基=地")
    @NotNull(message = "果园ID不能为空")
    private Long farmId;

    /**
     * 内容
     */
    @Excel(name = "内容")
    @NotNull(message = "内容不能为空")
    private String content;

    /**
     * 动态类型
     */
    @Excel(name = "动态类型")
    @NotNull(message = "动态类型不能为空")
    private String dynamicType;

    /**
     * 媒体链接
     */
    @Excel(name = "媒体链接")
    private String mediaUrl;

}
