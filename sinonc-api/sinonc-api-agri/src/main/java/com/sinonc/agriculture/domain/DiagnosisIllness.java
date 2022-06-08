package com.sinonc.agriculture.domain;

/**
 * 症状诊断
 */
public class DiagnosisIllness {
    /**
     * id
     */
    private String id;
    /**
     * 疾病名称
     */
    private String illness;
    /**
     * 发病率
     */
    private Integer percent;
    /**
     * 对应症状id
     */
    private Long diaSymptomIdP;
    /**
     * 对应字典表中的疾病id
     */
    private Long illnessIdP;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public Long getDiaSymptomIdP() {
        return diaSymptomIdP;
    }

    public void setDiaSymptomIdP(Long diaSymptomIdP) {
        this.diaSymptomIdP = diaSymptomIdP;
    }

    public Long getIllnessIdP() {
        return illnessIdP;
    }

    public void setIllnessIdP(Long illnessIdP) {
        this.illnessIdP = illnessIdP;
    }
}
