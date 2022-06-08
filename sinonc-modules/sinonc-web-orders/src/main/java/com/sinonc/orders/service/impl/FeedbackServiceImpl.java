package com.sinonc.orders.service.impl;

import com.sinonc.common.core.text.Convert;
import com.sinonc.orders.domain.AppFeedback;
import com.sinonc.orders.mapper.FeedbackMapper;
import com.sinonc.orders.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 反馈功能 服务层实现
 * 
 * @author wang
 * @date 2019-11-01
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {
	@Autowired
	private FeedbackMapper feedbackMapper;

	/**
     * 查询反馈功能信息
     * 
     * @param feedbackId 反馈功能ID
     * @return 反馈功能信息
     */
    @Override
	public AppFeedback getAppSuggestById(Long feedbackId) {
	    return feedbackMapper.selectAppSuggestById(feedbackId);
	}
	
	/**
     * 查询反馈功能列表
     * 
     * @param appFeedback 反馈功能信息
     * @return 反馈功能集合
     */
	@Override
	public List<AppFeedback> listAppSuggest(AppFeedback appFeedback) {
	    return feedbackMapper.selectAppSuggestList(appFeedback);
	}
	
    /**
     * 新增反馈功能
     * 
     * @param appFeedback 反馈功能信息
     * @return 结果
     */
	@Override
	public int addAppSuggest(AppFeedback appFeedback) {
	    return feedbackMapper.insertAppSuggest(appFeedback);
	}
	
	/**
     * 修改反馈功能
     * 
     * @param appFeedback 反馈功能信息
     * @return 结果
     */
	@Override
	public int updateAppSuggest(AppFeedback appFeedback) {
	    return feedbackMapper.updateAppSuggest(appFeedback);
	}

	/**
     * 删除反馈功能对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAppSuggestByIds(String ids) {
		return feedbackMapper.deleteAppSuggestByIds(Convert.toStrArray(ids));
	}
	
}
