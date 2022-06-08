package com.sinonc.system.vo;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.system.api.domain.BusinessEntity;
import com.sinonc.system.domain.BusinessCert;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zhangxinlong
 * @apiNote TODO
 * @date 2022-02-25  11:04
 */
@Data
public class BusinessEntityVo extends BusinessEntity {


    /**
     * 会员ID
     */
    private Long memberId;


    /**
     * 防止有多个的情况
     */
    private List<BusinessCert> businessCert;


    /**
     * 申请的主体类型(1种植  2加工  3经销  4服务)
     */
    private String applyType;

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
     * 证件地址
     */
    private String certPic;

    /**
     * 证件名称
     */
    private String certName;

    /** 种植面积 */
    private BigDecimal plantArea;
}
