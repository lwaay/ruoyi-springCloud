package com.sinonc.agriculture.mapper;

import java.util.List;
import com.sinonc.agriculture.domain.DiagnosisIllness;

/**
 * 疾病诊断Mapper接口
 * 
 * @author huanghao
 * @date 2020-04-21
 */
public interface DiagnosisIllnessMapper 
{
    /**
     * 查询疾病诊断
     * 
     * @param id 疾病诊断ID
     * @return 疾病诊断
     */
    public DiagnosisIllness selectDiagnosisIllnessById(Long id);

    /**
     * 查询疾病诊断列表
     * 
     * @param diagnosisIllness 疾病诊断
     * @return 疾病诊断集合
     */
    public List<DiagnosisIllness> selectDiagnosisIllnessList(DiagnosisIllness diagnosisIllness);

    /**
     * 新增疾病诊断
     * 
     * @param diagnosisIllness 疾病诊断
     * @return 结果
     */
    public int insertDiagnosisIllness(DiagnosisIllness diagnosisIllness);

    /**
     * 修改疾病诊断
     * 
     * @param diagnosisIllness 疾病诊断
     * @return 结果
     */
    public int updateDiagnosisIllness(DiagnosisIllness diagnosisIllness);

    /**
     * 删除疾病诊断
     * 
     * @param id 疾病诊断ID
     * @return 结果
     */
    public int deleteDiagnosisIllnessById(Long id);

    /**
     * 批量删除疾病诊断
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDiagnosisIllnessByIds(String[] ids);
}
