package com.sinonc.agriculture.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class WxBind extends WxUserInfo {

    @NotBlank(message = "验证码不能为空")
    @ApiModelProperty(value = "验证码", required = true)
    private String code;

    @NotBlank(message = "手机号不能为空")
    @ApiModelProperty(value = "手机号", required = true)
    private String phone;

    @ApiModelProperty(value = "绑定类型，0，APP；1，小程序")
    @NotNull(message = "绑定类型不能为空")
    private Integer type;

    @ApiModelProperty(value = "邀请码")
    private String invitationCode;
}
