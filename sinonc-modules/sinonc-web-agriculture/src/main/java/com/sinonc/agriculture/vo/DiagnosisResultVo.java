package com.sinonc.agriculture.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sinonc.agriculture.domain.DiagnosisIllnessResult;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiagnosisResultVo {
    /**
     * 疾病名称
     */
    private String illness;

    /**
     * 内容
     */
    private String content;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }
}
