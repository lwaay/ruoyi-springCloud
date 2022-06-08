package com.sinonc.orders.ec.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 实时爬取电商数据对象 eshop_product_realtime
 *
 * @author hhao
 * @date 2021-03-24
 */
public class EshopProductRealtime extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */

    @TableId(value = "eshop_id" , type = IdType.AUTO)
    private Long eshopId;

    /**
     * 电商平台类型1 taobao 2 tmall 3 jd 4 pdd
     */
    @Excel(name = "电商平台类型1 taobao 2 tmall 3 jd 4 pdd")
    private Integer platform;

    /**
     * 店铺名
     */
    @Excel(name = "店铺名")
    private String name;

    /**
     * 商品id
     */
    @Excel(name = "商品id")
    private Long goodsId;

    /**
     * 商品标题
     */
    @Excel(name = "商品标题")
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
    @Excel(name = "店铺所在地")
    private String shopAddr;

    /**
     * 图片链接
     */
    @Excel(name = "图片链接")
    private String images;

    /**
     * 净含量
     */
    @Excel(name = "净含量")
    private String weight;

    /**
     * 工厂地址
     */
    @Excel(name = "工厂地址")
    private String factoryAddr;

    /**
     * 工厂名称
     */
    @Excel(name = "工厂名称")
    private String factoryName;

    /**
     * 销售时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "销售时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date saleTime;

    /**
     * 网址
     */
    @Excel(name = "网址")
    private String url;

    /**
     * 商品品类
     */
    @Excel(name = "商品品类")
    private String productType;

    /**
     * 特产品类
     */
    @Excel(name = "特产品类")
    private String specialtyType;

    /**
     * 品牌
     */
    @Excel(name = "品牌")
    private String brand;

    /**
     * 1 开启 0 关闭
     */
    @Excel(name = "1 开启 0 关闭")
    private Integer reptileStatus;

    /**
     * 定时任务id
     */
    @Excel(name = "定时任务id")
    private Long cronId;

    /**
     * 1 开启 0 关闭
     */
    @Excel(name = "1 开启 0 关闭")
    private Integer reptileStatusRealtime;

    /**
     * 周期任务id
     */
    @Excel(name = "周期任务id")
    private Long cronIdRealtime;

    public void setEshopId(Long eshopId) {
        this.eshopId = eshopId;
    }

    public Long getEshopId() {
        return eshopId;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setSaleCount(Long saleCount) {
        this.saleCount = saleCount;
    }

    public Long getSaleCount() {
        return saleCount;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setShopAddr(String shopAddr) {
        this.shopAddr = shopAddr;
    }

    public String getShopAddr() {
        return shopAddr;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getImages() {
        return images;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeight() {
        return weight;
    }

    public void setFactoryAddr(String factoryAddr) {
        this.factoryAddr = factoryAddr;
    }

    public String getFactoryAddr() {
        return factoryAddr;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setSaleTime(Date saleTime) {
        this.saleTime = saleTime;
    }

    public Date getSaleTime() {
        return saleTime;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductType() {
        return productType;
    }

    public void setSpecialtyType(String specialtyType) {
        this.specialtyType = specialtyType;
    }

    public String getSpecialtyType() {
        return specialtyType;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setReptileStatus(Integer reptileStatus) {
        this.reptileStatus = reptileStatus;
    }

    public Integer getReptileStatus() {
        return reptileStatus;
    }

    public void setCronId(Long cronId) {
        this.cronId = cronId;
    }

    public Long getCronId() {
        return cronId;
    }

    public void setReptileStatusRealtime(Integer reptileStatusRealtime) {
        this.reptileStatusRealtime = reptileStatusRealtime;
    }

    public Integer getReptileStatusRealtime() {
        return reptileStatusRealtime;
    }

    public void setCronIdRealtime(Long cronIdRealtime) {
        this.cronIdRealtime = cronIdRealtime;
    }

    public Long getCronIdRealtime() {
        return cronIdRealtime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("eshopId", getEshopId())
                .append("platform", getPlatform())
                .append("name", getName())
                .append("goodsId", getGoodsId())
                .append("title", getTitle())
                .append("saleCount", getSaleCount())
                .append("salePrice", getSalePrice())
                .append("price", getPrice())
                .append("shopAddr", getShopAddr())
                .append("images", getImages())
                .append("weight", getWeight())
                .append("factoryAddr", getFactoryAddr())
                .append("factoryName", getFactoryName())
                .append("saleTime", getSaleTime())
                .append("url", getUrl())
                .append("productType", getProductType())
                .append("specialtyType", getSpecialtyType())
                .append("brand", getBrand())
                .append("reptileStatus", getReptileStatus())
                .append("cronId", getCronId())
                .append("reptileStatusRealtime", getReptileStatusRealtime())
                .append("cronIdRealtime", getCronIdRealtime())
                .toString();
    }
}
