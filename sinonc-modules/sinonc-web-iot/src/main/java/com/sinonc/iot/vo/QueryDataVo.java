package com.sinonc.iot.vo;

import java.util.Map;

/**
 * @author: yehuiwang
 * @date: 2020/11/7 9:12
 * @description:
 */
public class QueryDataVo {
    /**
     * 设备要素
     */
    private String metric;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 聚合函数
     */
    private String aggregator;
    /**
     * 取样类型
     */
    private String downsample;

    private Map<String,String> params;


    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAggregator() {
        return aggregator;
    }

    public void setAggregator(String aggregator) {
        this.aggregator = aggregator;
    }

    public String getDownsample() {
        return downsample;
    }

    public void setDownsample(String downsample) {
        this.downsample = downsample;
    }

    @Override
    public String toString() {
        return "QueryDataVo{" +
                "metric='" + metric + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", aggregator='" + aggregator + '\'' +
                ", downsample='" + downsample + '\'' +
                ", params=" + params +
                '}';
    }
}
