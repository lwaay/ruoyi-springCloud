package com.sinonc.agriculture.mapper;

import com.sinonc.agriculture.domain.MsgSession;

import java.util.List;

public interface MsgSessionMapper {
    int deleteByPrimaryKey(Long msgSid);

    int insert(MsgSession record);

    int insertSelective(MsgSession record);

    MsgSession selectByPrimaryKey(Long msgSid);

    int updateByPrimaryKeySelective(MsgSession record);

    int updateByPrimaryKey(MsgSession record);

    List<MsgSession> selectBySessionId(String sessionId);

    MsgSession selectBySessionIdAndMemberId(String sessionId, Long memberId);

    List<MsgSession> selectList(MsgSession msgSession);
}