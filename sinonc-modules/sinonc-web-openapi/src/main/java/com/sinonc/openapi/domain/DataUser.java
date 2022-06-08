package com.sinonc.openapi.domain;

import com.sinonc.common.core.annotation.Excel;
import lombok.Data;

import java.util.List;

/**
 * 第三方接口用户对象 data_user
 *
 * @author hhao
 * @date 2020-12-23
 */
@Data
public class DataUser extends MBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 用户名
     */
    @Excel(name = "用户名")
    private String name;

    /**
     * 私钥
     */
    @Excel(name = "私钥")
    private String privateKey;

    /**
     * 接口系统ids 多个逗号分隔
     */
    @Excel(name = "接口系统ids 多个逗号分隔")
    private String dataApiIds;


    private List<DataApi> dataApiList;


}
