package com.sinonc.agriculture.service.impl;

import com.google.common.collect.Lists;
import com.sinonc.agriculture.constants.ConcernInfoConstants;
import com.sinonc.agriculture.domain.*;
import com.sinonc.agriculture.dto.*;
import com.sinonc.agriculture.mapper.AgriQuestionDtoMapper;
import com.sinonc.agriculture.mapper.AgriQuestionMapper;
import com.sinonc.agriculture.mapper.AnswerOptionMapper;
import com.sinonc.agriculture.mapper.ConcernInfoMapper;
import com.sinonc.agriculture.service.*;
import com.sinonc.common.security.service.TokenService;
import com.sinonc.system.api.RemoteWxUserService;
import com.sinonc.system.api.domain.WxUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AgriQuestionDtoServiceImpl implements AgriQuestionDtoService {

    @Autowired
    private QuestionAnswerService questionAnswerService;

    @Autowired
    private AgriQuestionDtoMapper questionDtoMapper;

    @Autowired
    private AgriQuestionMapper questionMapper;

    @Autowired
    private ConcernInfoMapper concernInfoMapper;

    @Autowired
    private AnswerOptionMapper optionMapper;

    @Autowired
    private RemoteWxUserService wxUserService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private QuestionAnswerDtoService answerDtoService;

    @Autowired
    private ExpertInfoService expertInfoService;

    @Value("${push.agri.question.limiter:3}")
    private Integer pushAgriQuestionLimiter;

    @Override
    public List<AgriQuestionSimpleDto> getAgriQuestionSimpleDtoList(Boolean isLogin, String ask, Long cropId, Long[] parentId, Long sectionId) {

        List<AgriQuestionSimpleDto> agriQuestionSimpleDtoList = questionDtoMapper.selectAgriQuestionSimpleDtoList(ask, cropId, parentId, sectionId);
        log.info("agriQuestionSimpleDtoList size:" + agriQuestionSimpleDtoList.size());

        iniisConcern(isLogin, agriQuestionSimpleDtoList);

        return agriQuestionSimpleDtoList;
    }

    private void iniisConcern(Boolean isLogin, List<AgriQuestionSimpleDto> agriQuestionSimpleDtoList) {
        agriQuestionSimpleDtoList.forEach(entity -> {
            WxUser wxUser = wxUserService.getWxUserByMemberId(entity.getMemberId()).getData();
            entity.setWxUser(wxUser);
        });

        //判断是否登陆，如果已经登陆则需要查询该问题是否已经被关注过
        if (!isLogin) {
            return;
        }

        List<Long> questionIds = new ArrayList<>(agriQuestionSimpleDtoList.size());


        for (AgriQuestionSimpleDto agriQuestionSimpleDto : agriQuestionSimpleDtoList) {
            questionIds.add(agriQuestionSimpleDto.getQuestionId());
        }

        List<ConcernInfo> concernInfos = concernInfoMapper.selectConcernInfoByTargetIds(tokenService.getLoginUser().getUserid(), ConcernInfoConstants.CONCERN_INFO_QUESTION, questionIds);

        for (AgriQuestionSimpleDto questionSimpleDto : agriQuestionSimpleDtoList) {
            for (ConcernInfo concernInfo : concernInfos) {
                if (questionSimpleDto.getQuestionId().equals(concernInfo.getTargetId())) {
                    questionSimpleDto.setIsConcern(true);
                    break;
                }
            }
        }

    }

    private void initAnswer(List<AgriQuestionSimpleDto> agriQuestionSimpleDtoList){
        agriQuestionSimpleDtoList.forEach(entity -> {
            QuestionAnswer questionAnswer = new QuestionAnswer();
            questionAnswer.setQuestionId(entity.getQuestionId());
            List<QuestionAnswerDto> questionAnswerList = answerDtoService.getQuestionAnswerDtoList(questionAnswer, !ObjectUtils.isEmpty(tokenService.getLoginUser()));
            if (questionAnswerList.size() > 0){
                QuestionAnswerDto questionAnswerDto = questionAnswerList.get(0);
                questionAnswerDto.setExpertInfo(expertInfoService.selectExpertInfoByUserId(questionAnswerDto.getMemberId()));
                entity.setAnswer(questionAnswerDto);
            }
        });
    }

    @Override
    public AgriQuestionDetailDto getAgriQuestionDetailDto(Boolean isLogin, Long questionId) {


        AgriQuestionDetailDto agriQuestionDetailDto = questionDtoMapper.selectAgriQuestionDetailDto(questionId);
        if (agriQuestionDetailDto == null) {
            return null;
        }

        if (!isLogin) {

            agriQuestionDetailDto.setIsConcern(false);

            //更新阅读数
            AgriQuestion agriQuestion = new AgriQuestion();
            agriQuestion.setReadCount(agriQuestionDetailDto.getReadCount() + 1);
            agriQuestion.setQuestionId(agriQuestionDetailDto.getQuestionId());

            questionMapper.updateAgriQuestion(agriQuestion);

        } else {

            //查找是否已经关注过该问题
            ConcernInfo concernInfo = concernInfoMapper.selectMemberConcernInfoByTargetId(tokenService.getLoginUser().getUserid(), ConcernInfoConstants.CONCERN_INFO_QUESTION, questionId);

            if (concernInfo != null) {
                agriQuestionDetailDto.setIsConcern(true);
            } else {
                agriQuestionDetailDto.setIsConcern(false);
            }


            //查找用户是否对该答案表达过态度
            List<AnswerOption> answerOptions = optionMapper.selectListAnswerOption(tokenService.getLoginUser().getUserid(), questionId);

            //最佳答案
            QuestionAnswerDto bestAnswer = agriQuestionDetailDto.getBestAnswer();

            if (bestAnswer != null) {
                synMemberInfo(bestAnswer);
                for (AnswerOption answerOption : answerOptions) {
                    if (answerOption.getAnswerId().equals(bestAnswer.getAnswerId())) {
                        bestAnswer.setOpType(answerOption.getOpType());
                        break;
                    }
                }
            }


            //其他答案
            for (QuestionAnswerDto answer : agriQuestionDetailDto.getOtherAnswers()) {
                synMemberInfo(answer);
                for (AnswerOption option : answerOptions) {
                    if (answer.getAnswerId().equals(option.getAnswerId())) {
                        answer.setOpType(option.getOpType());
                        break;
                    }
                }
            }

            //if (!tokenService.getLoginUser().getUserid().equals(agriQuestionDetailDto.getMemberInfo().getMemberId())) {
            //更新阅读数,原逻辑为如果测试提问人和查看人一致则不增加阅读数
            AgriQuestion agriQuestion = new AgriQuestion();
            agriQuestion.setReadCount(agriQuestionDetailDto.getReadCount() + 1);
            agriQuestion.setQuestionId(agriQuestionDetailDto.getQuestionId());

            questionMapper.updateAgriQuestion(agriQuestion);
            //}


        }

        return agriQuestionDetailDto;
    }

    /**
     * 同步名称  -》 question_answer 表中有 用户名 nickname ，如果修改了用户名 则不会更新表中的 nickname
     * 这里手动更新一下 nickname
     *
     * @date 2020年7月1日
     */
    private void synMemberInfo(QuestionAnswerDto questionAnswerDto) {
        Long memberId = questionAnswerDto.getMemberId();
        WxUser memberInfo = wxUserService.getWxUserByMemberId(memberId).getData();
        questionAnswerDto.setNikeName(memberInfo.getName());
    }

    @Override
    public List<AgriQuestionSimpleDto> getAgriQuestionList(Boolean isLogin, AgriQuestion agriQuestion) {
        List<AgriQuestionSimpleDto> agriQuestionSimpleDtoList = questionDtoMapper.selectOwmAgriQuestionList(agriQuestion);
        if (agriQuestionSimpleDtoList != null && agriQuestionSimpleDtoList.size() > 0) {
            iniisConcern(isLogin, agriQuestionSimpleDtoList);
            initAnswer(agriQuestionSimpleDtoList);
        }
        return agriQuestionSimpleDtoList;
    }

    @Override
    public List<AgriQuestionSimpleDto> selectAgriQuestionListByAnswer(Boolean isLogin, AgriQuestion agriQuestion) {
        List<AgriQuestionSimpleDto> agriQuestionSimpleDtoList = questionDtoMapper.selectAgriQuestionListByAnswer(agriQuestion);
        if (agriQuestionSimpleDtoList != null && agriQuestionSimpleDtoList.size() > 0) {
            iniisConcern(isLogin, agriQuestionSimpleDtoList);
            initAnswer(agriQuestionSimpleDtoList);

        }

        return agriQuestionSimpleDtoList;
    }

    @Override
    public List<AgriQuestionSimpleDto> selectAgriQuestionListByConcernInfo(Boolean isLogin, AgriQuestion agriQuestion) {
        List<AgriQuestionSimpleDto> agriQuestionSimpleDtoList = questionDtoMapper.selectAgriQuestionListByConcernInfo(agriQuestion);
        if (agriQuestionSimpleDtoList != null && agriQuestionSimpleDtoList.size() > 0) {
            iniisConcern(isLogin, agriQuestionSimpleDtoList);
            initAnswer(agriQuestionSimpleDtoList);
        }

        return agriQuestionSimpleDtoList;
    }

    @Override
    public List<AgriQuestionSectionDto> selectAgriQuestionSectionList(Long memberId){
        return questionDtoMapper.selectAgriQuestionSectionList(memberId);
    }

    @Override
    public List<AgriQuestionDto> pushAgriQuestionBySection(List<Long> sectionIds) {
        List<AgriQuestionDto> dtos = questionDtoMapper.selectAgriQuestionBySection(
                sectionIds, pushAgriQuestionLimiter);
        if(CollectionUtils.isNotEmpty(dtos)){
            dtos.forEach(e -> {
                QuestionAnswer questionAnswer = new QuestionAnswer();
                questionAnswer.setQuestionId(e.getQuestionId());
                List<QuestionAnswer> questionAnswers =
                        questionAnswerService.getQuestionAnswerList(questionAnswer);
                e.setQuestionAnswers(questionAnswers);
            });
        }
        return dtos;
    }
}
