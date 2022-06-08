package com.sinonc.agriculture.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 专家信息暂存对象 expert_info_modify
 *
 * @author ruoyi
 * @date 2020-04-07
 */
public class ExpertInfoModify extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 专家信息暂存ID
     */
    @ApiModelProperty(hidden = true)
    private Long expertmodId;

    /**
     * 专家ID
     */
    @Excel(name = "专家ID")
    private Long expertIdP;

    /**
     * 会员ID
     */
    @ApiModelProperty(hidden = true)
    @Excel(name = "会员ID")
    private Long memberId;

    /**
     * 工作单位
     */
    private String jobCompany;

    /**
     * 身份证号码
     */
    @Excel(name = "身份证号码")
    private String idenCard;

    /**
     * 个人照片
     */
    @Excel(name = "个人照片")
    private String personalPhoto;

    /**
     * 真实姓名
     */
    @Excel(name = "真实姓名")
    private String realName;

    /**
     * 手机号
     */
    @Excel(name = "手机号")
    private String phone;

    /**
     * 邮箱
     */
    @Excel(name = "邮箱")
    private String email;

    /**
     * 所在地编码
     */
    @Excel(name = "所在地编码")
    private String areaCode;

    /**
     * 擅长专业作物编码
     */
    @Excel(name = "擅长专业作物编码")
    private String cropCode;

    /**
     * 擅长领域编码
     */
    @Excel(name = "擅长领域编码")
    private String regionCode;

    /**
     * 个人简介
     */
    @Excel(name = "个人简介")
    private String personalProfile;

    /**
     * 状态，0，审核中，1，审核通过，2审核不通过
     */
    @Excel(name = "状态，0，审核中，1，审核通过，2审核不通过")
    @ApiModelProperty(hidden = true)
    private Integer verifyStatus;

    /**
     * 专家注册时所属系统名称
     */
    @Excel(name = "专家注册时所属系统名称")
    private String sysName;

    public String getJobCompany() {
        return jobCompany;
    }

    public void setJobCompany(String jobCompany) {
        this.jobCompany = jobCompany;
    }

    public void setExpertmodId(Long expertmodId) {
        this.expertmodId = expertmodId;
    }

    public Long getExpertmodId() {
        return expertmodId;
    }

    public void setExpertIdP(Long expertIdP) {
        this.expertIdP = expertIdP;
    }

    public Long getExpertIdP() {
        return expertIdP;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setIdenCard(String idenCard) {
        this.idenCard = idenCard;
    }

    public String getIdenCard() {
        return idenCard;
    }

    public void setPersonalPhoto(String personalPhoto) {
        this.personalPhoto = personalPhoto;
    }

    public String getPersonalPhoto() {
        return personalPhoto;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRealName() {
        return realName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setCropCode(String cropCode) {
        this.cropCode = cropCode;
    }

    public String getCropCode() {
        return cropCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setPersonalProfile(String personalProfile) {
        this.personalProfile = personalProfile;
    }

    public String getPersonalProfile() {
        return personalProfile;
    }

    public void setVerifyStatus(Integer verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public Integer getVerifyStatus() {
        return verifyStatus;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getSysName() {
        return sysName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("expertmodId", getExpertmodId())
                .append("expertIdP", getExpertIdP())
                .append("memberId", getMemberId())
                .append("idenCard", getIdenCard())
                .append("personalPhoto", getPersonalPhoto())
                .append("realName", getRealName())
                .append("phone", getPhone())
                .append("email", getEmail())
                .append("areaCode", getAreaCode())
                .append("cropCode", getCropCode())
                .append("regionCode", getRegionCode())
                .append("personalProfile", getPersonalProfile())
                .append("verifyStatus", getVerifyStatus())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("createBy", getCreateBy())
                .append("updateBy", getUpdateBy())
                .append("sysName", getSysName())
                .toString();
    }
}
