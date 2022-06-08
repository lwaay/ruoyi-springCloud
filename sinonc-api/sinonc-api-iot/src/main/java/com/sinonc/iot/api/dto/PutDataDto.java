package com.sinonc.iot.api.dto;

import java.util.List;

/**
 * @author: yehuiwang
 * @date: 2020/11/9 16:23
 * @description:
 */
public class PutDataDto {

    private String StationID;
    private String MonitorTime;
    private List<DataDetialDto> data;

    public String getStationID() {
        return StationID;
    }

    public void setStationID(String stationID) {
        StationID = stationID;
    }

    public String getMonitorTime() {
        return MonitorTime;
    }

    public void setMonitorTime(String monitorTime) {
        MonitorTime = monitorTime;
    }

    public List<DataDetialDto> getData() {
        return data;
    }

    public void setData(List<DataDetialDto> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PutDataDto{" +
                "StationID='" + StationID + '\'' +
                ", MonitorTime='" + MonitorTime + '\'' +
                ", data=" + data +
                '}';
    }
}
