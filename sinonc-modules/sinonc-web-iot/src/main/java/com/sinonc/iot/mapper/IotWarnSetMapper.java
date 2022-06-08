package com.sinonc.iot.mapper;

import com.sinonc.iot.domain.IotWarnSet;

import java.util.List;

/**
 * 设备告警条件设置Mapper接口
 *
 * @author ruoyi
 * @date 2021-04-09
 */
public interface IotWarnSetMapper {
    /**
     * 查询设备告警条件设置
     *
     * @param warnsetId 设备告警条件设置ID
     * @return 设备告警条件设置
     */
    public IotWarnSet selectIotWarnSetById(Long warnsetId);

    /**
     * 查询设备告警条件设置列表
     *
     * @param iotWarnSet 设备告警条件设置
     * @return 设备告警条件设置集合
     */
    public List<IotWarnSet> selectIotWarnSetList(IotWarnSet iotWarnSet);

    /**
     * 新增设备告警条件设置
     *
     * @param iotWarnSet 设备告警条件设置
     * @return 结果
     */
    public int insertIotWarnSet(IotWarnSet iotWarnSet);

    /**
     * 修改设备告警条件设置
     *
     * @param iotWarnSet 设备告警条件设置
     * @return 结果
     */
    public int updateIotWarnSet(IotWarnSet iotWarnSet);

    /**
     * 删除设备告警条件设置
     *
     * @param warnsetId 设备告警条件设置ID
     * @return 结果
     */
    public int deleteIotWarnSetById(Long warnsetId);

    /**
     * 批量删除设备告警条件设置
     *
     * @param warnsetIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteIotWarnSetByIds(String[] warnsetIds);
}
