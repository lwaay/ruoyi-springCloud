package com.sinonc.iot.mqtt.configuration;

import lombok.Data;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.mqtt")
public class MqttConfiguration {

    private String username;
    private String password;
    private String url;
    private String clientId;
    private Integer completionTimeout = 2000;

    /**
     * 注册MQTT客户端工厂
     * @return
     */
    @Bean
    public MqttPahoClientFactory mqttClientFactory() throws MqttException  {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setConnectionTimeout(0);
        options.setKeepAliveInterval(90);
        options.setAutomaticReconnect(true);
        options.setUserName(this.getUsername());
        options.setPassword(this.getPassword().toCharArray());
        options.setServerURIs(new String[]{this.getUrl()});
        factory.setConnectionOptions(options);
        return factory;
    }
}
