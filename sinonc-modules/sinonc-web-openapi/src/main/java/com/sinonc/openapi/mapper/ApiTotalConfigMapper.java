package com.sinonc.openapi.mapper;


import com.sinonc.openapi.domain.ApiTotalConfig;
import com.sinonc.openapi.dto.ApiTotalConfigDto;

import java.util.List;
import java.util.Map;

/**
 * 接口配置Mapper接口
 *
 * @author ruoyi
 * @date 2021-10-09
 */
public interface ApiTotalConfigMapper {
    /**
     * 查询接口配置
     *
     * @param configId 接口配置ID
     * @return 接口配置
     */
    public ApiTotalConfig selectApiTotalConfigById(Long configId);

    /**
     * 查询接口配置列表
     *
     * @param apiTotalConfig 接口配置
     * @return 接口配置集合
     */
    public List<ApiTotalConfig> selectApiTotalConfigList(ApiTotalConfig apiTotalConfig);

    /**
     * 新增接口配置
     *
     * @param apiTotalConfig 接口配置
     * @return 结果
     */
    public int insertApiTotalConfig(ApiTotalConfig apiTotalConfig);

    /**
     * 修改接口配置
     *
     * @param apiTotalConfig 接口配置
     * @return 结果
     */
    public int updateApiTotalConfig(ApiTotalConfig apiTotalConfig);

    /**
     * 删除接口配置
     *
     * @param configId 接口配置ID
     * @return 结果
     */
    public int deleteApiTotalConfigById(Long configId);

    /**
     * 批量删除接口配置
     *
     * @param configIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteApiTotalConfigByIds(Long[] configIds);

    /**
     * 根据后缀获取自定义api配置
     */
    public ApiTotalConfig getConfigBySuffix(String suffix);

    /**
     * 获取数据库表信息
     * @return 结果
     */
    public List<Map<String,String>> selectTables();

    /**
     * 获取数据库表列信息
     * @return 结果
     */
    public List<Map<String,String>> selectColumns(String tableName);

    /**
     * 获取数据库表列信息
     * @return 结果
     */
    public List<Map<String,String>> queryList(ApiTotalConfigDto query);
}
