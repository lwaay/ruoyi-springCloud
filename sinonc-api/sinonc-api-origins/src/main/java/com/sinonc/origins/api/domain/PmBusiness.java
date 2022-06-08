package com.sinonc.origins.api.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;

/**
 * 经营主体对象 pm_business
 *
 * @author ruoyi
 * @date 2020-10-21
 */
public class PmBusiness extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long busiId;

    /**
     * 经营主体名字
     */
    @Excel(name = "经营主体名字")
    private String busiEntityname;

    /**
     * 经营主姓名
     */
    @Excel(name = "经营主姓名")
    private String busiMainname;

    /**
     * 主体类型（增加字典表)
     */
    @Excel(name = "主体类型", readConverterExp = "主体类型（增加字典表)")
    private Long busiMaintype;

    /**
     * 所属区域
     */
    @Excel(name = "所属区域")
    private String baseArea;

    /**
     * 经营类型(产业代码)
     */
    @Excel(name = "经营类型(产业代码)")
    private String busiInduSn;

    /**
     * 主体介绍
     */
    @Excel(name = "主体介绍")
    private String busiMainintroduce;

    /**
     * 认证材料（文件链接“，”逗号隔开）
     */
    @Excel(name = "认证材料", readConverterExp = "文=件链接“，”逗号隔开")
    private String busiCertification;

    /**
     * 是否认证（增加字典表）
     */
    @Excel(name = "是否认证", readConverterExp = "增=加字典表")
    private Long busiAuthentication;


    /**
     * 部门id
     */
    private Long deptIdP;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String busiRemark;

    /**
     * 联系人
     * @param busiId
     */
    @Excel(name = "联系人")
    private String contactName;

    /**
     * 联系电话
     * @param busiId
     */
    @Excel(name="联系电话")
    private String contactPhone;

    public void setBusiId(Long busiId) {
        this.busiId = busiId;
    }

    public Long getBusiId() {
        return busiId;
    }

    public void setBusiEntityname(String busiEntityname) {
        this.busiEntityname = busiEntityname;
    }

    public String getBusiEntityname() {
        return busiEntityname;
    }

    public void setBusiMainname(String busiMainname) {
        this.busiMainname = busiMainname;
    }

    public String getBusiMainname() {
        return busiMainname;
    }

    public void setBusiMaintype(Long busiMaintype) {
        this.busiMaintype = busiMaintype;
    }

    public Long getBusiMaintype() {
        return busiMaintype;
    }

    public void setBaseArea(String baseArea) {
        this.baseArea = baseArea;
    }

    public String getBaseArea() {
        return baseArea;
    }

    public void setBusiInduSn(String busiInduSn) {
        this.busiInduSn = busiInduSn;
    }

    public String getBusiInduSn() {
        return busiInduSn;
    }

    public void setBusiMainintroduce(String busiMainintroduce) {
        this.busiMainintroduce = busiMainintroduce;
    }

    public String getBusiMainintroduce() {
        return busiMainintroduce;
    }

    public void setBusiCertification(String busiCertification) {
        this.busiCertification = busiCertification;
    }

    public String getBusiCertification() {
        return busiCertification;
    }

    public void setBusiAuthentication(Long busiAuthentication) {
        this.busiAuthentication = busiAuthentication;
    }

    public Long getBusiAuthentication() {
        return busiAuthentication;
    }

    public void setBusiRemark(String busiRemark) {
        this.busiRemark = busiRemark;
    }

    public String getBusiRemark() {
        return busiRemark;
    }

    public Long getDeptIdP() {
        return deptIdP;
    }

    public void setDeptIdP(Long deptIdP) {
        this.deptIdP = deptIdP;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
}
