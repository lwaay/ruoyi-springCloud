package com.sinonc.agriculture.mapper;

import java.util.List;
import com.sinonc.agriculture.domain.Diagnosis;

/**
 * 诊断部位Mapper接口
 * 
 * @author huanghao
 * @date 2020-04-21
 */
public interface DiagnosisMapper 
{
    /**
     * 查询诊断部位
     * 
     * @param id 诊断部位ID
     * @return 诊断部位
     */
    public Diagnosis selectDiagnosisById(Long id);

    /**
     * 查询诊断部位列表
     * 
     * @param diagnosis 诊断部位
     * @return 诊断部位集合
     */
    public List<Diagnosis> selectDiagnosisList(Diagnosis diagnosis);

    /**
     * 新增诊断部位
     * 
     * @param diagnosis 诊断部位
     * @return 结果
     */
    public int insertDiagnosis(Diagnosis diagnosis);

    /**
     * 修改诊断部位
     * 
     * @param diagnosis 诊断部位
     * @return 结果
     */
    public int updateDiagnosis(Diagnosis diagnosis);

    /**
     * 删除诊断部位
     * 
     * @param id 诊断部位ID
     * @return 结果
     */
    public int deleteDiagnosisById(Long id);

    /**
     * 批量删除诊断部位
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDiagnosisByIds(String[] ids);
}
