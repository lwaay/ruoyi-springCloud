package com.sinonc.iot.service.impl;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/5/21 15:28
 */

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sinonc.common.core.exception.BusinessException;
import com.sinonc.common.core.manager.AsyncManager;
import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.redis.service.RedisService;
import com.sinonc.iot.domain.ProIrrigationLog;
import com.sinonc.iot.dto.EChartsDto;
import com.sinonc.iot.dto.IrrigationDto;
import com.sinonc.iot.mapper.ProIrrigationLogMapper;
import com.sinonc.iot.mqtt.MqttMessageSender;
import com.sinonc.iot.mqtt.configuration.MqttConfiguration;
import com.sinonc.iot.mqtt.task.AsyncIrrigationFactory;
import com.sinonc.iot.service.IProIrrigationLogService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 水肥灌溉记录Service业务层处理
 *
 * @author ruoyi
 * @date 2021-12-03
 */
@Service
@Slf4j
public class ProIrrigationLogServiceImpl implements IProIrrigationLogService {
    @Autowired
    private ProIrrigationLogMapper proIrrigationLogMapper;

//    @Autowired
//    private MqttMessageSender sender;
//
//    @Autowired
//    private MqttConfiguration mqttConfiguration;

    @Autowired
    private RedisService redisUtil;

//    @Autowired
//    private RedissonClient redissonClient;
//
//    @Autowired
//    private RestTemplate restTemplate;

    /**
     * 查询水肥灌溉记录
     *
     * @param logId 水肥灌溉记录ID
     * @return 水肥灌溉记录
     */
    @Override
    public ProIrrigationLog selectProIrrigationLogById(Long logId) {
        return proIrrigationLogMapper.selectProIrrigationLogById(logId);
    }

    /**
     * 获取汇总数据，根据月份统计
     * @return
     */
    @Override
    public Map<String, Object> getLogGroupByMonth(){
        List<EChartsDto> retMapList = proIrrigationLogMapper.getLogGroupByMonth();
        Map<String, Object> dataMap = new HashMap<>(2);
        List<String> monthList = new ArrayList<>();
        List<String> dataList = new ArrayList<>();
        for(EChartsDto key : retMapList){
            monthList.add(key.getMonth());
            dataList.add(key.getFlow());
        }
        dataMap.put("month", monthList);
        dataMap.put("data", dataList);
        return dataMap;
    }

    /**
     * 查询水肥灌溉记录列表
     *
     * @param proIrrigationLog 水肥灌溉记录
     * @return 水肥灌溉记录
     */
    @Override
    public List<ProIrrigationLog> selectProIrrigationLogList(ProIrrigationLog proIrrigationLog) {
        return proIrrigationLogMapper.selectProIrrigationLogList(proIrrigationLog);
    }

    /**
     * 查询水肥灌溉记录列表
     *
     * @param irrigationDto 水肥灌溉记录
     * @return 水肥灌溉记录集合
     */
    @Override
    public List<ProIrrigationLog> listProIrrigationLogList(IrrigationDto irrigationDto) {
        return proIrrigationLogMapper.listProIrrigationLogList(irrigationDto);
    }

    /**
     * 新增水肥灌溉记录
     *
     * @param proIrrigationLog 水肥灌溉记录
     * @return 结果
     */
    @Override
    public int insertProIrrigationLog(ProIrrigationLog proIrrigationLog) {
        proIrrigationLog.setCreateTime(DateUtils.getNowDate());
        return proIrrigationLogMapper.insertProIrrigationLog(proIrrigationLog);
    }

    /**
     * 修改水肥灌溉记录
     *
     * @param proIrrigationLog 水肥灌溉记录
     * @return 结果
     */
    @Override
    public int updateProIrrigationLog(ProIrrigationLog proIrrigationLog) {
        return proIrrigationLogMapper.updateProIrrigationLog(proIrrigationLog);
    }

    /**
     * 删除水肥灌溉记录对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteProIrrigationLogByIds(String ids) {
        return proIrrigationLogMapper.deleteProIrrigationLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除水肥灌溉记录信息
     *
     * @param logId 水肥灌溉记录ID
     * @return 结果
     */
    @Override
    public int deleteProIrrigationLogById(Long logId) {
        return proIrrigationLogMapper.deleteProIrrigationLogById(logId);
    }

//    /**
//     * 获取大棚设备实时数据
//     */
//    @Override
//    public JSONObject onlineDevice(String houseId){
//        if(Objects.equals(houseId, "greenhouse4")){
//            String token = getYunfeiToken();
//            if(StringUtils.isBlank(token)){
//                throw new ApiBusinessException("系统开小差了，请稍后再试");
//            }
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            headers.set("token", token);
//            List<MediaType> accept = new ArrayList();
//            accept.add(MediaType.APPLICATION_JSON);
//            headers.setAccept(accept);
//            HttpEntity httpEntity = new HttpEntity<>(null, headers);
//            String url = yunfei_host + "/data/" + yunfei_device_id;
//            ResponseEntity<String> exchage =restTemplate.exchange(url, HttpMethod.GET,httpEntity,String.class);
//            if(exchage.getStatusCode() == HttpStatus.OK){
//                JSONObject res= JSONObject.parseObject(exchage.getBody());
//                log.info("获取大棚设备状态成功");
//                return res;
//            }else{
//                log.info("获取大棚设备状态异常，{}", exchage.getBody());
//                return new JSONObject();
//            }
//        }
//
//        Set<String> datakeys = redisTemplate.keys(GreenHouseConstants.GREENHOUSE_DEVICE_PREFIX + houseId + "*");
//        Set<String> exceptionkeys = redisTemplate.keys(GreenHouseConstants.GREENHOUSE_EXCEPTION_PREFIX + houseId + "*");
//        //获取状态集合
//        JSONObject statusresult = new JSONObject();
//        for(String key : datakeys){
//            JSONObject tmp = redisUtil.get(key,JSONObject.class);
//            statusresult.putAll(tmp);
//        }
//        //获取异常集合
//        JSONObject exceptionresult = new JSONObject();
//        for(String key : exceptionkeys){
//            JSONObject tmp = redisUtil.get(key,JSONObject.class);
//            exceptionresult.putAll(tmp);
//        }
//        for(String key : statusresult.keySet()){
//            MqttProp prop = mqttPropService.selectedMqttPropByhouseIdAndOutputSignal(houseId, key);
//            if(StringUtils.isNotBlank(prop.getExceptionSignal()) && exceptionresult.get(prop.getExceptionSignal()).equals(1)){
//                statusresult.put(key,2);
//            }
//        }
//        return statusresult;
//    }

    /**
     * 提交灌溉数据
     */
    @Override
    public boolean submitIrrigation(String payload) {
        if (StringUtils.isBlank(payload)) {
            return false;
        }
        JSONObject now = JSON.parseObject(payload);
        if (CollectionUtils.isEmpty(now)) {
            return false;
        }
        JSONObject last = redisUtil.getCacheObject("baise:iot:online_irrigation");
        if (CollectionUtils.isEmpty(last)) {
            redisUtil.setCacheObject("baise:iot:online_irrigation", now);
            if(now.getInteger("run_status") == 1){
                redisUtil.setCacheObject("baise:iot:start_irrigation", now);
            }
            return true;
        }
        //判断数据，生成灌溉记录
        try {
            initIrrigationLog(now, last);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return true;
    }

    /**
     * 生成灌溉记录
     */
    private boolean initIrrigationLog(JSONObject now, JSONObject last) throws ParseException {
        //判断阀门开启状态
        Integer status = now.getInteger("run_status");
        if (status.equals(0)) {
            //阀门关闭
            //更新最后数据记录
            redisUtil.del("baise:iot:online_irrigation");
            redisUtil.setCacheObject("baise:iot:online_irrigation", now);
            //获取是否有开始灌溉记录
            JSONObject start = redisUtil.getCacheObject("baise:iot:start_irrigation");
            if (CollectionUtils.isEmpty(start)) {
                return true;
            }
            //有灌溉开始记录，本次记录为第一次关闭记录
            ProIrrigationLog log = new ProIrrigationLog();
            //获取开始结束灌溉ec,ph__start
            Integer startEc = now.getInteger("EC");
            Integer startPh = now.getInteger("pH");
            Integer endEc = start.getInteger("EC");
            Integer endPh = start.getInteger("pH");
            log.setStartEc(startEc);
            log.setStartPh(startPh);
            log.setEndEc(endEc);
            log.setEndPh(endPh);
            //获取开始结束灌溉ec,ph__start
            //获取开始结束灌溉时间__start
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            String startTimeStr = start.getString("_terminalTime");
            String endTimeStr = now.getString("_terminalTime");
            if (StringUtils.isNotBlank(startTimeStr)) {
                Date currentTime_2 = formatter.parse(startTimeStr);
                log.setStartTime(currentTime_2);
            }
            if (StringUtils.isNotBlank(endTimeStr)) {
                Date currentTime_3 = formatter.parse(endTimeStr);
                log.setEndTime(currentTime_3);
            }
            //获取开始结束灌溉时间__end
            //获取开始结束灌溉流量__start
            Double startFlow = start.getDouble("cumulative_flow_irrigation");
            Double endFlow = now.getDouble("cumulative_flow_irrigation");
            if (startFlow != null && endFlow != null) {
                BigDecimal startFlowNum = new BigDecimal(startFlow);
                BigDecimal endFlowNum = new BigDecimal(endFlow);
                log.setStartFlow(startFlowNum);
                log.setEndFlow(endFlowNum);
                BigDecimal flow = endFlowNum.subtract(startFlowNum).setScale(2, BigDecimal.ROUND_HALF_UP);
                if (flow != null && flow.compareTo(BigDecimal.ZERO) > 0) {
                    log.setFlow(flow);
                }
            }
            //获取开始结束灌溉流量__end
            //获取开始结束A、B管灌溉流量__start
            Double startAFlow = start.getDouble("cumulative_flow_A");
            Double endAFlow = now.getDouble("cumulative_flow_A");
            Double aRate = now.getDouble("fertilizer_A");
            log.setARate(new BigDecimal(aRate));
            if (startAFlow != null && endAFlow != null) {
                BigDecimal startAFlowNum = new BigDecimal(startAFlow);
                BigDecimal endAFlowNum = new BigDecimal(endAFlow);
                log.setStartAFlow(startAFlowNum);
                log.setEndAFlow(endAFlowNum);
                BigDecimal aflow = endAFlowNum.subtract(startAFlowNum).setScale(2, BigDecimal.ROUND_HALF_UP);
                if (aflow != null && aflow.compareTo(BigDecimal.ZERO) > 0) {
                    log.setAFlow(aflow);
                } else {
                    log.setAFlow(BigDecimal.ZERO);
                }
            }

            Double startBFlow = start.getDouble("cumulative_flow_B");
            Double endBFlow = now.getDouble("cumulative_flow_B");
            Double bRate = now.getDouble("fertilizer_B");
            log.setBRate(new BigDecimal(bRate));
            if (startBFlow != null && endBFlow != null) {
                BigDecimal startBFlowNum = new BigDecimal(startBFlow);
                BigDecimal endBFlowNum = new BigDecimal(endBFlow);
                log.setStartBFlow(startBFlowNum);
                log.setEndBFlow(endBFlowNum);
                BigDecimal bflow = endBFlowNum.subtract(startBFlowNum).setScale(2, BigDecimal.ROUND_HALF_UP);
                if (bflow != null && bflow.compareTo(BigDecimal.ZERO) > 0) {
                    log.setBFlow(bflow);
                } else {
                    log.setBFlow(BigDecimal.ZERO);
                }
            }
            //获取开始结束A、B管灌溉流量__end
            //获取开始结束吸酸管道累计吸肥量__start
            Double startAcidAbsorb = start.getDouble("cumulative_flow_acid");
            Double endAcidAbsorb = now.getDouble("cumulative_flow_acid");
            if (startAcidAbsorb != null && endAcidAbsorb != null) {
                BigDecimal startAcidAbsorbNum = new BigDecimal(startAcidAbsorb);
                BigDecimal endAcidAbsorbNum = new BigDecimal(endAcidAbsorb);
                log.setStartAcidAbsorb(startAcidAbsorbNum);
                log.setEndAcidAbsorb(endAcidAbsorbNum);
                BigDecimal acidAbsorbFlow = endAcidAbsorbNum.subtract(startAcidAbsorbNum).setScale(2, BigDecimal.ROUND_HALF_UP);
                if (acidAbsorbFlow != null && acidAbsorbFlow.compareTo(BigDecimal.ZERO) > 0) {
                    log.setAcidAbsorbUsed(acidAbsorbFlow);
                } else {
                    log.setAcidAbsorbUsed(BigDecimal.ZERO);
                }
            }
            //获取开始结束吸酸管道累计吸肥量__end
            log.setCreateTime(new Date());
            int res = proIrrigationLogMapper.insertProIrrigationLog(log);
            if (res > 0) {
                redisUtil.del("baise:iot:start_irrigation");
//                redisUtil.del("checked_irrigation");
                return true;
            }
        }

        if (status.equals(1)) {
//            //阀门状态开启。
//            Integer lastStatus = last.getInteger("run_status");
//            if (lastStatus.equals(0)){
//                //最后一条阀门未开启，第一条开启记录
//                redisUtil.setCacheObject("start_irrigation",now);
//            }
            //原判断最后一条是关闭则把此次记录为打开，现由于打开关闭只发送一条记录，如果连续收到两条打开则丢弃之前记录
            redisUtil.setCacheObject("baise:iot:start_irrigation", now);
            redisUtil.del("baise:iot:online_irrigation");
            redisUtil.setCacheObject("baise:iot:online_irrigation", now);
//            String checkedStr = redisUtil.getStr("checked_irrigation");
//            JSONArray checkeds = new JSONArray();
//            if (StringUtils.isNotBlank(checkedStr) && !checkedStr.equals("null")){
//                checkeds = JSON.parseArray(JSON.parse(checkedStr).toString());
//            }
//            List<String> checked = Arrays.asList(buttons).stream().map(button->{
//                Integer check = now.getInteger(button);
//                if (check == null || check ==0){
//                    return null;
//                }
//                return button;
//            }).filter(StringUtils::isNotBlank).collect(Collectors.toList());
//            JSONArray array = new JSONArray();
//            String nowTimeStr =  now.getString("_terminalTime");
//            if (CollectionUtils.isEmpty(checkeds)){
//                checked.forEach(item->{
//                    JSONObject object = new JSONObject();
//                    object.put("key",item);
//                    object.put("start",nowTimeStr);
//                    array.add(object);
//                });
//            }else {
//                //遍历历史开关据，是否需要关闭开关数据
//                checkeds.forEach(old->{
//                    JSONObject object = (JSONObject) old;
//                    //查看是否找到还在开启的按钮
//                    boolean hasCheck = checked.stream().anyMatch(fil-> fil.equals(object.getString("key")));
//                    if (hasCheck){
//                        array.add(object);
//                    }else {
//                        object.put("end",nowTimeStr);
//                        array.add(object);
//                    }
//                });
//                //新打开的开关
//                checked.forEach(item->{
//                    //过滤掉历史已开启的开关
//                    boolean hasOld = array.stream().anyMatch(fil->{
//                        JSONObject fill = (JSONObject) fil;
//                        return fill.equals(item);
//                    });
//                    if (hasOld){
//                        return;
//                    }
//                    JSONObject object = new JSONObject();
//                    object.put("key",item);
//                    object.put("start",nowTimeStr);
//                });
//            }
//
//            if (!CollectionUtils.isEmpty(array)){
//                redisUtil.del("checked_irrigation");
//                redisUtil.set("checked_irrigation",array.toJSONString());
//            }
        }
        return true;
    }

//    /**
//     * 获取水肥实时数据
//     */
//    public JSONObject getOnlineDate(){
//        JSONObject object = redisUtil.get("online_irrigation",JSONObject.class);
//        return object;
//    }

//    /**
//     * 控制大棚设备
//     */
//    @Override
//    public boolean controlDevice(String greenHouse, Map<String,Integer> params){
//        if (CollectionUtils.isEmpty(params)){
//            throw new ApiBusinessException("参数中未带软元件名");
//        }
//        if(StringUtils.isBlank(greenHouse)){
//            throw new ApiBusinessException("请求路径中未带建筑名");
//        }
//        Set<String> keyset = params.keySet();
//        String deviceTag = "";
//        Iterator<String> iterator = keyset.iterator();
//        while (iterator.hasNext()){
//            deviceTag = iterator.next();
//        }
//        if(deviceTag == ""){
//            throw new ApiBusinessException("请求体非法");
//        }
//        UserGreenhouseBind bind = userGreenhouseBindService.selectUserGreenhouseBindByUserId(JWTUtils.getUserId());
//        if(ObjectUtils.isEmpty(bind)){
//            throw new ApiBusinessException("当前用户没有对该大棚设备的控制权限配置");
//        }else if(!bind.getGreenhouse().contains(greenHouse)){
//            throw new ApiBusinessException("没有对该大棚设备的控制权限");
//        }
//        // 云飞
//        if(Objects.equals(greenHouse, "greenhouse4")){
//            String token = getYunfeiToken();
//            if(StringUtils.isBlank(token)){
//                throw new ApiBusinessException("系统开小差了，请稍后再试");
//            }
//            if(!deviceTag.startsWith("j")){
//                throw new ApiBusinessException("请求体非法");
//            }
//            int trueTag = 0;
//            try {
//                trueTag = Integer.parseInt(deviceTag.substring(1)) - 1;
//            } catch (NumberFormatException e) {
//                throw new ApiBusinessException("请求体非法");
//            }
//            int signal = params.get(deviceTag);
//            if(signal != 0 && signal != 1){
//                throw new ApiBusinessException("请求体非法");
//            }
//            HashMap<String,Object> param = new HashMap<>();
//            param.put("relayNum", trueTag);
//            param.put("deviceId", yunfei_device_id);
//            param.put("relayState",signal);
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            log.info("param:{}",JSON.toJSONString(param));
//            List<MediaType> accept = new ArrayList();
//            accept.add(MediaType.APPLICATION_JSON);
//            headers.setAccept(accept);
//            headers.set("token", token);
//            HttpEntity httpEntity = new HttpEntity<>(param, headers);
//            String url = yunfei_host + "/relay";
//            log.info("控制大棚请求发送，{}",JSON.toJSONString(param));
//            ResponseEntity<Boolean> exchage =restTemplate.exchange(url, HttpMethod.POST,httpEntity,Boolean.class);
//            if(exchage.getStatusCode() == HttpStatus.OK){
//                log.info("控制大棚请求发送成功");
//                return exchage.getBody();
//            }else{
//                log.error("与云飞通讯异常");
//                return false;
//            }
//        }
//        // 奥鑫
//        MqttProp mqttProp = new MqttProp();
//        mqttProp.setBelongTo(greenHouse);
//        mqttProp.setOutputSignal(deviceTag);
//        List<MqttProp> propList = mqttPropService.selectMqttPropList(mqttProp);
//        if(propList.size() != 1){
//            throw new ApiBusinessException("没有该参数的配置信息");
//        }
//        // 软元件名
//        String operateSignal = "";
//        String operateTopic = "";
//        // 预防上一操作信号未关闭
//        String operateTopicBack = "";
//        String operateSignalBack = "";
//        if(params.get(deviceTag).equals(1)){
//            operateSignal = propList.get(0).getOpenSignal().toLowerCase();
//            operateTopic = propList.get(0).getOpenSignalTopic();
//            operateSignalBack = propList.get(0).getStopSignal().toLowerCase();
//            operateTopicBack = propList.get(0).getStopSignalTopic();
//        }else if(params.get(deviceTag).equals(0)){
//            operateSignal = propList.get(0).getStopSignal().toLowerCase();
//            operateTopic = propList.get(0).getStopSignalTopic();
//            operateSignalBack = propList.get(0).getOpenSignal().toLowerCase();
//            operateTopicBack = propList.get(0).getOpenSignalTopic();
//        }else{
//            throw new ApiBusinessException("非法参数");
//        }
//
//        RLock lock = redissonClient.getLock(GreenHouseConstants.GREENHOUSE_EXEC_PREFIX + greenHouse + ":" + deviceTag);
//        boolean result = false;
//        try{
//            int time = Convert.toInt(configService.selectConfigByKey("sys.iot.loading"),20);
//            if(lock.tryLock(0, time, TimeUnit.SECONDS)){
//                //异步
//                // 关闭上一操作信号（预防信号关闭指令未收到）
//                AsyncManager.me().execute(AsyncIrrigationFactory.sendSignalClose(time, operateSignalBack, operateTopicBack));
//                // 发送该次操作指令（带关闭信号）
//                AsyncManager.me().execute(AsyncIrrigationFactory.sendSignal(time, operateSignal, operateTopic));
//                result = true;
//            }else{
//                throw new BusinessException("请求频繁。");
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }finally {
//            return result;
//        }
//    }

//    /**
//     * 发送开启关闭信号
//     * @param time
//     * @param signal
//     * @param topic
//     */
//    @Override
//    public void sendSignal(int time, String signal, String topic){
//        // 发送信号
//        sendSignal(time,signal,1,topic);
//        // 清除信号
//        sendSignal(time,signal,0,topic);
//    }

//    /**
//     * 发送信号
//     *
//     * @param time   次数
//     * @param signal 软元件名
//     * @param value  值
//     * @param topic  主题
//     */
//    public void sendSignal(int time, String signal, int value, String topic) {
//        IntStream.range(0, time).forEach(i -> {
//            JSONObject payload = new JSONObject();
//            payload.put(signal, value);
//            log.info("topic: {} send :{}", topic, payload.toString());
//            try {
//                Thread.sleep(500);
//                sender.send(topic, 1, payload.toJSONString());
//            } catch (Exception e) {
//                log.error(e.getMessage(), e);
//            }
//        });
//    }
//
//    private String getYunfeiToken() {
//        if (redisUtil.hasKey(GreenHouseConstants.GREENHOUSE_YUNFEI_TOKEN)) {
//            return (String) redisTemplate.opsForValue().get(GreenHouseConstants.GREENHOUSE_YUNFEI_TOKEN);
//        }
//        HashMap<String, String> params = new HashMap<>();
//        params.put("username", yunfei_username);
//        params.put("password", yunfei_password);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        List<MediaType> accept = new ArrayList();
//        accept.add(MediaType.APPLICATION_JSON);
//        headers.setAccept(accept);
//        HttpEntity httpEntity = new HttpEntity<>(params, headers);
//        String url = yunfei_host + "/login";
//        log.info("与云飞通讯获取token，{}", JSON.toJSONString(params));
//        ResponseEntity<String> exchage = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
//        if (exchage.getStatusCode() == HttpStatus.OK) {
//            JSONObject res = JSONObject.parseObject(exchage.getBody());
//            redisUtil.set(GreenHouseConstants.GREENHOUSE_YUNFEI_TOKEN, res.get("token"), res.getLong("expiration"), TimeUnit.SECONDS);
//            log.info("与云飞通讯获取token，成功");
//            return res.getString("token");
//        } else {
//            log.info("与云飞通讯获取token，失败:{}", exchage.getBody());
//            return "";
//        }
//    }
}
