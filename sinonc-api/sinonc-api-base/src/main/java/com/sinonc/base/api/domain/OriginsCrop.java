package com.sinonc.base.api.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 企业信息
 * 对象 origins_crop
 *
 * @author ruoyi
 * @date 2021-04-25
 */
public class OriginsCrop extends BaseEntity {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private Long id;

    /**
     * 主键
     */
    private String corpId;

    /**
     * 企业名称
     */
    @Excel(name = "企业名称")
    private String corpName;

    /**
     * 企业类型
     */
    @Excel(name = "企业类型")
    private String corpType;

    /**
     * 企业性质
     */
    @Excel(name = "企业性质")
    private String corpNature;

    /**
     * 企业证件号码
     */
    @Excel(name = "企业证件号码")
    private String corpLicenseNo;

    /**
     * 市
     */
    @Excel(name = "市")
    private String originCity;

    /**
     * 区县
     */
    @Excel(name = "区县")
    private String originDistrict;

    /**
     * 乡镇
     */
    @Excel(name = "乡镇")
    private String originTown;

    /**
     * 年总产量
     */
    @Excel(name = "年总产量")
    private BigDecimal annualOutput;

    /**
     * 法人代表
     */
    @Excel(name = "法人代表")
    private String legalRep;

    /**
     * 传真
     */
    @Excel(name = "传真")
    private String fax;

    /**
     * 联系人
     */
    @Excel(name = "联系人")
    private String contactPerson;

    /**
     * 手机
     */
    @Excel(name = "手机")
    private String mobilePhone;

    /**
     * EMAIL
     */
    @Excel(name = "EMAIL")
    private String email;

    /**
     * 账号
     */
    @Excel(name = "账号")
    private String account;

    /**
     * 注册时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "注册时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date registTime;

    /**
     * 上传营业执照扫描件地址
     */
    @Excel(name = "上传营业执照扫描件地址")
    private String corpLicensePath;

    /**
     * 组织机构代码
     */
    @Excel(name = "组织机构代码")
    private String organizationCode;

    /**
     * 三品认证种类
     */
    @Excel(name = "三品认证种类")
    private String sprzzl;

    /**
     * 用户身份码
     */
    @Excel(name = "用户身份码")
    private String userIdCode;

    /**
     * 手持身份证照
     */
    @Excel(name = "手持身份证照")
    private String handIdcardimg;

    /**
     * 营业期限是否是长期
     */
    @Excel(name = "营业期限是否是长期")
    private String isLong;

    /**
     * 企业营业期限
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "企业营业期限", width = 30, dateFormat = "yyyy-MM-dd")
    private Date businessOperation;

    /**
     * 联系人证件号码
     */
    @Excel(name = "联系人证件号码")
    private String idcode;

    /**
     * 联系人证件类型
     */
    @Excel(name = "联系人证件类型")
    private String contactidcardType;

    /**
     * 企业证件类型
     */
    @Excel(name = "企业证件类型")
    private String cardType;

    /**
     * 真实姓名
     */
    @Excel(name = "真实姓名")
    private String realName;

    /**
     * 企业种植业年产量计量单位名称
     */
    @Excel(name = "企业种植业年产量计量单位名称")
    private String unitName;

    /**
     * 畜牧业年产量
     */
    @Excel(name = "畜牧业年产量")
    private BigDecimal annualOutputX;

    /**
     * 企业营业期限起日
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "企业营业期限起日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date busiOperaStart;

    /**
     * 企业营业期限止日
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "企业营业期限止日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date busiOperaEnd;

    /**
     * 法人证件类型
     */
    @Excel(name = "法人证件类型")
    private String contactIdCardType;

    /**
     * 通过三品一标接口是（SPYB），默认为空
     */
    @Excel(name = "通过三品一标接口是", readConverterExp = "S=PYB")
    private String accountResource;

    /**
     * 组织机构代码证照
     */
    @Excel(name = "组织机构代码证照")
    private String orgaCertificateimg;

    /**
     * 水产品年产量
     */
    @Excel(name = "水产品年产量")
    private BigDecimal annualOutputS;

    /**
     * 企业畜牧业年产量计量单位名称
     */
    @Excel(name = "企业畜牧业年产量计量单位名称")
    private String unitNameX;

    /**
     * 企业水产品年产量计量单位名称
     */
    @Excel(name = "企业水产品年产量计量单位名称")
    private String unitNameS;

    /**
     * 法人证件类型
     */
    @Excel(name = "法人证件类型")
    private String legalidcardType;

    /**
     * 法人电话
     */
    @Excel(name = "法人电话")
    private String legalPhone;

    /**
     * 法人相关照片
     */
    @Excel(name = "法人相关照片")
    private String legalImages;

    /**
     * 证件照片
     */
    @Excel(name = "证件照片")
    private String documentImages;

    /**
     * 删除标识
     */
    @Excel(name = "删除标识")
    private String delFlag;

    /**
     * 该字段为冗余字段，防止注册信息出现重复
     */
    @Excel(name = "该字段为冗余字段，防止注册信息出现重复")
    private String infoUnique;

    /**
     * 主体类型字典名称
     */
    @Excel(name = "主体类型字典名称")
    private String entityTypeName;

    /**
     * 主体属性字典名称
     */
    @Excel(name = "主体属性字典名称")
    private String entityPropertyName;

    /**
     * 身份证正面照
     */
    @Excel(name = "身份证正面照")
    private String positiveIdcardeimg;

    /**
     * 身份证反面照
     */
    @Excel(name = "身份证反面照")
    private String negativeIdcardimg;

    /**
     * 法人证件号码
     */
    @Excel(name = "法人证件号码")
    private String legalIdnumber;

    /**
     * 审批状态
     */
    @Excel(name = "审批状态")
    private String approveStatus;

    /**
     * 审批意见
     */
    @Excel(name = "审批意见")
    private String approveOpinion;

    /**
     * 审批人主体用户码
     */
    @Excel(name = "审批人主体用户码")
    private String approveUserIdcode;

    /**
     * 审批人姓名
     */
    @Excel(name = "审批人姓名")
    private String approveName;

    /**
     * 审批时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审批时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date approveTime;

    /**
     * 密码(国家)
     */
    @Excel(name = "密码(国家)")
    private String passwordG;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JSONField(name = "CORP_ID")
    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getCorpId() {
        return corpId;
    }

    @JSONField(name = "CORP_NAME")
    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getCorpName() {
        return corpName;
    }

    public String getCorpType() {
        return corpType;
    }

    @JSONField(name = "CORP_TYPE")
    public void setCorpType(String corpType) {
        this.corpType = corpType;
    }

    @JSONField(name = "CORP_NATURE")
    public void setCorpNature(String corpNature) {
        this.corpNature = corpNature;
    }

    public String getCorpNature() {
        return corpNature;
    }

    @JSONField(name = "CORP_LICENSE_NO")
    public void setCorpLicenseNo(String corpLicenseNo) {
        this.corpLicenseNo = corpLicenseNo;
    }

    public String getCorpLicenseNo() {
        return corpLicenseNo;
    }

    @JSONField(name = "ORIGIN_CITY")
    public void setOriginCity(String originCity) {
        this.originCity = originCity;
    }

    public String getOriginCity() {
        return originCity;
    }

    @JSONField(name = "ORIGIN_DISTRICT")
    public void setOriginDistrict(String originDistrict) {
        this.originDistrict = originDistrict;
    }

    public String getOriginDistrict() {
        return originDistrict;
    }

    @JSONField(name = "ORIGIN_TOWN")
    public void setOriginTown(String originTown) {
        this.originTown = originTown;
    }

    public String getOriginTown() {
        return originTown;
    }

    @JSONField(name = "ANNUAL_OUTPUT")
    public void setAnnualOutput(BigDecimal annualOutput) {
        this.annualOutput = annualOutput;
    }

    public BigDecimal getAnnualOutput() {
        return annualOutput;
    }

    @JSONField(name = "LEGAL_REP")
    public void setLegalRep(String legalRep) {
        this.legalRep = legalRep;
    }

    public String getLegalRep() {
        return legalRep;
    }

    @JSONField(name = "FAX")
    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getFax() {
        return fax;
    }

    @JSONField(name = "CONTACT_PERSON")
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    @JSONField(name = "MOBILE_PHONE")
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    @JSONField(name = "EMAIL")
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @JSONField(name = "ACCOUNT")
    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }

    @JSONField(name = "REGIST_TIME")
    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    public Date getRegistTime() {
        return registTime;
    }

    @JSONField(name = "CORP_LICENSE_PATH")
    public void setCorpLicensePath(String corpLicensePath) {
        this.corpLicensePath = corpLicensePath;
    }

    public String getCorpLicensePath() {
        return corpLicensePath;
    }

    @JSONField(name = "ORGANIZATION_CODE")
    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    @JSONField(name = "SPRZZL")
    public void setSprzzl(String sprzzl) {
        this.sprzzl = sprzzl;
    }

    public String getSprzzl() {
        return sprzzl;
    }

    @JSONField(name = "USER_ID_CODE")
    public void setUserIdCode(String userIdCode) {
        this.userIdCode = userIdCode;
    }

    public String getUserIdCode() {
        return userIdCode;
    }

    @JSONField(name = "HAND_IDCARDIMG")
    public void setHandIdcardimg(String handIdcardimg) {
        this.handIdcardimg = handIdcardimg;
    }

    public String getHandIdcardimg() {
        return handIdcardimg;
    }

    @JSONField(name = "IS_LONG")
    public void setIsLong(String isLong) {
        this.isLong = isLong;
    }

    public String getIsLong() {
        return isLong;
    }

    @JSONField(name = "BUSINESS_OPERATION")
    public void setBusinessOperation(Date businessOperation) {
        this.businessOperation = businessOperation;
    }

    public Date getBusinessOperation() {
        return businessOperation;
    }

    @JSONField(name = "IDCODE")
    public void setIdcode(String idcode) {
        this.idcode = idcode;
    }

    public String getIdcode() {
        return idcode;
    }

    @JSONField(name = "CONTACTIDCARDTYPE")
    public void setContactidcardType(String contactidcardType) {
        this.contactidcardType = contactidcardType;
    }

    public String getContactidcardType() {
        return contactidcardType;
    }

    @JSONField(name = "CARD_TYPE")
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardType() {
        return cardType;
    }

    @JSONField(name = "REAL_NAME")
    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRealName() {
        return realName;
    }

    @JSONField(name = "UNIT_NAME")
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitName() {
        return unitName;
    }

    @JSONField(name = "ANNUAL_OUTPUT_X")
    public void setAnnualOutputX(BigDecimal annualOutputX) {
        this.annualOutputX = annualOutputX;
    }

    public BigDecimal getAnnualOutputX() {
        return annualOutputX;
    }

    @JSONField(name = "BUSI_OPERA_START")
    public void setBusiOperaStart(Date busiOperaStart) {
        this.busiOperaStart = busiOperaStart;
    }

    public Date getBusiOperaStart() {
        return busiOperaStart;
    }

    @JSONField(name = "BUSI_OPERA_END")
    public void setBusiOperaEnd(Date busiOperaEnd) {
        this.busiOperaEnd = busiOperaEnd;
    }

    public Date getBusiOperaEnd() {
        return busiOperaEnd;
    }

    @JSONField(name = "CONTACT_ID_CARD_TYPE")
    public void setContactIdCardType(String contactIdCardType) {
        this.contactIdCardType = contactIdCardType;
    }

    public String getContactIdCardType() {
        return contactIdCardType;
    }

    @JSONField(name = "ACCOUNT_RESOURCE")
    public void setAccountResource(String accountResource) {
        this.accountResource = accountResource;
    }

    public String getAccountResource() {
        return accountResource;
    }

    @JSONField(name = "ORGA_CERTIFICATEIMG")
    public void setOrgaCertificateimg(String orgaCertificateimg) {
        this.orgaCertificateimg = orgaCertificateimg;
    }

    public String getOrgaCertificateimg() {
        return orgaCertificateimg;
    }

    @JSONField(name = "ANNUAL_OUTPUT_S")
    public void setAnnualOutputS(BigDecimal annualOutputS) {
        this.annualOutputS = annualOutputS;
    }

    public BigDecimal getAnnualOutputS() {
        return annualOutputS;
    }

    @JSONField(name = "UNIT_NAME_X")
    public void setUnitNameX(String unitNameX) {
        this.unitNameX = unitNameX;
    }

    public String getUnitNameX() {
        return unitNameX;
    }

    @JSONField(name = "UNIT_NAME_S")
    public void setUnitNameS(String unitNameS) {
        this.unitNameS = unitNameS;
    }

    public String getUnitNameS() {
        return unitNameS;
    }

    @JSONField(name = "LEGALIDCARDTYPE")
    public void setLegalidcardType(String legalidcardType) {
        this.legalidcardType = legalidcardType;
    }

    public String getLegalidcardType() {
        return legalidcardType;
    }

    @JSONField(name = "LEGAL_PHONE")
    public void setLegalPhone(String legalPhone) {
        this.legalPhone = legalPhone;
    }

    public String getLegalPhone() {
        return legalPhone;
    }

    @JSONField(name = "LEGAL_IMAGES")
    public void setLegalImages(String legalImages) {
        this.legalImages = legalImages;
    }

    public String getLegalImages() {
        return legalImages;
    }

    @JSONField(name = "DOCUMENT_IMAGES")
    public void setDocumentImages(String documentImages) {
        this.documentImages = documentImages;
    }

    public String getDocumentImages() {
        return documentImages;
    }

    @JSONField(name = "DEL_FLAG")
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    @JSONField(name = "INFO_UNIQUE")
    public void setInfoUnique(String infoUnique) {
        this.infoUnique = infoUnique;
    }

    public String getInfoUnique() {
        return infoUnique;
    }

    @JSONField(name = "ENTITY_TYPE_NAME")
    public void setEntityTypeName(String entityTypeName) {
        this.entityTypeName = entityTypeName;
    }

    public String getEntityTypeName() {
        return entityTypeName;
    }

    @JSONField(name = "ENTITY_PROPERTY_NAME")
    public void setEntityPropertyName(String entityPropertyName) {
        this.entityPropertyName = entityPropertyName;
    }

    public String getEntityPropertyName() {
        return entityPropertyName;
    }

    @JSONField(name = "POSITIVE_IDCARDEIMG")
    public void setPositiveIdcardeimg(String positiveIdcardeimg) {
        this.positiveIdcardeimg = positiveIdcardeimg;
    }

    public String getPositiveIdcardeimg() {
        return positiveIdcardeimg;
    }

    @JSONField(name = "NEGATIVE_IDCARDIMG")
    public void setNegativeIdcardimg(String negativeIdcardimg) {
        this.negativeIdcardimg = negativeIdcardimg;
    }

    public String getNegativeIdcardimg() {
        return negativeIdcardimg;
    }

    @JSONField(name = "LEGAL_IDNUMBER")
    public void setLegalIdnumber(String legalIdnumber) {
        this.legalIdnumber = legalIdnumber;
    }

    public String getLegalIdnumber() {
        return legalIdnumber;
    }

    @JSONField(name = "APPROVE_STATUS")
    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    @JSONField(name = "APPROVE_OPINION")
    public void setApproveOpinion(String approveOpinion) {
        this.approveOpinion = approveOpinion;
    }

    public String getApproveOpinion() {
        return approveOpinion;
    }

    @JSONField(name = "APPROVE_USER_IDCODE")
    public void setApproveUserIdcode(String approveUserIdcode) {
        this.approveUserIdcode = approveUserIdcode;
    }

    public String getApproveUserIdcode() {
        return approveUserIdcode;
    }

    @JSONField(name = "APPROVE_NAME")
    public void setApproveName(String approveName) {
        this.approveName = approveName;
    }

    public String getApproveName() {
        return approveName;
    }

    @JSONField(name = "APPROVE_TIME")
    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public Date getApproveTime() {
        return approveTime;
    }

    @JSONField(name = "PASSWORD_G")
    public void setPasswordG(String passwordG) {
        this.passwordG = passwordG;
    }

    public String getPasswordG() {
        return passwordG;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("corpId", getCorpId())
                .append("corpName", getCorpName())
                .append("corpType", getCorpType())
                .append("corpNature", getCorpNature())
                .append("corpLicenseNo", getCorpLicenseNo())
                .append("originCity", getOriginCity())
                .append("originDistrict", getOriginDistrict())
                .append("originTown", getOriginTown())
                .append("annualOutput", getAnnualOutput())
                .append("legalRep", getLegalRep())
                .append("fax", getFax())
                .append("contactPerson", getContactPerson())
                .append("mobilePhone", getMobilePhone())
                .append("email", getEmail())
                .append("account", getAccount())
                .append("registTime", getRegistTime())
                .append("corpLicensePath", getCorpLicensePath())
                .append("organizationCode", getOrganizationCode())
                .append("sprzzl", getSprzzl())
                .append("userIdCode", getUserIdCode())
                .append("handIdcardimg", getHandIdcardimg())
                .append("isLong", getIsLong())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("businessOperation", getBusinessOperation())
                .append("idcode", getIdcode())
                .append("contactidcardType", getContactidcardType())
                .append("cardType", getCardType())
                .append("realName", getRealName())
                .append("unitName", getUnitName())
                .append("annualOutputX", getAnnualOutputX())
                .append("busiOperaStart", getBusiOperaStart())
                .append("busiOperaEnd", getBusiOperaEnd())
                .append("contactIdCardType", getContactIdCardType())
                .append("accountResource", getAccountResource())
                .append("orgaCertificateimg", getOrgaCertificateimg())
                .append("annualOutputS", getAnnualOutputS())
                .append("unitNameX", getUnitNameX())
                .append("unitNameS", getUnitNameS())
                .append("legalidcardType", getLegalidcardType())
                .append("legalPhone", getLegalPhone())
                .append("legalImages", getLegalImages())
                .append("documentImages", getDocumentImages())
                .append("delFlag", getDelFlag())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("infoUnique", getInfoUnique())
                .append("entityTypeName", getEntityTypeName())
                .append("entityPropertyName", getEntityPropertyName())
                .append("positiveIdcardeimg", getPositiveIdcardeimg())
                .append("negativeIdcardimg", getNegativeIdcardimg())
                .append("legalIdnumber", getLegalIdnumber())
                .append("approveStatus", getApproveStatus())
                .append("approveOpinion", getApproveOpinion())
                .append("approveUserIdcode", getApproveUserIdcode())
                .append("approveName", getApproveName())
                .append("approveTime", getApproveTime())
                .append("passwordG", getPasswordG())
                .toString();
    }
}
