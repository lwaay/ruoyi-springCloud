package com.sinonc.openapi.domain;

import com.sinonc.common.core.annotation.Excel;

/**
 * 接口配置对象 api_total_config
 *
 * @author ruoyi
 * @date 2021-10-09
 */
public class ApiTotalConfig extends MBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long configId;

    /**
     * 接口后缀
     */
    @Excel(name = "接口后缀")
    private String urlSuffix;

    /**
     * 模块类型
     */
    @Excel(name = "模块类型")
    private Integer moduleType;

    /**
     * 表名
     */
    @Excel(name = "表名")
    private String tableName;

    /**
     * 表标签
     */
    @Excel(name = "表标签")
    private String tableLabel;

    /**
     * 列名
     */
    @Excel(name = "列名")
    private String columnName;

    /**
     * 列标签
     */
    @Excel(name = "列标签")
    private String columnLabel;

    /**
     * 状态
     */
    @Excel(name = "状态")
    private Integer status;

    /**
     * 创建人名称
     */
    @Excel(name = "创建人名称")
    private String createName;

    public void setConfigId(Long configId) {
        this.configId = configId;
    }

    public Long getConfigId() {
        return configId;
    }

    public void setUrlSuffix(String urlSuffix) {
        this.urlSuffix = urlSuffix;
    }

    public String getUrlSuffix() {
        return urlSuffix;
    }

    public void setModuleType(Integer moduleType) {
        this.moduleType = moduleType;
    }

    public Integer getModuleType() {
        return moduleType;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableLabel(String tableLabel) {
        this.tableLabel = tableLabel;
    }

    public String getTableLabel() {
        return tableLabel;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnLabel(String columnLabel) {
        this.columnLabel = columnLabel;
    }

    public String getColumnLabel() {
        return columnLabel;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getCreateName() {
        return createName;
    }

}
