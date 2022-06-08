package com.sinonc.agriculture.dto;


import com.sinonc.agriculture.domain.AgriQuestion;
import com.sinonc.agriculture.domain.MemberInfo;
import com.sinonc.system.api.domain.WxUser;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * 问题简要信息
 */
@Getter
@Setter
public class AgriQuestionSimpleDto extends AgriQuestion {

    /**
     * 提问的会员信息
     */
    private WxUser wxUser;

    /**
     * 关注的信息
     */
    private List<Map<String, Object>> concerns;

    /**
     * 总关注数
     */
    private Integer concernCount;

    /**
     * 是否已经关注，用户登陆后有该字段为用户实际关注状态
     */
    private Boolean isConcern = false;

    /**
     * 回答总数
     */
    private Integer answerCount;

    private List<QuestionAnswerDto> answers;

    private QuestionAnswerDto answer;


}
