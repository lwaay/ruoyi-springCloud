package com.sinonc.base.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * 病虫害施药标准对象 k_pesticide_norm
 *
 * @author ruoyi
 * @date 2022-04-19
 */
@Data
public class KPesticideNorm extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 病虫害编号
     */
    @Excel(name = "病虫害编号")
    private String insectCode;

    /**
     * 药剂编号
     */
    @Excel(name = "药剂编号")
    private String pesticideCode;

    /**
     * 最大次数每年
     */
    @Excel(name = "最大次数每年")
    private String maxTime;

    /**
     * 安全周期
     */
    @Excel(name = "安全周期")
    private String safePeriod;

    /**
     * 优先级
     */
    @Excel(name = "优先级")
    private Integer indexSort;

    /**
     * 创建人
     */
    @Excel(name = "创建人")
    private String createUser;

}
