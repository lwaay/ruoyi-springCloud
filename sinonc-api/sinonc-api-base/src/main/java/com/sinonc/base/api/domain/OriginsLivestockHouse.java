package com.sinonc.base.api.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 圈舍信息（畜禽）
 * 对象 origins_livestock_house
 *
 * @author ruoyi
 * @date 2021-04-25
 */
public class OriginsLivestockHouse extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 主键
     */
    private String houseId;

    /**
     * 饲养种类
     */
    @Excel(name = "饲养种类")
    private String breed;

    /**
     * 圈舍名称
     */
    @Excel(name = "圈舍名称")
    private String houseName;

    /**
     * 圈舍面积
     */
    @Excel(name = "圈舍面积")
    private BigDecimal houseArea;

    /**
     * 面积单位
     */
    @Excel(name = "面积单位")
    private String areaUnit;

    /**
     * 圈舍位置
     */
    @Excel(name = "圈舍位置")
    private String houseLocation;

    /**
     * 企业ID
     */
    @Excel(name = "企业ID")
    private String corpId;

    /**
     * 添加时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date adddatetime;

    /**
     * 品种类型（1、畜类 2禽类）
     */
    @Excel(name = "品种类型", readConverterExp = "1=、畜类,2=禽类")
    private Integer breedType;

    /**
     * 环境检测单位电话
     */
    @Excel(name = "环境检测单位电话")
    private String hjjcdwdh;

    /**
     * 基地编号
     */
    @Excel(name = "基地编号")
    private String jdbh;

    /**
     * 执行标准
     */
    @Excel(name = "执行标准")
    private String zxbz;

    /**
     * 负责人
     */
    @Excel(name = "负责人")
    private String fzr;

    /**
     * 负责人电话
     */
    @Excel(name = "负责人电话")
    private String fzrdh;

    /**
     * 环境检测单位
     */
    @Excel(name = "环境检测单位")
    private String hjjcdw;

    /**
     * 检测依据（标准编号及名称）
     */
    @Excel(name = "检测依据", readConverterExp = "标=准编号及名称")
    private String jcyjbzbhjmc;

    /**
     * 检测时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "检测时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date jcsj;

    /**
     * 检测结论
     */
    @Excel(name = "检测结论")
    private String jcjl;

    /**
     * 检测报告
     */
    @Excel(name = "检测报告")
    private String jcbg;

    /**
     * 主要产品
     */
    @Excel(name = "主要产品")
    private String productNames;

    /**
     * 市级编码
     */
    @Excel(name = "市级编码")
    private String city;

    /**
     * 区县编码
     */
    @Excel(name = "区县编码")
    private String district;

    /**
     * 乡镇编码
     */
    @Excel(name = "乡镇编码")
    private String town;

    /**
     * 经度
     */
    @Excel(name = "经度")
    private String longitude;

    /**
     * 纬度
     */
    @Excel(name = "纬度")
    private String latitude;

    /**
     * 状态: Y启用，N停用
     */
    @Excel(name = "状态: Y启用，N停用")
    private String status;

    /**
     * IP
     */
    @Excel(name = "IP")
    private String ip;

    /**
     * 图片
     */
    @Excel(name = "图片")
    private String licensePath;


    @JSONField(name = "HOUSE_ID")
    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getHouseId() {
        return houseId;
    }

    @JSONField(name = "BREED")
    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    @JSONField(name = "HOUSE_NAME")
    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getHouseName() {
        return houseName;
    }

    @JSONField(name = "HOUSE_AREA")
    public void setHouseArea(BigDecimal houseArea) {
        this.houseArea = houseArea;
    }

    public BigDecimal getHouseArea() {
        return houseArea;
    }

    @JSONField(name = "AREA_UNIT")
    public void setAreaUnit(String areaUnit) {
        this.areaUnit = areaUnit;
    }

    public String getAreaUnit() {
        return areaUnit;
    }

    @JSONField(name = "HOUSE_LOCATION")
    public void setHouseLocation(String houseLocation) {
        this.houseLocation = houseLocation;
    }

    public String getHouseLocation() {
        return houseLocation;
    }

    @JSONField(name = "CORP_ID")
    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getCorpId() {
        return corpId;
    }

    @JSONField(name = "ADDDATETIME")
    public void setAdddatetime(Date adddatetime) {
        this.adddatetime = adddatetime;
    }

    public Date getAdddatetime() {
        return adddatetime;
    }

    @JSONField(name = "BREED_TYPE")
    public void setBreedType(Integer breedType) {
        this.breedType = breedType;
    }

    public Integer getBreedType() {
        return breedType;
    }

    @JSONField(name = "HJJCDWDH")
    public void setHjjcdwdh(String hjjcdwdh) {
        this.hjjcdwdh = hjjcdwdh;
    }

    public String getHjjcdwdh() {
        return hjjcdwdh;
    }

    @JSONField(name = "JDBH")
    public void setJdbh(String jdbh) {
        this.jdbh = jdbh;
    }

    public String getJdbh() {
        return jdbh;
    }

    @JSONField(name = "ZXBZ")
    public void setZxbz(String zxbz) {
        this.zxbz = zxbz;
    }

    public String getZxbz() {
        return zxbz;
    }

    @JSONField(name = "FZR")
    public void setFzr(String fzr) {
        this.fzr = fzr;
    }

    public String getFzr() {
        return fzr;
    }

    @JSONField(name = "FZRDH")
    public void setFzrdh(String fzrdh) {
        this.fzrdh = fzrdh;
    }

    public String getFzrdh() {
        return fzrdh;
    }

    @JSONField(name = "HJJCDW")
    public void setHjjcdw(String hjjcdw) {
        this.hjjcdw = hjjcdw;
    }

    public String getHjjcdw() {
        return hjjcdw;
    }

    @JSONField(name = "JCYJBZBHJMC")
    public void setJcyjbzbhjmc(String jcyjbzbhjmc) {
        this.jcyjbzbhjmc = jcyjbzbhjmc;
    }

    public String getJcyjbzbhjmc() {
        return jcyjbzbhjmc;
    }

    @JSONField(name = "JCSJ")
    public void setJcsj(Date jcsj) {
        this.jcsj = jcsj;
    }

    public Date getJcsj() {
        return jcsj;
    }

    @JSONField(name = "JCJL")
    public void setJcjl(String jcjl) {
        this.jcjl = jcjl;
    }

    public String getJcjl() {
        return jcjl;
    }

    @JSONField(name = "JCBG")
    public void setJcbg(String jcbg) {
        this.jcbg = jcbg;
    }

    public String getJcbg() {
        return jcbg;
    }

    @JSONField(name = "PRODUCT_NAMES")
    public void setProductNames(String productNames) {
        this.productNames = productNames;
    }

    public String getProductNames() {
        return productNames;
    }

    @JSONField(name = "CITY")
    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    @JSONField(name = "DISTRICT")
    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrict() {
        return district;
    }

    @JSONField(name = "TOWN")
    public void setTown(String town) {
        this.town = town;
    }

    public String getTown() {
        return town;
    }

    @JSONField(name = "LONGITUDE")
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLongitude() {
        return longitude;
    }

    @JSONField(name = "LATITUDE")
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLatitude() {
        return latitude;
    }

    @JSONField(name = "STATUS")
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @JSONField(name = "IP")
    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    @JSONField(name = "LICENSE_PATH")
    public void setLicensePath(String licensePath) {
        this.licensePath = licensePath;
    }

    public String getLicensePath() {
        return licensePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("houseId", getHouseId())
                .append("breed", getBreed())
                .append("houseName", getHouseName())
                .append("houseArea", getHouseArea())
                .append("areaUnit", getAreaUnit())
                .append("houseLocation", getHouseLocation())
                .append("corpId", getCorpId())
                .append("adddatetime", getAdddatetime())
                .append("breedType", getBreedType())
                .append("hjjcdwdh", getHjjcdwdh())
                .append("jdbh", getJdbh())
                .append("zxbz", getZxbz())
                .append("fzr", getFzr())
                .append("fzrdh", getFzrdh())
                .append("hjjcdw", getHjjcdw())
                .append("jcyjbzbhjmc", getJcyjbzbhjmc())
                .append("jcsj", getJcsj())
                .append("jcjl", getJcjl())
                .append("jcbg", getJcbg())
                .append("productNames", getProductNames())
                .append("city", getCity())
                .append("district", getDistrict())
                .append("town", getTown())
                .append("longitude", getLongitude())
                .append("latitude", getLatitude())
                .append("status", getStatus())
                .append("ip", getIp())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("licensePath", getLicensePath())
                .toString();
    }
}
