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
 * 池塘信息（水产）
 * 对象 origins_fish_pond
 *
 * @author ruoyi
 * @date 2021-04-25
 */
public class OriginsFishPond extends BaseEntity {
    private static final long serialVersionUID = 1L;



    /**
     * 主键
     */
    private Long id;
    /**
     * 主键
     */
    private String pondId;

    /**
     * 池塘名称
     */
    @Excel(name = "池塘名称")
    private String pondName;

    /**
     * 池塘编号
     */
    @Excel(name = "池塘编号")
    private String jdbh;

    /**
     * 池塘地址
     */
    @Excel(name = "池塘地址")
    private String jddz;

    /**
     * 执行标准
     */
    @Excel(name = "执行标准")
    private String zxbz;

    /**
     * 平均水深
     */
    @Excel(name = "平均水深")
    private BigDecimal depth;

    /**
     * 池塘面积
     */
    @Excel(name = "池塘面积")
    private BigDecimal area;

    /**
     * 面积单位
     */
    @Excel(name = "面积单位")
    private String areaUnit;

    /**
     * 行业名称
     */
    @Excel(name = "行业名称")
    private String hymc;

    /**
     * 作物/饲养
     */
    @Excel(name = "作物/饲养")
    private String zwsy;

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
     * 经度
     */
    @Excel(name = "经度")
    private BigDecimal longitude;

    /**
     * 纬度
     */
    @Excel(name = "纬度")
    private BigDecimal latitude;

    /**
     * 图片
     */
    @Excel(name = "图片")
    private String picture;

    /**
     * 状态Y启用，N停用
     */
    @Excel(name = "状态Y启用，N停用")
    private String status;

    /**
     * ip地址
     */
    @Excel(name = "ip地址")
    private String ip;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date adddatetime;

    /**
     * 企业ID
     */
    @Excel(name = "企业ID")
    private String corpId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JSONField(name = "POND_ID")
    public void setPondId(String pondId) {
        this.pondId = pondId;
    }

    public String getPondId() {
        return pondId;
    }

    @JSONField(name = "POND_NAME")
    public void setPondName(String pondName) {
        this.pondName = pondName;
    }

    public String getPondName() {
        return pondName;
    }

    @JSONField(name = "JDBH")
    public void setJdbh(String jdbh) {
        this.jdbh = jdbh;
    }

    public String getJdbh() {
        return jdbh;
    }

    @JSONField(name = "JDDZ")
    public void setJddz(String jddz) {
        this.jddz = jddz;
    }

    public String getJddz() {
        return jddz;
    }

    @JSONField(name = "ZXBZ")
    public void setZxbz(String zxbz) {
        this.zxbz = zxbz;
    }

    public String getZxbz() {
        return zxbz;
    }

    @JSONField(name = "DEPTH")
    public void setDepth(BigDecimal depth) {
        this.depth = depth;
    }

    public BigDecimal getDepth() {
        return depth;
    }

    @JSONField(name = "AREA")
    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getArea() {
        return area;
    }

    @JSONField(name = "AREA_UNIT")
    public void setAreaUnit(String areaUnit) {
        this.areaUnit = areaUnit;
    }

    public String getAreaUnit() {
        return areaUnit;
    }

    @JSONField(name = "HYMC")
    public void setHymc(String hymc) {
        this.hymc = hymc;
    }

    public String getHymc() {
        return hymc;
    }

    @JSONField(name = "ZWSY")
    public void setZwsy(String zwsy) {
        this.zwsy = zwsy;
    }

    public String getZwsy() {
        return zwsy;
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

    @JSONField(name = "LONGITUDE")
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    @JSONField(name = "LATITUDE")
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    @JSONField(name = "PICTURE")
    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPicture() {
        return picture;
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

    @JSONField(name = "ADDDATETIME")
    public void setAdddatetime(Date adddatetime) {
        this.adddatetime = adddatetime;
    }

    public Date getAdddatetime() {
        return adddatetime;
    }

    @JSONField(name = "CORP_ID")
    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getCorpId() {
        return corpId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("pondId", getPondId())
                .append("pondName", getPondName())
                .append("jdbh", getJdbh())
                .append("jddz", getJddz())
                .append("zxbz", getZxbz())
                .append("depth", getDepth())
                .append("area", getArea())
                .append("areaUnit", getAreaUnit())
                .append("hymc", getHymc())
                .append("zwsy", getZwsy())
                .append("fzr", getFzr())
                .append("fzrdh", getFzrdh())
                .append("hjjcdw", getHjjcdw())
                .append("hjjcdwdh", getHjjcdwdh())
                .append("jcyjbzbhjmc", getJcyjbzbhjmc())
                .append("jcsj", getJcsj())
                .append("jcjl", getJcjl())
                .append("jcbg", getJcbg())
                .append("productNames", getProductNames())
                .append("longitude", getLongitude())
                .append("latitude", getLatitude())
                .append("picture", getPicture())
                .append("status", getStatus())
                .append("ip", getIp())
                .append("createBy", getCreateBy())
                .append("adddatetime", getAdddatetime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("corpId", getCorpId())
                .toString();
    }
}
