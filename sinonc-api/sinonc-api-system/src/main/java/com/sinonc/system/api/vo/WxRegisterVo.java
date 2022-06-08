package com.sinonc.system.api.vo;

import javax.validation.constraints.NotNull;

/**
 * @author huanghao
 * @apiNote 微信登录
 * @date 2021/3/3 16:09
 */

public class WxRegisterVo {
    @NotNull(message = "encryptedData cant no be null")
    private String encryptedData;
    @NotNull(message = "iv cant no be null")
    private String iv;
    @NotNull(message = "code cant no be null")
    private String code;

    private String loginCode;

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }
}
