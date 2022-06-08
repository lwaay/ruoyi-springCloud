package com.sinonc.agriculture.mapper;

import com.sinonc.agriculture.domain.MsgContent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MsgContentMapper {
    int deleteByPrimaryKey(Long msgId);

    int insert(MsgContent record);

    int insertSelective(MsgContent record);

    MsgContent selectByPrimaryKey(Long msgId);

    int updateByPrimaryKeySelective(MsgContent record);

    int updateByPrimaryKey(MsgContent record);

    List<MsgContent> selectBySessionId(String sessionId);

    MsgContent selectBySessionIdLatest(@Param("sessionId") String sessionId);
}