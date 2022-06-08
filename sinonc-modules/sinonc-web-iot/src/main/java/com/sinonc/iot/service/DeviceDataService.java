package com.sinonc.iot.service;

import com.sinonc.iot.api.vo.ProStaticVo;
import com.sinonc.iot.domain.InsectImg;
import com.sinonc.iot.api.dto.ProdEnviStatisticsDto;
import com.sinonc.iot.vo.QueryDataVo;
import com.sinonc.iot.vo.RealData;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/1/24 15:45
 */
public interface DeviceDataService {

    /**
     * 查询设备数据
     * @param queryDataVo
     * @return
     */
    public Map<String, Object> getDeviceData(QueryDataVo queryDataVo);

    /**
     * 查询实时数据
     * @param deviceId
     * @return
     */
    public List<RealData> getRealData(String deviceId);

    /**
     * 根据设备id查询虫情图片
     * @param deviceId
     * @return
     */
    InsectImg getImg(String deviceId);

    /**
     * 获取设备数据统计，
     * @param proStaticVo
     * @return
     */
    ProdEnviStatisticsDto getProdEnviStatisticsDto(ProStaticVo proStaticVo);
}
