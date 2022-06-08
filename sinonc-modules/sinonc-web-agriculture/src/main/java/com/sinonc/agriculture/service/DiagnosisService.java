package com.sinonc.agriculture.service;

import java.util.List;
import com.sinonc.agriculture.domain.Diagnosis;

/**
 * 诊断部位Service接口
 * 
 * @author huanghao
 * @date 2020-04-21
 */
public interface DiagnosisService
{
    /**
     * 查询诊断部位
     * 
     * @param id 诊断部位ID
     * @return 诊断部位
     */
    public Diagnosis getDiagnosisById(Long id);

    /**
     * 查询诊断部位列表
     * 
     * @param diagnosis 诊断部位
     * @return 诊断部位集合
     */
    public List<Diagnosis> getDiagnosisList(Diagnosis diagnosis);

    /**
     * 新增诊断部位
     * 
     * @param diagnosis 诊断部位
     * @return 结果
     */
    public int addDiagnosis(Diagnosis diagnosis);

    /**
     * 修改诊断部位
     * 
     * @param diagnosis 诊断部位
     * @return 结果
     */
    public int updateDiagnosis(Diagnosis diagnosis);

    /**
     * 批量删除诊断部位
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDiagnosisByIds(String ids);

    /**
     * 删除诊断部位信息
     * 
     * @param id 诊断部位ID
     * @return 结果
     */
    public int deleteDiagnosisById(Long id);
}
