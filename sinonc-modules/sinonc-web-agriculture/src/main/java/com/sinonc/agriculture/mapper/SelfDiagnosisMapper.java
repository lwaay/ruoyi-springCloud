package com.sinonc.agriculture.mapper;

import com.sinonc.agriculture.domain.DiagnosisIllness;
import com.sinonc.agriculture.domain.DiagnosisIllnessResult;
import com.sinonc.agriculture.domain.DiagnosisSymptom;
import com.sinonc.agriculture.vo.DiagnosisResultVo;
import com.sinonc.agriculture.vo.SelfDiagnosisVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SelfDiagnosisMapper {

    List<SelfDiagnosisVo> selectOneByCropId(@Param("cropId") Long cropId);

     List<DiagnosisSymptom> selectSymptomByDiaId(@Param("id") Long id);

    List<DiagnosisIllnessResult> selectDiagnosisIllnessResult(@Param("illnessId") String illnessId);

    List<DiagnosisIllness> selectDiagnosisIllness(@Param("idList") List<Long> ids);
}
