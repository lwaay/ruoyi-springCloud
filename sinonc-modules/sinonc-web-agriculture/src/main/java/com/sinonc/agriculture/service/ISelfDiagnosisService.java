package com.sinonc.agriculture.service;

import com.sinonc.agriculture.domain.DiagnosisIllness;
import com.sinonc.agriculture.domain.DiagnosisIllnessResult;
import com.sinonc.agriculture.vo.DiagnosisResultVo;
import com.sinonc.agriculture.vo.SelfDiagnosisVo;

import java.util.List;

public interface ISelfDiagnosisService {

    /**
     * 根据 cropId 获取诊断信息
     * @param cropId
     * @return SelfDiagnosisVo
     */
    List<SelfDiagnosisVo> getDiagnosisBodyAndSymptom(Long cropId);

    /**
     * 根据症状id获取疾病对策
     * @param illnessId
     * @return
     */
    List<DiagnosisIllnessResult> getDiagnosisIllnessResult(String illnessId);

    /**
     * 获取针状疾病
     * @param symptomIds
     * @return
     */
    List<DiagnosisIllness> getDiagnosisIllness(String symptomIds);
}
