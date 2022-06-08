package com.sinonc.iot.service;

import java.util.List;

import com.sinonc.iot.domain.DeviceCapture;

/**
 * 摄像抓拍Service接口
 *
 * @author ruoyi
 * @date 2022-05-09
 */
public interface IDeviceCaptureService {
    /**
     * 查询摄像抓拍
     *
     * @param id 摄像抓拍ID
     * @return 摄像抓拍
     */
    public DeviceCapture selectDeviceCaptureById(Long id);

    /**
     * 查询摄像抓拍列表
     *
     * @param deviceCapture 摄像抓拍
     * @return 摄像抓拍集合
     */
    public List<DeviceCapture> selectDeviceCaptureList(DeviceCapture deviceCapture);

    /**
     * 新增摄像抓拍
     *
     * @param deviceCapture 摄像抓拍
     * @return 结果
     */
    public int insertDeviceCapture(DeviceCapture deviceCapture);

    /**
     * 修改摄像抓拍
     *
     * @param deviceCapture 摄像抓拍
     * @return 结果
     */
    public int updateDeviceCapture(DeviceCapture deviceCapture);

    /**
     * 批量删除摄像抓拍
     *
     * @param ids 需要删除的摄像抓拍ID
     * @return 结果
     */
    public int deleteDeviceCaptureByIds(Long[] ids);

    /**
     * 删除摄像抓拍信息
     *
     * @param id 摄像抓拍ID
     * @return 结果
     */
    public int deleteDeviceCaptureById(Long id);
}
