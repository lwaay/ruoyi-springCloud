package com.sinonc.agriculture.dto;

import com.sinonc.agriculture.domain.QuestionAnswer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 农业问题DTO
 */
@Getter
@Setter
public class AgriQuestionDto {

    /**
     * 问题Id
     */
    private Long questionId;

    /**
     * 提问
     */
    private String ask;

    /**
     * 问题图片
     */
    private String img;

    /**
     * 回答列表
     */
    private List<QuestionAnswer> questionAnswers;
}
