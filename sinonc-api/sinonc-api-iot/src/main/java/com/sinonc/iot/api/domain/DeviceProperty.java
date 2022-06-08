package com.sinonc.iot.api.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.ibatis.type.Alias;

/**
 * 设备属性对象 device_property
 *
 * @author ruoyi
 * @date 2020-11-05
 */
@Alias("DeviceProperty")
public class DeviceProperty extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 类型id
     */
    private Long propertyId;

    /**
     * 设备属性
     */
    @Excel(name = "设备属性")
    private String property;

    /**
     * $column.columnComment
     */
    @Excel(name = "设备属性")
    private String value;

    /**
     * 属性名称
     */
    @Excel(name = "属性名称")
    private String name;

    /**
     * 所属设备类型
     */
    @Excel(name = "所属设备类型")
    private String deviceTypeId;

    /**
     * 单位
     */
    @Excel(name = "单位")
    private String unit;

    @Excel(name="图片地址")
    private String imageUrl;

    /**
     * 设备编号
     */
    private String deviceIdp;
    private String deviceName;

    private String lastTime;

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getProperty() {
        return property;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDeviceTypeId(String deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public String getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDeviceIdp() {
        return deviceIdp;
    }

    public void setDeviceIdp(String deviceIdp) {
        this.deviceIdp = deviceIdp;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("propertyId", getPropertyId())
                .append("property", getProperty())
                .append("value", getValue())
                .append("name", getName())
                .append("deviceTypeId", getDeviceTypeId())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("unit", getUnit())
                .toString();
    }
}
