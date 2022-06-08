package com.sinonc.agriculture.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.agriculture.mapper.DiagnosisIllnessMapper;
import com.sinonc.agriculture.domain.DiagnosisIllness;
import com.sinonc.agriculture.service.DiagnosisIllnessService;
import com.sinonc.common.core.text.Convert;

/**
 * 疾病诊断Service业务层处理
 *
 * @author huanghao
 * @date 2020-04-21
 */
@Service
public class DiagnosisIllnessServiceImpl implements DiagnosisIllnessService
{
    @Autowired
    private DiagnosisIllnessMapper diagnosisIllnessMapper;

    /**
     * 查询疾病诊断
     *
     * @param id 疾病诊断ID
     * @return 疾病诊断
     */
    @Override
    public DiagnosisIllness getDiagnosisIllnessById(Long id)
    {
        return diagnosisIllnessMapper.selectDiagnosisIllnessById(id);
    }

    /**
     * 查询疾病诊断列表
     *
     * @param diagnosisIllness 疾病诊断
     * @return 疾病诊断
     */
    @Override
    public List<DiagnosisIllness> getDiagnosisIllnessList(DiagnosisIllness diagnosisIllness)
    {
        return diagnosisIllnessMapper.selectDiagnosisIllnessList(diagnosisIllness);
    }

    /**
     * 新增疾病诊断
     *
     * @param diagnosisIllness 疾病诊断
     * @return 结果
     */
    @Override
    public int addDiagnosisIllness(DiagnosisIllness diagnosisIllness)
    {
        return diagnosisIllnessMapper.insertDiagnosisIllness(diagnosisIllness);
    }

    /**
     * 修改疾病诊断
     *
     * @param diagnosisIllness 疾病诊断
     * @return 结果
     */
    @Override
    public int updateDiagnosisIllness(DiagnosisIllness diagnosisIllness)
    {
        return diagnosisIllnessMapper.updateDiagnosisIllness(diagnosisIllness);
    }

    /**
     * 删除疾病诊断对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDiagnosisIllnessByIds(String ids)
    {
        return diagnosisIllnessMapper.deleteDiagnosisIllnessByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除疾病诊断信息
     *
     * @param id 疾病诊断ID
     * @return 结果
     */
    @Override
    public int deleteDiagnosisIllnessById(Long id)
    {
        return diagnosisIllnessMapper.deleteDiagnosisIllnessById(id);
    }
}
