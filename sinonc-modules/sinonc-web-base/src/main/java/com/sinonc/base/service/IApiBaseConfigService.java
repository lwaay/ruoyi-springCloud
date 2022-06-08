package com.sinonc.base.service;

import java.util.List;
import java.util.Map;

/**
 * 行政区域Service接口
 *
 * @author ruoyi
 * @date 2020-09-23
 */
public interface IApiBaseConfigService {

    /**
     * 根据项目类型获取数据库表
     */
    public List<Map<String,String>> getTables();

    /**
     * 根据项目类型，表名获取表字段
     */
    public List<Map<String,String>> getColumn(String tableName);

    /**
     * 统一查询数据
     */
    public List<Map<String,String>> queryList(Map<String,String> params);

}
