package com.sinonc.agriculture.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 会员信息对象 member_info
 *
 * @author ruoyi
 * @date 2020-03-05
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 会员ID */
    private Long memberId;

    /** 昵称 */
    @Excel(name = "昵称")
    private String nikeName;

    /** 性别，0，男，1，女，2，未知 */
    @Excel(name = "性别，0，男，1，女，2，未知")
    private Integer sex;

    /** 生日 */
    @Excel(name = "生日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /** 个性签名 */
    @Excel(name = "个性签名")
    private String personalSignature;

    /** 用户头像 */
    @Excel(name = "用户头像")
    private String headImg;

    /** 手机号 */
    @Excel(name = "手机号")
    private String mobilePhone;

    /**
     * 所在城市
     */
    @Excel(name = "所在城市")
    private String city;

    /**
     * 种养殖经验（年）
     */
    @Excel(name = "种养殖经验", readConverterExp = "年=")
    private Integer experience;

    /**
     * 擅长领域
     */
    private String sectionIds;

    /**
     * 用户角色，0，农户达人，1，农业专家
     */
    @Excel(name = "用户角色，0，农户达人，1，农业专家")
    private Integer role;

    /**
     * 微信unionId
     */
    @Excel(name = "微信unionId")
    private String unionid;

    /**
     * 小程序openid
     */
    @Excel(name = "小程序openid")
    private String mpOpenid;

    /** APP微信openid */
    @Excel(name = "APP微信openid")
    private String appOpenid;

    /** 系统生成的UUID,用于确保用户的唯一属性 */
    @Excel(name = "系统生成的UUID,用于确保用户的唯一属性")
    private String sysUuid;

    /** 关注焦点标签（脐橙、蔬菜、种植） */
    @Excel(name = "关注焦点标签", readConverterExp = "脐=橙、蔬菜、种植")
    private String focusTags;

    /** 专家标签（脐橙，蔬菜，油茶） */
    @Excel(name = "专家标签", readConverterExp = "脐=橙，蔬菜，油茶")
    private String expertTags;

    /** 账户积分 */
    @Excel(name = "账户积分")
    private Integer score;

    /** 会员等级 */
    @Excel(name = "会员等级")
    private Integer rank;

    /** 账户状态，0，正常，1，冻结 */
    @Excel(name = "账户状态，0，正常，1，冻结")
    private Integer status;

    /**
     * 最后一次登录时间
     */
    @Excel(name = "最后一次登录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastLoginTime;

    /**
     * 注册系统名称
     */
    @Excel(name = "注册系统名称")
    private String regSys;

    /**
     * 邀请注册的会员ID
     */
    private Long invitationMember;

    /**
     * 默认为false  是否被关注
     **/
    private boolean checked = false;

    /***被关注数**/
    private Long concernNum;

    /**
     * 经营主体id
     */
    private String entityId;

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getSectionIds() {
        return sectionIds;
    }

    public void setSectionIds(String sectionIds) {
        this.sectionIds = sectionIds;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Long getConcernNum() {
        return concernNum;
    }

    public void setConcernNum(Long concernNum) {
        this.concernNum = concernNum;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMemberId() {
        return memberId;
    }
    public void setNikeName(String nikeName)
    {
        this.nikeName = nikeName;
    }

    public String getNikeName()
    {
        return nikeName;
    }
    public void setSex(Integer sex)
    {
        this.sex = sex;
    }

    public Integer getSex()
    {
        return sex;
    }
    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }

    public Date getBirthday()
    {
        return birthday;
    }
    public void setPersonalSignature(String personalSignature)
    {
        this.personalSignature = personalSignature;
    }

    public String getPersonalSignature()
    {
        return personalSignature;
    }
    public void setHeadImg(String headImg)
    {
        this.headImg = headImg;
    }

    public String getHeadImg()
    {
        return headImg;
    }
    public void setMobilePhone(String mobilePhone)
    {
        this.mobilePhone = mobilePhone;
    }

    public String getMobilePhone()
    {
        return mobilePhone;
    }
    public void setCity(String city)
    {
        this.city = city;
    }

    public String getCity()
    {
        return city;
    }
    public void setExperience(Integer experience)
    {
        this.experience = experience;
    }

    public Integer getExperience()
    {
        return experience;
    }
    public void setRole(Integer role)
    {
        this.role = role;
    }

    public Integer getRole()
    {
        return role;
    }
    public void setUnionid(String unionid)
    {
        this.unionid = unionid;
    }

    public String getUnionid()
    {
        return unionid;
    }
    public void setMpOpenid(String mpOpenid)
    {
        this.mpOpenid = mpOpenid;
    }

    public String getMpOpenid()
    {
        return mpOpenid;
    }
    public void setAppOpenid(String appOpenid)
    {
        this.appOpenid = appOpenid;
    }

    public String getAppOpenid()
    {
        return appOpenid;
    }
    public void setSysUuid(String sysUuid)
    {
        this.sysUuid = sysUuid;
    }

    public String getSysUuid()
    {
        return sysUuid;
    }
    public void setFocusTags(String focusTags)
    {
        this.focusTags = focusTags;
    }

    public String getFocusTags()
    {
        return focusTags;
    }
    public void setExpertTags(String expertTags)
    {
        this.expertTags = expertTags;
    }

    public String getExpertTags()
    {
        return expertTags;
    }
    public void setScore(Integer score)
    {
        this.score = score;
    }

    public Integer getScore()
    {
        return score;
    }
    public void setRank(Integer rank)
    {
        this.rank = rank;
    }

    public Integer getRank()
    {
        return rank;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }
    public void setLastLoginTime(Date lastLoginTime)
    {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getLastLoginTime()
    {
        return lastLoginTime;
    }

    public void setRegSys(String regSys) {
        this.regSys = regSys;
    }

    public String getRegSys() {
        return regSys;
    }

    public Long getInvitationMember() {
        return invitationMember;
    }

    public void setInvitationMember(Long invitationMember) {
        this.invitationMember = invitationMember;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("memberId", getMemberId())
                .append("nikeName", getNikeName())
                .append("sex", getSex())
                .append("birthday", getBirthday())
                .append("personalSignature", getPersonalSignature())
                .append("headImg", getHeadImg())
            .append("mobilePhone", getMobilePhone())
            .append("city", getCity())
            .append("experience", getExperience())
            .append("role", getRole())
            .append("unionid", getUnionid())
            .append("mpOpenid", getMpOpenid())
            .append("appOpenid", getAppOpenid())
            .append("sysUuid", getSysUuid())
            .append("focusTags", getFocusTags())
            .append("expertTags", getExpertTags())
            .append("score", getScore())
            .append("rank", getRank())
            .append("status", getStatus())
            .append("lastLoginTime", getLastLoginTime())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("regSys", getRegSys())
            .toString();
    }
}
