package com.sinonc.iot.mapper;

import com.sinonc.iot.domain.IotWarn;

import java.util.List;

/**
 * 设备告警信息Mapper接口
 *
 * @author ruoyi
 * @date 2021-04-09
 */
public interface IotWarnMapper {
    /**
     * 查询设备告警信息
     *
     * @param warnId 设备告警信息ID
     * @return 设备告警信息
     */
    public IotWarn selectIotWarnById(Long warnId);

    /**
     * 查询设备告警信息列表
     *
     * @param iotWarn 设备告警信息
     * @return 设备告警信息集合
     */
    public List<IotWarn> selectIotWarnList(IotWarn iotWarn);

    /**
     * 查询设备告警信息列表
     *
     * @param iotWarn 设备告警信息
     * @return 设备告警信息集合
     */
    public List<IotWarn> listIotWarm(IotWarn iotWarn);

    /**
     * 新增设备告警信息
     *
     * @param iotWarn 设备告警信息
     * @return 结果
     */
    public int insertIotWarn(IotWarn iotWarn);

    /**
     * 修改设备告警信息
     *
     * @param iotWarn 设备告警信息
     * @return 结果
     */
    public int updateIotWarn(IotWarn iotWarn);

    /**
     * 删除设备告警信息
     *
     * @param warnId 设备告警信息ID
     * @return 结果
     */
    public int deleteIotWarnById(Long warnId);

    /**
     * 批量删除设备告警信息
     *
     * @param warnIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteIotWarnByIds(String[] warnIds);

    /**
     * 预警数量
     * @return
     */
    public int warnCount();
}
