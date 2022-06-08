package com.sinonc.agriculture.service.impl;

import com.sinonc.agriculture.domain.AnswerOption;
import com.sinonc.agriculture.domain.QuestionAnswer;
import com.sinonc.agriculture.dto.QuestionAnswerDto;
import com.sinonc.agriculture.mapper.AnswerOptionMapper;
import com.sinonc.agriculture.mapper.QuestionAnswerDtoMapper;
import com.sinonc.agriculture.service.QuestionAnswerDtoService;
import com.sinonc.common.security.service.TokenService;
import com.sinonc.system.api.RemoteWxUserService;
import com.sinonc.system.api.domain.WxUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionAnswerDtoServiceImpl implements QuestionAnswerDtoService {

    @Autowired
    private QuestionAnswerDtoMapper answerDtoMapper;

    @Autowired
    private AnswerOptionMapper optionMapper;

    @Autowired
    private RemoteWxUserService wxUserService;

    @Autowired
    private TokenService tokenService;

    @Override
    public List<QuestionAnswerDto> getQuestionAnswerDtoList(QuestionAnswer questionAnswer, Boolean login) {

        List<QuestionAnswerDto> questionAnswerDtoList = answerDtoMapper.selectQuestionAnswerDtoList(questionAnswer);

        if (login) {

            List<AnswerOption> answerOptions = optionMapper.selectListAnswerOption(tokenService.getLoginUser().getUserid(), questionAnswer.getQuestionId());
            for (QuestionAnswerDto questionAnswerDto : questionAnswerDtoList) {
                //添加回复用户信息
                WxUser wxUser = wxUserService.getUserById(questionAnswerDto.getMemberId()).getData();
                questionAnswerDto.setWxUser(wxUser);
                for (AnswerOption answerOption : answerOptions) {
                    if (answerOption.getAnswerId().equals(questionAnswerDto.getAnswerId())) {
                        questionAnswerDto.setOpType(answerOption.getOpType());
                        break;
                    }
                }
            }
        }

        return questionAnswerDtoList;
    }

}
