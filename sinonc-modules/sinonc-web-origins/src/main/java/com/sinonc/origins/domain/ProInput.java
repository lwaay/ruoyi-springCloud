package com.sinonc.origins.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * 农产品投入品信息对象 pro_input
 *
 * @author ruoyi
 * @date 2022-04-15
 */
@Data
public class ProInput extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 投入品主键
     */
    private Long inputId;

    /**
     * 类型
     */
    @Excel(name = "类型")
    private String inputType;

    /**
     * 规格
     */
    @Excel(name = "规格")
    private String specs;

    /**
     * 品牌
     */
    @Excel(name = "品牌")
    private String brand;

    /**
     * 投入量
     */
    @Excel(name = "投入量")
    private BigDecimal inputNum;

    /**
     * 投入单位
     */
    @Excel(name = "投入单位")
    private String inoutUnit;

    /**
     * 操作人
     */
    @Excel(name = "操作人")
    private String oprateMan;

    /**
     * 经营主体
     */
    @Excel(name = "经营主体")
    private Long entityId;

    /**
     * 投入时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "投入时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inputTime;

}
