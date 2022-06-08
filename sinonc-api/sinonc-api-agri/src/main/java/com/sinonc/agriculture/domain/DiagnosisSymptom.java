package com.sinonc.agriculture.domain;

public class DiagnosisSymptom {
    /**
     * 症状id
     */
    private Long id;
    /**
     * 症状名称
     */
    private String info;
    /**
     * 对应部位id
     */
    private Long diaIdP;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Long getDiaIdP() {
        return diaIdP;
    }

    public void setDiaIdP(Long diaIdP) {
        this.diaIdP = diaIdP;
    }
}
