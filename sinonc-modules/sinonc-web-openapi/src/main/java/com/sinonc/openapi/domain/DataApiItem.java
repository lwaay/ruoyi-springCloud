package com.sinonc.openapi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sinonc.common.core.annotation.Excel;
import lombok.Data;

/**
 * 系统接口明细对象 data_api_item
 *
 * @author huanghao
 * @date 2020-11-05
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataApiItem extends MBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    @TableId(value = "id",type= IdType.AUTO)
    private Long id;

    /**
     * 接口名称
     */
    @Excel(name = "接口名称")
    private String apiName;

    /**
     * 1 开启 0 关闭
     */
    @Excel(name = "1 开启 0 关闭")
    private Integer status;

    /**
     * 接口地址
     */
    @Excel(name = "接口地址")
    private String apiUrl;

    /**
     * 接口描述
     */
    @Excel(name = "接口描述")
    private String apiInfo;

    /**
     * 最大连接数
     */
    @Excel(name = "最大连接数")
    private Integer maxConnection;

    private Long apiDataId;
    /**
     * 接口分类 1 基础数据 2 物联网3溯源信息接口
     */
    @Excel(name = "接口分类 1 基础数据 2 物联网3溯源信息接口")
    private Integer apiType;

    /** 接口数量*/
    @TableField(exist = false)
    private Integer isUse;

}
