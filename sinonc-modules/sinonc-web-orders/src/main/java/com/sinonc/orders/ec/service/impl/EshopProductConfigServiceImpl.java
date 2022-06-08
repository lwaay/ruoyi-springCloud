package com.sinonc.orders.ec.service.impl;

import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.orders.ec.domain.EshopProductConfig;
import com.sinonc.orders.ec.mapper.EshopProductConfigMapper;
import com.sinonc.orders.ec.service.IEshopProductConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 定时任务配置Service业务层处理
 *
 * @author ruoyi
 * @date 2021-04-10
 */
@Service
public class EshopProductConfigServiceImpl implements IEshopProductConfigService {
    @Autowired
    private EshopProductConfigMapper eshopProductConfigMapper;

    /**
     * 查询定时任务配置
     *
     * @param configId 定时任务配置ID
     * @return 定时任务配置
     */
    @Override
    public EshopProductConfig selectEshopProductConfigById(Long configId) {
        return eshopProductConfigMapper.selectEshopProductConfigById(configId);
    }

    /**
     * 查询定时任务配置列表
     *
     * @param eshopProductConfig 定时任务配置
     * @return 定时任务配置
     */
    @Override
    public List<EshopProductConfig> selectEshopProductConfigList(EshopProductConfig eshopProductConfig) {
        return eshopProductConfigMapper.selectEshopProductConfigList(eshopProductConfig);
    }

    /**
     * 新增定时任务配置
     *
     * @param eshopProductConfig 定时任务配置
     * @return 结果
     */
    @Override
    public int insertEshopProductConfig(EshopProductConfig eshopProductConfig) {
        return eshopProductConfigMapper.insertEshopProductConfig(eshopProductConfig);
    }

    /**
     * 修改定时任务配置
     *
     * @param eshopProductConfig 定时任务配置
     * @return 结果
     */
    @Override
    public int updateEshopProductConfig(EshopProductConfig eshopProductConfig) {
        eshopProductConfig.setUpdateTime(DateUtils.getNowDate());
        return eshopProductConfigMapper.updateEshopProductConfig(eshopProductConfig);
    }

    /**
     * 批量删除定时任务配置
     *
     * @param configIds 需要删除的定时任务配置ID
     * @return 结果
     */
    @Override
    public int deleteEshopProductConfigByIds(Long[] configIds) {
        return eshopProductConfigMapper.deleteEshopProductConfigByIds(configIds);
    }

    /**
     * 删除定时任务配置信息
     *
     * @param configId 定时任务配置ID
     * @return 结果
     */
    @Override
    public int deleteEshopProductConfigById(Long configId) {
        return eshopProductConfigMapper.deleteEshopProductConfigById(configId);
    }

    /**
     * 查询定时任务配置
     * @return 定时任务配置
     */
    public EshopProductConfig getEshopProductConfig(){
        return eshopProductConfigMapper.selectEshopProductConfig();
    }
}
