package com.sinonc.agriculture.mapper;

import java.util.List;
import com.sinonc.agriculture.domain.DiagnosisSymptom;

/**
 * 症状Mapper接口
 * 
 * @author huanghao
 * @date 2020-04-21
 */
public interface DiagnosisSymptomMapper 
{
    /**
     * 查询症状
     * 
     * @param id 症状ID
     * @return 症状
     */
    public DiagnosisSymptom selectDiagnosisSymptomById(Long id);

    /**
     * 查询症状列表
     * 
     * @param diagnosisSymptom 症状
     * @return 症状集合
     */
    public List<DiagnosisSymptom> selectDiagnosisSymptomList(DiagnosisSymptom diagnosisSymptom);

    /**
     * 新增症状
     * 
     * @param diagnosisSymptom 症状
     * @return 结果
     */
    public int insertDiagnosisSymptom(DiagnosisSymptom diagnosisSymptom);

    /**
     * 修改症状
     * 
     * @param diagnosisSymptom 症状
     * @return 结果
     */
    public int updateDiagnosisSymptom(DiagnosisSymptom diagnosisSymptom);

    /**
     * 删除症状
     * 
     * @param id 症状ID
     * @return 结果
     */
    public int deleteDiagnosisSymptomById(Long id);

    /**
     * 批量删除症状
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDiagnosisSymptomByIds(String[] ids);
}
