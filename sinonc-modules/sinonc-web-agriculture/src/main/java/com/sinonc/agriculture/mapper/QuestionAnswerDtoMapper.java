package com.sinonc.agriculture.mapper;

import com.sinonc.agriculture.domain.QuestionAnswer;
import com.sinonc.agriculture.dto.QuestionAnswerDto;

import java.util.List;

public interface QuestionAnswerDtoMapper {

    List<QuestionAnswerDto> selectQuestionAnswerDtoList(QuestionAnswer questionAnswer);
}
