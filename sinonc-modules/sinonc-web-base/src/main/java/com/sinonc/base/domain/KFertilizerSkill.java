package com.sinonc.base.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * 施肥技术对象 k_fertilizer_skill
 *
 * @author ruoyi
 * @date 2022-04-19
 */
@Data
public class KFertilizerSkill extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 是否缺乏元素方案 0否 1是
     */
    @Excel(name = "是否缺乏元素方案 0否 1是")
    private String skillType;

    /**
     * 缺乏的元素名称 如：氮、钾
     */
    @Excel(name = "缺乏的元素名称 如：氮、钾")
    private String eleName;

    /**
     * 施肥技术标题
     */
    @Excel(name = "施肥技术标题")
    private String title;

    /**
     * 标题图片路径
     */
    @Excel(name = "标题图片路径")
    private String imageUrl;

    /**
     * 施肥技术内容
     */
    @Excel(name = "施肥技术内容")
    private String content;

    /**
     * 创建人
     */
    @Excel(name = "创建人")
    private String createUser;

}
