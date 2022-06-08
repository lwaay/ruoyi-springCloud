package com.sinonc.agriculture.service;

import com.sinonc.agriculture.domain.MsgContent;

import java.util.List;

public interface MsgContentService {

    int sendMsg(MsgContent msgContent);

    List<MsgContent> getBySessionId(String sessionId, Long memberId);
}
