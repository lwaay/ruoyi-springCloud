package com.sinonc.base.api.vo;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;

import java.util.Date;
import java.util.Objects;

/**
 * 基地信息对象 base_farm
 *
 * @author ruoyi
 * @date 2020-09-25
 */
public class BaseFarmVo {

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

    /** 简称 */
    @Excel(name = "简称")
    private String forShort;

    /** 所属部门id */
    @Excel(name = "所属部门id")
    private Integer deptIdP;

    /**
     * 联系方式
     */
    @Excel(name = "联系方式")
    private String phone;

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

    /** 作物名称数组 */
    @Excel(name = "作物名称数组")
    private String cropNames;

    /**
     * 监控id数组
     */
    @Excel(name = "监控id数组")
    private String monitorIds;

    /**
     *地址
     */
    @Excel(name = "地址")
    private String address;

    private JSONArray devices;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
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

    public String getCropNames() {
        return cropNames;
    }

    public void setCropNames(String cropNames) {
        this.cropNames = cropNames;
    }

    public String getForShort() {
        return forShort;
    }

    public void setForShort(String forShort) {
        this.forShort = forShort;
    }


    public Integer getDeptIdP() {
        return deptIdP;
    }

    public void setDeptIdP(Integer deptIdP) {
        this.deptIdP = deptIdP;
    }

    public JSONArray getDevices() {
        return devices;
    }

    public void setDevices(JSONArray devices) {
        this.devices = devices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseFarmVo baseFarm = (BaseFarmVo) o;
        return Objects.equals(farmId, baseFarm.farmId) && Objects.equals(farmCode, baseFarm.farmCode) && Objects.equals(farmName, baseFarm.farmName) && Objects.equals(baseArea, baseFarm.baseArea) && Objects.equals(principal, baseFarm.principal) && Objects.equals(forShort, baseFarm.forShort) && Objects.equals(deptIdP, baseFarm.deptIdP) && Objects.equals(phone, baseFarm.phone) && Objects.equals(baseCreateDate, baseFarm.baseCreateDate) && Objects.equals(hasMonitor, baseFarm.hasMonitor) && Objects.equals(areaCode, baseFarm.areaCode) && Objects.equals(hasIot, baseFarm.hasIot) && Objects.equals(deviceIds, baseFarm.deviceIds) && Objects.equals(farmLng, baseFarm.farmLng) && Objects.equals(farmLat, baseFarm.farmLat) && Objects.equals(pictures, baseFarm.pictures) && Objects.equals(delFlag, baseFarm.delFlag) && Objects.equals(cropNames, baseFarm.cropNames) && Objects.equals(monitorIds, baseFarm.monitorIds) && Objects.equals(address, baseFarm.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(farmId, farmCode, farmName, baseArea, principal, forShort, deptIdP, phone, baseCreateDate, hasMonitor, areaCode, hasIot, deviceIds, farmLng, farmLat, pictures, delFlag, cropNames, monitorIds, address);
    }
}
