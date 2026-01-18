package com.arqivame.user.infrastructure.configuration.messaging;

import java.util.Objects;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.arqivame.user.infrastructure.messaging.producer.MessageProducer;
import com.arqivame.user.infrastructure.messaging.producer.springcloud.SpringCloudMessageProducer;
import com.arqivame.user.infrastructure.user.message.UserCreatedMessage;

@Configuration
public class MessageProducerConfig {

    private final StreamBridge streamBridge;

    public MessageProducerConfig(final StreamBridge streamBridge) {
        this.streamBridge = Objects.requireNonNull(streamBridge);
    }

    @Bean
    @Primary
    MessageProducer<UserCreatedMessage> userCreatedEvent() {
        return new SpringCloudMessageProducer<>(streamBridge, "userCreatedEvent-out-0");
    }

    @Bean
    MessageProducer<UserCreatedMessage> userCreatedEventError() {
        return new SpringCloudMessageProducer<>(streamBridge, "userCreatedEventError-out-0");
    }

}
