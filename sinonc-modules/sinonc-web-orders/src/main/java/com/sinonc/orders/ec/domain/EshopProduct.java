package com.sinonc.orders.ec.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 电商数据对象 eshop_product
 *
 * @author ruoyi
 * @date 2020-09-27
 */
@Data
public class EshopProduct extends BaseEntity {
    public static final Integer ON = 1;
    public static final Integer OFF = 1;
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "eshop_id",type = IdType.AUTO)
    private Long eshopId;

    /**
     * 电商平台类型1 taobao 2 tmall 3 jd 4 pdd
     */
    @Excel(name = "电商平台类型",type = Excel.Type.EXPORT)
    private Integer platform;

    @Excel(name = "店铺类型")
    @TableField(exist = false)
    private String shopType;

    /**
     * 店铺名
     */
    @Excel(name = "店铺名称")
    private String name;

    /**
     * 商品标题
     */
    @Excel(name = "标题")
    private String title;

    /**
     * 销量
     */
    @Excel(name = "销量")
    private Long saleCount;

    /**
     * 销售额
     */
    @Excel(name = "销售额")
    private BigDecimal salePrice;

    /**
     * 价格
     */
    @Excel(name = "价格")
    private BigDecimal price;

    /**
     * 店铺所在地
     */
    @Excel(name = "店铺所在地域")
    private String shopAddr;

    /**
     * 图片链接
     */
    @Excel(name = "图片链接")
    private String images;

    /**
     * 净含量
     */
    @Excel(name = "属性:净含量")
    private String weight;

    /**
     * 工厂地址
     */
    @Excel(name = "属性:厂址")
    private String factoryAddr;

    /**
     * 工厂名称
     */
    @Excel(name = "属性:厂名")
    private String factoryName;

    /**
     * 销售时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date saleTime;

    /**
     * 网址
     */
    @Excel(name = "网址")
    private String url;

    /**
     * 商品品类 ：梁平鸭 梁平柚
     */
    @Excel(name = "三级类目名称")
    private String productType;

    /**
     * 特产品类
     */
    @Excel(name = "属性:特产品类")
    private String specialtyType;

    /**
     * 品牌
     */
    @Excel(name = "品牌名称")
    private String brand;

    /**
     * 商品id
     */
    @Excel(name = "商品ID")
    private Long goodsId;

    private Integer reptileStatus;

    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
}
