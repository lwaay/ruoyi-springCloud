package com.sinonc.iot.api.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 设备对象 device_info
 *
 * @author ruoyi
 * @date 2020-09-25
 */
public class DeviceInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 设备id
     */
    private String deviceId;

    /**
     * 设备类型id
     */
    @Excel(name = "设备类型id")
    private String deviceTypeId;

    /**
     * 0 未删除 ，1删除
     */
    private Integer delFlag;

    /**
     * 企业名称
     */
    @Excel(name = "企业名称")
    private String corpName;

    /**
     * 设备名称
     */
    @Excel(name = "设备名称")
    private String deviceName;

    /**
     * 设备ip
     */
    @Excel(name = "设备ip")
    private String ip;

    /**
     * 设备端口
     */
    @Excel(name = "设备端口")
    private String port;

    /**
     * 最后更新时间
     */
    @Excel(name = "最后更新时间")
    private String lastTime;

    /**
     * 纬度
     */
    @Excel(name = "纬度")
    private String latitude;

    /**
     * 经度
     */
    @Excel(name = "经度")
    private String longitude;

    /**
     * 单元ID
     */
    @Excel(name = "单元ID")
    private String unitId;

    /**
     * 说明
     */
    @Excel(name = "说明")
    private String remark;

    private String deviceTypeDesc;

    public String getDeviceTypeDesc() {
        return deviceTypeDesc;
    }

    public void setDeviceTypeDesc(String deviceTypeDesc) {
        this.deviceTypeDesc = deviceTypeDesc;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceTypeId(String deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public String getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPort() {
        return port;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitId() {
        return unitId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("deviceId", getDeviceId())
                .append("deviceTypeId", getDeviceTypeId())
                .append("delFlag", getDelFlag())
                .append("corpName", getCorpName())
                .append("deviceName", getDeviceName())
                .append("ip", getIp())
                .append("port", getPort())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("lastTime", getLastTime())
                .append("latitude", getLatitude())
                .append("longitude", getLongitude())
                .append("remark", getRemark())
                .append("unitId", getUnitId())
                .toString();
    }
}
