package com.sinonc.iot.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 设备告警条件设置对象 iot_warn_set
 *
 * @author ruoyi
 * @date 2021-04-09
 */
public class IotWarnSet extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long warnsetId;

    /**
     * 设备ID
     */
    @Excel(name = "设备ID")
    private String deviceId;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    private String deviceName;

    /**
     * 最低阈值
     */
    @Excel(name = "最低阈值")
    private Double lowLimit;

    /**
     * 最高阈值
     */
    @Excel(name = "最高阈值")
    private Double highLimit;

    /**
     * 超低阈值
     */
    @Excel(name = "超低阈值")
    private Double superLowLimit;

    /**
     * 是否启用(0未启用1启用)
     */
    @Excel(name = "是否启用(0未启用1启用)")
    private Integer isEnble;

    /**
     * 处理意见
     */
    @Excel(name = "处理意见")
    private String handlingSuggestion;

    /**
     * 设备属性
     */
    @Excel(name = "设备属性")
    private String metric;

    /**
     * 预警电话
     */
    @Excel(name= "预警电话")
    private String phone;

    public void setWarnsetId(Long warnsetId) {
        this.warnsetId = warnsetId;
    }

    public Long getWarnsetId() {
        return warnsetId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setLowLimit(Double lowLimit) {
        this.lowLimit = lowLimit;
    }

    public Double getLowLimit() {
        return lowLimit;
    }

    public void setHighLimit(Double highLimit) {
        this.highLimit = highLimit;
    }

    public Double getHighLimit() {
        return highLimit;
    }

    public void setSuperLowLimit(Double superLowLimit) {
        this.superLowLimit = superLowLimit;
    }

    public Double getSuperLowLimit() {
        return superLowLimit;
    }

    public void setIsEnble(Integer isEnble) {
        this.isEnble = isEnble;
    }

    public Integer getIsEnble() {
        return isEnble;
    }

    public void setHandlingSuggestion(String handlingSuggestion) {
        this.handlingSuggestion = handlingSuggestion;
    }

    public String getHandlingSuggestion() {
        return handlingSuggestion;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public String getMetric() {
        return metric;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("warnsetId", getWarnsetId())
                .append("deviceId", getDeviceId())
                .append("lowLimit", getLowLimit())
                .append("highLimit", getHighLimit())
                .append("superLowLimit", getSuperLowLimit())
                .append("isEnble", getIsEnble())
                .append("handlingSuggestion", getHandlingSuggestion())
                .append("metric", getMetric())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
