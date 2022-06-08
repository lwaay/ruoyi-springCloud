package com.sinonc.iot.domain;

import lombok.Data;

/**
 * @author zhangxinlong
 * @date 2021/3/30  17:53
 */
@Data
public class IvmsZeroCamera {
    /**
     * 监控点UUID
     */
    private String cameraUuid;

    /**
     * 监控点名称
     */
    private String cameraName;

    /**
     * 0不在线  1在线
     */
    private String onLineStatus;

    /**
     * 0枪机 1半球  2快球 3带云镜枪机
     */
    private String cameraType;

    /**
     * 播放地址
     */
    private String playUrl;

    /**
     * 0非默认，1默认
     */
    private String defaultStatus;
}
