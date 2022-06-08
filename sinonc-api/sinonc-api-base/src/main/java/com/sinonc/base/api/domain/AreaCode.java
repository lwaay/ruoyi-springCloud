package com.sinonc.base.api.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * 行政区域对象 area_code
 *
 * @author ruoyi
 * @date 2020-09-23
 */
public class AreaCode extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 区划代码
     */
    @Excel(name = "区划代码")
    private Long code;

    /**
     * 名称
     */
    @Excel(name = "名称")
    private String name;

    /**
     * 级别1-5,省市县镇村
     */
    @Excel(name = "级别1-5,省市县镇村")
    private Integer level;

    /**
     * 父级区划代码
     */
    @Excel(name = "父级区划代码")
    private Long pcode;

    /**
     * 是否选中
     */
    private boolean checked;

    /**
     * 子部门
     */
    private List<AreaCode> children = new ArrayList<AreaCode>();


    public List<AreaCode> getChildren() {
        return children;
    }

    public void setChildren(List<AreaCode> children) {
        this.children = children;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Long getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getLevel() {
        return level;
    }

    public void setPcode(Long pcode) {
        this.pcode = pcode;
    }

    public Long getPcode() {
        return pcode;
    }

    public AreaCode(Long code, String name, Integer level, Long pcode) {
        this.code = code;
        this.name = name;
        this.level = level;
        this.pcode = pcode;
    }
    public AreaCode(){}
    public AreaCode(Long pcode){
        this.pcode = pcode;
    }

    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("code", getCode())
                .append("name", getName())
                .append("level", getLevel())
                .append("pcode", getPcode())
                .toString();
    }
}
