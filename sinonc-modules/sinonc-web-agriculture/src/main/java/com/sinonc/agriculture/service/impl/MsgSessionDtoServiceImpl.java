package com.sinonc.agriculture.service.impl;

import com.sinonc.agriculture.domain.MsgContent;
import com.sinonc.agriculture.dto.MsgSessionDto;
import com.sinonc.agriculture.mapper.MsgContentMapper;
import com.sinonc.agriculture.mapper.MsgSessionDtoMapper;
import com.sinonc.agriculture.service.MsgSessionDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class MsgSessionDtoServiceImpl implements MsgSessionDtoService {

    @Autowired
    private MsgSessionDtoMapper sessionMapper;
    @Autowired
    private MsgContentMapper contentMapper;

    @Override
    public List<MsgSessionDto> getMsgSessionDtoList(Long memberId) {
        List<MsgSessionDto> msgSessionDtos = sessionMapper.selectListByMemberId(memberId);
        msgSessionDtos.forEach(entity -> {
            String avatarUrl = sessionMapper.selectAvatarUrl(entity.getToMemberId());
            entity.setAvatarUrl(avatarUrl);
            MsgContent msgContent = contentMapper.selectBySessionIdLatest(entity.getSessionId());
            if (msgContent != null) {
                entity.setLastMsgContent(msgContent.getContent());
                entity.setType(msgContent.getType());
            }
        });
        return msgSessionDtos;
    }
}
