package com.sinonc.system.dto;

import com.sinonc.system.api.domain.BusinessEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhangxinlong
 * @apiNote TODO
 * @date 2022-02-25  11:34
 */
@Data
public class BusinessEntityDto extends BusinessEntity {

    /**
     * 申请的主体类型(1种植  2加工  3经销  4服务)
     */
    private String applyType;


    /**
     * 审核状态(1未认证，2审核中，3审核成功(已认证) 4审核失败)
     */
    private String auditStatus;

    /**
     * 证件地址
     */
    private String certPic;

    /**
     * 证件名称
     */
    private String certName;


    /**
     * 企业名称
     */
    private String companyName;

    /**
     * 统一信用代码/注册号
     */
    private String companyCode;

    /**
     * 法人名称
     */
    private String legalPerson;

    /**
     * 法人身份证号码
     */
    private String legalIdcard;


    /**
     * 是否种植户
     */
    private boolean isPlant = false;

    /**
     * 是否是加工商
     */
    private boolean isProcess = false;

    /**
     * 是否是经销商
     */
    private boolean isDist = false;

    /**
     * 是否是服务商
     */
    private boolean isService = false;

    /** 种植面积 */
    private BigDecimal plantArea;

}
