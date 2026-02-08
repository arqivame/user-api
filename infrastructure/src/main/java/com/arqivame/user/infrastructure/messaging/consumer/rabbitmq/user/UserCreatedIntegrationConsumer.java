package com.arqivame.user.infrastructure.messaging.consumer.rabbitmq.user;

import java.util.Set;

import org.springframework.messaging.Message;

import com.arqivame.user.domain.exception.NotFoundException;
import com.arqivame.user.domain.user.User;
import com.arqivame.user.domain.user.UserID;
import com.arqivame.user.domain.user.gateway.query.UserQueryGateway;
import com.arqivame.user.infrastructure.messaging.consumer.rabbitmq.RabbitMQMessageConsumer;
import com.arqivame.user.infrastructure.messaging.producer.MessageProducer;
import com.arqivame.user.infrastructure.user.message.UserCreatedIntegrationMessageV1;
import com.arqivame.user.infrastructure.user.message.UserCreatedMessage;

public class UserCreatedIntegrationConsumer extends RabbitMQMessageConsumer<UserCreatedMessage> {

    private final UserQueryGateway userQueryGateway;

    private final MessageProducer<UserCreatedIntegrationMessageV1> userCreatedIntegrationV1MessageProducer;

    public UserCreatedIntegrationConsumer(
            final Long maxRetryAttempts,
            final MessageProducer<UserCreatedMessage> errorMessageProducer,
            final UserQueryGateway userQueryGateway,
            final MessageProducer<UserCreatedIntegrationMessageV1> userCreatedIntegrationV1MessageProducer) {
        super(maxRetryAttempts, errorMessageProducer, Set.of());
        this.userQueryGateway = userQueryGateway;
        this.userCreatedIntegrationV1MessageProducer = userCreatedIntegrationV1MessageProducer;
    }

    @Override
    public void consume(final Message<UserCreatedMessage> message) {

        final UserID userId = UserID.of(message.getPayload().data().userId());

        final User user = userQueryGateway
                .findById(userId)
                .orElseThrow(() -> NotFoundException.create(User.class, userId));

        userCreatedIntegrationV1MessageProducer.produce(UserCreatedIntegrationMessageV1.from(user));

    }

}
