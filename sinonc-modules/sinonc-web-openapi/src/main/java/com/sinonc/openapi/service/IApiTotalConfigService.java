package com.sinonc.openapi.service;


import com.sinonc.openapi.domain.ApiTotalConfig;
import com.sinonc.openapi.dto.ApiTotalConfigDto;

import java.util.List;
import java.util.Map;

/**
 * 接口配置Service接口
 *
 * @author ruoyi
 * @date 2021-10-09
 */
public interface IApiTotalConfigService {
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
     * 批量删除接口配置
     *
     * @param configIds 需要删除的接口配置ID
     * @return 结果
     */
    public int deleteApiTotalConfigByIds(Long[] configIds);

    /**
     * 删除接口配置信息
     *
     * @param configId 接口配置ID
     * @return 结果
     */
    public int deleteApiTotalConfigById(Long configId);

}
