package com.sinonc.iot.api.dto;

import java.util.List;

/**
 * @author: yehuiwang
 * @date: 2020/11/6 10:29
 * @description:
 */
public class ThirdDeviceDataDto {
    /**
     * 设备id
     */
    private String deviceId;
    /**
     * 系统名称
     */
    private String systemName;
    /**
     * 区域编码
     */
    private String areaCode;
    /**
     * 所属基地ID
     */
    private String baseId;
    /**
     * 设备数据
     */
    private List<DataDto> datas;

    public String getBaseId() {
        return baseId;
    }

    public void setBaseId(String baseId) {
        this.baseId = baseId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public List<DataDto> getDatas() {
        return datas;
    }

    public void setDatas(List<DataDto> datas) {
        this.datas = datas;
    }


}
