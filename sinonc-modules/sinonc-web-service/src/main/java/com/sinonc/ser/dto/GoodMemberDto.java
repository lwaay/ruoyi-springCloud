package com.sinonc.ser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zhangxinlong
 * @apiNote
 * @date 2020/8/10  15:57
 */
@Data
@AllArgsConstructor
public class GoodMemberDto {

    /**
     * 会员ID
     */
    private Long memberId;

    /**
     * 会员名称
     */
    private String userName;
}
