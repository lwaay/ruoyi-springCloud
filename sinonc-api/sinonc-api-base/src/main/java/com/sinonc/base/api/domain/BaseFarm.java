package com.sinonc.base.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * 基地信息对象 base_farm
 *
 * @author ruoyi
 * @date 2020-09-25
 */
public class BaseFarm extends BaseEntity {
    private static final long serialVersionUID = 1L;


    /**
     * 基地id
     */
    private Long farmId;

    /**
     * 基地编号
     */
    private Long farmCode;

    /**
     * 基地名称
     */
    @Excel(name = "基地名称")
    private String farmName;

    /** 经营者身份证号码 */
    @Excel(name = "经营者身份证号码")
    private String legalIdcard;

    /**
     * 基地面积 ，单位，平方米
     */
    @Excel(name = "基地面积 ，单位，平方米")
    private Integer baseArea;

    /**
     * 基地联系人
     */
    @Excel(name = "基地联系人")
    private String principal;

    /**
     * 简称
     */
    @Excel(name = "简称")
    private String forShort;

    /**
     * 所属部门id
     */
    @Excel(name = "所属部门id")
    private Long deptIdP;

    /**
     * 联系方式
     */
    @Excel(name = "联系方式")
    private String phone;

    @ApiModelProperty(value = "当前页")
    private Integer pageNum;

    @ApiModelProperty(value = "页大小")
    private Integer pageSize;

    /**
     * 经营主体ID
     */
    private Long entityId;


    /**
     * 会员id
     */
    private Long memberId;


    /**
     * 建立时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "建立时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date baseCreateDate;

    /**
     * 是否有摄像头 1有，0没有
     */
    @Excel(name = "是否有摄像头 1有，0没有")
    private Long hasMonitor;

    /**
     * 所属区域id
     */
    @Excel(name = "所属区域id")
    private String areaCode;

    /**
     * 是否物联网设备 1是，0否
     */
    @Excel(name = "是否物联网设备 1是，0否")
    private Long hasIot;

    /**
     * 设备id数组
     */
    @Excel(name = "设备id数组")
    private String deviceIds;

    /**
     * 经度
     */
    @Excel(name = "经度")
    private String farmLng;

    /**
     * 纬度
     */
    @Excel(name = "纬度")
    private String farmLat;

    /**
     * 基地图片地址     用,隔开
     */
    @Excel(name = "基地图片地址     用,隔开")
    private String pictures;

    /**
     * 基地描述
     */
    @Excel(name = "基地描述")
    private String remark;

    /**
     * 0，未删除，1，已删除
     */
    private Integer delFlag;

    /**
     * 作物名称数组
     */
    @Excel(name = "作物名称数组")
    private String cropNames;

    /**
     * 监控id数组
     */
    @Excel(name = "监控id数组")
    private String monitorIds;

    /**
     * 地址
     */
    @Excel(name = "地址")
    private String address;


    /** 评分 */
    @Excel(name = "评分")
    private BigDecimal gradeScore;

    /** 评级 */
    @Excel(name = "评级")
    private String gradeRate;

    /** 产量预估(KG) */
    @Excel(name = "产量预估(KG)")
    private BigDecimal yieldEst;

    /** 地块信息（富文本） */
    @Excel(name = "地块信息", readConverterExp = "富=文本")
    private String plotInfo;

    /** 生长环境（富文本，去掉会动态变化的） */
    @Excel(name = "生长环境", readConverterExp = "富=文本，去掉会动态变化的")
    private String growEnv;

    /** 林权证照片 */
    @Excel(name = "林权证照片")
    private String forestPic;

    public Long getFarmId() {
        return farmId;
    }

    public void setFarmId(Long farmId) {
        this.farmId = farmId;
    }

    public Long getFarmCode() {
        return farmCode;
    }

    public void setFarmCode(Long farmCode) {
        this.farmCode = farmCode;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public Integer getBaseArea() {
        return baseArea;
    }

    public void setBaseArea(Integer baseArea) {
        this.baseArea = baseArea;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getForShort() {
        return forShort;
    }

    public void setForShort(String forShort) {
        this.forShort = forShort;
    }

    public Long getDeptIdP() {
        return deptIdP;
    }

    public void setDeptIdP(Long deptIdP) {
        this.deptIdP = deptIdP;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public Integer getPageNum() {
        return pageNum;
    }

    @Override
    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public Integer getPageSize() {
        return pageSize;
    }

    @Override
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Date getBaseCreateDate() {
        return baseCreateDate;
    }

    public void setBaseCreateDate(Date baseCreateDate) {
        this.baseCreateDate = baseCreateDate;
    }

    public Long getHasMonitor() {
        return hasMonitor;
    }

    public void setHasMonitor(Long hasMonitor) {
        this.hasMonitor = hasMonitor;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Long getHasIot() {
        return hasIot;
    }

    public void setHasIot(Long hasIot) {
        this.hasIot = hasIot;
    }

    public String getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(String deviceIds) {
        this.deviceIds = deviceIds;
    }

    public String getFarmLng() {
        return farmLng;
    }

    public void setFarmLng(String farmLng) {
        this.farmLng = farmLng;
    }

    public String getFarmLat() {
        return farmLat;
    }

    public void setFarmLat(String farmLat) {
        this.farmLat = farmLat;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getCropNames() {
        return cropNames;
    }

    public void setCropNames(String cropNames) {
        this.cropNames = cropNames;
    }

    public String getMonitorIds() {
        return monitorIds;
    }

    public void setMonitorIds(String monitorIds) {
        this.monitorIds = monitorIds;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getGradeScore() {
        return gradeScore;
    }

    public void setGradeScore(BigDecimal gradeScore) {
        this.gradeScore = gradeScore;
    }

    public String getGradeRate() {
        return gradeRate;
    }

    public void setGradeRate(String gradeRate) {
        this.gradeRate = gradeRate;
    }

    public BigDecimal getYieldEst() {
        return yieldEst;
    }

    public void setYieldEst(BigDecimal yieldEst) {
        this.yieldEst = yieldEst;
    }

    public String getPlotInfo() {
        return plotInfo;
    }

    public void setPlotInfo(String plotInfo) {
        this.plotInfo = plotInfo;
    }

    public String getGrowEnv() {
        return growEnv;
    }

    public void setGrowEnv(String growEnv) {
        this.growEnv = growEnv;
    }


    public String getLegalIdcard() {
        return legalIdcard;
    }

    public void setLegalIdcard(String legalIdcard) {
        this.legalIdcard = legalIdcard;
    }

    public String getForestPic() {
        return forestPic;
    }

    public void setForestPic(String forestPic) {
        this.forestPic = forestPic;
    }

    public BaseFarm(Long entityId) {
        this.entityId = entityId;
    }

    public BaseFarm(){}
}
