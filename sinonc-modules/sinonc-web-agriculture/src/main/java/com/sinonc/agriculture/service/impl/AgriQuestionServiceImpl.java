package com.sinonc.agriculture.service.impl;

import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.core.text.Convert;
import com.sinonc.agriculture.constants.ListQuestionAnswerConstants;
import com.sinonc.agriculture.constants.MemberDictConstants;
import com.sinonc.agriculture.domain.AgriQuestion;
import com.sinonc.agriculture.domain.MemberDict;
import com.sinonc.agriculture.mapper.AgriQuestionMapper;
import com.sinonc.agriculture.service.AgriQuestionService;
import com.sinonc.agriculture.service.MemberDictService;
import com.sinonc.agriculture.service.OwnDynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

/**
 * 农业问题Service业务层处理
 *
 * @author ruoyi
 * @date 2020-03-07
 */
@Service
public class AgriQuestionServiceImpl implements AgriQuestionService {
    @Autowired
    private AgriQuestionMapper agriQuestionMapper;

    @Autowired
    private OwnDynamicService ownDynamicService;

    @Autowired
    @Qualifier("threadPoolTaskExecutor")
    private ThreadPoolTaskExecutor executor;

    @Autowired
    private MemberDictService memberDictService;

    /**
     * 查询农业问题
     *
     * @param questionId 农业问题ID
     * @return 农业问题
     */
    @Override
    public AgriQuestion getAgriQuestionById(Long questionId) {
        return agriQuestionMapper.selectAgriQuestionById(questionId);
    }

    /**
     * 查询农业问题列表
     *
     * @param agriQuestion 农业问题
     * @return 农业问题
     */
    @Override
    public List<AgriQuestion> getAgriQuestionList(AgriQuestion agriQuestion)
    {
        return agriQuestionMapper.selectAgriQuestionList(agriQuestion);
    }

    /**
     * 新增农业问题
     *
     * @param agriQuestion 农业问题
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addAgriQuestion(AgriQuestion agriQuestion, Long memberId) {
        Date date = DateUtils.getNowDate();

        agriQuestion.setCreateTime(date);
        agriQuestion.setCreateBy("member");
        agriQuestion.setUpdateBy("member");
        agriQuestion.setUpdateTime(date);

        int row = agriQuestionMapper.insertAgriQuestion(agriQuestion);

        row = row + ownDynamicService.addOwnQuestionDynamic(agriQuestion, agriQuestion.getQuestionId(), ListQuestionAnswerConstants.OwnQuestion, memberId);
        return row;
    }

    /**
     * 修改农业问题
     *
     * @param agriQuestion 农业问题
     * @return 结果
     */
    @Override
    public int updateAgriQuestion(AgriQuestion agriQuestion)
    {
        agriQuestion.setUpdateTime(DateUtils.getNowDate());
        return agriQuestionMapper.updateAgriQuestion(agriQuestion);
    }

    /**
     * 删除农业问题对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAgriQuestionByIds(String ids)
    {
        String[] questionArray=ids.split(",");
        int row=0;
        for (int i = 0; i < questionArray.length; i++) {
            Long tempId=Long.valueOf(questionArray[i]);
            AgriQuestion agriQuestion=agriQuestionMapper.selectAgriQuestionById(tempId);
            row=row+agriQuestionMapper.deleteAgriQuestionById(tempId);
            subtractDictCount(agriQuestion.getMemberId(), MemberDictConstants.TYPE_ADD_QUESTION_TOTAL);
        }
        return row;
    }

    /**
     * 字典值-1操作
     *
     * @param memberId 会员ID
     * @param dictType 字典类型
     */
    private void subtractDictCount(Long memberId, String dictType) {

        executor.execute(new TimerTask() {
            @Override
            public void run() {

                MemberDict memberDict = memberDictService.getDictByMemberIdAndType(memberId, dictType);

                if (memberDict != null) {

                    memberDict.setDictValue(String.valueOf(Integer.parseInt(memberDict.getDictValue()) - 1));
                    memberDict.setUpdateTime(new Date());
                    memberDictService.updateMemberDict(memberDict);
                }

            }
        });
    }

    /**
     * 删除农业问题信息
     *
     * @param questionId 农业问题ID
     * @return 结果
     */
    @Override
    public int deleteAgriQuestionById(Long questionId)
    {
        return agriQuestionMapper.deleteAgriQuestionById(questionId);
    }

    /**
     * 根据问题id查询问题
     *
     * @param questionId
     * @return
     */
    @Override
    public Map<String, Object> getAgriQuestionByIdForMap(Long questionId) {
        return agriQuestionMapper.getAgriQuestionByIdForMap(questionId);
    }

    @Override
    public List<AgriQuestion> selectAgriQuestionListByAnswer(AgriQuestion agriQuestion) {
        return agriQuestionMapper.selectAgriQuestionListByAnswer(agriQuestion);
    }

    @Override
    public List<AgriQuestion> selectAgriQuestionListByConcernInfo(AgriQuestion agriQuestion) {
        return agriQuestionMapper.selectAgriQuestionListByConcernInfo(agriQuestion);
    }
}
