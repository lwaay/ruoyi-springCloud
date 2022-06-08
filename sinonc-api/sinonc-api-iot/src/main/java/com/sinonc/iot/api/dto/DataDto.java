package com.sinonc.iot.api.dto;

/**
 * @author: yehuiwang
 * @date: 2020/11/6 11:00
 * @description:
 */
public class DataDto {

    /**
     * 要素
     */
    private String metrics;
    /**
     *  要素值
     * @return
     */
    private String value;
    /**
     * 时间 格式2020-11-06 10:33:10
     */
    private String dataTime;



    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public String getMetrics() {
        return metrics;
    }

    public void setMetrics(String metrics) {
        this.metrics = metrics;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
