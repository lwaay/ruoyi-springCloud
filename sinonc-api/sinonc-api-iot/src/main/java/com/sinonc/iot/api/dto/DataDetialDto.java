package com.sinonc.iot.api.dto;

import java.io.Serializable;

/**
 * @author: yehuiwang
 * @date: 2020/11/9 16:24
 * @description:
 */
public class DataDetialDto implements Serializable {
    private String metrics;
    private String name;
    private String value;
    private String unit;
    private String imageUrl;

    public String getMetrics() {
        return metrics;
    }

    public void setMetrics(String metrics) {
        this.metrics = metrics;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "DataDetialDto{" +
                "metrics='" + metrics + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
}
