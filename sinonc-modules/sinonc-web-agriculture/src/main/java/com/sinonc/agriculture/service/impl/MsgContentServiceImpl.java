package com.sinonc.agriculture.service.impl;

import com.sinonc.agriculture.domain.MsgContent;
import com.sinonc.agriculture.domain.MsgSession;
import com.sinonc.agriculture.mapper.MsgContentMapper;
import com.sinonc.agriculture.mapper.MsgSessionMapper;
import com.sinonc.agriculture.service.MsgContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class MsgContentServiceImpl implements MsgContentService {

    @Autowired
    private MsgContentMapper contentMapper;

    @Autowired
    private MsgSessionMapper sessionMapper;

    /**
     * 发送消息
     *
     * @param msgContent 消息实体
     * @return 结果
     */
    @Override
    public int sendMsg(MsgContent msgContent) {

        Date date = new Date();

        MsgSession msgSessions = sessionMapper.selectBySessionIdAndMemberId(msgContent.getSessionId(), msgContent.getToMemberId());

        if (msgSessions == null) {
            throw new RuntimeException("会话不存在");
        }

        msgSessions.setDelFlag(0);
        msgSessions.setUpdateTime(date);
        msgSessions.setLastMsgContent(msgContent.getContent());
        msgSessions.setUnreadCount(msgSessions.getUnreadCount() + 1);

        sessionMapper.updateByPrimaryKeySelective(msgSessions);

        msgContent.setCreateTime(date);
        msgContent.setUpdateTime(date);

        return contentMapper.insertSelective(msgContent);
    }

    @Override
    @Transactional
    public List<MsgContent> getBySessionId(String sessionId, Long memberId) {

        List<MsgContent> msgContents = contentMapper.selectBySessionId(sessionId);

        //修改消息状态
        MsgSession msgSession = sessionMapper.selectBySessionIdAndMemberId(sessionId, memberId);

        MsgSession update = new MsgSession();

        update.setUnreadCount(0);
        update.setMsgSid(msgSession.getMsgSid());

        sessionMapper.updateByPrimaryKeySelective(update);

        return msgContents;
    }
}
