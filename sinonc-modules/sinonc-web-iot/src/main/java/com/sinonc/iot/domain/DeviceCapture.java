package com.sinonc.iot.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * 摄像抓拍对象 device_capture
 *
 * @author ruoyi
 * @date 2022-05-09
 */
@Data
public class DeviceCapture extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 图片url
     */
    @Excel(name = "图片url")
    private String url;

    /**
     * 图片名称
     */
    @Excel(name = "图片名称")
    private String name;

    /**
     * 摄像头id
     */
    @Excel(name = "摄像头id")
    private Long monitorId;

    /**
     * 摄像头名称
     */
    @Excel(name = "摄像头名称")
    private String monitorName;

}
