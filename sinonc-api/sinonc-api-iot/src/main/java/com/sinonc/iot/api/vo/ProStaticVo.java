package com.sinonc.iot.api.vo;

import java.util.Date;

/**
 * @author: lw
 * @date: 2022/4/23 11:30
 * @description:
 */
public class ProStaticVo {
    /**
     * 溯源时间
     */
    private Date originsDate;
    /**
     * 结束时间
     */
    private Date endDate;
    /**
     * 设备id
     */
    private String deviceId;

    public Date getOriginsDate() {
        return originsDate;
    }

    public void setOriginsDate(Date originsDate) {
        this.originsDate = originsDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public ProStaticVo(Date originsDate, Date endDate, String deviceId) {
        this.originsDate = originsDate;
        this.endDate = endDate;
        this.deviceId = deviceId;
    }

    public ProStaticVo(){}
}
