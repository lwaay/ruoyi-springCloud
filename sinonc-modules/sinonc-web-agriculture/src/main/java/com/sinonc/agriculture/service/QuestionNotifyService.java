package com.sinonc.agriculture.service;

import com.sinonc.agriculture.domain.AgriQuestion;
import com.sinonc.agriculture.domain.QuestionAnswer;

/**
 * 问题通知服务
 */
public interface QuestionNotifyService {

    /**
     * 通知会员被邀请回答问题
     *
     * @param question  问题
     * @param memberIds 会员Id
     */
    public void invitationToAnswer(AgriQuestion question, Long[] memberIds);

    /**
     * 通知问题关注者，问题有新回答
     *
     * @param questionAnswer
     */
    public void newAnswerNotify(QuestionAnswer questionAnswer);

}
