package com.sinonc.agriculture.vo;

import com.sinonc.agriculture.domain.AgriQuestion;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgriQuestionVo extends AgriQuestion {

    /**
     * 被邀请回答的专家ID
     */
    private String expertIds;
}
