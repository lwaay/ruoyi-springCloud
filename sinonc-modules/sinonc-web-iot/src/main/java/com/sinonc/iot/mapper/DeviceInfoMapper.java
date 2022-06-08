package com.sinonc.iot.mapper;

import com.sinonc.iot.api.domain.DeviceInfo;
import com.sinonc.iot.vo.DeviceInfoVo;
import com.sinonc.iot.vo.InfoVo;

import java.util.List;

/**
 * 设备Mapper接口
 *
 * @author ruoyi
 * @date 2020-09-25
 */
public interface DeviceInfoMapper {
    /**
     * 查询设备
     *
     * @param id 设备ID
     * @return 设备
     */
    public DeviceInfo selectDeviceInfoById(Long id);

    /**
     * 查询设备列表
     *
     * @param deviceInfo 设备
     * @return 设备集合
     */
    public List<DeviceInfo> selectDeviceInfoList(DeviceInfo deviceInfo);

    /**
     * 新增设备
     *
     * @param deviceInfo 设备
     * @return 结果
     */
    public int insertDeviceInfo(DeviceInfo deviceInfo);

    /**
     * 修改设备
     *
     * @param deviceInfo 设备
     * @return 结果
     */
    public int updateDeviceInfo(DeviceInfo deviceInfo);

    /**
     * 删除设备
     *
     * @param id 设备ID
     * @return 结果
     */
    public int deleteDeviceInfoById(Long id);

    /**
     * 批量删除设备
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDeviceInfoByIds(Long[] ids);

    /**
     * 根据id列表查询设备
     *
     * @return
     */
    public List<DeviceInfo> getDeviceListByIds(String[] ids);

    /**
     * 查询设备总数
     *
     * @return
     */
    Integer getCount();

    /**
     * 根据基地id获取设备列表
     * @param id
     * @return
     */
    public List<DeviceInfo> getDeviceListByBaseId(Long id);

    /**
     * 根据设备id获取设备数据
     * @param id
     * @return
     */
    public DeviceInfo getDeviceInfoByDeviceId(String id);

    /**
     * 根据类型id获取设备列表
     * @param id
     * @return
     */
    public List<DeviceInfo> getDeviceByType(String id);

    /**
     * 查询所有设备列表
     * @param deviceInfo
     * @return
     */
    public List<DeviceInfoVo> getDeviceInfoList(DeviceInfo deviceInfo);

    /**
     * 根据设备id查询设备信息
     * @param deviceId
     * @return
     */
    public DeviceInfoVo getDeviceInfo(String deviceId);

    /**
     * 获取物联网设备运行情况
     * @return
     */
    List<InfoVo> selectDeviceList();
}
