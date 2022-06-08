package com.sinonc.agriculture.mapper;

import com.sinonc.agriculture.dto.MsgSessionDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MsgSessionDtoMapper {

    List<MsgSessionDto> selectListByMemberId(Long memberId);

    String selectAvatarUrl(@Param("toMemberId") Long toMemberId);
}
