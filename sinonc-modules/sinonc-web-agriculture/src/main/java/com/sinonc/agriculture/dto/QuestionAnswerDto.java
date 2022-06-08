package com.sinonc.agriculture.dto;

import com.sinonc.agriculture.domain.ExpertInfo;
import com.sinonc.agriculture.domain.MemberInfo;
import com.sinonc.agriculture.domain.QuestionAnswer;
import com.sinonc.system.api.domain.WxUser;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class QuestionAnswerDto extends QuestionAnswer {

//    /**
//     * 回答问题的用户信息
//     */
//    private MemberInfo memberInfo;
    /**
     * 回答问题的专家
     */
    private ExpertInfo expertInfo;

    /**
     * 当前登陆的会员发表的观点
     */
    private Integer opType;


    /**
     * 已经发表过观点的用户列表
     */
    private List<Map<String, Object>> opList;

    private WxUser wxUser;

}
