package com.sinonc.iot.service;

import com.alibaba.fastjson.JSONObject;
import com.sinonc.iot.domain.ProIrrigationLog;
import com.sinonc.iot.dto.IrrigationDto;

import java.util.List;
import java.util.Map;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/5/21 15:26
 */
public interface IProIrrigationLogService{
    /**
     * 查询水肥灌溉记录
     *
     * @param logId 水肥灌溉记录ID
     * @return 水肥灌溉记录
     */
    ProIrrigationLog selectProIrrigationLogById(Long logId);

    /**
     * 获取汇总数据，根据月份统计
     * @return
     */
    public Map<String, Object> getLogGroupByMonth();

    /**
     * 查询水肥灌溉记录列表
     *
     * @param proIrrigationLog 水肥灌溉记录
     * @return 水肥灌溉记录集合
     */
    List<ProIrrigationLog> selectProIrrigationLogList(ProIrrigationLog proIrrigationLog);

    /**
     * 查询水肥灌溉记录列表
     *
     * @param irrigationDto 水肥灌溉记录
     * @return 水肥灌溉记录集合
     */
    List<ProIrrigationLog> listProIrrigationLogList(IrrigationDto irrigationDto);

    /**
     * 新增水肥灌溉记录
     *
     * @param proIrrigationLog 水肥灌溉记录
     * @return 结果
     */
    int insertProIrrigationLog(ProIrrigationLog proIrrigationLog);

    /**
     * 修改水肥灌溉记录
     *
     * @param proIrrigationLog 水肥灌溉记录
     * @return 结果
     */
    int updateProIrrigationLog(ProIrrigationLog proIrrigationLog);

    /**
     * 批量删除水肥灌溉记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteProIrrigationLogByIds(String ids);

    /**
     * 删除水肥灌溉记录信息
     *
     * @param logId 水肥灌溉记录ID
     * @return 结果
     */
    int deleteProIrrigationLogById(Long logId);

    /**
     * 提交灌溉数据
     */
    boolean submitIrrigation(String payload);

//    /**
//     * 提交大棚设备实时数据
//     */
//    boolean submitDevice(String greenHouseTag, String payload);

//    /**
//     * 获取大棚设备实时数据
//     */
//    JSONObject onlineDevice(String houseId);
//
//    /**
//     * 获取水肥实时数据
//     */
//    JSONObject getOnlineDate();
//
//    /**
//     * 控制大棚设备
//     */
//    boolean controlDevice(String greenHouse, Map<String,Integer> params);
//
//    void sendSignal(int time, String signal, String topic);
//
//    void sendSignal(int time, String signal, int value, String topic);

}
