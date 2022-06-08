package com.sinonc.base.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 脐橙信息对象 crop_info
 *
 * @author ruoyi
 * @date 2020-09-27
 */
public class CropInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 作物名称
     */
    @Excel(name = "作物名称")
    private String name;

    /**
     * 面积
     */
    @Excel(name = "面积")
    private Long area;

    /**
     * 数量
     */
    @Excel(name = "数量")
    private Long count;

    /**
     * 种植时间
     */
    @Excel(name = "种植时间")
    private Integer createAt;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setArea(Long area) {
        this.area = area;
    }

    public Long getArea() {
        return area;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getCount() {
        return count;
    }

    public void setCreateAt(Integer createAt) {
        this.createAt = createAt;
    }

    public Integer getCreateAt() {
        return createAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("area", getArea())
                .append("count", getCount())
                .append("createAt", getCreateAt())
                .toString();
    }
}
