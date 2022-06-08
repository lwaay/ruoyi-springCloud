package com.sinonc.iot.vo;

import java.io.Serializable;

/**
 * @author: yehuiwang
 * @date: 2020/11/6 18:14
 * @description:
 */
public class RealData implements Serializable {
    private String metrics;
    private String value;
    private String unit;
    private String name;
    private String online;
    private String lastTime;

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
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



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }


    @Override
    public String toString() {
        return "EchartsData{" +
                "metrics='" + metrics + '\'' +
                ", value=" + value +
                ", unit='" + unit + '\'' +
                ", name='" + name + '\'' +
                ", online='" + online + '\'' +
                '}';
    }
}
