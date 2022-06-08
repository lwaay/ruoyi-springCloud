package com.sinonc.iot.mapper;

import com.sinonc.iot.api.domain.DeviceMonitor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 监控设备Mapper接口
 *
 * @author ruoyi
 * @date 2020-09-27
 */
public interface DeviceMonitorMapper {
    /**
     * 查询监控设备
     *
     * @param id 监控设备ID
     * @return 监控设备
     */
    public DeviceMonitor selectDeviceMonitorById(Long id);

    /**
     * 查询监控设备列表
     *
     * @param deviceMonitor 监控设备
     * @return 监控设备集合
     */
    public List<DeviceMonitor> selectDeviceMonitorList(DeviceMonitor deviceMonitor);

    /**
     * 新增监控设备
     *
     * @param deviceMonitor 监控设备
     * @return 结果
     */
    public int insertDeviceMonitor(DeviceMonitor deviceMonitor);

    /**
     * 修改监控设备
     *
     * @param deviceMonitor 监控设备
     * @return 结果
     */
    public int updateDeviceMonitor(DeviceMonitor deviceMonitor);

    /**
     * 删除监控设备
     *
     * @param id 监控设备ID
     * @return 结果
     */
    public int deleteDeviceMonitorById(Long id);

    /**
     * 批量删除监控设备
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDeviceMonitorByIds(Long[] ids);

    /**
     * 根据id列表查询摄像头
     *
     * @return
     */
    public List<DeviceMonitor> getMonitorByIds(String[] ids);

    /**
     * 查询监控总数
     *
     * @return
     */
    public int getMonitorCount();

    /**
     * 根据设备号、通道号获取设备信息
     * @Param devSn 设备号
     * @Param channel 通道号
     * @return
     */
    DeviceMonitor getMonitorByDevSnChannel(@Param("devSn") String devSn, @Param("channel") Integer channel);
}
