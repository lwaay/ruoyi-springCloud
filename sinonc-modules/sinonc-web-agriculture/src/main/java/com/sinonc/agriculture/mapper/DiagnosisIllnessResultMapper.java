package com.sinonc.agriculture.mapper;

import java.util.List;
import com.sinonc.agriculture.domain.DiagnosisIllnessResult;

/**
 * 诊断疾病结果Mapper接口
 * 
 * @author huangaho
 * @date 2020-04-24
 */
public interface DiagnosisIllnessResultMapper 
{
    /**
     * 查询诊断疾病结果
     * 
     * @param id 诊断疾病结果ID
     * @return 诊断疾病结果
     */
    public DiagnosisIllnessResult selectDiagnosisIllnessResultById(Long id);

    /**
     * 查询诊断疾病结果列表
     * 
     * @param diagnosisIllnessResult 诊断疾病结果
     * @return 诊断疾病结果集合
     */
    public List<DiagnosisIllnessResult> selectDiagnosisIllnessResultList(DiagnosisIllnessResult diagnosisIllnessResult);

    /**
     * 新增诊断疾病结果
     * 
     * @param diagnosisIllnessResult 诊断疾病结果
     * @return 结果
     */
    public int insertDiagnosisIllnessResult(DiagnosisIllnessResult diagnosisIllnessResult);

    /**
     * 修改诊断疾病结果
     * 
     * @param diagnosisIllnessResult 诊断疾病结果
     * @return 结果
     */
    public int updateDiagnosisIllnessResult(DiagnosisIllnessResult diagnosisIllnessResult);

    /**
     * 删除诊断疾病结果
     * 
     * @param id 诊断疾病结果ID
     * @return 结果
     */
    public int deleteDiagnosisIllnessResultById(Long id);

    /**
     * 批量删除诊断疾病结果
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDiagnosisIllnessResultByIds(String[] ids);
}
