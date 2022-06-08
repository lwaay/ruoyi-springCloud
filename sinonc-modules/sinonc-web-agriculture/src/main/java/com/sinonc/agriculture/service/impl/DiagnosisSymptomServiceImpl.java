package com.sinonc.agriculture.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.agriculture.mapper.DiagnosisSymptomMapper;
import com.sinonc.agriculture.domain.DiagnosisSymptom;
import com.sinonc.agriculture.service.DiagnosisSymptomService;
import com.sinonc.common.core.text.Convert;

/**
 * 症状Service业务层处理
 *
 * @author huanghao
 * @date 2020-04-21
 */
@Service
public class DiagnosisSymptomServiceImpl implements DiagnosisSymptomService
{
    @Autowired
    private DiagnosisSymptomMapper diagnosisSymptomMapper;

    /**
     * 查询症状
     *
     * @param id 症状ID
     * @return 症状
     */
    @Override
    public DiagnosisSymptom getDiagnosisSymptomById(Long id)
    {
        return diagnosisSymptomMapper.selectDiagnosisSymptomById(id);
    }

    /**
     * 查询症状列表
     *
     * @param diagnosisSymptom 症状
     * @return 症状
     */
    @Override
    public List<DiagnosisSymptom> getDiagnosisSymptomList(DiagnosisSymptom diagnosisSymptom)
    {
        return diagnosisSymptomMapper.selectDiagnosisSymptomList(diagnosisSymptom);
    }

    /**
     * 新增症状
     *
     * @param diagnosisSymptom 症状
     * @return 结果
     */
    @Override
    public int addDiagnosisSymptom(DiagnosisSymptom diagnosisSymptom)
    {
        return diagnosisSymptomMapper.insertDiagnosisSymptom(diagnosisSymptom);
    }

    /**
     * 修改症状
     *
     * @param diagnosisSymptom 症状
     * @return 结果
     */
    @Override
    public int updateDiagnosisSymptom(DiagnosisSymptom diagnosisSymptom)
    {
        return diagnosisSymptomMapper.updateDiagnosisSymptom(diagnosisSymptom);
    }

    /**
     * 删除症状对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDiagnosisSymptomByIds(String ids)
    {
        return diagnosisSymptomMapper.deleteDiagnosisSymptomByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除症状信息
     *
     * @param id 症状ID
     * @return 结果
     */
    @Override
    public int deleteDiagnosisSymptomById(Long id)
    {
        return diagnosisSymptomMapper.deleteDiagnosisSymptomById(id);
    }
}
