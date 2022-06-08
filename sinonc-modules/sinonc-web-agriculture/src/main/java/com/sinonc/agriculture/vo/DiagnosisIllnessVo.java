package com.sinonc.agriculture.vo;

import com.sinonc.agriculture.domain.DiagnosisIllness;

public class DiagnosisIllnessVo extends DiagnosisIllness {
    private String percentRate;
    private String idName;

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public String getPercentRate() {
        return percentRate;
    }

    public void setPercentRate(String percentRate) {
        this.percentRate = percentRate;
    }
}
