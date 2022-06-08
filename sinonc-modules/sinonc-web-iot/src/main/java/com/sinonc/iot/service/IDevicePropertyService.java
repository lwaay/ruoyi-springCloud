package com.sinonc.iot.service;

import com.sinonc.iot.api.vo.DevicePropertyVo;
import com.sinonc.iot.api.domain.DeviceProperty;

import java.util.List;

/**
 * 设备属性Service接口
 *
 * @author ruoyi
 * @date 2020-11-05
 */
public interface IDevicePropertyService {
    /**
     * 查询设备属性
     *
     * @param propertyId 设备属性ID
     * @return 设备属性
     */
    public DeviceProperty selectDevicePropertyById(Long propertyId);

    /**
     * 查询设备属性列表
     *
     * @param deviceProperty 设备属性
     * @return 设备属性集合
     */
    public List<DeviceProperty> selectDevicePropertyList(DeviceProperty deviceProperty);

    /**
     * 新增设备属性
     *
     * @param deviceProperty 设备属性
     * @return 结果
     */
    public int insertDeviceProperty(DeviceProperty deviceProperty);

    /**
     * 修改设备属性
     *
     * @param deviceProperty 设备属性
     * @return 结果
     */
    public int updateDeviceProperty(DeviceProperty deviceProperty);

    /**
     * 批量删除设备属性
     *
     * @param propertyIds 需要删除的设备属性ID
     * @return 结果
     */
    public int deleteDevicePropertyByIds(Long[] propertyIds);

    /**
     * 删除设备属性信息
     *
     * @param propertyId 设备属性ID
     * @return 结果
     */
    public int deleteDevicePropertyById(Long propertyId);

    /**
     * 根据设备类型查询所有设备属性
     * @param deviceTypeId
     * @return
     */
    public List<DeviceProperty> getPropertyByType(String deviceTypeId);

    /**
     * 查询所有设备属性
     * @return
     */
    public List<DevicePropertyVo> getAllProperty(DeviceProperty deviceProperty);
}
