package com.sinonc.base.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 大屏菜单对象 menu
 *
 * @author hhao
 * @date 2020-11-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Menu extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 标题名称
     */
    @Excel(name = "标题名称")
    private String menuName;

    /**
     * 排序
     */
    @Excel(name = "排序")
    private Integer sort;

    /**
     * 路径uri
     */
    @Excel(name = "路径uri")
    private String uri;

    /**
     * 父菜单
     */
    @Excel(name = "父菜单")
    private Long parentId;

    /**
     * 1 显示 0 不显示
     */
    @Excel(name = "1 显示 0 不显示")
    private Integer status;

    /**
     * type 3 的高度
     */
    private String height;

    public Menu(Long parentId) {
        this.parentId = parentId;
    }

    public Menu(Integer status) {
        this.status = status;
    }

    private String parentName;
    /**
     * 1 系统预设 2 avue 自定义大屏
     */
    private Integer type;

    /**
     * 视频地址
     */
    private String videoUrl;


}
