package com.sinonc.agriculture.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.sinonc.agriculture.constants.ConcernInfoConstants;
import com.sinonc.agriculture.constants.MemberNoticeConstants;
import com.sinonc.agriculture.domain.*;
import com.sinonc.agriculture.mapper.AgriQuestionMapper;
import com.sinonc.agriculture.mapper.ConcernInfoMapper;
import com.sinonc.agriculture.mapper.MemberInfoMapper;
import com.sinonc.agriculture.mapper.MemberNoticeMapper;
import com.sinonc.agriculture.service.ExpertInfoService;
import com.sinonc.agriculture.service.QuestionNotifyService;
import com.sinonc.system.api.RemoteUserService;
import com.sinonc.system.api.domain.SysUser;
import com.sinonc.system.api.domain.WxUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionNotifyServiceImpl implements QuestionNotifyService {

    @Autowired
    private MemberNoticeMapper noticeMapper;

    @Autowired
    private ConcernInfoMapper concernInfoMapper;

    @Autowired
    private AgriQuestionMapper questionMapper;

    @Autowired
    private RemoteUserService userService;



    /**
     * 邀请会员来回答通知
     *
     * @param question  问题
     * @param memberIds 被邀请的会员Id
     */
    @Async("notifyAsync")
    @Override
    public void invitationToAnswer(AgriQuestion question, Long[] memberIds) {

//        List<MemberInfo> memberInfoList = memberInfoMapper.selectMemberInfoByIds(memberIds);
        List<WxUser> userInfoList = userService.infos(memberIds).getData();
        List<MemberNotice> memberNotices = new ArrayList<>(memberIds.length);

        Date date = new Date();

//        for (MemberInfo memberInfo : memberInfoList) {
//
//            HashMap<String, Object> content = new HashMap<>();
//
//            content.put("headImg", memberInfo.getHeadImg());
//            content.put("nikeName", memberInfo.getNikeName());
//            content.put("ask", question.getAsk());
//            content.put("questionId", question.getQuestionId());
//            content.put("createTime", date);
//
//            MemberNotice memberNotice = new MemberNotice();
//
//            memberNotice.setCreateTime(date);
//            memberNotice.setUpdateTime(date);
//            memberNotice.setMemberId(memberInfo.getMemberId());
//            memberNotice.setType(MemberNoticeConstants.TYPE_INVITATION_ANSWER);
//            memberNotice.setNoticeContent(JSONUtils.toJSONString(content));
//
//            memberNotices.add(memberNotice);
//        }

        for (WxUser userInfo : userInfoList) {

            HashMap<String, Object> content = new HashMap<>();

            content.put("headImg", userInfo.getHeadimg());
            content.put("nikeName", userInfo.getName());
            content.put("ask", question.getAsk());
            content.put("questionId", question.getQuestionId());
            content.put("createTime", date);

            MemberNotice memberNotice = new MemberNotice();

            memberNotice.setCreateTime(date);
            memberNotice.setUpdateTime(date);
            memberNotice.setMemberId(userInfo.getId());
            memberNotice.setType(MemberNoticeConstants.TYPE_INVITATION_ANSWER);
            memberNotice.setNoticeContent(JSONUtils.toJSONString(content));

            memberNotices.add(memberNotice);
        }
        if (userInfoList.size() > 0) {
            noticeMapper.batchInsert(memberNotices);
        }


    }

    @Async("notifyAsync")
    @Override
    public void newAnswerNotify(QuestionAnswer questionAnswer) {


        ConcernInfo concernInfo = new ConcernInfo();
        concernInfo.setTargetType(ConcernInfoConstants.CONCERN_INFO_QUESTION);
        concernInfo.setTargetId(questionAnswer.getQuestionId());

//        MemberInfo memberInfo = memberInfoMapper.selectMemberInfoById(questionAnswer.getMemberId());
        WxUser userInfo = userService.getWxUserById(questionAnswer.getMemberId()).getData();
        AgriQuestion agriQuestion = questionMapper.selectAgriQuestionById(questionAnswer.getQuestionId());

        List<ConcernInfo> concernInfos = concernInfoMapper.selectConcernInfoList(concernInfo);
        List<MemberNotice> noticeList = new ArrayList<>(concernInfos.size());

        Date date = new Date();

        for (ConcernInfo info : concernInfos) {

            Map<String, Object> content = new HashMap<>();

//            content.put("headImg", memberInfo.getHeadImg());
//            content.put("nickName", memberInfo.getNikeName());
            content.put("headImg", userInfo.getHeadimg());
            content.put("nickName", userInfo.getName());
            content.put("ask", agriQuestion.getAsk());
            content.put("questionId", agriQuestion.getQuestionId());
            content.put("answerId", questionAnswer.getAnswerId());
            content.put("createTime", date);

            MemberNotice memberNotice = new MemberNotice();

            memberNotice.setMemberId(info.getMemberId());
            memberNotice.setCreateTime(date);
            memberNotice.setType(MemberNoticeConstants.TYPE_QUESTION_NEW_ANSWER);
            memberNotice.setUpdateTime(date);
            memberNotice.setNoticeContent(JSONUtils.toJSONString(content));

            noticeList.add(memberNotice);
        }

        noticeMapper.batchInsert(noticeList);
    }
}
