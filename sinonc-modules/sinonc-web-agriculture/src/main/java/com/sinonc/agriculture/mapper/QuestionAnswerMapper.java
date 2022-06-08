package com.sinonc.agriculture.mapper;

import com.sinonc.agriculture.domain.QuestionAnswer;

import java.util.List;
import java.util.Map;

/**
 * 问题回答Mapper接口
 * 
 * @author ruoyi
 * @date 2020-03-10
 */
public interface QuestionAnswerMapper 
{
    /**
     * 查询问题回答
     * 
     * @param answerId 问题回答ID
     * @return 问题回答
     */
    public QuestionAnswer selectQuestionAnswerById(Long answerId);

    /**
     * 查询问题回答列表
     * 
     * @param questionAnswer 问题回答
     * @return 问题回答集合
     */
    public List<QuestionAnswer> selectQuestionAnswerList(QuestionAnswer questionAnswer);

    /**
     * 新增问题回答
     * 
     * @param questionAnswer 问题回答
     * @return 结果
     */
    public int insertQuestionAnswer(QuestionAnswer questionAnswer);

    /**
     * 修改问题回答
     * 
     * @param questionAnswer 问题回答
     * @return 结果
     */
    public int updateQuestionAnswer(QuestionAnswer questionAnswer);

    /**
     * 删除问题回答
     * 
     * @param answerId 问题回答ID
     * @return 结果
     */
    public int deleteQuestionAnswerById(Long answerId);

    /**
     * 批量删除问题回答
     * 
     * @param answerIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteQuestionAnswerByIds(String[] answerIds);

    /**
     * 根据专家会员id查询此专家回答的问题
     *
     * @param memberId
     * @return
     */
    List<Map<String, Object>> getQuestionAnswerByMemberId(Long memberId);

    /**
     * 查找最佳答案
     *
     * @param questionId
     * @return
     */
    QuestionAnswer selectBestAnswer(Long questionId);
}
