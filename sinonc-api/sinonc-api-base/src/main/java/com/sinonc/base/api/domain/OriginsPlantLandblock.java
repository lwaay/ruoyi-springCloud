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
 * 地块信息（种植）
 * 对象 origins_plant_landblock
 *
 * @author ruoyi
 * @date 2021-04-25
 */
public class OriginsPlantLandblock extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 主键
     */
    private String landBlockId;

    /**
     * 地块类型
     */
    @Excel(name = "地块类型")
    private String landBlockType;

    /**
     * 地块名称
     */
    @Excel(name = "地块名称")
    private String landBlockName;

    /**
     * 地块面积
     */
    @Excel(name = "地块面积")
    private BigDecimal landBlockArea;

    /**
     * 面积单位
     */
    @Excel(name = "面积单位")
    private String mjdw;

    /**
     * 负责人
     */
    @Excel(name = "负责人")
    private String chargeFarmer;

    /**
     * 负责人电话
     */
    @Excel(name = "负责人电话")
    private String fzrdh;

    /**
     * 所属市编码
     */
    @Excel(name = "所属市编码")
    private String city;

    /**
     * 所属区县编码
     */
    @Excel(name = "所属区县编码")
    private String district;

    /**
     * 所属乡镇编码
     */
    @Excel(name = "所属乡镇编码")
    private String town;

    /**
     * 地块位置
     */
    @Excel(name = "地块位置")
    private String location;

    /**
     * 企业标识(id)
     */
    @Excel(name = "企业标识(id)")
    private String corpId;

    /**
     * 地块编号
     */
    @Excel(name = "地块编号")
    private String jdbh;

    /**
     * 执行标准
     */
    @Excel(name = "执行标准")
    private String zxbz;

    /**
     * 作物/饲养
     */
    @Excel(name = "作物/饲养")
    private String zwsy;

    /**
     * 环境检测单位
     */
    @Excel(name = "环境检测单位")
    private String hjjcdw;

    /**
     * 环境检测单位电话
     */
    @Excel(name = "环境检测单位电话")
    private String hjjcdwdh;

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
     * 图片
     */
    @Excel(name = "图片")
    private String picture;

    @JSONField(name = "LAND_BLOCK_ID")
    public void setLandBlockId(String landBlockId) {
        this.landBlockId = landBlockId;
    }

    public String getLandBlockId() {
        return landBlockId;
    }

    @JSONField(name = "LAND_BLOCK_TYPE")
    public void setLandBlockType(String landBlockType) {
        this.landBlockType = landBlockType;
    }

    public String getLandBlockType() {
        return landBlockType;
    }

    @JSONField(name = "LAND_BLOCK_NAME")
    public void setLandBlockName(String landBlockName) {
        this.landBlockName = landBlockName;
    }

    public String getLandBlockName() {
        return landBlockName;
    }

    @JSONField(name = "LAND_BLOCK_AREA")
    public void setLandBlockArea(BigDecimal landBlockArea) {
        this.landBlockArea = landBlockArea;
    }

    public BigDecimal getLandBlockArea() {
        return landBlockArea;
    }

    @JSONField(name = "MJDW")
    public void setMjdw(String mjdw) {
        this.mjdw = mjdw;
    }

    public String getMjdw() {
        return mjdw;
    }

    @JSONField(name = "CHARGE_FARMER")
    public void setChargeFarmer(String chargeFarmer) {
        this.chargeFarmer = chargeFarmer;
    }

    public String getChargeFarmer() {
        return chargeFarmer;
    }

    @JSONField(name = "FZRDH")
    public void setFzrdh(String fzrdh) {
        this.fzrdh = fzrdh;
    }

    public String getFzrdh() {
        return fzrdh;
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

    @JSONField(name = "LOCATION")
    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    @JSONField(name = "CORP_ID")
    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getCorpId() {
        return corpId;
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

    @JSONField(name = "ZWSY")
    public void setZwsy(String zwsy) {
        this.zwsy = zwsy;
    }

    public String getZwsy() {
        return zwsy;
    }

    @JSONField(name = "HJJCDW")
    public void setHjjcdw(String hjjcdw) {
        this.hjjcdw = hjjcdw;
    }

    public String getHjjcdw() {
        return hjjcdw;
    }

    @JSONField(name = "HJJCDWDH")
    public void setHjjcdwdh(String hjjcdwdh) {
        this.hjjcdwdh = hjjcdwdh;
    }

    public String getHjjcdwdh() {
        return hjjcdwdh;
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

    @JSONField(name = "PICTURE")
    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPicture() {
        return picture;
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
                .append("landBlockId", getLandBlockId())
                .append("landBlockType", getLandBlockType())
                .append("landBlockName", getLandBlockName())
                .append("landBlockArea", getLandBlockArea())
                .append("mjdw", getMjdw())
                .append("chargeFarmer", getChargeFarmer())
                .append("fzrdh", getFzrdh())
                .append("city", getCity())
                .append("district", getDistrict())
                .append("town", getTown())
                .append("location", getLocation())
                .append("corpId", getCorpId())
                .append("jdbh", getJdbh())
                .append("zxbz", getZxbz())
                .append("zwsy", getZwsy())
                .append("hjjcdw", getHjjcdw())
                .append("hjjcdwdh", getHjjcdwdh())
                .append("jcyjbzbhjmc", getJcyjbzbhjmc())
                .append("jcsj", getJcsj())
                .append("jcjl", getJcjl())
                .append("jcbg", getJcbg())
                .append("productNames", getProductNames())
                .append("picture", getPicture())
                .append("remark", getRemark())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
