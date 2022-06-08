package com.sinonc.agriculture.service;

import java.util.List;

import com.sinonc.agriculture.domain.StudyRecord;

/**
 * 学习记录Service接口
 *
 * @author ruoyi
 * @date 2022-02-24
 */
public interface IStudyRecordService {
    /**
     * 查询学习记录
     *
     * @param recordId 学习记录ID
     * @return 学习记录
     */
    public StudyRecord selectStudyRecordById(Long recordId);

    /**
     * 查询学习记录列表
     *
     * @param studyRecord 学习记录
     * @return 学习记录集合
     */
    public List<StudyRecord> selectStudyRecordList(StudyRecord studyRecord);

    /**
     * 新增学习记录
     *
     * @param studyRecord 学习记录
     * @return 结果
     */
    public int insertStudyRecord(StudyRecord studyRecord);

    /**
     * 修改学习记录
     *
     * @param studyRecord 学习记录
     * @return 结果
     */
    public int updateStudyRecord(StudyRecord studyRecord);

    /**
     * 批量删除学习记录
     *
     * @param recordIds 需要删除的学习记录ID
     * @return 结果
     */
    public int deleteStudyRecordByIds(Long[] recordIds);

    /**
     * 删除学习记录信息
     *
     * @param recordId 学习记录ID
     * @return 结果
     */
    public int deleteStudyRecordById(Long recordId);
}
