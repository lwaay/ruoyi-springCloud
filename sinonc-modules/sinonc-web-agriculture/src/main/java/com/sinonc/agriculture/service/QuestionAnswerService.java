package com.sinonc.agriculture.service;

import com.sinonc.agriculture.domain.QuestionAnswer;

import java.util.List;
import java.util.Map;

/**
 * 问题回答Service接口
 * 
 * @author ruoyi
 * @date 2020-03-10
 */
public interface QuestionAnswerService
{
    /**
     * 查询问题回答
     * 
     * @param answerId 问题回答ID
     * @return 问题回答
     */
    public QuestionAnswer getQuestionAnswerById(Long answerId);

    /**
     * 查询问题回答列表
     * 
     * @param questionAnswer 问题回答
     * @return 问题回答集合
     */
    public List<QuestionAnswer> getQuestionAnswerList(QuestionAnswer questionAnswer);

    /**
     * 新增问题回答
     * 
     * @param questionAnswer 问题回答
     * @return 结果
     */
    public int addQuestionAnswer(QuestionAnswer questionAnswer);

    /**
     * 修改问题回答
     * 
     * @param questionAnswer 问题回答
     * @return 结果
     */
    public int updateQuestionAnswer(QuestionAnswer questionAnswer);

    /**
     * 批量删除问题回答
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteQuestionAnswerByIds(String ids);

    /**
     * 删除问题回答信息
     * 
     * @param answerId 问题回答ID
     * @return 结果
     */
    public int deleteQuestionAnswerById(Long answerId);

    /**
     * 根据专家会员id查询此专家回答的问题
     *
     * @param memberId
     * @return
     */
    List<Map<String, Object>> getQuestionAnswerByMemberId(Long memberId);


    int setBestAnswer(Long questionId, Long answerId);

}
