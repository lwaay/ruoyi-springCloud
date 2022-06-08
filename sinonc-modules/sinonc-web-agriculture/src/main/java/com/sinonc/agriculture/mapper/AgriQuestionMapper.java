package com.sinonc.agriculture.mapper;

import com.sinonc.agriculture.domain.AgriQuestion;

import java.util.List;
import java.util.Map;

/**
 * 农业问题Mapper接口
 * 
 * @author ruoyi
 * @date 2020-03-07
 */
public interface AgriQuestionMapper 
{
    /**
     * 查询农业问题
     * 
     * @param questionId 农业问题ID
     * @return 农业问题
     */
    public AgriQuestion selectAgriQuestionById(Long questionId);

    /**
     * 查询农业问题列表
     * 
     * @param agriQuestion 农业问题
     * @return 农业问题集合
     */
    public List<AgriQuestion> selectAgriQuestionList(AgriQuestion agriQuestion);

    /**
     * 新增农业问题
     * 
     * @param agriQuestion 农业问题
     * @return 结果
     */
    public int insertAgriQuestion(AgriQuestion agriQuestion);

    /**
     * 修改农业问题
     * 
     * @param agriQuestion 农业问题
     * @return 结果
     */
    public int updateAgriQuestion(AgriQuestion agriQuestion);

    /**
     * 删除农业问题
     * 
     * @param questionId 农业问题ID
     * @return 结果
     */
    public int deleteAgriQuestionById(Long questionId);

    /**
     * 批量删除农业问题
     * 
     * @param questionIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteAgriQuestionByIds(String[] questionIds);

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
    List<AgriQuestion> selectAgriQuestionListByAnswer(AgriQuestion agriQuestion);

    List<AgriQuestion> selectAgriQuestionListByConcernInfo(AgriQuestion agriQuestion);
}
