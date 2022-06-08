package com.sinonc.orders.ec.service;

import com.sinonc.orders.ec.domain.EshopProductConfig;

import java.util.List;

/**
 * 定时任务配置Service接口
 *
 * @author ruoyi
 * @date 2021-04-10
 */
public interface IEshopProductConfigService {
    /**
     * 查询定时任务配置
     *
     * @param configId 定时任务配置ID
     * @return 定时任务配置
     */
    public EshopProductConfig selectEshopProductConfigById(Long configId);

    /**
     * 查询定时任务配置列表
     *
     * @param eshopProductConfig 定时任务配置
     * @return 定时任务配置集合
     */
    public List<EshopProductConfig> selectEshopProductConfigList(EshopProductConfig eshopProductConfig);

    /**
     * 新增定时任务配置
     *
     * @param eshopProductConfig 定时任务配置
     * @return 结果
     */
    public int insertEshopProductConfig(EshopProductConfig eshopProductConfig);

    /**
     * 修改定时任务配置
     *
     * @param eshopProductConfig 定时任务配置
     * @return 结果
     */
    public int updateEshopProductConfig(EshopProductConfig eshopProductConfig);

    /**
     * 批量删除定时任务配置
     *
     * @param configIds 需要删除的定时任务配置ID
     * @return 结果
     */
    public int deleteEshopProductConfigByIds(Long[] configIds);

    /**
     * 删除定时任务配置信息
     *
     * @param configId 定时任务配置ID
     * @return 结果
     */
    public int deleteEshopProductConfigById(Long configId);

    /**
     * 查询定时任务配置
     * @return 定时任务配置
     */
    public EshopProductConfig getEshopProductConfig();
}
