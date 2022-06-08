package com.sinonc.iot.mqtt.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sinonc.common.core.utils.SpringUtils;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.iot.service.IProIrrigationLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/**
 * 异步工厂（产生任务用）
 *
 * @author liuhulu
 *
 */
public class AsyncIrrigationFactory
{
    private static final Logger irrigation_logger = LoggerFactory.getLogger("irrigation");

    /**
     * 记录水肥灌溉信息
     * @return 任务task
     */
    public static TimerTask logIrrigation(String payload)
    {
        irrigation_logger.info("灌溉记录：{}",payload);
        return new TimerTask()
        {
            @Override
            public void run()
            {
                if (StringUtils.isBlank(payload)){
                    return;
                }
                JSONObject data = JSON.parseObject(payload);
                if (data == null || data.isEmpty()){
                    return;
                }
                SpringUtils.getBean(IProIrrigationLogService.class).submitIrrigation(payload);
            }
        };
    }

    /**
     * 大棚记录设备实时数据
     */
    public static TimerTask logDevice(String greenHouse, String payload)
    {
        return new TimerTask()
        {
            @Override
            public void run()
            {
                if (StringUtils.isBlank(payload)){
                    return;
                }
                JSONObject data = JSON.parseObject(payload);
                if (data == null || data.isEmpty()){
                    return;
                }
//                SpringUtils.getBean(IProIrrigationLogService.class).submitDevice(greenHouse,payload);
            }
        };
    }

    /**
     * 发送大棚操作信号
     * @param time
     * @param signal
     * @param topic
     * @return
     */
    public static TimerTask sendSignal(int time, String signal, String topic){
        return new TimerTask() {
            @Override
            public void run() {
                if(time == 0){
                    return;
                }
                if(StringUtils.isBlank(signal)){
                    return;
                }
                if(StringUtils.isBlank(topic)){
                    return;
                }
//                SpringUtils.getBean(IProIrrigationLogService.class).sendSignal(time, signal, topic);
            }
        };
    }

    /**
     * 关闭上一操作信号（冗余操作）
     * @param time
     * @param signal
     * @param topic
     * @return
     */
    public static TimerTask sendSignalClose(int time, String signal, String topic){
        return new TimerTask() {
            @Override
            public void run() {
                if(time == 0){
                    return;
                }
                if(StringUtils.isBlank(signal)){
                    return;
                }
                if(StringUtils.isBlank(topic)){
                    return;
                }
//                SpringUtils.getBean(IProIrrigationLogService.class).sendSignal(time, signal, 0, topic);
            }
        };
    }
}
