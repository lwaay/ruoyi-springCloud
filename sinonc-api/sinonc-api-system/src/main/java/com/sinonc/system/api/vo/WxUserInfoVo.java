package com.sinonc.system.api.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * 微信
 */
@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
public class WxUserInfoVo {
    @ApiModelProperty(value = "openId", required = true)
    @NotBlank(message = "openId不能为空")
    private String openId;
    @ApiModelProperty(value = "昵称")
    private String nickName;
    @ApiModelProperty(value = "性别")
    private String gender;
    @ApiModelProperty(value = "城市")
    private String city;
    @ApiModelProperty(value = "身份")
    private String province;
    @ApiModelProperty(value = "国家")
    private String country;
    @ApiModelProperty(value = "头像")
    private String avatarUrl;
    @ApiModelProperty(value = "unionId", required = true)
    @NotBlank(message = "unionId不能为空")
    private String unionId;
    @ApiModelProperty(value = "code", required = false, name = "微信小程序登陆需要传入code")
    private String code;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
