package com.sinonc.agriculture.service.impl;

import java.util.List;
import com.sinonc.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.agriculture.mapper.DiagnosisIllnessResultMapper;
import com.sinonc.agriculture.domain.DiagnosisIllnessResult;
import com.sinonc.agriculture.service.DiagnosisIllnessResultService;
import com.sinonc.common.core.text.Convert;

/**
 * 诊断疾病结果Service业务层处理
 *
 * @author huangaho
 * @date 2020-04-24
 */
@Service
public class DiagnosisIllnessResultServiceImpl implements DiagnosisIllnessResultService
{
    @Autowired
    private DiagnosisIllnessResultMapper diagnosisIllnessResultMapper;

    /**
     * 查询诊断疾病结果
     *
     * @param id 诊断疾病结果ID
     * @return 诊断疾病结果
     */
    @Override
    public DiagnosisIllnessResult getDiagnosisIllnessResultById(Long id)
    {
        return diagnosisIllnessResultMapper.selectDiagnosisIllnessResultById(id);
    }

    /**
     * 查询诊断疾病结果列表
     *
     * @param diagnosisIllnessResult 诊断疾病结果
     * @return 诊断疾病结果
     */
    @Override
    public List<DiagnosisIllnessResult> getDiagnosisIllnessResultList(DiagnosisIllnessResult diagnosisIllnessResult)
    {
        return diagnosisIllnessResultMapper.selectDiagnosisIllnessResultList(diagnosisIllnessResult);
    }

    /**
     * 新增诊断疾病结果
     *
     * @param diagnosisIllnessResult 诊断疾病结果
     * @return 结果
     */
    @Override
    public int addDiagnosisIllnessResult(DiagnosisIllnessResult diagnosisIllnessResult)
    {
        diagnosisIllnessResult.setCreateTime(DateUtils.getNowDate());
        return diagnosisIllnessResultMapper.insertDiagnosisIllnessResult(diagnosisIllnessResult);
    }

    /**
     * 修改诊断疾病结果
     *
     * @param diagnosisIllnessResult 诊断疾病结果
     * @return 结果
     */
    @Override
    public int updateDiagnosisIllnessResult(DiagnosisIllnessResult diagnosisIllnessResult)
    {
        return diagnosisIllnessResultMapper.updateDiagnosisIllnessResult(diagnosisIllnessResult);
    }

    /**
     * 删除诊断疾病结果对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDiagnosisIllnessResultByIds(String ids)
    {
        return diagnosisIllnessResultMapper.deleteDiagnosisIllnessResultByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除诊断疾病结果信息
     *
     * @param id 诊断疾病结果ID
     * @return 结果
     */
    @Override
    public int deleteDiagnosisIllnessResultById(Long id)
    {
        return diagnosisIllnessResultMapper.deleteDiagnosisIllnessResultById(id);
    }
}
