package com.sinonc.iot.mapper;

import com.sinonc.iot.domain.ProIrrigationLog;
import com.sinonc.iot.dto.EChartsDto;
import com.sinonc.iot.dto.IrrigationDto;

import java.util.List;
import java.util.Map;

/**
 * 水肥灌溉记录Mapper接口
 *
 * @author ruoyi
 * @date 2021-12-03
 */
public interface ProIrrigationLogMapper {
    /**
     * 查询水肥灌溉记录
     *
     * @param logId 水肥灌溉记录ID
     * @return 水肥灌溉记录
     */
    ProIrrigationLog selectProIrrigationLogById(Long logId);

    /**
     * 查询水肥灌溉记录列表
     *
     * @param proIrrigationLog 水肥灌溉记录
     * @return 水肥灌溉记录集合
     */
    List<ProIrrigationLog> selectProIrrigationLogList(ProIrrigationLog proIrrigationLog);


    /**
     * 查询水肥记录
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
     * 删除水肥灌溉记录
     *
     * @param logId 水肥灌溉记录ID
     * @return 结果
     */
    int deleteProIrrigationLogById(Long logId);

    /**
     * 批量删除水肥灌溉记录
     *
     * @param logIds 需要删除的数据ID
     * @return 结果
     */
    int deleteProIrrigationLogByIds(String[] logIds);

    /**
     * 获取汇总数据，根据月份统计
     * @return
     */
    List<EChartsDto> getLogGroupByMonth();

}
