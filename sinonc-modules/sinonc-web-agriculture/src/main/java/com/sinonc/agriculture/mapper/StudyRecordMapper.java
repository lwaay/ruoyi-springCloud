package com.sinonc.agriculture.mapper;

import java.util.List;

import com.sinonc.agriculture.domain.StudyRecord;

/**
 * 学习记录Mapper接口
 *
 * @author ruoyi
 * @date 2022-02-24
 */
public interface StudyRecordMapper {
    /**
     * 根据外链id查询学习记录
     *
     * @param externalId 外链id
     * @param userId 用户id
     * @return 学习记录
     */
    public StudyRecord selectStudyRecordByExternalId(Long externalId, Long userId);

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
     * 删除学习记录
     *
     * @param recordId 学习记录ID
     * @return 结果
     */
    public int deleteStudyRecordById(Long recordId);

    /**
     * 批量删除学习记录
     *
     * @param recordIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteStudyRecordByIds(Long[] recordIds);
}
