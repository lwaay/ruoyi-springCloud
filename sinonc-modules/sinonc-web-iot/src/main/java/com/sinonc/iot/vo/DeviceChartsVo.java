package com.sinonc.iot.vo;

import java.util.List;

/**
 * @author: yehuiwang
 * @date: 2020/6/19 15:58
 * @description:
 */
public class DeviceChartsVo {
    private List<String> time;
    private String unit;
    private List<DataVo> data;

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<DataVo> getData() {
        return data;
    }

    public void setData(List<DataVo> data) {
        this.data = data;
    }
}
