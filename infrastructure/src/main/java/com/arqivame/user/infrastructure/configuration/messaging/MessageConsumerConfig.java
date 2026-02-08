package com.arqivame.user.infrastructure.configuration.messaging;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import com.arqivame.user.domain.user.gateway.query.UserQueryGateway;
import com.arqivame.user.infrastructure.messaging.consumer.rabbitmq.user.UserCreatedIntegrationConsumer;
import com.arqivame.user.infrastructure.messaging.producer.MessageProducer;
import com.arqivame.user.infrastructure.user.message.UserCreatedIntegrationMessageV1;
import com.arqivame.user.infrastructure.user.message.UserCreatedMessage;

@Configuration
public class MessageConsumerConfig {

    private final UserQueryGateway userQueryGateway;

    private final MessageProducer<UserCreatedIntegrationMessageV1> userCreatedIntegrationV1MessageProducer;

    public MessageConsumerConfig(
            final UserQueryGateway userQueryGateway,
            final MessageProducer<UserCreatedIntegrationMessageV1> userCreatedIntegrationV1MessageProducer) {
        this.userQueryGateway = userQueryGateway;
        this.userCreatedIntegrationV1MessageProducer = userCreatedIntegrationV1MessageProducer;
    }

    @Bean
    Consumer<Message<UserCreatedMessage>> userCreatedIntegrationConsumer(
            @Value("${application.messaging.consumer.user-created-event.max-attempts}") final Long maxRetryAttempts,
            @Qualifier("userCreatedEventError") final MessageProducer<UserCreatedMessage> errorMessageProducer) {
        return new UserCreatedIntegrationConsumer(
                maxRetryAttempts,
                errorMessageProducer,
                userQueryGateway,
                userCreatedIntegrationV1MessageProducer);
    }

}
