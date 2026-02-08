package com.arqivame.user.infrastructure.user.message;

import java.io.Serializable;
import java.util.UUID;

import com.arqivame.user.domain.user.event.UserCreatedEvent;
import com.arqivame.user.infrastructure.event.message.EventMessage;

public record UserCreatedMessage(EventMessage.Metadata metadata, Data data)
        implements EventMessage<UserCreatedMessage.Data> {

    public record Data(UUID userId) implements Serializable {
    }

    public static UserCreatedMessage from(final UserCreatedEvent event) {
        return new UserCreatedMessage(
                EventMessage.Metadata.from(event.getMetadata()),
                new Data(event.getData().userId()));
    }

}
