package com.sinonc.iot.service;

import com.sinonc.iot.api.domain.DeviceMonitor;
import com.sinonc.iot.domain.IvmsZeroPage;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 监控设备Service接口
 *
 * @author ruoyi
 * @date 2020-09-27
 */
public interface IDeviceMonitorService {
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
     * 批量删除监控设备
     *
     * @param ids 需要删除的监控设备ID
     * @return 结果
     */
    public int deleteDeviceMonitorByIds(Long[] ids);

    /**
     * 删除监控设备信息
     *
     * @param id 监控设备ID
     * @return 结果
     */
    public int deleteDeviceMonitorById(Long id);

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
     * 获取监控中心监控列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    IvmsZeroPage getIvmsZeroCameraList(Integer pageNo, Integer pageSize) throws Exception;

    /**
     * 同步萤石商居监控设备
     *
     * @return
     */
    boolean syncEsYs();
    /**
     * 根据设备号获取token
     * @Param devSn 设备devSn
     * @Param channel 设备通道号channel
     */
    String getTokenByDevSn(String devSn,Integer channel);

    /**
     * 抓拍图片
     */
    public String capture(String devSn, Integer channel);

}
