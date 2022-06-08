package com.sinonc.agriculture.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AgriQuestionDetailDto extends AgriQuestionSimpleDto {

    private QuestionAnswerDto bestAnswer;

    private List<QuestionAnswerDto> otherAnswers;
}
