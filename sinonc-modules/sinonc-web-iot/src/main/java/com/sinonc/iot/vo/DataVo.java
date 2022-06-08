package com.sinonc.iot.vo;

import java.util.List;

/**
 * @author: yehuiwang
 * @date: 2020/6/19 15:59
 * @description:
 */
public class DataVo {

    private String type = "bar";
    private String name;
    private String metric;
    private List<String> value;

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }
}
