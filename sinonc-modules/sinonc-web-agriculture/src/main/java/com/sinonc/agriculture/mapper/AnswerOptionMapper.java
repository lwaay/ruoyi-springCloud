package com.sinonc.agriculture.mapper;

import com.sinonc.agriculture.domain.AnswerOption;

import java.util.List;

public interface AnswerOptionMapper {

    int deleteByPrimaryKey(Long opId);

    int insert(AnswerOption record);

    int insertSelective(AnswerOption record);

    AnswerOption selectByPrimaryKey(Long opId);

    int updateByPrimaryKeySelective(AnswerOption record);

    int updateByPrimaryKey(AnswerOption record);

    List<AnswerOption> selectAnswerOptionList(AnswerOption option);

    AnswerOption selectOneAnswerOption(Long memberId, Long answerId);

    List<AnswerOption> selectListAnswerOption(Long memberId, Long questionId);

    List<AnswerOption> selectAnswerOptionPraise(AnswerOption option);
}