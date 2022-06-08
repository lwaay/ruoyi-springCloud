package com.sinonc.orders.ec.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 自定义折线展示对象 eshop_type
 *
 * @author ruoyi
 * @date 2020-11-23
 */
public class EshopType extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 父id
     */
    @Excel(name = "父id")
    private Long parentId;

    /**
     * 总类名称
     */
    @Excel(name = "总类名称")
    private String name;

    /**
     * 0不显示 1显示
     */
    @Excel(name = "0不显示 1显示")
    private Integer state;

    /**
     * 0不展示 1展示
     */
    @Excel(name = "0不展示 1展示")
    private Integer show;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getState() {
        return state;
    }

    public Integer getShow() {
        return show;
    }

    public void setShow(Integer show) {
        this.show = show;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("parentId", getParentId())
                .append("name", getName())
                .append("state", getState())
                .append("show", getShow())
                .toString();
    }

    public EshopType(){

    }

    public EshopType(Integer state){
        this.state = state;
    }
}
