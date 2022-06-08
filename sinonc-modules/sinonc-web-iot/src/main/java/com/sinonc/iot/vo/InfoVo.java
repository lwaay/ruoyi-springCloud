package com.sinonc.iot.vo;

import com.sinonc.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lw
 * 物联网设备运行情况
 */
@Data
@ApiModel("物联网设备运行情况Vo")
public class InfoVo {

    @ApiModelProperty("基地名称")
    private String farmName;

    @ApiModelProperty("乡镇")
    private String countryName;

    @ApiModelProperty("设备名称")
    private String deviceName;

    @ApiModelProperty("来源")
    private String from;

    @ApiModelProperty("状态")
    private String status;
}
