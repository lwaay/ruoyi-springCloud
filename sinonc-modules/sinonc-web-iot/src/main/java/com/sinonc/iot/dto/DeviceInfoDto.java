package com.sinonc.iot.dto;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 设备对象 device
 *
 * @author ruoyi
 * @date 2020-04-22
 */
@ApiModel("设备对象")
@Data
public class DeviceInfoDto extends BaseEntity {
    private static final long serialVersionUID = 1L;


    /**
     * 设备id
     */
    @ApiModelProperty(value = "设备id")
    private String deviceId;


    /**
     * 设备名称
     */
    @ApiModelProperty(value = "设备名称")
    @Excel(name = "设备名称")
    private String deviceName;

    @ApiModelProperty(value = "基地ID")
    private Long farmId;

    @ApiModelProperty(value = "基地名称")
    private String farmName;
}
