package com.sinonc.base.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * 药剂对象 k_pesticide
 *
 * @author ruoyi
 * @date 2022-04-19
 */
@Data
public class KPesticide extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 药剂编号
     */
    @Excel(name = "药剂编号")
    private String pesticideCode;

    /**
     * 药剂名称
     */
    @Excel(name = "药剂名称")
    private String pesticideName;

    /**
     * 是否禁止药剂,0-否，1-是
     */
    @Excel(name = "是否禁止药剂,0-否，1-是")
    private String forbidden;

    /**
     * 安全周期
     */
    @Excel(name = "安全周期")
    private String safePeriod;

    /**
     * 创建人
     */
    private String createUser;

}
