package com.sinonc.agriculture.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 微信
 */
@Data
@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
public class WxUserInfo {
    @ApiModelProperty(value = "openId", required = true)
    @NotBlank(message = "openId不能为空")
    private String openId;
    @ApiModelProperty(value = "昵称")
    private String nickName;
    @ApiModelProperty(value = "性别")
    private String gender;
    @ApiModelProperty(value = "城市")
    private String city;
    @ApiModelProperty(value = "省份")
    private String province;
    @ApiModelProperty(value = "国家")
    private String country;
    @ApiModelProperty(value = "头像")
    private String avatarUrl;
    @ApiModelProperty(value = "unionId", required = true)
    @NotBlank(message = "unionId不能为空")
    private String unionId;
}
