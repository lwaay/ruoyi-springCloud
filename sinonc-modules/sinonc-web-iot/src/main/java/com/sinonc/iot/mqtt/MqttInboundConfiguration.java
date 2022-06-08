package com.sinonc.iot.mqtt;

import com.sinonc.iot.constant.TopicConstants;
import com.sinonc.iot.mqtt.configuration.MqttConfiguration;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Slf4j
@AllArgsConstructor
@Configuration
@IntegrationComponentScan
public class MqttInboundConfiguration {

    private MqttConfiguration mqttConfig;
    private MqttPahoClientFactory factory;
    private MqttMessageReceiver mqttMessageReceiver;

    private static final String CHANNEL_NAME_IN = "mqttInboundChannel";

    /**
     * 此处可以使用其他消息通道
     * Spring Integration默认的消息通道，它允许将消息发送给一个订阅者，然后阻碍发送直到消息被接收。
     *
     * @return
     */
    @Bean(name = CHANNEL_NAME_IN)
    public MessageChannel mqttInBoundChannel() {
        return new DirectChannel();
    }

    /**
     * 适配器, 两个topic共用一个adapter
     * 客户端作为消费者，订阅主题，消费消息
     * @param
     * @param
     * @return
     */
    @Bean
    public MqttPahoMessageDrivenChannelAdapter mqttInbound() {
        log.info(TopicConstants.BAISE_SHUIFEI);
        String[] receiver = {
                "data/baise/group/#"
        };
        MqttPahoMessageDrivenChannelAdapter
                adapter = new MqttPahoMessageDrivenChannelAdapter(mqttConfig.getClientId() + System.currentTimeMillis(),
                factory,
                receiver);
        adapter.setCompletionTimeout(60000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setRecoveryInterval(10000);
        adapter.setQos(0);
        adapter.setOutputChannel(mqttInBoundChannel());
        return adapter;
    }


    /**
     * mqtt入站消息处理工具，对于指定消息入站通道接收到生产者生产的消息后处理消息的工具。
     * @return
     */
    @Bean
    @ServiceActivator(inputChannel = CHANNEL_NAME_IN)
    public MessageHandler mqttMessageHandler() {
        return this.mqttMessageReceiver;
    }
}
