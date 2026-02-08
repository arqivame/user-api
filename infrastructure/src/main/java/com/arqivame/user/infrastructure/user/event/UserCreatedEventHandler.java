package com.arqivame.user.infrastructure.user.event;

import org.springframework.stereotype.Component;

import com.arqivame.user.domain.event.EventHandler;
import com.arqivame.user.domain.user.event.UserCreatedEvent;
import com.arqivame.user.infrastructure.messaging.producer.MessageProducer;
import com.arqivame.user.infrastructure.user.message.UserCreatedMessage;

@Component
public class UserCreatedEventHandler extends EventHandler<UserCreatedEvent> {

    private final MessageProducer<UserCreatedMessage> messageProducer;

    public UserCreatedEventHandler(final MessageProducer<UserCreatedMessage> messageProducer) {
        super(UserCreatedEvent.eventKey());
        this.messageProducer = messageProducer;
    }

    @Override
    public void handle(final UserCreatedEvent event) {
        messageProducer.produce(UserCreatedMessage.from(event));
    }

}
