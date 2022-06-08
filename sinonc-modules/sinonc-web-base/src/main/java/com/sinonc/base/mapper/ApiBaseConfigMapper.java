package com.sinonc.base.mapper;

import java.util.List;
import java.util.Map;

/**
 * 接口配置Mapper接口
 *
 * @author ruoyi
 * @date 2021-10-09
 */
public interface ApiBaseConfigMapper {

    /**
     * 获取数据库表信息
     * @return 结果
     */
    public List<Map<String,String>> getTables();

    /**
     * 获取数据库表列信息
     * @return 结果
     */
    public List<Map<String,String>> getColumns(String tableName);

    /**
     * 获取数据库表列信息
     * @return 结果
     */
    public List<Map<String,String>> queryList(Map<String,String> query);
}
