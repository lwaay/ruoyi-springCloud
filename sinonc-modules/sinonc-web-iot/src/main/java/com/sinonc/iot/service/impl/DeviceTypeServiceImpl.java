package com.sinonc.iot.service.impl;

import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.iot.api.domain.DeviceType;
import com.sinonc.iot.mapper.DeviceTypeMapper;
import com.sinonc.iot.service.IDeviceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 设备类型Service业务层处理
 *
 * @author ruoyi
 * @date 2020-11-05
 */
@Service
public class DeviceTypeServiceImpl implements IDeviceTypeService {
    @Autowired
    private DeviceTypeMapper deviceTypeMapper;

    /**
     * 查询设备类型
     *
     * @param id 设备类型ID
     * @return 设备类型
     */
    @Override
    public DeviceType selectDeviceTypeById(Long id) {
        return deviceTypeMapper.selectDeviceTypeById(id);
    }

    /**
     * 查询设备类型列表
     *
     * @param deviceType 设备类型
     * @return 设备类型
     */
    @Override
    public List<DeviceType> selectDeviceTypeList(DeviceType deviceType) {
        return deviceTypeMapper.selectDeviceTypeList(deviceType);
    }

    /**
     * 新增设备类型
     *
     * @param deviceType 设备类型
     * @return 结果
     */
    @Override
    public int insertDeviceType(DeviceType deviceType) {
        deviceType.setCreateTime(DateUtils.getNowDate());
        return deviceTypeMapper.insertDeviceType(deviceType);
    }

    /**
     * 修改设备类型
     *
     * @param deviceType 设备类型
     * @return 结果
     */
    @Override
    public int updateDeviceType(DeviceType deviceType) {
        deviceType.setUpdateTime(DateUtils.getNowDate());
        return deviceTypeMapper.updateDeviceType(deviceType);
    }

    /**
     * 批量删除设备类型
     *
     * @param ids 需要删除的设备类型ID
     * @return 结果
     */
    @Override
    public int deleteDeviceTypeByIds(Long[] ids) {
        return deviceTypeMapper.deleteDeviceTypeByIds(ids);
    }

    /**
     * 删除设备类型信息
     *
     * @param id 设备类型ID
     * @return 结果
     */
    @Override
    public int deleteDeviceTypeById(Long id) {
        return deviceTypeMapper.deleteDeviceTypeById(id);
    }

    @Override
    public List<DeviceType> getAllType() {
        return deviceTypeMapper.getAllType();
    }

    @Override
    public DeviceType getDeviceType(String type) {
        return deviceTypeMapper.getDeviceType(type);
    }
}
