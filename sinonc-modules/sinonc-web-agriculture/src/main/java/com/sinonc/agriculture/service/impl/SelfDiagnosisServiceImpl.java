package com.sinonc.agriculture.service.impl;


import com.sinonc.agriculture.domain.DiagnosisIllness;
import com.sinonc.agriculture.domain.DiagnosisIllnessResult;
import com.sinonc.agriculture.mapper.SelfDiagnosisMapper;
import com.sinonc.agriculture.service.ISelfDiagnosisService;
import com.sinonc.agriculture.vo.SelfDiagnosisVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class SelfDiagnosisServiceImpl implements ISelfDiagnosisService {

    @Autowired
    private SelfDiagnosisMapper selfDiagnosisMapper;

    @Override
    public List<SelfDiagnosisVo> getDiagnosisBodyAndSymptom(Long cropId) {
        // 根据 cropId 查询 部位
        return selfDiagnosisMapper.selectOneByCropId(cropId);
    }

    @Override
    public List<DiagnosisIllnessResult> getDiagnosisIllnessResult(String illnessId) {
         return selfDiagnosisMapper.selectDiagnosisIllnessResult(illnessId);
    }

    @Override
    public List<DiagnosisIllness> getDiagnosisIllness(String symptomIds) {
        List<Long> ids = new ArrayList<>();
        for (String id : symptomIds.split("[,，]")) {
            ids.add(Long.valueOf(id));
        }
        return selfDiagnosisMapper.selectDiagnosisIllness(ids);
    }
}
