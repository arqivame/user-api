package com.arqivame.user.infrastructure.user.message;

import java.io.Serializable;
import java.util.UUID;

import com.arqivame.user.infrastructure.event.model.EventMessage;

public record UserCreatedMessage(EventMessage.Metadata metadata, Data data)
        implements EventMessage<UserCreatedMessage.Data> {

    public record Data(UUID fileId) implements Serializable {
    }

}
