package com.sinonc.agriculture.service.impl;

import com.sinonc.agriculture.domain.MemberInfo;
import com.sinonc.agriculture.domain.MsgSession;
import com.sinonc.agriculture.mapper.MemberInfoMapper;
import com.sinonc.agriculture.mapper.MsgSessionMapper;
import com.sinonc.agriculture.service.MsgSessionService;
import com.sinonc.common.core.utils.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MsgSessionServiceImpl implements MsgSessionService {

    @Autowired
    private MsgSessionMapper msgSessionMapper;

    @Autowired
    private MemberInfoMapper memberInfoMapper;

    /**
     * 创建私信会话
     *
     * @param fromMemberId 发起会话的用户ID
     * @param toMemberId   目标用户的ID
     * @return
     */
    @Override
    public MsgSession createMsgSession(Long fromMemberId, Long toMemberId) {

        Date date = new Date();
        String sessionId = getSessionId(fromMemberId, toMemberId);

        List<MsgSession> msgSessions = msgSessionMapper.selectBySessionId(sessionId);

        if (msgSessions == null || msgSessions.size() == 0) {

            MemberInfo fromMember = memberInfoMapper.selectMemberInfoById(fromMemberId);
            MemberInfo toMember = memberInfoMapper.selectMemberInfoById(toMemberId);

            MsgSession from = new MsgSession();

            from.setSessionName(toMember.getNikeName());
            from.setCreateTime(date);
            from.setUpdateTime(date);
            from.setSessionId(sessionId);
            from.setMemberId(fromMemberId);
            from.setDelFlag(0);

            msgSessionMapper.insertSelective(from);

            MsgSession to = new MsgSession();
            to.setSessionName(fromMember.getNikeName());
            to.setCreateTime(date);
            to.setUpdateTime(date);
            to.setSessionId(sessionId);
            to.setMemberId(toMemberId);
            to.setDelFlag(1);

            msgSessionMapper.insertSelective(to);

            return from;

        } else {

            for (MsgSession msgSession : msgSessions) {
                if (msgSession.getMemberId().equals(fromMemberId)) {
                    if (msgSession.getDelFlag() == 1) {

                        MsgSession update = new MsgSession();
                        update.setDelFlag(0);
                        update.setMsgSid(msgSession.getMsgSid());
                        update.setUpdateTime(date);

                        msgSessionMapper.updateByPrimaryKeySelective(update);
                    }
                    return msgSession;
                }
            }

            return null;
        }

    }

    @Override
    public int deleteMsgSession(Long msgSid) {

        MsgSession msgSession = new MsgSession();
        msgSession.setDelFlag(1);
        msgSession.setMsgSid(msgSid);
        return msgSessionMapper.updateByPrimaryKeySelective(msgSession);

    }

    @Override
    public List<MsgSession> listSession(MsgSession msgSession) {
        return msgSessionMapper.selectList(msgSession);
    }

    /**
     * 创建会话ID
     *
     * @param fromId
     * @param toId
     * @return
     */
    private String getSessionId(Long fromId, Long toId) {
        if (fromId < toId) {
            return Md5Utils.hash(fromId + "_" + toId);
        } else {
            return Md5Utils.hash(toId + "_" + fromId);
        }
    }
}
