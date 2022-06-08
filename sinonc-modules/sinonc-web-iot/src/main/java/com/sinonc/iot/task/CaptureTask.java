package com.sinonc.iot.task;

import com.sinonc.iot.api.domain.DeviceMonitor;
import com.sinonc.iot.domain.DeviceCapture;
import com.sinonc.iot.service.IDeviceCaptureService;
import com.sinonc.iot.service.IDeviceMonitorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/5/9 9:53
 */
@Component
@Slf4j
@RefreshScope
public class CaptureTask {

    @Autowired
    private IDeviceMonitorService monitorService;

    @Autowired
    private IDeviceCaptureService captureService;

    private static int TIME = 0;


    @PostConstruct
    private void init(){
        log.info("抓拍定时任务启动成功");
    }

    @Scheduled(cron = "0 */30 * * * ? ")
    public void doTask(){
        log.info("第{}次任务开始",TIME);
        List<DeviceMonitor> monitors = monitorService.selectDeviceMonitorList(new DeviceMonitor());
        if (CollectionUtils.isEmpty(monitors)){
            return;
        }
        Date now = new Date();
        monitors.forEach(monitor->{
            //每个摄像头调用一次抓拍
            if (StringUtils.isEmpty(monitor.getDevSn())|| monitor.getDevChannel() == null){
                return;
            }
            String capture = monitorService.capture(monitor.getDevSn(),monitor.getDevChannel());
            log.info("{}抓拍图片：{}",monitor.getName(),capture);
            if (StringUtils.isEmpty(capture)){
                return;
            }
            DeviceCapture deviceCapture = new DeviceCapture();
            deviceCapture.setCreateTime(now);
            SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
            String name = monitor.getName()+format.format(now);
            deviceCapture.setMonitorName(monitor.getName());
            deviceCapture.setMonitorId(monitor.getId());
            deviceCapture.setName(name);
            deviceCapture.setUrl(capture);
            captureService.insertDeviceCapture(deviceCapture);
            log.info("抓拍成功");
        });
        log.info("第{}次任务结束",TIME++);
    }
}
