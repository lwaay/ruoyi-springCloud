package com.sinonc.system.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 经销商对象 budist_company
 *
 * @author ruoyi
 * @date 2022-02-24
 */
public class BudistCompany extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long distId;

    /**
     * 企业名称
     */
    @Excel(name = "企业名称")
    private String companyName;

    /**
     * 统一信用代码/注册号
     */
    @Excel(name = "统一信用代码/注册号")
    private String companyCode;

    /**
     * 法人名称
     */
    @Excel(name = "法人名称")
    private String legalPerson;

    /**
     * 法人身份证号码
     */
    @Excel(name = "法人身份证号码")
    private String legalIdcard;

    /**
     * 营业执照图片地址
     */
    @Excel(name = "营业执照图片地址")
    private String bulicensePic;

    public void setDistId(Long distId) {
        this.distId = distId;
    }

    public Long getDistId() {
        return distId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalIdcard(String legalIdcard) {
        this.legalIdcard = legalIdcard;
    }

    public String getLegalIdcard() {
        return legalIdcard;
    }

    public void setBulicensePic(String bulicensePic) {
        this.bulicensePic = bulicensePic;
    }

    public String getBulicensePic() {
        return bulicensePic;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("distId", getDistId())
                .append("companyName", getCompanyName())
                .append("companyCode", getCompanyCode())
                .append("legalPerson", getLegalPerson())
                .append("legalIdcard", getLegalIdcard())
                .append("bulicensePic", getBulicensePic())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
