package com.sinonc.agriculture.dto;

import com.sinonc.agriculture.domain.MsgSession;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MsgSessionDto extends MsgSession {
    private Long toMemberId;
    private Long fromMemberId;
    private String avatarUrl;
    /**
     *  最后一条消息类型
     */
    private int type;
}
