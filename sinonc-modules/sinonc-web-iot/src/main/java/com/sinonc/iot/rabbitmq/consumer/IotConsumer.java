package com.sinonc.iot.rabbitmq.consumer;


import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.sms.SmsService;
import com.sinonc.iot.api.domain.DeviceInfo;
import com.sinonc.iot.config.property.IotConfig;
import com.sinonc.iot.domain.IotWarn;
import com.sinonc.iot.domain.IotWarnSet;
import com.sinonc.iot.mapper.IotWarnSetMapper;
import com.sinonc.iot.service.IDeviceInfoService;
import com.sinonc.iot.service.IotWarnService;
import com.sinonc.iot.vo.DataPointVo;
import com.sinonc.iot.rabbitmq.config.RabbitProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;


/**
 * 接收物联网项目发送的消息
 */
@Component
@Slf4j
@ConditionalOnProperty(prefix = "rabbitmq", name = "enable", havingValue = "true")
public class IotConsumer {

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private IotWarnSetMapper iotWarnSetMapper;

    @Autowired
    private IotWarnService iotWarnService;

    @Autowired
    private IDeviceInfoService deviceInfoService;

    @Autowired
    private SmsService smsService;

    @Autowired
    private IotConfig appInfo;

    @Autowired
    private RabbitProperties rabbitProperties1;
    /**
     * 获取设备信息
     *
     * @param
     */
    @RabbitListener(queues = "iot-linchuan-queue")
    public void process(DataPointVo dataPointVo) {

        threadPoolTaskExecutor.execute(() -> {
            try {
                // 预警信息
              Boolean res =  checkDataPoint(dataPointVo);
                log.info(res?"预警信息发送成功: " + DateUtils.dateTimeNow():"预警信息发送失败: "+ DateUtils.dateTimeNow());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        });
    }



    public boolean checkDataPoint(DataPointVo dataPointVo) {
        if (ObjectUtils.isEmpty(dataPointVo)) {
            return false;
        }

        String deviceId = dataPointVo.getDeviceId();
        boolean rs = isHaveDevice(deviceId);
        if (!rs) {
            //不是这边的设备，不做处理
            return false;
        }

        //正常
        long status = dataPointVo.getOnline();
        if (status == DeviceStatusContents.STATUS_NORMAL) {
            return execData(dataPointVo);
        }
        //离线
        if (status == DeviceStatusContents.OFF_ONLINE) {
            return execIotOffWarn(dataPointVo);
        }
        //上线
        if (status == DeviceStatusContents.ON_ONLINE) {
            //return execIotOnWarn(dataPointVo);
            return false;
        }
        //虫情图片
        if (status == DeviceStatusContents.IMG_WARM){
            return execImgWarm(dataPointVo);
        }

        return false;
    }

    private boolean isHaveDevice(String deviceId) {
        DeviceInfo device= deviceInfoService.getDeviceInfoByDeviceId(deviceId);
        return Optional.ofNullable(device).isPresent();
    }

    private boolean execIotOffWarn(DataPointVo dataPointVo) {
        String deviceId = dataPointVo.getDeviceId();

        IotWarn iotWarn = new IotWarn();
        //未处理
        iotWarn.setIsHandle(0);
        iotWarn.setDeviceId(deviceId);
        // 时间戳
        String stamp = String.valueOf(dataPointVo.getTimestamp());
        String linuxTime = DateUtils.getLinuxTime(stamp, DateUtils.YYYYMMDDHHMMSS);
        iotWarn.setMetricTime(linuxTime);
        iotWarn.setWarnInfo("设备状态为离线。");
        //离线
        iotWarn.setWarnType(3);
        iotWarn.setCreateTime(DateUtils.getNowDate());
        iotWarnService.addIotWarn(iotWarn);
        DeviceInfo info =deviceInfoService.getDeviceInfoByDeviceId(deviceId);
        if (!Optional.ofNullable(info).isPresent()){
            return false;
        }
        IotWarnSet query = new IotWarnSet();
        query.setDeviceId(deviceId);
        List<IotWarnSet> set = iotWarnSetMapper.selectIotWarnSetList(query);
        if (CollectionUtils.isEmpty(set)){
            return false;
        }
        //发送预警短信
        return smsService.sendWarmSms(set.get(0).getPhone(), info.getDeviceName(),"设备状态为离线。");
    }

    private boolean execImgWarm(DataPointVo dataPointVo) {
        String deviceId = dataPointVo.getDeviceId();

        IotWarn iotWarn = new IotWarn();
        //未处理
        iotWarn.setIsHandle(0);
        iotWarn.setDeviceId(deviceId);
        // 时间戳
        String stamp = String.valueOf(dataPointVo.getTimestamp());
        String linuxTime = DateUtils.getLinuxTime(stamp, DateUtils.YYYYMMDDHHMMSS);
        iotWarn.setMetricTime(linuxTime);
        iotWarn.setWarnInfo(dataPointVo.getRemark());
        //虫情
        iotWarn.setWarnType(4);
        iotWarn.setCreateTime(DateUtils.getNowDate());
        iotWarnService.addIotWarn(iotWarn);
        DeviceInfo info =deviceInfoService.getDeviceInfo(deviceId);
        if (!Optional.ofNullable(info).isPresent()){
            return false;
        }
        IotWarnSet query = new IotWarnSet();
        query.setDeviceId(deviceId);
        List<IotWarnSet> set = iotWarnSetMapper.selectIotWarnSetList(query);
        if (CollectionUtils.isEmpty(set)){
            return false;
        }
        //发送预警短信
        return smsService.sendWarmSms(set.get(0).getPhone(), info.getDeviceName(), iotWarn.getRemark());
    }

    private boolean execIotOnWarn(DataPointVo dataPointVo) {
        String deviceId = dataPointVo.getDeviceId();

        IotWarn iotWarn = new IotWarn();
        //未处理
        iotWarn.setIsHandle(0);
        iotWarn.setDeviceId(deviceId);
        // 时间戳
        String stamp = String.valueOf(dataPointVo.getTimestamp());
        String linuxTime = DateUtils.getLinuxTime(stamp, DateUtils.YYYYMMDDHHMMSS);
        iotWarn.setMetricTime(linuxTime);
        iotWarn.setWarnInfo("设备状态为上线。");
        //上线
        iotWarn.setWarnType(2);
        iotWarn.setCreateTime(DateUtils.getNowDate());
        iotWarnService.addIotWarn(iotWarn);
        DeviceInfo info =deviceInfoService.getDeviceInfo(deviceId);
        if (!Optional.ofNullable(info).isPresent()){
            return false;
        }
        IotWarnSet query = new IotWarnSet();
        query.setDeviceId(deviceId);
        List<IotWarnSet> set = iotWarnSetMapper.selectIotWarnSetList(query);
        if (CollectionUtils.isEmpty(set)){
            return false;
        }
        //发送预警短信
        return smsService.sendWarmSms(set.get(0).getPhone(), info.getDeviceName(),"设备状态为上线。");
    }


    private boolean execData(DataPointVo dataPointVo) {
        String deviceId = dataPointVo.getDeviceId();
        String metric = dataPointVo.metric;
        IotWarnSet iotWarnSet = new IotWarnSet();
        iotWarnSet.setDeviceId(deviceId);
        iotWarnSet.setMetric(metric);
        iotWarnSet.setIsEnble(1);
        DeviceInfo info =deviceInfoService.getDeviceInfo(deviceId);
        List<IotWarnSet> iotWarnSetList = iotWarnSetMapper.selectIotWarnSetList(iotWarnSet);
        if (iotWarnSetList != null && iotWarnSetList.size() > 0) {
            IotWarnSet tempIotWarnSet = iotWarnSetList.get(0);
            Double highLimit = tempIotWarnSet.getHighLimit();
            Double lowLimit = tempIotWarnSet.getLowLimit();
            Double superLowLimit = tempIotWarnSet.getSuperLowLimit();
            Double metricValue = dataPointVo.value;

            IotWarn iotWarn = new IotWarn();
            iotWarn.setDeviceId(deviceId);
            iotWarn.setMetric(metric);
            iotWarn.setMetricValue(metricValue);
            iotWarn.setMetricTime(String.valueOf(dataPointVo.timestamp));
            //未处理
            iotWarn.setIsHandle(0);

            if (metricValue > highLimit) {
                iotWarn.setWarnType(1);
                iotWarn.setWarnInfo(dataPointVo.getRemark() + "，过高");
                iotWarnService.addIotWarn(iotWarn);
                if (!Optional.ofNullable(info).isPresent()){
                    return false;
                }
                //发送预警短信
                return smsService.sendWarmSms(tempIotWarnSet.getPhone(), info.getDeviceName(), iotWarn.getWarnInfo());
            }
            if (metricValue < superLowLimit) {
                iotWarn.setWarnType(1);
                iotWarn.setWarnInfo(dataPointVo.getRemark() + "，低于最低限制");
                iotWarnService.addIotWarn(iotWarn);
                //发送预警短信
                return smsService.sendWarmSms(tempIotWarnSet.getPhone(), info.getDeviceName(), iotWarn.getWarnInfo());
            }
            if (metricValue < lowLimit) {
                iotWarn.setWarnType(1);
                iotWarn.setWarnInfo(dataPointVo.getRemark() + "，过低");
                iotWarn.setCreateTime(DateUtils.getNowDate());
                iotWarnService.addIotWarn(iotWarn);
                //发送预警短信
                return smsService.sendWarmSms(tempIotWarnSet.getPhone(), info.getDeviceName(), iotWarn.getWarnInfo());
            }
        }
        return true;
    }
}
