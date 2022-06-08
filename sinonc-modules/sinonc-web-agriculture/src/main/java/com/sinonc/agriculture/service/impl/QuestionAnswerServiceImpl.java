package com.sinonc.agriculture.service.impl;

import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.core.text.Convert;
import com.sinonc.agriculture.constants.ListQuestionAnswerConstants;
import com.sinonc.agriculture.constants.MemberDictConstants;
import com.sinonc.agriculture.domain.AgriQuestion;
import com.sinonc.agriculture.domain.MemberDict;
import com.sinonc.agriculture.domain.QuestionAnswer;
import com.sinonc.agriculture.mapper.AgriQuestionMapper;
import com.sinonc.agriculture.mapper.MemberDictMapper;
import com.sinonc.agriculture.mapper.QuestionAnswerMapper;
import com.sinonc.agriculture.service.OwnDynamicService;
import com.sinonc.agriculture.service.QuestionAnswerService;
import com.sinonc.agriculture.service.QuestionNotifyService;
import com.sinonc.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

/**
 * 问题回答Service业务层处理
 *
 * @author ruoyi
 * @date 2020-03-10
 */
@Service
public class QuestionAnswerServiceImpl implements QuestionAnswerService {

    @Autowired
    private QuestionAnswerMapper answerMapper;

    @Autowired
    private MemberDictMapper memberDictMapper;

    @Autowired
    private AgriQuestionMapper questionMapper;

    @Autowired
    private OwnDynamicService ownDynamicService;

    @Autowired
    @Qualifier("threadPoolTaskExecutor")
    private ThreadPoolTaskExecutor executor;

    @Autowired
    private QuestionNotifyService notifyService;

    /**
     * 查询问题回答
     *
     * @param answerId 问题回答ID
     * @return 问题回答
     */
    @Override
    public QuestionAnswer getQuestionAnswerById(Long answerId) {
        return answerMapper.selectQuestionAnswerById(answerId);
    }

    /**
     * 查询问题回答列表
     *
     * @param questionAnswer 问题回答
     * @return 问题回答
     */
    @Override
    public List<QuestionAnswer> getQuestionAnswerList(QuestionAnswer questionAnswer) {
        return answerMapper.selectQuestionAnswerList(questionAnswer);
    }

    /**
     * 新增问题回答
     *
     * @param questionAnswer 问题回答
     * @return 结果
     */
    @Override
    public int addQuestionAnswer(QuestionAnswer questionAnswer) {

        Date date = new Date();

        questionAnswer.setCreateBy(SecurityUtils.getUsername());
        questionAnswer.setUpdateBy("member");
        questionAnswer.setUpdateTime(date);
        questionAnswer.setCreateTime(date);


        //回答入库
        int i = answerMapper.insertQuestionAnswer(questionAnswer);

        AgriQuestion agriQuestion = questionMapper.selectAgriQuestionById(questionAnswer.getQuestionId());

        //添加问题至动态表
        i = i + ownDynamicService.addOwnQuestionDynamic(agriQuestion, questionAnswer.getAnswerId(), ListQuestionAnswerConstants.OwnAnswerQuestion, questionAnswer.getMemberId());

        //更新用户字典表
        executor.execute(new TimerTask() {
            @Override
            public void run() {

                MemberDict memberDict = memberDictMapper.selectDictByMemberIdAndType(questionAnswer.getMemberId(), MemberDictConstants.TYPE_ANSWER_TOTAL);

                Date date = new Date();

                //判断用户字典是否存在
                if (memberDict == null) {

                    memberDict = new MemberDict();
                    memberDict.setDictType(MemberDictConstants.TYPE_ANSWER_TOTAL);
                    memberDict.setMemberId(questionAnswer.getMemberId());
                    memberDict.setDictValue(String.valueOf(1));
                    memberDict.setCreateBy("system");
                    memberDict.setCreateBy("system");
                    memberDict.setCreateTime(date);
                    memberDict.setUpdateTime(date);

                    memberDictMapper.insertMemberDict(memberDict);
                } else {

                    MemberDict update = new MemberDict();

                    update.setDictId(memberDict.getDictId());
                    update.setDictValue(String.valueOf(Integer.parseInt(memberDict.getDictValue()) + 1));
                    update.setUpdateTime(date);

                    memberDictMapper.updateMemberDict(update);
                }

                //更新问题状态
                AgriQuestion agriQuestion = questionMapper.selectAgriQuestionById(questionAnswer.getQuestionId());

                if (agriQuestion != null) {

                    AgriQuestion update = new AgriQuestion();

                    update.setQuestionId(questionAnswer.getQuestionId());
                    update.setLastAnswerTime(date);
                    update.setAnswerCount(agriQuestion.getAnswerCount() + 1);

                    questionMapper.updateAgriQuestion(update);
                }
            }
        });

        //发送回答通知至关注者
        notifyService.newAnswerNotify(questionAnswer);

        return i;
    }

    /**
     * 修改问题回答
     *
     * @param questionAnswer 问题回答
     * @return 结果
     */
    @Override
    public int updateQuestionAnswer(QuestionAnswer questionAnswer) {
        questionAnswer.setUpdateTime(DateUtils.getNowDate());
        return answerMapper.updateQuestionAnswer(questionAnswer);
    }

    /**
     * 删除问题回答对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteQuestionAnswerByIds(String ids) {
        return answerMapper.deleteQuestionAnswerByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除问题回答信息
     *
     * @param answerId 问题回答ID
     * @return 结果
     */
    @Override
    public int deleteQuestionAnswerById(Long answerId) {
        return answerMapper.deleteQuestionAnswerById(answerId);
    }

    /**
     * 根据专家会员id查询此专家回答的问题
     *
     * @param memberId
     * @return
     */
    @Override
    public List<Map<String, Object>> getQuestionAnswerByMemberId(Long memberId) {
        return answerMapper.getQuestionAnswerByMemberId(memberId);
    }

    /**
     * 设置最佳答案
     *
     * @param questionId
     * @param answerId
     * @return
     */
    @Override
    public int setBestAnswer(Long questionId, Long answerId) {

        QuestionAnswer bestAnswer = answerMapper.selectBestAnswer(questionId);

        if (bestAnswer != null) {
            throw new RuntimeException("不可重复设置最佳答案");
        }

        QuestionAnswer questionAnswer = new QuestionAnswer();
        questionAnswer.setAnswerId(answerId);
        questionAnswer.setBestFlag(1);
        questionAnswer.setUpdateTime(new Date());

        return answerMapper.updateQuestionAnswer(questionAnswer);

    }
}
