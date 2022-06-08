package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.AppFeedback;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 反馈功能 数据层
 * 
 * @author wang
 * @date 2019-11-01
 */
@Mapper
public interface FeedbackMapper {
	/**
     * 查询反馈功能信息
     * 
     * @param feedbackId 反馈功能ID
     * @return 反馈功能信息
     */
	public AppFeedback selectAppSuggestById(Long feedbackId);
	
	/**
     * 查询反馈功能列表
     * 
     * @param appFeedback 反馈功能信息
     * @return 反馈功能集合
     */
	public List<AppFeedback> selectAppSuggestList(AppFeedback appFeedback);
	
	/**
     * 新增反馈功能
     * 
     * @param appFeedback 反馈功能信息
     * @return 结果
     */
	public int insertAppSuggest(AppFeedback appFeedback);
	
	/**
     * 修改反馈功能
     * 
     * @param appFeedback 反馈功能信息
     * @return 结果
     */
	public int updateAppSuggest(AppFeedback appFeedback);
	
	/**
     * 删除反馈功能
     * 
     * @param FeedbackId 反馈功能ID
     * @return 结果
     */
	public int deleteAppSuggestById(Long FeedbackId);
	
	/**
     * 批量删除反馈功能
     * 
     * @param FeedbackIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteAppSuggestByIds(String[] FeedbackIds);
	
}