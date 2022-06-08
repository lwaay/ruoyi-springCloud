package com.sinonc.system.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 用户注册审核对象 sys_user_examine
 *
 * @author ruoyi
 * @date 2021-10-30
 */
public class SysUserExamine extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 审核id
     */
    private Long examineId;

    /**
     * 用户名称
     */
    @Excel(name = "用户名称")
    private String userName;

    /**
     * 手机号码
     */
    @Excel(name = "手机号码")
    private String phonenumber;

    /**
     * 密码
     */
    @Excel(name = "密码")
    private String password;

    /**
     * 审核状态
     */
    @Excel(name = "审核状态")
    private String status;

    /**
     * 审核人
     */
    @Excel(name = "审核人")
    private String examineBy;

    /**
     * 审核时间
     */
    @Excel(name = "审核时间")
    private Date examineTime;


    /**
     * 验证码
     */
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setExamineId(Long examineId) {
        this.examineId = examineId;
    }

    public Long getExamineId() {
        return examineId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getExamineBy() {
        return examineBy;
    }

    public void setExamineBy(String examineBy) {
        this.examineBy = examineBy;
    }

    public Date getExamineTime() {
        return examineTime;
    }

    public void setExamineTime(Date examineTime) {
        this.examineTime = examineTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("examineId", getExamineId())
                .append("userName", getUserName())
                .append("phonenumber", getPhonenumber())
                .append("password", getPassword())
                .append("status", getStatus())
                .append("examineBy", getExamineBy())
                .append("examineTime", getExamineTime())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
