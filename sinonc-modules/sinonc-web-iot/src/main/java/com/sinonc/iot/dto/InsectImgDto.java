package com.sinonc.iot.dto;

/**
 * @author: yehuiwang
 * @date: 2020/12/10 11:01
 * @description:
 */
public class InsectImgDto {
    private Long Id;
    private String deviceId;
    private int pageNum;
    private int pageSize;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
