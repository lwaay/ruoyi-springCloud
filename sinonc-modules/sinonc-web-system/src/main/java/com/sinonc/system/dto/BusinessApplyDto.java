package com.sinonc.system.dto;

import com.sinonc.system.api.domain.BusinessEntity;
import com.sinonc.system.domain.*;
import lombok.Data;

/**
 * @author zhangxinlong
 * @apiNote TODO
 * @date 2022-04-20  9:54
 */
@Data
public class BusinessApplyDto extends BusinessApply {

    /**
     * 实体名称
     */
    private String entityName;

    /**
     * 证件
     */
    private BusinessCert businessCert;


    /**
     * 实体
     */
    private BusinessEntity BusinessEntity;


    /**
     * 经销商对象
     */
    private BudistCompany budistCompany;

    /**
     * 种植户
     */
    private BuplantCompany buplantCompany;

    /**
     * 加工商
     */
    private BuprocessCompany buprocessCompany;

    /**
     * 服务商
     */
    private BuserviceCompany buserviceCompany;
}
