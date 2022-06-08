package com.sinonc.agriculture.vo;

import com.sinonc.agriculture.domain.DiagnosisSymptom;

import java.util.List;

public class SelfDiagnosisVo {

    private Long id;
    /**
     * 诊断部位名称
     */
    private String name;
    /**
     * 对应鱼的 id
     */
    private Long cropIdP;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCropIdP() {
        return cropIdP;
    }

    public void setCropIdP(Long cropIdP) {
        this.cropIdP = cropIdP;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
