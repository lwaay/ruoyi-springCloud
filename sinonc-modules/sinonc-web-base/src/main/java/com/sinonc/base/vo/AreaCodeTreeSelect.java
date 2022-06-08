package com.sinonc.base.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sinonc.base.api.domain.AreaCode;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Treeselect树结构实体类
 *
 * @author ruoyi
 */
public class AreaCodeTreeSelect implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 节点ID
     */
    private Long id;

    /**
     * 节点名称
     */
    private String label;

    /**
     * 子节点
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<AreaCodeTreeSelect> children;

    public AreaCodeTreeSelect() {

    }

    public AreaCodeTreeSelect(AreaCode areaCode) {
        this.id = areaCode.getCode();
        this.label = areaCode.getName();
        this.children = areaCode.getChildren().stream().map(AreaCodeTreeSelect::new).collect(Collectors.toList());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<AreaCodeTreeSelect> getChildren() {
        return children;
    }

    public void setChildren(List<AreaCodeTreeSelect> children) {
        this.children = children;
    }
}
