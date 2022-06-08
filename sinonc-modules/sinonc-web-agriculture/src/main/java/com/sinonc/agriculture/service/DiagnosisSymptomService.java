package com.sinonc.agriculture.service;

import java.util.List;
import com.sinonc.agriculture.domain.DiagnosisSymptom;

/**
 * 症状Service接口
 * 
 * @author huanghao
 * @date 2020-04-21
 */
public interface DiagnosisSymptomService
{
    /**
     * 查询症状
     * 
     * @param id 症状ID
     * @return 症状
     */
    public DiagnosisSymptom getDiagnosisSymptomById(Long id);

    /**
     * 查询症状列表
     * 
     * @param diagnosisSymptom 症状
     * @return 症状集合
     */
    public List<DiagnosisSymptom> getDiagnosisSymptomList(DiagnosisSymptom diagnosisSymptom);

    /**
     * 新增症状
     * 
     * @param diagnosisSymptom 症状
     * @return 结果
     */
    public int addDiagnosisSymptom(DiagnosisSymptom diagnosisSymptom);

    /**
     * 修改症状
     * 
     * @param diagnosisSymptom 症状
     * @return 结果
     */
    public int updateDiagnosisSymptom(DiagnosisSymptom diagnosisSymptom);

    /**
     * 批量删除症状
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDiagnosisSymptomByIds(String ids);

    /**
     * 删除症状信息
     * 
     * @param id 症状ID
     * @return 结果
     */
    public int deleteDiagnosisSymptomById(Long id);
}
