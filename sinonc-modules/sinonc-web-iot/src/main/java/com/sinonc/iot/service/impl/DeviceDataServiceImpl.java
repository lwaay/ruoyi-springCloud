package com.sinonc.iot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.iot.api.domain.DeviceInfo;
import com.sinonc.iot.api.domain.DeviceProperty;
import com.sinonc.iot.api.vo.ProStaticVo;
import com.sinonc.iot.domain.InsectImg;
import com.sinonc.iot.api.dto.ProdEnviStatisticsDto;
import com.sinonc.iot.service.DeviceDataService;
import com.sinonc.iot.service.IDeviceInfoService;
import com.sinonc.iot.service.IDevicePropertyService;
import com.sinonc.iot.service.hbase.TSDBService;
import com.sinonc.iot.vo.QueryDataVo;
import com.sinonc.iot.vo.RealData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/1/24 15:48
 */
@Service
@RefreshScope
@Slf4j
public class DeviceDataServiceImpl implements DeviceDataService {


    @Value("${iot.host}")
    public String iotHost;

    @Value("${iot.appKey}")
    private String appId;

    @Value("${iot.appSecret}")
    private String secret;

    @Autowired
    private IDeviceInfoService deviceInfoService;
    @Autowired
    private IDevicePropertyService propertyService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    TSDBService tsdbService;


    private String api="/api/iot/data/query";

    @Override
    public Map<String, Object> getDeviceData(QueryDataVo queryDataVo) {
        //调用统一下单获取的prepay_id
//RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("appId",appId);
        headers.set("secret",secret);
        HttpEntity<QueryDataVo> httpEntity = new HttpEntity<>(queryDataVo, headers);
        ResponseEntity<String> exchage = restTemplate.exchange(iotHost+api, HttpMethod.POST,httpEntity,String.class);

        JSONObject res= JSONObject.parseObject(exchage.getBody());
        JSONObject data=res.getJSONObject("data");
        return data;
    }

    @Override
    public List<RealData> getRealData(String deviceId) {
        //调用统一下单获取的prepay_id
        //RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("appId", appId);
        headers.set("secret", secret);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> exchage = restTemplate.exchange(iotHost + "/api/iot/devicedata/getDataByMetrics?deviceId=" + deviceId, HttpMethod.GET, httpEntity, String.class);
        log.info("deviceId:"+deviceId);
        log.info("appId:"+appId);
        log.info("secret:"+secret);
        log.info("exchage.getBody():"+exchage.getBody());
        JSONObject res = JSONObject.parseObject(exchage.getBody());
        JSONObject data = res.getJSONObject("data");
        Map<String, String> map = JSONObject.toJavaObject(data, Map.class);
        if (map == null) {
            return null;
        }
        DeviceInfo deviceInfo = deviceInfoService.getDeviceInfo(deviceId);
        List<DeviceProperty> devicePropertyList = propertyService.getPropertyByType(deviceInfo.getDeviceTypeId());
        Iterator iterator = map.entrySet().iterator();
        List<RealData> realDataList = new ArrayList<>();
        while (iterator.hasNext()) {
            Map.Entry objectMap = (Map.Entry) iterator.next();
            RealData realData = new RealData();
            for (DeviceProperty deviceProperty : devicePropertyList) {

                if (deviceProperty.getProperty().equals(objectMap.getKey())) {
                    realData.setMetrics(deviceProperty.getProperty());
                    realData.setName(deviceProperty.getName());
                    realData.setUnit(deviceProperty.getUnit());
                    realData.setLastTime(StringUtils.isNull(deviceProperty.getLastTime()) ? String.valueOf(System.currentTimeMillis()) : deviceProperty.getLastTime());
                    if (objectMap.getValue().toString() == null) {
                        realData.setValue("0");
                    } else {
                        realData.setValue(objectMap.getValue().toString());
                    }
                    realDataList.add(realData);

                }
            }

        }
        return realDataList;
    }

    @Override
    public InsectImg getImg(String deviceId) {
        //调用统一下单获取的prepay_id
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("appId", appId);
        headers.set("secret", secret);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> exchage = restTemplate.exchange(iotHost + "/api/iot/devicedata/getInsectImg?deviceId=" + deviceId, HttpMethod.GET, httpEntity, String.class);
        JSONObject res = JSONObject.parseObject(exchage.getBody());
        JSONObject data = res.getJSONObject("data");
        if (data == null) {
            return null;
        }
        return data.toJavaObject(InsectImg.class);
    }

    @Override
    public ProdEnviStatisticsDto getProdEnviStatisticsDto(ProStaticVo proStaticVo) {
        Date startDateConvert = proStaticVo.getOriginsDate();
        Date endDateConvert = proStaticVo.getEndDate();
        String deviceId = proStaticVo.getDeviceId();
        ProdEnviStatisticsDto prodEnviStatisticsDto=new ProdEnviStatisticsDto();
        Map<String, String> filter = new HashMap<>();
        filter.put("deviceId", deviceId);

        //有效积温
        double accumulateTemperature = queryAccumulateTemperature(startDateConvert, endDateConvert, filter, "KQWD");
        prodEnviStatisticsDto.setAccumulateTemperature(accumulateTemperature);

        //平均光照强度
        double avgIllumination = queryAverage(startDateConvert, endDateConvert, filter, "GZ");
        prodEnviStatisticsDto.setAvgIllumination(avgIllumination);

        //平均昼夜温差
        double avgCivilDayTemperature = queryCivilDay(startDateConvert, endDateConvert, filter, "KQWD");
        prodEnviStatisticsDto.setAvgCivilDayTemperature(avgCivilDayTemperature);

        return prodEnviStatisticsDto;
    }


    /**
     * 查询昼夜温差
     *
     * @param startDate
     * @param endDate
     * @param filter
     * @param metric
     * @return
     */
    private double queryCivilDay(Date startDate, Date endDate, Map<String, String> filter, String metric) {
        QueryDataVo queryDataVo = new QueryDataVo();
        queryDataVo.setStartTime(String.valueOf(startDate.getTime()));
        queryDataVo.setEndTime(String.valueOf(endDate.getTime()));
        queryDataVo.setMetric(metric);
        queryDataVo.setAggregator("max");
        queryDataVo.setDownsample("1d-max");
        queryDataVo.setParams(filter);
        Map<String, Object> max = tsdbService.executeQuery(queryDataVo);
        queryDataVo.setAggregator("min");
        queryDataVo.setDownsample("1d-min");
        Map<String, Object> min = tsdbService.executeQuery(queryDataVo);

        if (min == null || max == null || min.get("result") == null || max.get("result") == null) {
            return 0;
        }

        double minTQWD = addTQWD(min);
        double maxTQWD = addTQWD(max);

        long nd = 24 * 60 * 60 * 1000;
        long diff = endDate.getTime() - startDate.getTime();
        long day = diff / nd;

        return new BigDecimal((maxTQWD - minTQWD) / day).setScale(2, RoundingMode.UP).doubleValue();
    }

    private static double addTQWD(Map<String, Object> tqwd) {

        String result = tqwd.get("result").toString();
        JSONObject vlJSONObject = (JSONObject) JSONObject.parse(result);
        Map<String, Object> value = vlJSONObject.getInnerMap();

        BigDecimal ljBigDecimal = new BigDecimal("0.00");
        for (String key : value.keySet()) {
            String myvalue = value.get(key).toString();
            BigDecimal myBigBigDecimal = new BigDecimal(myvalue);
            ljBigDecimal = ljBigDecimal.add(myBigBigDecimal);
        }
        return ljBigDecimal.setScale(2, RoundingMode.UP).doubleValue();
    }

    private double queryAverage(Date startDate, Date endDate, Map<String, String> filter, String metric) {
        QueryDataVo queryDataVo = new QueryDataVo();
        queryDataVo.setStartTime(String.valueOf(startDate.getTime()));
        queryDataVo.setEndTime(String.valueOf(endDate.getTime()));

        queryDataVo.setMetric(metric);
        queryDataVo.setAggregator("avg");

        long nd = 24 * 60 * 60 * 1000;
        long diff = endDate.getTime() - startDate.getTime();
        long day = diff / nd;
        queryDataVo.setDownsample(day + "d-avg");
        queryDataVo.setParams(filter);
        Map<String, Object> max = tsdbService.executeQuery(queryDataVo);
        if (max == null || max.get("result") == null) {
            return 0;
        }
        String result = max.get("result").toString();
        JSONObject vlJSONObject = (JSONObject) JSONObject.parse(result);
        Map<String, Object> value = vlJSONObject.getInnerMap();

        for (String key : value.keySet()) {
            String myvalue = value.get(key).toString();
            return new BigDecimal(myvalue).setScale(2, RoundingMode.UP).doubleValue();
        }

        return 0;
    }

    /**
     * 查询活动积温
     *
     * @param startDate
     * @param endDate
     * @return
     */
    private double queryAccumulateTemperature(Date startDate, Date endDate, Map<String, String> filter, String metric) {
        QueryDataVo queryDataVo = new QueryDataVo();
        queryDataVo.setStartTime(String.valueOf(startDate.getTime()));
        queryDataVo.setEndTime(String.valueOf(endDate.getTime()));
        queryDataVo.setMetric(metric);
        queryDataVo.setAggregator("avg");
        queryDataVo.setDownsample("1d-avg");
        queryDataVo.setParams(filter);
        Map<String, Object> max = tsdbService.executeQuery(queryDataVo);

        if (max == null || max.get("result") == null) {
            return 0;
        }

        String result = max.get("result").toString();
        JSONObject vlJSONObject = (JSONObject) JSONObject.parse(result);
        Map<String, Object> value = vlJSONObject.getInnerMap();

        BigDecimal hdBigDecimal = new BigDecimal("10.00");
        BigDecimal ljBigDecimal = new BigDecimal("0.00");

        for (String key : value.keySet()) {
            String myvalue = value.get(key).toString();
            BigDecimal myBigBigDecimal = new BigDecimal(myvalue);

            //平均温度大于10摄氏度
            if (myBigBigDecimal.compareTo(hdBigDecimal) == 1) {
                ljBigDecimal = ljBigDecimal.add(myBigBigDecimal);
            }

        }

        return ljBigDecimal.setScale(2, RoundingMode.UP).doubleValue();
    }

}
