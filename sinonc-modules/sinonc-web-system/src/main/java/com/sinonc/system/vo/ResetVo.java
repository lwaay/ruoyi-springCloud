package com.sinonc.system.vo;

import lombok.Data;

/**
 * @author
 * 找回密码vo类
 */
@Data
public class ResetVo {
    /**
     * 账户名称
     */
    private String username;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 验证码
     */
    private String code;

    /**
     * 新密码
     */
    private String password;

}
