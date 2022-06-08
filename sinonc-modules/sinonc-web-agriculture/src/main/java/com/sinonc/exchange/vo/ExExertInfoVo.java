package com.sinonc.exchange.vo;

import com.sinonc.common.core.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ExExertInfoVo {
    private static final long serialVersionUID = 1L;

    /** 专家ID */
    private Long expertId;

    /**
     * 专家姓名
     */
    private String name;
    /**
     * 电话号码
     */
    private String phone;

    /** 会员ID */
    @Excel(name = "会员ID")
    private Long memberId;

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

    @ApiModelProperty(required = true)
    @NotEmpty(message = "系统名称不能为空")
    private String sysName;

    /**
     * 审核状态
     */
    private Integer verifyStatus;

    private Integer pageSize;

    private Integer pageNum;
}
