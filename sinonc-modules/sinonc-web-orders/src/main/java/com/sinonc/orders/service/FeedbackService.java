package com.sinonc.orders.service;

import com.sinonc.orders.domain.AppFeedback;

import java.util.List;

/**
 * 反馈功能 服务层
 * 
 * @author wang
 * @date 2019-11-01
 */
public interface FeedbackService {

	/**
     * 查询反馈功能信息
     * 
     * @param feedbackId 反馈功能ID
     * @return 反馈功能信息
     */
	public AppFeedback getAppSuggestById(Long feedbackId);
	
	/**
     * 查询反馈功能列表
     * 
     * @param appFeedback 反馈功能信息
     * @return 反馈功能集合
     */
	public List<AppFeedback> listAppSuggest(AppFeedback appFeedback);
	
	/**
     * 新增反馈功能
     * 
     * @param appFeedback 反馈功能信息
     * @return 结果
     */
	public int addAppSuggest(AppFeedback appFeedback);
	
	/**
     * 修改反馈功能
     * 
     * @param appFeedback 反馈功能信息
     * @return 结果
     */
	public int updateAppSuggest(AppFeedback appFeedback);
		
	/**
     * 删除反馈功能信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAppSuggestByIds(String ids);
	
}
