package com.sinonc.base.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * 施肥方案标准对象 k_fertilizer_schema
 *
 * @author ruoyi
 * @date 2022-04-19
 */
@Data
public class KFertilizerSchema extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 树龄：0-幼苗、1-幼树、2-初果树、3-成年树、9-衰老树
     */
    @Excel(name = "树龄：0-幼苗、1-幼树、2-初果树、3-成年树、9-衰老树")
    private String ageCode;

    /**
     * 有机肥方案
     */
    @Excel(name = "有机肥方案")
    private String organicSchema;

    /**
     * 尿素、钾肥方案
     */
    @Excel(name = "尿素、钾肥方案")
    private String ureaSchema;

    /**
     * 氮肥方案
     */
    @Excel(name = "氮肥方案")
    private String nitrogenSchema;

    /**
     * 酸性土壤改良方案
     */
    @Excel(name = "酸性土壤改良方案")
    private String aciditySoilSchema;

    /**
     * 碱性土壤改良方案
     */
    @Excel(name = "碱性土壤改良方案")
    private String alkalineSoilSchema;

    /**
     * 创建人
     */
    @Excel(name = "创建人")
    private String createUser;

}
