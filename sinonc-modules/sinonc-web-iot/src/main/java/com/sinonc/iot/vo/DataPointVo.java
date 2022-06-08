package com.sinonc.iot.vo;

import java.io.Serializable;


public class DataPointVo implements Serializable {
    public DataPointVo(){
    }

    /**
     * 设备要素
     */
    public String metric;
    /**
     * 时间戳
     */
    public Long timestamp;
    /**
     * 要素值
     */
    public Double value;
    /**
     * 设备id
     */
    public String deviceId;

    /**
     * 0 离线  1 正常  2 上线
     */
    public long online;

    /**
     * 要数名称
     * @return
     */
    public String property;

    /**
     * 单位
     * @return
     */
    public String unit;

    /**
     * 属性名称
     */
    public String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getOnline() {
        return online;
    }

    public void setOnline(long online) {
        this.online = online;
    }


    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "DataPointVo{" +
                "metric='" + metric + '\'' +
                ", timestamp=" + timestamp +
                ", value=" + value +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }
}
