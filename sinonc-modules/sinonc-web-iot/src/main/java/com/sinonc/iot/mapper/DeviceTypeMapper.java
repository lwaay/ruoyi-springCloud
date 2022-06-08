package com.sinonc.iot.mapper;

import com.sinonc.iot.api.domain.DeviceType;

import java.util.List;

/**
 * 设备类型Mapper接口
 *
 * @author ruoyi
 * @date 2020-11-05
 */
public interface DeviceTypeMapper {
    /**
     * 查询设备类型
     *
     * @param id 设备类型ID
     * @return 设备类型
     */
    public DeviceType selectDeviceTypeById(Long id);

    /**
     * 查询设备类型列表
     *
     * @param deviceType 设备类型
     * @return 设备类型集合
     */
    public List<DeviceType> selectDeviceTypeList(DeviceType deviceType);

    /**
     * 新增设备类型
     *
     * @param deviceType 设备类型
     * @return 结果
     */
    public int insertDeviceType(DeviceType deviceType);

    /**
     * 修改设备类型
     *
     * @param deviceType 设备类型
     * @return 结果
     */
    public int updateDeviceType(DeviceType deviceType);

    /**
     * 删除设备类型
     *
     * @param id 设备类型ID
     * @return 结果
     */
    public int deleteDeviceTypeById(Long id);

    /**
     * 批量删除设备类型
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDeviceTypeByIds(Long[] ids);

    /**
     * 查询所有设备类型
     * @return
     */
    public List<DeviceType> getAllType();


    /**
     * 根据类型获取设备类型
     * @param type
     * @return
     */
    public DeviceType getDeviceType(String type);
}
