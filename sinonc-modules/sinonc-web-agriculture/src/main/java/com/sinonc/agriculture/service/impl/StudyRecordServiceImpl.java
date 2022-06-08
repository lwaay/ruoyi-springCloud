package com.sinonc.agriculture.service.impl;

import java.util.List;
import java.util.Optional;

import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.agriculture.mapper.StudyRecordMapper;
import com.sinonc.agriculture.domain.StudyRecord;
import com.sinonc.agriculture.service.IStudyRecordService;

/**
 * 学习记录Service业务层处理
 *
 * @author ruoyi
 * @date 2022-02-24
 */
@Service
public class StudyRecordServiceImpl implements IStudyRecordService {
    @Autowired
    private StudyRecordMapper studyRecordMapper;

    /**
     * 查询学习记录
     *
     * @param recordId 学习记录ID
     * @return 学习记录
     */
    @Override
    public StudyRecord selectStudyRecordById(Long recordId) {
        return studyRecordMapper.selectStudyRecordById(recordId);
    }

    /**
     * 查询学习记录列表
     *
     * @param studyRecord 学习记录
     * @return 学习记录
     */
    @Override
    public List<StudyRecord> selectStudyRecordList(StudyRecord studyRecord) {
        return studyRecordMapper.selectStudyRecordList(studyRecord);
    }

    /**
     * 新增学习记录
     *
     * @param studyRecord 学习记录
     * @return 结果
     */
    @Override
    public int insertStudyRecord(StudyRecord studyRecord) {
        Long userId = SecurityUtils.getUserId();
        String userName = SecurityUtils.getUsername();
        studyRecord.setUserId(userId);
        studyRecord.setUserName(userName);
        //如果外链id存在，更新时间
        StudyRecord oldStudyRecord = studyRecordMapper.selectStudyRecordByExternalId(studyRecord.getExternalId(), userId);
        int rows;
        if(Optional.ofNullable(oldStudyRecord).isPresent()){
            oldStudyRecord.setTitle(studyRecord.getTitle());
            oldStudyRecord.setImg(studyRecord.getImg());
            oldStudyRecord.setRecordTime(DateUtils.getNowDate());
            //更新老记录
            rows = studyRecordMapper.updateStudyRecord(oldStudyRecord);
        }else {
            studyRecord.setRecordTime(DateUtils.getNowDate());
            rows = studyRecordMapper.insertStudyRecord(studyRecord);
        }
        return rows;
    }

    /**
     * 修改学习记录
     *
     * @param studyRecord 学习记录
     * @return 结果
     */
    @Override
    public int updateStudyRecord(StudyRecord studyRecord) {
        return studyRecordMapper.updateStudyRecord(studyRecord);
    }

    /**
     * 批量删除学习记录
     *
     * @param recordIds 需要删除的学习记录ID
     * @return 结果
     */
    @Override
    public int deleteStudyRecordByIds(Long[] recordIds) {
        return studyRecordMapper.deleteStudyRecordByIds(recordIds);
    }

    /**
     * 删除学习记录信息
     *
     * @param recordId 学习记录ID
     * @return 结果
     */
    @Override
    public int deleteStudyRecordById(Long recordId) {
        return studyRecordMapper.deleteStudyRecordById(recordId);
    }
}
