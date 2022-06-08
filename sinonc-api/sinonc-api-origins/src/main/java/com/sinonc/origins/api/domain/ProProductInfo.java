package com.sinonc.origins.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 产品信息对象 pro_product_info
 *
 * @author zhangxl
 * @date 2020-10-21
 */
@Data
public class ProProductInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long productId;

    /**
     * 经营主体（生产商）主键
     */
    @Excel(name = "经营主体", readConverterExp = "生=产商")
    private Long tillmainIdP;

    /**
     * 生产商ID，从经营主体取
     */
    @Excel(name = "生产商ID，从经营主体取")
    private Long manuIdP;

    /**
     * 经营主体Id
     */
    @Excel(name = "经营主体Id")
    private Long entityIdP;

    /**
     * 经营主体名称
     */
    @Excel(name = "经营主体名称")
    private String entityNameP;

    /**
     * 产品名称
     */
    @Excel(name = "产品名称")
    private String productName;

    /**
     * 品牌主键
     */
    @Excel(name = "品牌主键")
    private Long brandId;

    /**
     * 生产地址（产地）
     */
    @Excel(name = "生产地址", readConverterExp = "产=地")
    private String tillArea;

    /**
     * 产品编码
     */
    @Excel(name = "产品编码")
    private String productCode;

    /**
     * 规格
     */
    @Excel(name = "规格")
    private String productSpec;

    /**
     * 保质期
     */
    @Excel(name = "保质期")
    private String shelfLife;

    /**
     * 保质期单位（天、月、年）
     */
    @Excel(name = "保质期单位", readConverterExp = "天=、月、年")
    private String shelfLifeUnit;

    /**
     * 存储方式，常温、冷藏
     */
    @Excel(name = "存储方式，常温、冷藏")
    private String proStore;

    /**
     * 批次
     */
    @Excel(name = "批次")
    private String proBatch;

    /**
     * 购买网址
     */
    @Excel(name = "购买网址")
    private String buyUrl;

    /**
     * 生产日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生产日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date productionDate;

    /**
     * 图片地址
     */
    @Excel(name = "图片地址")
    private String mainImages;

    /**
     * 所属区域
     */
    @Excel(name = "所属区域")
    private Long baseArea;

    /**
     * 生产商名称
     * */
    private String businessName;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 备注
     */
    private String remark;

    private String videoUrl;

    private Long proLike;

    private String mainIntroduce;

    private String produceName;

    private String[] imgs;

    private Long proVisit;

    private Long originsSize;

    /**
     * 二维码链接
     */
    private String qrUrl;

    /** 模糊查询专用 */
    private String like;
}
