package com.sinonc.agriculture.service.impl;

import com.sinonc.agriculture.domain.AnswerOption;
import com.sinonc.agriculture.domain.QuestionAnswer;
import com.sinonc.agriculture.mapper.AnswerOptionMapper;
import com.sinonc.agriculture.mapper.QuestionAnswerMapper;
import com.sinonc.agriculture.service.AnswerOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.TimerTask;

@Service
public class AnswerOptionServiceImpl implements AnswerOptionService {

    @Autowired
    private AnswerOptionMapper optionMapper;

    @Autowired
    private QuestionAnswerMapper answerMapper;

    @Autowired
    @Qualifier("threadPoolTaskExecutor")
    private ThreadPoolTaskExecutor executor;

    /**
     * 添加选项
     *
     * @param option 选项
     * @return 结果
     */
    @Override
    public int addOption(AnswerOption option) {

        AnswerOption answerOption = optionMapper.selectOneAnswerOption(option.getMemberId(), option.getAnswerId());

        if (answerOption != null) {
            throw new RuntimeException("不可重复操作");
        }

        option.setCreateTime(new Date());
        int i = optionMapper.insertSelective(option);

        executor.execute(new TimerTask() {
            @Override
            public void run() {

                QuestionAnswer questionAnswer = answerMapper.selectQuestionAnswerById(option.getAnswerId());

                if (questionAnswer != null) {
                    if (option.getOpType() == 0) {
                        questionAnswer.setFavorCount(questionAnswer.getFavorCount() + 1);
                    } else {
                        questionAnswer.setOpposeCount(questionAnswer.getOpposeCount() + 1);
                    }

                    answerMapper.updateQuestionAnswer(questionAnswer);
                }
            }
        });

        return i;

    }

    @Override
    public List<AnswerOption> selectAnswerOptionPraise(AnswerOption option) {
        List<AnswerOption> list = optionMapper.selectAnswerOptionPraise(option);
        return list;
    }
}
