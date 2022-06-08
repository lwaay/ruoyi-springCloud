package com.sinonc.iot.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 设备告警信息对象 iot_warn
 *
 * @author ruoyi
 * @date 2021-04-09
 */
public class IotWarn extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long warnId;

    /**
     * 设备ID
     */
    @Excel(name = "设备ID")
    private String deviceId;

    /**
     * 设备属性
     */
    @Excel(name = "设备属性")
    private String metric;

    /**
     * 属性值
     */
    @Excel(name = "属性值")
    private Double metricValue;

    /**
     * 数据报送时间
     */
    private String metricTime;

    /**
     * 报警信息
     */
    @Excel(name = "报警信息")
    private String warnInfo;

    /**
     * 处理状态(1已处理0未处理)
     */
    @Excel(name = "处理状态(1已处理0未处理)")
    private Integer isHandle;

    /**
     * 处理记录
     */
    @Excel(name = "处理记录")
    private String handleRecord;

    /**
     * 告警类型(0过低 1过高 2正常 3离线)
     */
    @Excel(name = "告警类型(0过低 1过高 2正常 3离线)")
    private Integer warnType;

    /**
     * 物联网设备名称
     */
    private String deviceName;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setWarnId(Long warnId) {
        this.warnId = warnId;
    }

    public Long getWarnId() {
        return warnId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetricValue(Double metricValue) {
        this.metricValue = metricValue;
    }

    public Double getMetricValue() {
        return metricValue;
    }

    public void setMetricTime(String metricTime) {
        this.metricTime = metricTime;
    }

    public String getMetricTime() {
        return metricTime;
    }

    public void setWarnInfo(String warnInfo) {
        this.warnInfo = warnInfo;
    }

    public String getWarnInfo() {
        return warnInfo;
    }

    public void setIsHandle(Integer isHandle) {
        this.isHandle = isHandle;
    }

    public Integer getIsHandle() {
        return isHandle;
    }

    public void setHandleRecord(String handleRecord) {
        this.handleRecord = handleRecord;
    }

    public String getHandleRecord() {
        return handleRecord;
    }

    public void setWarnType(Integer warnType) {
        this.warnType = warnType;
    }

    public Integer getWarnType() {
        return warnType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("warnId", getWarnId())
                .append("deviceId", getDeviceId())
                .append("metric", getMetric())
                .append("metricValue", getMetricValue())
                .append("metricTime", getMetricTime())
                .append("warnInfo", getWarnInfo())
                .append("isHandle", getIsHandle())
                .append("handleRecord", getHandleRecord())
                .append("warnType", getWarnType())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
