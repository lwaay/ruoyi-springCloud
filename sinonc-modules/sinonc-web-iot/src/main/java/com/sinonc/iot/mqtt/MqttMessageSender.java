package com.sinonc.iot.mqtt;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class MqttMessageSender {

    private MqttGateway mqttGateway;

    public void send(String topic, String message) {
        mqttGateway.sendToMqtt(topic, message);
    }

    public void send(String topic, int qos, String message){
        mqttGateway.sendToMqtt(topic, qos, message);
    }

    public void send(String topic, int qos, byte[] message){
        mqttGateway.sendToMqtt(topic, qos, message);
    }
}
