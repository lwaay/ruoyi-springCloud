package com.sinonc.agriculture.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.agriculture.mapper.DiagnosisMapper;
import com.sinonc.agriculture.domain.Diagnosis;
import com.sinonc.agriculture.service.DiagnosisService;
import com.sinonc.common.core.text.Convert;

/**
 * 诊断部位Service业务层处理
 *
 * @author huanghao
 * @date 2020-04-21
 */
@Service
public class DiagnosisServiceImpl implements DiagnosisService
{
    @Autowired
    private DiagnosisMapper diagnosisMapper;

    /**
     * 查询诊断部位
     *
     * @param id 诊断部位ID
     * @return 诊断部位
     */
    @Override
    public Diagnosis getDiagnosisById(Long id)
    {
        return diagnosisMapper.selectDiagnosisById(id);
    }

    /**
     * 查询诊断部位列表
     *
     * @param diagnosis 诊断部位
     * @return 诊断部位
     */
    @Override
    public List<Diagnosis> getDiagnosisList(Diagnosis diagnosis)
    {
        return diagnosisMapper.selectDiagnosisList(diagnosis);
    }

    /**
     * 新增诊断部位
     *
     * @param diagnosis 诊断部位
     * @return 结果
     */
    @Override
    public int addDiagnosis(Diagnosis diagnosis)
    {
        return diagnosisMapper.insertDiagnosis(diagnosis);
    }

    /**
     * 修改诊断部位
     *
     * @param diagnosis 诊断部位
     * @return 结果
     */
    @Override
    public int updateDiagnosis(Diagnosis diagnosis)
    {
        return diagnosisMapper.updateDiagnosis(diagnosis);
    }

    /**
     * 删除诊断部位对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDiagnosisByIds(String ids)
    {
        return diagnosisMapper.deleteDiagnosisByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除诊断部位信息
     *
     * @param id 诊断部位ID
     * @return 结果
     */
    @Override
    public int deleteDiagnosisById(Long id)
    {
        return diagnosisMapper.deleteDiagnosisById(id);
    }
}
