package com.sinonc.exchange.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class SysUUIDVo {

    @ApiModelProperty(required = true)
    @NotEmpty(message = "sysUUID不能为空")
    private String sysUUID;


    @ApiModelProperty(required = true)
    @NotEmpty(message = "系统名称不能为空")
    private String sysName;

    /**
     * 签名方式
     * 1、将除Sign的所有的非空字段进行字典排序
     * 2、将排序后的字段用key=value的形式进行拼接，不用字段用&符号连接
     * 3、将拼接后的字段末尾拼接上&key=密钥进行MD5加密
     * <p>
     * 拼接示例：
     * param1=value1&param2=value2&param3=value3&key=keyValue
     */
    @NotEmpty(message = "签名不能为空")
    private String sign;

    /**
     * 时间戳
     */
    @NotNull(message = "时间戳不能为空")
    private Date timestamp;

}
