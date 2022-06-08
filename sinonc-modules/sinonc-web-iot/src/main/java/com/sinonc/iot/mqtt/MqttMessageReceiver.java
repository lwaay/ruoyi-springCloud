package com.sinonc.iot.mqtt;

import com.sinonc.common.core.manager.AsyncManager;
import com.sinonc.common.core.utils.SpringUtils;
import com.sinonc.iot.constant.TopicConstants;
import com.sinonc.iot.mqtt.task.AsyncIrrigationFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledExecutorService;

@Slf4j
@AllArgsConstructor
@Component
public class MqttMessageReceiver implements MessageHandler {

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        try {
            MessageHeaders headers = message.getHeaders();
            //获取消息Topic
            String receivedTopic = (String) headers.get(MqttHeaders.RECEIVED_TOPIC);
            //log.info("[获取到的消息的topic :]{} ", receivedTopic);
            String payload = (String) message.getPayload();
            switch (receivedTopic){
                case TopicConstants.BAISE_SHUIFEI:
                    AsyncManager.me().execute(AsyncIrrigationFactory.logIrrigation(payload));
                    break;
//                case TopicConstants.BAISE_SHUIFEI_TEST:
//                    AsyncManager.me().execute(AsyncIrrigationFactory.logIrrigation(payload));
//                    break;
//                //三号棚（数据）
//                case TopicConstants.GREENHOUSE_THREE_DATA_TOPIC:AsyncManager.me().execute(AsyncIrrigationFactory.logDevice(GreenHouseConstants.GREENHOUSE_DEVICE_PREFIX + "greenhouse3",payload));break;
//                // 一号棚（数据）
//                case TopicConstants.GREENHOUSE_ONE_DATA_TOPIC_A:
//                    AsyncManager.me().execute(AsyncIrrigationFactory.logDevice(GreenHouseConstants.GREENHOUSE_DEVICE_PREFIX + "greenhouse1.1",payload));break;
//                case TopicConstants.GREENHOUSE_ONE_DATA_TOPIC_B:AsyncManager.me().execute(AsyncIrrigationFactory.logDevice(GreenHouseConstants.GREENHOUSE_DEVICE_PREFIX + "greenhouse1.2",payload));break;
//                // 二号棚（数据）
//                case TopicConstants.GREENHOUSE_TWO_DATA_TOPIC_A:AsyncManager.me().execute(AsyncIrrigationFactory.logDevice(GreenHouseConstants.GREENHOUSE_DEVICE_PREFIX + "greenhouse2.1",payload));break;
//                case TopicConstants.GREENHOUSE_TWO_DATA_TOPIC_B:AsyncManager.me().execute(AsyncIrrigationFactory.logDevice(GreenHouseConstants.GREENHOUSE_DEVICE_PREFIX + "greenhouse2.2",payload));break;
//                // 一号棚（异常）
//                case TopicConstants.GREENHOUSE_ONE_EXCEPTION_TOPIC_A:AsyncManager.me().execute(AsyncIrrigationFactory.logDevice(GreenHouseConstants.GREENHOUSE_EXCEPTION_PREFIX + "greenhouse1.1",payload));break;
//                case TopicConstants.GREENHOUSE_ONE_EXCEPTION_TOPIC_B:AsyncManager.me().execute(AsyncIrrigationFactory.logDevice(GreenHouseConstants.GREENHOUSE_EXCEPTION_PREFIX + "greenhouse1.2",payload));break;
//                // 二号棚（异常）
//                case TopicConstants.GREENHOUSE_TWO_EXCEPTION_TOPIC_A:AsyncManager.me().execute(AsyncIrrigationFactory.logDevice(GreenHouseConstants.GREENHOUSE_EXCEPTION_PREFIX + "greenhouse2.1",payload));break;
//                case TopicConstants.GREENHOUSE_TWO_EXCEPTION_TOPIC_B:AsyncManager.me().execute(AsyncIrrigationFactory.logDevice(GreenHouseConstants.GREENHOUSE_EXCEPTION_PREFIX + "greenhouse2.2",payload));break;
//                //三号棚（异常）
//                case TopicConstants.GREENHOUSE_THREE_EXCEPTION_TOPIC:AsyncManager.me().execute(AsyncIrrigationFactory.logDevice(GreenHouseConstants.GREENHOUSE_EXCEPTION_PREFIX + "greenhouse3",payload));break;
                default:log.error("未知主题订阅{}", receivedTopic);
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}
