package com.sinonc.system.api.vo;

import javax.validation.constraints.NotNull;

/**
 * @author lw
 * @Date 2022/2/14 9:16
 * 微信手机登录注册vo类
 */
public class WxUserVo {

    @NotNull(message = "phone is not null")
    private String phone;

    @NotNull(message = "code is not null")
    private String code;

    @NotNull(message = "password is not null")
    private String password;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 邀请码
     */
    private String invitationCode;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    @Override
    public String toString() {
        return "WxUserVo{" +
                "phone='" + phone + '\'' +
                ", code='" + code + '\'' +
                ", password='" + password + '\'' +
                ", invitationCode='" + invitationCode + '\'' +
                '}';
    }


}
