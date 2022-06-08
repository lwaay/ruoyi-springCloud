package com.sinonc.orders.mapper;


import com.sinonc.orders.domain.AppFeedback;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 意见反馈Mapper
 */
public interface AppFeedbackMapper {

    /**
     * 新增意见反馈
     * @param appFeedback
     * @return
     */
    int addFeedback(AppFeedback appFeedback);

    /**
     * 查询意见反馈列表
     * @return
     */
    List<Map<String, Object>> selectFeedbackList(@Param("memberId") Long memberId);

    /**
     * 根据id查询意见反馈
     * @param feedbackId
     * @return
     */
    Map<String, Object> selectFeedbackById(@Param("feedbackId") Long feedbackId);
}
