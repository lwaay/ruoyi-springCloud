package com.sinonc.openapi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinonc.common.core.annotation.Excel;
import com.sinonc.openapi.vo.DataApiVo;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 第三方数据接入管理对象 data_api
 *
 * @author huanghao
 * @date 2020-10-21
 */
@Data
public class DataApi {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id",type= IdType.AUTO)
    private Long id;

    /**
     * 系统名称
     */
    @Excel(name = "系统名称")
    private String systemName;

    /**
     * 访问密钥
     */
    @Excel(name = "访问密钥")
    private String privateKey;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /**
     * 1 启用 0 禁用
     */
    @Excel(name = "1 启用 0 禁用")
    private Integer status;

    /**
     * 接口数量
     */
    @Excel(name = "接口数量")
    private Integer interfaceNum;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 开放接口 逗号分隔
     */
    private String openApi;
    /**
     * 系统标签
     */
    private String systemLabel;

    /**
     * 接口数量
     */
    @TableField(exist = false)
    private Integer apiNum;

    /**
     * 接口
     */
    @TableField(exist = false)
    private List<DataApiItem> apiItems;

    /**
     * 白名单 ip
     */
    private String whiteList;
    /**
     * 黑名单 ip
     */
    private String blackList;

    @TableField(exist = false)
    private List<DataApiVo> configList;
}
