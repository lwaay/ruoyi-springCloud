package com.sinonc.orders.service.impl;

import com.sinonc.orders.domain.AppFeedback;
import com.sinonc.orders.mapper.AppFeedbackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 意见反馈Service
 */
@Service
@Transactional
public class AppFeedbackService {

    @Autowired
    private AppFeedbackMapper appFeedbackMapper;

    /**
     * 新增意见反馈
     * @param appFeedback
     * @return
     */
    public int addSuggest(AppFeedback appFeedback) {
        return appFeedbackMapper.addFeedback(appFeedback);
    }

    /**
     * 查询反馈意见列表
     * @return
     */
    public List<Map<String,Object>> selectSuggestList(Long memberId) {
        return appFeedbackMapper.selectFeedbackList(memberId);
    }

    /**
     *根据id查询意见反馈
     * @param feedbackId
     * @return
     */
    public Map<String, Object> selectSuggestById(Long feedbackId) {
        return appFeedbackMapper.selectFeedbackById(feedbackId);
    }
}
