package com.sinonc.agriculture.service;

import com.sinonc.agriculture.dto.MsgSessionDto;

import java.util.List;

public interface MsgSessionDtoService {

    List<MsgSessionDto> getMsgSessionDtoList(Long memberId);

}
