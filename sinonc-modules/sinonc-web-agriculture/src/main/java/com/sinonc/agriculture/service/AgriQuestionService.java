package com.sinonc.agriculture.service;

import com.sinonc.agriculture.domain.AgriQuestion;

import java.util.List;
import java.util.Map;

/**
 * 农业问题Service接口
 * 
 * @author ruoyi
 * @date 2020-03-07
 */
public interface AgriQuestionService
{
    /**
     * 查询农业问题
     * 
     * @param questionId 农业问题ID
     * @return 农业问题
     */
    public AgriQuestion getAgriQuestionById(Long questionId);

    /**
     * 查询农业问题列表
     * 
     * @param agriQuestion 农业问题
     * @return 农业问题集合
     */
    public List<AgriQuestion> getAgriQuestionList(AgriQuestion agriQuestion);

    /**
     * 新增农业问题
     *
     * @param agriQuestion 农业问题
     * @return 结果
     */
    public int addAgriQuestion(AgriQuestion agriQuestion, Long memberId);

    /**
     * 修改农业问题
     * 
     * @param agriQuestion 农业问题
     * @return 结果
     */
    public int updateAgriQuestion(AgriQuestion agriQuestion);

    /**
     * 批量删除农业问题
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAgriQuestionByIds(String ids);

    /**
     * 删除农业问题信息
     * 
     * @param questionId 农业问题ID
     * @return 结果
     */
    public int deleteAgriQuestionById(Long questionId);

    /**
     * 根据问题id查询问题
     *
     * @param questionId
     * @return
     */
    Map<String, Object> getAgriQuestionByIdForMap(Long questionId);

    /**
     * 根据回答查询问题
     *
     * @param agriQuestion
     * @return
     */
    public List<AgriQuestion> selectAgriQuestionListByAnswer(AgriQuestion agriQuestion);

    /**
     * 根据关注查询问题
     *
     * @param agriQuestion
     * @return
     */
    public List<AgriQuestion> selectAgriQuestionListByConcernInfo(AgriQuestion agriQuestion);
}
