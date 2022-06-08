package com.sinonc.system.vo;

import lombok.Data;

/**
 * @author
 * 找回密码vo类
 */
@Data
public class ReWxUserVo {
    /**
     * 账户名称
     */
    private String username;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 旧密码
     */
    private String code;

    /**
     * 旧密码
     */
    private String oldPassword;

    /**
     * 新密码
     */
    private String newPassword;

}
