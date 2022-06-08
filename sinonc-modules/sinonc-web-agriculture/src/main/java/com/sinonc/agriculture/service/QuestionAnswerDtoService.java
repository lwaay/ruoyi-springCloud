package com.sinonc.agriculture.service;

import com.sinonc.agriculture.domain.QuestionAnswer;
import com.sinonc.agriculture.dto.QuestionAnswerDto;

import java.util.List;

public interface QuestionAnswerDtoService {

    List<QuestionAnswerDto> getQuestionAnswerDtoList(QuestionAnswer questionAnswer, Boolean login);

}
