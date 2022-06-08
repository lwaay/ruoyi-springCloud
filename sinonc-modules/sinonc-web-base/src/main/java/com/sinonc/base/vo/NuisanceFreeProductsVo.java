package com.sinonc.base.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 三品一标信息对象 nuisance_free_products
 *
 * @author ruoyi
 * @date 2021-05-15
 */
@Data
public class NuisanceFreeProductsVo {

    /**
     * 主键
     */
    private Long prodId;

    /**
     * 行政区划
     */
    private Long areaCode;

    /**
     * 产品名称
     */
    @Excel(name = "产品名称")
    private String prodName;

    /**
     * 生产单位
     */
    @Excel(name = "生产单位")
    private String manufacturer;

    /**
     * 产品认证信息
     */
    @Excel(name = "产品认证信息")
    private String authentication;

    /**
     * 证书类型
     */
    @Excel(name = "证书类型")
    private String credentials;

    /**
     * 产品证书编号
     */
    @Excel(name = "产品证书编号")
    private String credentialsCode;

    /**
     * 认定产品规模
     */
    @Excel(name = "认定产品规模")
    private BigDecimal plantArea;

    /**
     * 开始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    /**
     * 结束日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /**
     * 分页
     */
    private Integer pageNum;
    private Integer pageSize;

    private Integer status;
}
