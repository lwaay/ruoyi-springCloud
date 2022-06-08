package com.sinonc.agriculture.service;

import com.sinonc.agriculture.domain.MsgSession;

import java.util.List;

public interface MsgSessionService {

    /**
     * 创建私信会话
     *
     * @param fromMemberId 发起会话的用户ID
     * @param toMemberId   目标用户的ID
     * @return
     */
    MsgSession createMsgSession(Long fromMemberId, Long toMemberId);

    /**
     * 删除会话
     *
     * @param msgSid 会话ID
     * @return 影响行数
     */
    int deleteMsgSession(Long msgSid);

    /**
     * 获取session列表
     *
     * @param msgSession
     * @return
     */
    List<MsgSession> listSession(MsgSession msgSession);
}
