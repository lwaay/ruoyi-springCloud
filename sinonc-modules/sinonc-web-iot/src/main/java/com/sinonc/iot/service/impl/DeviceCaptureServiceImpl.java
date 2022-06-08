package com.sinonc.iot.service.impl;

import java.util.List;

import com.sinonc.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.iot.mapper.DeviceCaptureMapper;
import com.sinonc.iot.domain.DeviceCapture;
import com.sinonc.iot.service.IDeviceCaptureService;

/**
 * 摄像抓拍Service业务层处理
 *
 * @author ruoyi
 * @date 2022-05-09
 */
@Service
public class DeviceCaptureServiceImpl implements IDeviceCaptureService {
    @Autowired
    private DeviceCaptureMapper deviceCaptureMapper;

    /**
     * 查询摄像抓拍
     *
     * @param id 摄像抓拍ID
     * @return 摄像抓拍
     */
    @Override
    public DeviceCapture selectDeviceCaptureById(Long id) {
        return deviceCaptureMapper.selectDeviceCaptureById(id);
    }

    /**
     * 查询摄像抓拍列表
     *
     * @param deviceCapture 摄像抓拍
     * @return 摄像抓拍
     */
    @Override
    public List<DeviceCapture> selectDeviceCaptureList(DeviceCapture deviceCapture) {
        return deviceCaptureMapper.selectDeviceCaptureList(deviceCapture);
    }

    /**
     * 新增摄像抓拍
     *
     * @param deviceCapture 摄像抓拍
     * @return 结果
     */
    @Override
    public int insertDeviceCapture(DeviceCapture deviceCapture) {
        deviceCapture.setCreateTime(DateUtils.getNowDate());
        return deviceCaptureMapper.insertDeviceCapture(deviceCapture);
    }

    /**
     * 修改摄像抓拍
     *
     * @param deviceCapture 摄像抓拍
     * @return 结果
     */
    @Override
    public int updateDeviceCapture(DeviceCapture deviceCapture) {
        return deviceCaptureMapper.updateDeviceCapture(deviceCapture);
    }

    /**
     * 批量删除摄像抓拍
     *
     * @param ids 需要删除的摄像抓拍ID
     * @return 结果
     */
    @Override
    public int deleteDeviceCaptureByIds(Long[] ids) {
        return deviceCaptureMapper.deleteDeviceCaptureByIds(ids);
    }

    /**
     * 删除摄像抓拍信息
     *
     * @param id 摄像抓拍ID
     * @return 结果
     */
    @Override
    public int deleteDeviceCaptureById(Long id) {
        return deviceCaptureMapper.deleteDeviceCaptureById(id);
    }
}
