package com.sinonc.iot.api.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author: yehuiwang
 * @date: 2020/12/25 9:25
 * @description:
 */
public class RealDataDto implements Serializable {
    private Long lastTime;
    private int online;
    private List<DataDetialDto> dataDetialDtos;

    public Long getLastTime() {
        return lastTime;
    }

    public void setLastTime(Long lastTime) {
        this.lastTime = lastTime;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public List<DataDetialDto> getDataDetialDtos() {
        return dataDetialDtos;
    }

    public void setDataDetialDtos(List<DataDetialDto> dataDetialDtos) {
        this.dataDetialDtos = dataDetialDtos;
    }
}
