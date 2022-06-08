package com.sinonc.iot.mqtt;

import com.sinonc.iot.constant.TopicConstants;
import com.sinonc.iot.mqtt.configuration.MqttConfiguration;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.stream.CharacterStreamReadingMessageSource;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Slf4j
@AllArgsConstructor
@Configuration
public class MqttOutboundConfiguration {

    private static final String CHANNEL_NAME_OUT = "mqttOutboundChannel";

    @Autowired
    private MqttConfiguration mqttConfiguration;
    @Autowired
    private MqttPahoClientFactory factory;

    @Bean
    public IntegrationFlow mqttOutFlow() {
        return IntegrationFlows.from(CharacterStreamReadingMessageSource.stdin(),
                e -> e.poller(Pollers.fixedDelay(2000)))
                .transform(p -> p + "")
                .handle(mqttOutbound())
                .get();
    }

    @Bean
    @ServiceActivator(inputChannel = CHANNEL_NAME_OUT)
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(mqttConfiguration.getClientId() + System.currentTimeMillis(),
                factory);
        //开启异步
        messageHandler.setDefaultQos(0);
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(TopicConstants.BAISE_SHUIFEI);
        return messageHandler;
    }

    @Bean(name = CHANNEL_NAME_OUT)
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }
}
