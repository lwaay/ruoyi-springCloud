package com.sinonc.iot.service.impl;

import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.iot.api.vo.DevicePropertyVo;
import com.sinonc.iot.api.domain.DeviceProperty;
import com.sinonc.iot.mapper.DevicePropertyMapper;
import com.sinonc.iot.service.IDevicePropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 设备属性Service业务层处理
 *
 * @author ruoyi
 * @date 2020-11-05
 */
@Service
public class DevicePropertyServiceImpl implements IDevicePropertyService {
    @Autowired
    private DevicePropertyMapper devicePropertyMapper;

    /**
     * 查询设备属性
     *
     * @param propertyId 设备属性ID
     * @return 设备属性
     */
    @Override
    public DeviceProperty selectDevicePropertyById(Long propertyId) {
        return devicePropertyMapper.selectDevicePropertyById(propertyId);
    }

    /**
     * 查询设备属性列表
     *
     * @param deviceProperty 设备属性
     * @return 设备属性
     */
    @Override
    public List<DeviceProperty> selectDevicePropertyList(DeviceProperty deviceProperty) {
        return devicePropertyMapper.selectDevicePropertyList(deviceProperty);
    }

    /**
     * 新增设备属性
     *
     * @param deviceProperty 设备属性
     * @return 结果
     */
    @Override
    public int insertDeviceProperty(DeviceProperty deviceProperty) {
        deviceProperty.setCreateTime(DateUtils.getNowDate());
        return devicePropertyMapper.insertDeviceProperty(deviceProperty);
    }

    /**
     * 修改设备属性
     *
     * @param deviceProperty 设备属性
     * @return 结果
     */
    @Override
    public int updateDeviceProperty(DeviceProperty deviceProperty) {
        deviceProperty.setUpdateTime(DateUtils.getNowDate());
        return devicePropertyMapper.updateDeviceProperty(deviceProperty);
    }

    /**
     * 批量删除设备属性
     *
     * @param propertyIds 需要删除的设备属性ID
     * @return 结果
     */
    @Override
    public int deleteDevicePropertyByIds(Long[] propertyIds) {
        return devicePropertyMapper.deleteDevicePropertyByIds(propertyIds);
    }

    /**
     * 删除设备属性信息
     *
     * @param propertyId 设备属性ID
     * @return 结果
     */
    @Override
    public int deleteDevicePropertyById(Long propertyId) {
        return devicePropertyMapper.deleteDevicePropertyById(propertyId);
    }

    @Override
    public List<DeviceProperty> getPropertyByType(String deviceTypeId) {
        return devicePropertyMapper.getPropertyByType(deviceTypeId);
    }

    @Override
    public List<DevicePropertyVo> getAllProperty(DeviceProperty deviceProperty) {
        return devicePropertyMapper.getAllProperty(deviceProperty);
    }
}
