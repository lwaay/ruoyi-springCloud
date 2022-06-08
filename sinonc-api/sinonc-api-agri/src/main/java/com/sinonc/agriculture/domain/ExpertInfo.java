package com.sinonc.agriculture.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.ibatis.type.Alias;

import java.util.List;

/**
 * 专家消息对象 expert_info
 *
 * @author ruoyi
 * @date 2020-03-05
 */
@Alias("ExpertInfo")
@Getter
@Setter
public class  ExpertInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**
     * 回复率
     */
    private Double  revrate;

    /** 专家ID */
    private Long expertId;

    /** 会员ID */
    @ApiModelProperty(hidden = true)
    @Excel(name = "会员ID")
    private Long memberId;

    /**
     * 工作单位
     */
    private String jobCompany;

    /** 个人照片 */
    @Excel(name = "个人照片")
    private String personalPhoto;

    /** 真实姓名 */
    @Excel(name = "真实姓名")
    private String realName;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 所在地编码 */
    @Excel(name = "所在地编码")
    private String areaCode;

    /** 擅长专业作物编码(使用芒果字典) */
    @Excel(name = "擅长专业作物编码")
    private String cropCode;

    /** 擅长领域编码 */
    @Excel(name = "擅长领域编码")
    private String regionCode;

    /** 个人简介 */
    @Excel(name = "个人简介")
    private String personalProfile;

    /**
     * 状态，0，审核中，1，审核通过
     */
    @ApiModelProperty(hidden = true)
    @Excel(name = "状态，0，审核中，1，审核通过")
    private Integer verifyStatus;

    /**
     * 专家注册时所属系统名称
     */
    @ApiModelProperty(required = true)
    @Excel(name = "专家注册时所属系统名称")
    private String sysName;

    /**
     * 状态，0，审核中，1，审核通过
     */
    @ApiModelProperty(required = true)
    @Excel(name = "类型：1，农业专家，2、农技员  ")
    private Integer roleType;

    /*** 身份证号码*/
    private String idenCard;

    /**
     * 默认为false  是否被关注
     **/
    @ApiModelProperty(hidden = true)
    private boolean checked = false;

    /***被关注数**/
    @ApiModelProperty(hidden = true)
    private Long concernNum;

    /**
     * 擅长的作物
     **/
    @ApiModelProperty(hidden = true)
    private List<String> cropCodeList;

    /**
     * 擅长的领域
     */
    @ApiModelProperty(hidden = true)


    /** 现从事专业 */
    @Excel(name = "现从事专业")
    private String currMajor;

    /** 职务/职称 */
    @Excel(name = "职务/职称")
    private String postName;

    private List<String> regionCodeList;

    private String certification;

    private int questionCount;

    private int answerCount;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("expertId", getExpertId())
            .append("memberId", getMemberId())
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
