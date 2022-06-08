package com.sinonc.base.vo;

import com.sinonc.base.api.domain.AreaCode;

import java.io.Serializable;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/1/20 17:46
 */
public class AreaListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 节点ID
     */
    private Long id;

    /**
     * 节点名称
     */
    private String label;

    public AreaListVo(AreaCode areaCode) {
        this.id = areaCode.getCode();
        this.label = areaCode.getName();
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
}
