package com.sinonc.iot.dto;

import lombok.Data;

/**
 * @author zhangxinlong
 * @date 2021/5/6  18:22
 */
@Data
public class ExtDeviceInfoDto {

    /**
     * 园区名称
     */
    private String baseFarmName;

    /**
     * 乡镇名称
     */
    private String townsName;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 来源
     */
    private String source;

    /**
     * 状态
     */
    private String status;

}
