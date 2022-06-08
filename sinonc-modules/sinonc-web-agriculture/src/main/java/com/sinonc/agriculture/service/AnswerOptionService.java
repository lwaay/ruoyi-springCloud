package com.sinonc.agriculture.service;

import com.sinonc.agriculture.domain.AnswerOption;

import java.util.List;

public interface AnswerOptionService {

    /**
     * 添加问题选项（支持或反对）
     *
     * @param option
     * @return
     */
    int addOption(AnswerOption option);


    /**
     * 获取称赞答案数
     *
     * @param option
     * @return
     */
    List<AnswerOption> selectAnswerOptionPraise(AnswerOption option);

}
