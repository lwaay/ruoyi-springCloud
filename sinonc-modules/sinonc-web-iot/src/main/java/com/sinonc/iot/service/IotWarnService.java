package com.sinonc.iot.service;

import com.sinonc.iot.domain.IotWarn;
import com.sinonc.iot.dto.IotWarnDto;

import java.util.List;

/**
 * 设备告警信息Service接口
 *
 * @author ruoyi
 * @date 2021-04-09
 */
public interface IotWarnService {
    /**
     * 查询设备告警信息
     *
     * @param warnId 设备告警信息ID
     * @return 设备告警信息
     */
    public IotWarn getIotWarnById(Long warnId);

    /**
     * 查询设备告警信息列表
     *
     * @param iotWarn 设备告警信息
     * @return 设备告警信息集合
     */
    public List<IotWarn> getIotWarnList(IotWarn iotWarn);

    /**
     * 物联网设备预警列表
     */
    public List<IotWarn> listIotWarm(IotWarn iotWarn);

    /**
     * 新增设备告警信息
     *
     * @param iotWarn 设备告警信息
     * @return 结果
     */
    public int addIotWarn(IotWarn iotWarn);

    /**
     * 修改设备告警信息
     *
     * @param iotWarn 设备告警信息
     * @return 结果
     */
    public int updateIotWarn(IotWarn iotWarn);

    /**
     * 批量删除设备告警信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteIotWarnByIds(String ids);

    /**
     * 删除设备告警信息信息
     *
     * @param warnId 设备告警信息ID
     * @return 结果
     */
    public int deleteIotWarnById(Long warnId);

    /**
     * 根据id获取预警明细
     */
    public IotWarnDto getIotWarnDetailById(Long warnId);

    public int warnCount();
}
